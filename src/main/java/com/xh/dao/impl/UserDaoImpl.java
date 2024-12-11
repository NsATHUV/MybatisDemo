package com.xh.dao.impl;

import com.xh.bean.UserBean;
import com.xh.dao.UserDao;
import com.xh.factory.SF;

import org.apache.ibatis.session.SqlSession;

import java.util.Collections;
import java.util.List;

public class UserDaoImpl implements UserDao {
    SqlSession session = null;

    /**
     * 添加用户
     *
     * @param userBean 传入UserBean对象。
     * @return 大于1表示成功，0 失败
     */
    @Override
    public int addUser(UserBean userBean) {
        //连接数据库
        try {
//            session = SF.getSession(); 这是错误的演示，血泪的教训
            if (this.getUserById(userBean.getId()) == null) {
                session = SF.getSession(); //这句一定要写在里面，否则在查询完后session就会被关闭，一下代码均对空执行，最后只会得到null
                int result = session.insert("addUser", userBean);
                session.commit();
                System.out.println(result);
                return result;
            } else {
                System.out.println("ID为 " + userBean.getId() + " 的用户已存在！");
                return 0;
            }
        } catch (Exception e) {
            if (session != null) {
                session.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return 0;
    }


    /**
     * 更新用户信息
     * 该方法需要设置对象的所有属性后使用，否则未设置的属性将会被覆盖为未填写的状态！
     *
     * @param userBean 传入UserBean对象。
     */
    @Override
    public void updateUser(UserBean userBean) {
        try {
            session = SF.getSession();
            int result = session.update("updateUser", userBean);
            session.commit();
            System.out.println(result);
        } catch (Exception e) {
            if (session != null) {
                session.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * 根据ID删除用户信息
     *
     * @param id 传入id
     */
    @Override
    public void deleteUser(int id) {
        try {
            session = SF.getSession();
            int result = session.delete("deleteUser", id);
            session.commit();
            System.out.println(result);
        } catch (Exception e) {
            if (session != null) {
                session.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * 根据ID查询用户的信息
     *
     * @param id 传入用户的ID
     * @return 用户对象
     */
    @Override
    public UserBean getUserById(int id) {
        try {
            session = SF.getSession();
            UserBean userBean = session.selectOne("getUserById", id);
            session.commit();
            return userBean;
        } catch (Exception e) {
            if (session != null) {
                session.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    /**
     * 获取所有用户的信息
     *
     * @return 存储所有用户对象的List集合
     */
    @Override
    public List<UserBean> getUserAll() {
        try {
            session = SF.getSession();
            List<UserBean> userBeanList = session.selectList("getUserAll");
            session.commit();
            return userBeanList;
        } catch (Exception e) {
            if (session != null) {
                session.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return Collections.emptyList();
    }

    /**
     * 更好地更新用户信息
     * 未设置的属性将会继承之前的属性值，准确来说就是修改哪个就给哪个赋值。
     *
     * @param userBean 包含用户id的对象
     * @return 大于等于1为成功 0为失败
     */
    @Override
    public int updateUser_better(UserBean userBean) {

        try {
            session = SF.getSession();
            int result = session.update("updateUser_better", userBean);
            session.commit();
            return result;
        } catch (Exception e) {
            if (session != null) {
                session.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return 0;
    }

    /**
     * 更好地查询用户信息，可以使用某一个条件查询用户信息。
     * 精确查找
     *
     * @param userBean 传入包含用户信息的对象
     * @return 包含符合条件的用户信息的对象的列表
     */
    @Override
    public List<UserBean> getUserBy_better(UserBean userBean) {
        try {
            session = SF.getSession();
            List<UserBean> userBeanList = session.selectList("getUserBy_better", userBean);
            session.commit();
            return userBeanList;
        } catch (Exception e) {
            if (session != null) {
                session.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return Collections.emptyList();
    }

    /**
     * 模糊查询（根据姓氏）
     *
     * @param condition 名字模糊查询条件
     * @return 列表
     */
    @Override
    public List<UserBean> getUserBy_Fuzzy(String condition) {
        try {
            session = SF.getSession();
            List<UserBean> userBeanList = session.selectList("getUserBy_Fuzzy", condition);
            session.commit();
            return userBeanList;
        } catch (Exception e) {
            if (session != null) {
                session.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return Collections.emptyList();
    }
}

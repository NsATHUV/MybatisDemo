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
}

package com.xh.dao;

import com.xh.bean.UserBean;

import java.util.List;

public interface UserDao {
    /**
     * 添加用户
     *
     * @param userBean 传入UserBean对象。
     * @return 大于1表示成功，0 失败
     */
    int addUser(UserBean userBean);

    /**
     * 更新用户信息
     * 该方法需要设置对象的所有属性后使用，否则未设置的属性将会被覆盖为未填写的状态！
     *
     * @param userBean 传入UserBean对象。
     */
    void updateUser(UserBean userBean);

    /**
     * 根据ID删除用户信息
     *
     * @param id 传入id
     */
    void deleteUser(int id);

    /**
     * 根据ID查询用户的信息
     *
     * @param id 传入用户的ID
     * @return 用户对象
     */
    UserBean getUserById(int id);

    /**
     * 获取所有用户的信息
     *
     * @return 存储所有用户对象的List集合
     */
    List<UserBean> getUserAll();

    /**
     * 更好地更新用户信息
     * 未设置的属性将会继承之前的属性值，准确来说就是修改哪个就给哪个赋值。
     *
     * @param userBean 包含用户id的对象
     * @return 大于等于1为成功 0为失败
     */
    int updateUser_better(UserBean userBean);

    /**
     * 更好的查询用户信息，可以使用某一个条件查询用户信息。
     *
     * @param userBean 传入包含用户信息的对象
     * @return 包含符合条件的用户信息的对象的列表
     */
    List<UserBean> getUserBy_better(UserBean userBean);

    /**
     * 模糊查询（根据姓氏）
     *
     * @param condition 名字模糊查询条件
     * @return 列表
     */
    List<UserBean> getUserBy_Fuzzy(String condition);
}

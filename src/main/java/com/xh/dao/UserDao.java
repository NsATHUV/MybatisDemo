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
}

package com.xh.test;

import com.xh.bean.UserBean;
import com.xh.dao.UserDao;
import com.xh.dao.impl.UserDaoImpl;
import org.junit.Test;

import java.util.List;


public class TestUser {
    @Test
    //测试添加一个用户
    public void addUserTest() {
        UserDao userDao = new UserDaoImpl();
        UserBean user = new UserBean();
        user.setId(1);
        user.setUsername("张三");
        user.setAge(18);
        int result = userDao.addUser(user);
        if (result == 0) {
            System.out.println(userDao.getUserById(user.getId()));
        }
    }

    @Test
    public void updateUserTest() {
        UserDao userDao = new UserDaoImpl();
        UserBean user = new UserBean();
        user.setId(1);
        user.setUsername("李四");
        user.setAge(20);
        userDao.updateUser(user);
    }

    @Test
//    测试删除id为1的用户
    public void deleteUserTest() {
        int id = 1;
        UserDao userDao = new UserDaoImpl();
        userDao.deleteUser(id);
    }

    @Test
    //测试添加10位用户
    public void addTenUserTest() {
        UserDao userDao = new UserDaoImpl();
        for (int i = 1; i < 12; i++) {
            UserBean user = new UserBean();
            user.setId(i);
            user.setUsername("张" + i);
            user.setAge(18 + i);
            userDao.addUser(user);
        }
    }

    @Test
    //都给删了
    public void deleteAllUserTest() {
        UserDao userDao = new UserDaoImpl();
        for (int i = 1; i < 12; i++) {
            userDao.deleteUser(i);
        }
    }

    @Test
    //查询用户id1的信息
    public void getUserByIdTest() {
        UserDao userDao = new UserDaoImpl();
        UserBean user = userDao.getUserById(100);
        System.out.println(user);
    }

    @Test
    public void getAllUserTest() {
        UserDao userDao = new UserDaoImpl();
        List<UserBean> userBeanList = userDao.getUserAll();
        for (UserBean userBean : userBeanList) {
            System.out.println(userBean);
        }
    }
}

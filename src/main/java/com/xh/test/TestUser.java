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
        for (int i = 11; i < 22; i++) {
            UserBean user = new UserBean();
            user.setId(i);
            user.setUsername("王" + i);
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

    @Test
    /*
      测试不设置属性值时默认值是什么
     */
    public void testDefaultValue() {
        UserBean u = new UserBean();
        System.out.println(u);
//        可以得到默认值是UserBean{id=0, username='null', age=0}
    }

    @Test
    public void getUserByUsernameTest() {
        UserDao userDao = new UserDaoImpl();
        UserBean userBean = new UserBean();
        userBean.setId(10);
        userBean.setAge(55);
        if (userDao.updateUser_better(userBean)>0) {
            System.out.println("更新成功");
            this.getAllUserTest();
        } else {
            System.out.println("更新失败");
        }

    }

    @Test
    public void getUserByUsernameAndAgeTest() {
        UserDao userDao = new UserDaoImpl();
        UserBean userBean = new UserBean();
        userBean.setAge(0);
        List<UserBean> userBeanList = userDao.getUserBy_better(userBean);
        for (UserBean userBean1 : userBeanList) {
            System.out.println(userBean1);
        }
    }

    @Test
    public void getUser_Fuzzy_Test() {
        UserDao userDao = new UserDaoImpl();
        String condition = "%2%";
        List<UserBean> userBeanList = userDao.getUserBy_Fuzzy(condition);
        for (UserBean userBean1 : userBeanList) {
            System.out.println(userBean1);
        }

    }

    @Test
    //测试注解是否可行
    public void getUserByAge_Test(){
        UserDao userDao = new UserDaoImpl();
        int age = 18;
        List<UserBean> ub = userDao.getUserByAge(age);
        for (UserBean userBean1 : ub) {
            System.out.println(userBean1);
        }
    }
}

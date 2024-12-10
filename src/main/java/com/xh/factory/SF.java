package com.xh.factory;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

@SuppressWarnings("CallToPrintStackTrace")
public class SF {
    private static SqlSessionFactory sf;

    static {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis.xml");
            sf = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 公共的打开数据库链接的方法
     * @return 数据库连接会话
     */
    public static SqlSession getSession() {
        return sf.openSession();
    }
}

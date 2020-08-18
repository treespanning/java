package Util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//功能：帮助管理连接，DBUtil本质上是实现了DataSource类的单例版本
//对于一个应用程序来说，DataSource只需要有一个实例就可以了
//单例是面试中最常考的设计模式
//饿汉模式比较简单，但是懒汉模式更常考
//注意线程安全问题
//1.合适的位置加锁
//2.双重if判定
//3.volatile关键词
public class DBUtil {
    private static  volatile DataSource dataSource=null;
    private static final String USERNAME="root";
    private static final String PASSWORD="";
    private static final String URL="jdbc:mysql://127.0.0.1:3306/Chatroom?characterEncoding=utf-8&useSSl=true";

    public static DataSource getDataSource(){
        if(dataSource==null){
            synchronized (DBUtil.class){
                if(dataSource==null){
                    dataSource=new MysqlDataSource();
                    ((MysqlDataSource)dataSource).setPassword(PASSWORD);
                    ((MysqlDataSource)dataSource).setUrl(URL);
                    ((MysqlDataSource)dataSource).setUser(USERNAME);
                }
            }
        }
        return dataSource;
    }

    public static Connection getConnection(){
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(Connection c, PreparedStatement p, ResultSet rs){
        try {
            if(rs!=null){
                rs.close();
            }
            if(p!=null){
                p.close();
            }
            if(c!=null){
                c.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(Connection c, PreparedStatement p){
        close(c,p,null);
    }
}

package Util;

import exeception.ChatroomException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//通过UserDao这个类来针对User表的基本操作
public class UserDao {
    //1.新增一个用户（注册功能）
    public void add(User user){
        //1.获取数据库连接
        Connection c=DBUtil.getConnection();
        PreparedStatement ps=null;

        try {
            //2.拼装SQL语句
            String sql="insert into user value (null,?,?,?,now())";
            ps=c.prepareStatement(sql);
            ps.setString(1,user.getName());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getNickname());

            //3.执行SQL语句
            //executeUpdate();负责插入删除修改等的操作
            //executeQuery()；负责查询操作
            //返回结构表示“影响到的行数”
            int rs=ps.executeUpdate();
            if(rs!=1){
                //出现问题的处理方式有很多种
                //此处可以抛出一个自定义异常
                throw new ChatroomException("插入用户失败");
            }
            System.out.println("插入用户成功");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ChatroomException("插入用户失败");
        } finally {
            //4.释放连接
            DBUtil.close(c,ps);
        }
    }

    //2.按用户名查找用户信息（登录功能）
    public User selectByName(String name){
        //1.获取到连接

        Connection c=DBUtil.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        //2.拼装SQL
        String sql= null;
        try {
            sql = "select userId,name ,password, nickname, lastLogout from user where name=?";
            ps=c.prepareStatement(sql);
            ps.setString(1,name);

            //3.执行SQL
            rs=ps.executeQuery();

            //4.遍历结果集合（执行查找操作，必须要有这一步）
            //由于查找结果预期最多只有一条记录，所以使用过if判断即可
            //如果有多条结果的话，使用while进行循环来判定
            //如果rs.next直接为false，说明改用户名不存在
            if(rs.next()){
                User user=new User();
                user.setUserId(rs.getInt("userId"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                user.setLastLogout(rs.getTimestamp("lastLogout"));
                return user;
            }else{
                System.out.println("该用户不存在");
            }
            //throw new ChatroomException("查询用户信息失败");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ChatroomException("按用户名查询用户信息失败");
        } finally {
            //5.释放连接
            DBUtil.close(c,ps,rs);
        }
        return null;
    }


    //3.按用户id查找用户信息（把userID转换成昵称的时候）
    public User selectByID(int userId){
        //1.获取到连接
        Connection c=DBUtil.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        //2.拼装SQL
        String sql= null;
        try {
            sql = "select userId,name ,password, nickname, lastLogout from user where userId= ?";
            ps=c.prepareStatement(sql);
            ps.setInt(1,userId);

            //3.执行SQL
            rs=ps.executeQuery();

            //4.遍历结果集合（执行查找操作，必须要有这一步）
            //由于查找结果预期最多只有一条记录，所以使用过if判断即可
            //如果有多条结果的话，使用while进行循环来判定
            //如果rs.next直接为false，说明改用户名不存在
            if(rs.next()){
                User user=new User();
                user.setUserId(rs.getInt("userId"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                user.setLastLogout(rs.getTimestamp("lastLogout"));
                return user;
            }else{
                System.out.println("该用户不存在");
            }
            //throw new ChatroomException("查询用户信息失败");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ChatroomException("按id查询用户信息失败");
        } finally {
            //5.释放连接
            DBUtil.close(c,ps,rs);
        }
        return null;
    }


    //4.更新用户的lastLogout时间，（用户下线的时刻更新，为了实现历史纪录功能）
    public void updateLogout(int userID){
        //哪个用户下线了，就更新哪个
        //1.获取连接
        Connection c=DBUtil.getConnection();
        PreparedStatement ps=null;

        //2.拼装sql
        try {
            String sql="update user set lastLogout=now() where userId=?";
            ps=c.prepareStatement(sql);
            ps.setInt(1,userID);
            //3.执行操作
            int ret=ps.executeUpdate();
            if(ret!=1) {
                throw new ChatroomException("退出更新时间失败1");
            }
            System.out.println("更新退出时间成功");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ChatroomException("退出更新时间失败1");
        } finally {
            DBUtil.close(c,ps);
        }
    }

    public static void main(String[] args) {
        UserDao userDao=new UserDao();
        //针对上面的代码进行简单验证
//        //1.验证add方法
//        User user=new User();
//        user.setNickname("YIYI一一");
//        user.setName("abc");
//        user.setPassword("123");
//        userDao.add(user);
//        //2.按名字查找用户信息
//        User user=userDao.selectByName("abc");
//        System.out.println(user);
//        //3.按用户id查找用户信息
//        User user=userDao.selectByID(1);
//        System.out.println(user);
        //4.更新用户的退出时间
        userDao.updateLogout(1);
    }

}

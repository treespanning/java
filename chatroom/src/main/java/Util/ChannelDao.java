package Util;

import exeception.ChatroomException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//围绕着频道进行操作的逻辑
public class ChannelDao {
    //1.新增频道
    public void add(Channel channel){
        //1.获取数据库连接
        Connection c=DBUtil.getConnection();
        PreparedStatement ps=null;
        try {
            String sql="insert into channel value (null,?)";
            //2.拼装SQL
            ps=c.prepareStatement(sql);
            ps.setString(1,channel.getChannelName());

            //3.执行SQL
            int rs=ps.executeUpdate();
            if(rs!=1){
                throw new ChatroomException("新增频道失败");
            }
            System.out.println("新增频道成功");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ChatroomException("新增频道失败");
        } finally {
            //4.断开连接
            DBUtil.close(c,ps);
        }
    }

    //2.删除频道
    public void delete(int channelId){
        //1.获取数据库连接
        Connection c=DBUtil.getConnection();
        PreparedStatement ps=null;
        try {
            String sql="delete from channel where channelId=?";
            //2.拼装SQL
            ps=c.prepareStatement(sql);
            ps.setInt(1,channelId);

            //3.执行SQL
            int rs=ps.executeUpdate();
            if(rs!=1){
                throw new ChatroomException("删除频道失败");
            }
            System.out.println("删除频道成功");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ChatroomException("删除频道失败");
        } finally {
            //4.断开连接
            DBUtil.close(c,ps);
        }
    }

    //3.查看频道列表
    public List<Channel> selectAll(){
        List<Channel> channels=new ArrayList<>();

        //1.获取数据库连接
        Connection c=DBUtil.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="select channelId, channelName from channel";
            //2.拼装SQL
            ps=c.prepareStatement(sql);

            //3.执行SQL
            rs=ps.executeQuery();
            while (rs.next()){
                Channel channel=new Channel();
                channel.setChannelId(rs.getInt("channelId"));
                channel.setChannelName(rs.getString("channelName"));
                channels.add(channel);
            }
            if(rs==null){
                throw new ChatroomException("查询频道列表失败");
            }
            return channels;
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new ChatroomException("查询频道列表失败");
        } finally {
            //4.断开连接
            DBUtil.close(c,ps,rs);
        }
        return null;
    }

    //4.关注频道

    //5.取消关注频道


    //单元测试内容
    public static void main(String[] args) {
        //创建一个ChannelDao实例
        ChannelDao channelDao=new ChannelDao();
        //1.验证add操作
//        Channel channel=new Channel();
//        channel.setChannelName("ASMR");
//        channelDao.add(channel);//不设置channelID是因为此字段是自增字段，由Mysql自行增加，避免ID冲突


//        //2.验证SelectAll
//        System.out.println(channelDao.selectAll());

        //3.验证Delete
        channelDao.delete(1);
    }
}

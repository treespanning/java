package Util;

import exeception.ChatroomException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//把发送的消息给保存起来，为了实现历史消息功能
//把用户上次下线时间点到下次上线这个时间点，这两个之间间隔的消息都查询出来
public class MessageDao {
    //1.新增消息
    public void add(Message message){
        //1.获取连接
        Connection c=DBUtil.getConnection();
        PreparedStatement ps=null;
        //2.拼装sql
        String sql="insert into message values (null,?,?,?,?)";
        try {
            ps=c.prepareStatement(sql);
            ps.setInt(1,message.getUserId());
            ps.setInt(2,message.getChannelId());
            ps.setString(3,message.getContent());
            ps.setTimestamp(4,message.getSendTime());

            //3.执行SQL
            int ret=ps.executeUpdate();
            if(ret!=1){
                throw new ChatroomException("消息发送失败");
            }
            System.out.println("插入消息成功");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ChatroomException("消息发送失败");
        } finally {
            //4.关闭链接
            DBUtil.close(c,ps);
        }

    }
    //2.按时间段查询消息
    public List<Message> selectByTimeStamp(Timestamp from, Timestamp to){
        List<Message> messages=new ArrayList<>();
        //1.获取到链接
        Connection c=DBUtil.getConnection();

        //2.拼装SQL
        //MySQL中的datetime类型是可以直接比较大小
        String sql="select messageId, userId, channelId, content, sendTime from message where sendTime>=? and sendTime<=?";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps=c.prepareStatement(sql);
            ps.setTimestamp(1,from);
            ps.setTimestamp(2,to);

            //3.执行SQL
            rs=ps.executeQuery();
            while (rs.next()){
                Message message=new Message();
                message.setMessageId(rs.getInt("messageId"));
                message.setUserId(rs.getInt("userId"));
                message.setChannelId(rs.getInt("channelId"));
                message.setContent(rs.getString("content"));
                message.setSendTime(rs.getTimestamp("sendTime"));
                messages.add(message);
            }
            return messages;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(c,ps,rs);
        }
        return null;
    }

    public static void main(String[] args) {
        MessageDao messageDao=new MessageDao();
//        //1.测试新增消息
//        Message message=new Message();
//        message.setUserId(1);
//        message.setChannelId(1);
//        message.setContent("ASMR");
//        message.setSendTime(new Timestamp(System.currentTimeMillis()));
//        messageDao.add(message);

        //2.获取指定时间段的消息
        //from 构造了：2020-07-28 12:20:25
        //to 构造了：2020-07-28 12:35:25
        //此处时间戳是一个很大的数字，已经超出了int的范围，因此加上L后缀，表示这是一个long的类型
        List<Message> messages=messageDao.selectByTimeStamp(
                new Timestamp(1595910025000L),new Timestamp(1595910925000L)
        );
        System.out.println(messages);
    }
}

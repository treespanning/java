package Util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;

import javax.websocket.Session;
import java.io.IOException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

//消息中心，负责管理所有的消息和用户列表，实现消息转发
//这个类作为一个单例即可
public class MessageCenter {
    private volatile static MessageCenter instance=null;

    public static MessageCenter getInstance(){
        if(instance==null) {
            synchronized (MessageCenter.class) {
                if (instance == null) {
                    instance = new MessageCenter();
                }
            }
        }
        return instance;
    }

    //两个数据结构
    //1.保存消息的队列（堵塞队列）
    private BlockingDeque<Message> messages=new LinkedBlockingDeque<>();

    //2.保存用户列表的hash表
    //hashmap默认没有线程安全机制的，在多线程环境下使用必须要考虑线程安全
    //ConcurrentHashMap采用分段锁的方式保证了线程安全
    private ConcurrentHashMap<Integer, Session> onlineUsers=new ConcurrentHashMap<>();


    //实现几个操作这两个结构的方法：
    //1.用户上线
    public void addOnlineUser(int userId,Session session){
        onlineUsers.put(userId,session);
    }

    //2.用户下线
    public void delOnlineUser(int userId){
        onlineUsers.remove(userId);
    }

    //3.新增消息
    public void addMessage(Message message) throws InterruptedException{
        messages.put(message);
    }


    //创建一个线程，来一直扫描消息的队列，把里面存的消息转发给所有的在线用户
    //构造MessageCenter 实例的时候，就启动这个线程
    private MessageCenter(){
        Thread t=new Thread(){
            @Override
            public void run() {
                Gson gson=new GsonBuilder().create();
                while(true){
                    try {
                        //1.从队列中尝试取消息
                        //如果队列为空，此时take就会阻塞
                        Message message=messages.take();
                        //2.把消息转成json字符串
                        String jsonString=gson.toJson(message);

                        //3.遍历在线用户列表，把消息转发给每个用户
                        for(ConcurrentHashMap.Entry<Integer,Session> entry:onlineUsers.entrySet()){
                            Session session=entry.getValue();
                            session.getBasicRemote().sendText(jsonString);
                        }
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }
}



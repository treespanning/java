package Servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exeception.ChatroomException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Util.User;
import Util.UserDao;
import Util.ChannelDao;
import Util.Channel;
public class ChannelServlet extends HttpServlet {
    private Gson gson=new GsonBuilder().create();
    //新增频道
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    //删除频道
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    //获取频道列表
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Channel> channelList=new ArrayList<>();
        try {
            //1.验证登录状态，如果未登录则不能查看
            HttpSession httpSession=req.getSession(false);
            if(httpSession==null){
                throw new ChatroomException("尚未登陆");
            }
            User user=(User)httpSession.getAttribute("user");
            //2.查数据库
            ChannelDao channelDao=new ChannelDao();
           channelList=channelDao.selectAll();

        } catch (ChatroomException e) {
            e.printStackTrace();
            //如果前面触发了异常，此时channels将是一个空的list
            //下面的finally中将会构造出一个空数组的json
        } finally {
            //3.把查询结果包装成响应内容
            //此处如果参数是个list，转出的json字符串就是一个数组
            resp.setContentType("application/json;charset=utf-8");
            String jsonString=gson.toJson(channelList);
            resp.getWriter().write(jsonString);
        }
    }
}

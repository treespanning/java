package Servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import Util.UserDao;
import Util.User;
import com.google.gson.JsonSyntaxException;
import exeception.ChatroomException;

public class LoginServlet extends HttpServlet {
    private Gson gson=new GsonBuilder().create();

    static class Request{
        public String name;
        public String password;
    }
    static class Response{
        public int ok;
        public String reason;
        public int userId;
        public String name;
        public String nickName;
    }

    //实现登录功能
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        Response response=new Response();
        try {
            //1.读取body中的数据
            String body=Util.readBody(req);
            //2.把读到的数据转成Request对象
            Request request=gson.fromJson(body,Request.class);
            //3.按用户名在数据库中查找，匹配密码是否正确
            UserDao userDao=new UserDao();
            User user=userDao.selectByName(request.name);
            //4.如果登录失败，则给出提示
            //a>用户名没查到
            //b>密码不匹配
            if(user==null||!request.password.equals(user.getPassword())){
                throw new ChatroomException("用户名或密码错误");
            }
            //5.如果登录成功，创建一个session对象。
            HttpSession httpSession=req.getSession(true);
            httpSession.setAttribute("user",user);
            //6.把结果写回到浏览器
            response.ok=1;
            response.name=user.getName();
            response.userId=user.getUserId();
            response.nickName=user.getNickname();
            response.reason="";
        } catch (JsonSyntaxException |ChatroomException e) {
            e.printStackTrace();
            response.ok=0;
            response.reason=e.getMessage();
        }finally {
            String jsonString=gson.toJson(response);
            resp.getWriter().write(jsonString);
        }

    }


    //检测登录状态
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Response response=new Response();
        try {
            //1.根据请求，查看该sessionid对应的session是否存在。
            //如果session不存在，就是登陆失败的状态
            HttpSession httpSession=req.getSession(false);
            if(httpSession==null){
                //不存在的情况
                throw new ChatroomException("当前未登录");
            }
            User user=(User)httpSession.getAttribute("user");
            //2.如果session存在，直接返回一个登录成功即可。
            response.ok=1;
            response.name=user.getName();
            response.nickName=user.getNickname();
            response.userId=user.getUserId();
            response.reason="";
        } catch (ChatroomException e) {
            e.printStackTrace();
            response.reason=e.getMessage();
            response.ok=0;
        } finally {
            resp.setContentType("application/json;charset=utf-8");
            String jsonString=gson.toJson(response);
            resp.getWriter().write(jsonString);
        }
    }
}

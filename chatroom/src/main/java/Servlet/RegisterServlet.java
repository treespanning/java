package Servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import Util.User;
import Util.UserDao;
import com.google.gson.JsonSyntaxException;
import exeception.ChatroomException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    private Gson gson=new GsonBuilder().create();
    //这个类以内部类的方式来组织，这个Request类只是针对RegisterServlet来使用
    //其他的Servlet对应的Request类可能结构不同。
    //从body中的json中转换过来的
    static class Request{
        public String name;
        public String password;
        public String nickName;

    }

    //响应的数据内容
    //要把这个对象再转回json字符串，并写回给客户端
    static class Response{
        public int ok;
        public String reason;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //显示的告诉servlet按照utf-8这样的编码方式来处理请求
        req.setCharacterEncoding("utf-8");

        Response response=new Response();
        try {
            //1.从请求中读取body中得信息（json格式得字符串）
            String body=Util.readBody(req);
            //2.把json数据转成Java中得对象
            //创建一个Request类来表示这次请求的结构
            //此处需要把body转成Request对象
            //此处最好借助第三方库来完成，（JSON的第三方库有很多，fastjosn，jackson...）
            //此处咱们使用GSON（Google的一个库）
            Request request=gson.fromJson(body,Request.class);
            //3.在数据库中查一下，看看用户名是否存在，如果存在就注册失败
            UserDao userDao=new UserDao();
            User existsUser=userDao.selectByName(request.name);
            if(existsUser!=null){
                throw new ChatroomException("用户名已经存在");
            }

            //4.把新的用户名和密码构造成User对象并插入数据库
            User user=new User();
            user.setName(request.name);
            user.setPassword(request.password);
            user.setNickname(request.nickName);
            userDao.add(user);
            //5.返回一个响应结果，响应结果是一个Response对象
            response.ok=1;
            response.reason="";
        } catch (ChatroomException|JsonSyntaxException e) {
            e.printStackTrace();
            response.ok=0;
            response.reason=e.getMessage();
        }finally {
            //处理响应的逻辑
            resp.setContentType("application/json;charset=utf-8");
            String jsonString=gson.toJson(response);
            resp.getWriter().write(jsonString);
        }
    }
}

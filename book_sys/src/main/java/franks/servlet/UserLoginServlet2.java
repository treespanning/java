package franks.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
//@WebServlet("/user/login2")
public class UserLoginServlet2 extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return null;
    }

    /*@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //针对请求体设置编码，而对url中的请求数据无效

        resp.setCharacterEncoding("UTF-8");
        //针对响应体设置编码

        resp.setContentType("application/json");
        //设置响应的数据格式：响应头Content-Type告诉浏览器怎么解析


        //解析json的数据格式
        //json数据，需要通过io流获取
        HashMap json=new ObjectMapper().readValue(req.getInputStream(),HashMap.class);

        System.out.println(json);

        HashMap<String,Object> r=new HashMap<>();
        r.put("success",true);
        r.put("code",200);
        PrintWriter pw=resp.getWriter();
        pw.println(new ObjectMapper().writeValueAsString(r));
    }
    */
}

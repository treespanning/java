package franks.servlet;

import franks.DAO.UserDAO;
import franks.exception.BusinessException;
import franks.exception.SystemException;
import franks.model.User;
import franks.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/user/login")
public class UserLoginServlet extends AbstractBaseServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //req.getParameter("")这个方式只能获取URL和请求体，K=V这种格式的数据

        User user= JSONUtil.read(req.getInputStream(),User.class);
        //user是http请求解析的用户数据
//queryUser通过JSONUtil中的反序列化，将输入流反序列化为object对象

        User queryUser= UserDAO.query(user);
        //通过请求的用户名密码在数据库查询的结果，且获取用户信息

        if(queryUser==null) {
            throw new BusinessException("000000","用户名密码校验错误");
        }
        HttpSession session=req.getSession();
        session.setAttribute("user",queryUser);
        return null;
    }
}

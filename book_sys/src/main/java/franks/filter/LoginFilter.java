package franks.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    //初始化
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    //销毁
    @Override
    public void destroy() {

    }

    //过滤的方法
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)servletRequest;
        String url=req.getServletPath();


        //优化登录
        //访问首页/public/page/main.html，没有登录重定向到登录页面
        //访问后台的敏感资源，servlet的敏感服务，返回json数据

        //放行
        if(!"/login.html".equals(url)&&!url.startsWith("/public/")&&!
                url.startsWith("/static/")&&!"/user/login".equals(url))
        {
            //不满足放行条件，跳转到login页面
            HttpSession session=req.getSession(false);
            if(session==null){
                //访问敏感资源，没用登录的情况，需要跳转到登录页面
                //重定向
                String schema=req.getScheme();//http
                String host=req.getServerName();//服务器域名或ip
                int port=req.getServerPort();//服务器端口号
                String contextPath=req.getContextPath();//项目部署名
                String basePath=schema+"://"+host+":"+port+contextPath;
                ((HttpServletResponse) servletResponse).sendRedirect(basePath+"/public/index.html");
                return;
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}

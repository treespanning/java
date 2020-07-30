package franks.servlet;

import franks.exception.BaseException;
import franks.model.ResponseResult;
import franks.util.CountHolder;
import franks.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public abstract class AbstractBaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.setCharacterEncoding("UTF-8");
       resp.setCharacterEncoding("UTF-8");
       resp.setContentType("application/json");

        ResponseResult r=new ResponseResult();
        try {
            Object data= process(req,resp);
            r.setSuccess(true);
            r.setCode("200");//200不是设置成状态码，仅表示返回数据的一个字段
            r.setMessage("操作成功");
            r.setTotal(CountHolder.get());//可能是分页的接口，get可以获取到值，也可能不是，返回null
            r.setData(data);
        } catch (Exception e) {//process抛异常的处理逻辑
            e.printStackTrace();
            if(e instanceof BaseException){
                BaseException be= (BaseException) e;
                r.setCode(be.getCode());
                r.setMessage(be.getMessage());
            }else{
                r.setCode("500");//500不是设置成状态码，仅表示返回数据的一个字段
                r.setMessage("未知错误");
            }
            //设置堆栈信息
            StringWriter sw=new StringWriter();
            PrintWriter pw=new PrintWriter(sw);
            e.printStackTrace(pw);
            r.setStackTrace(sw.toString());
        }finally {
            CountHolder.remove();//ThreadLocal规范做法，线程结束前一定要remove删除
            //否则可能出现内存泄漏
        }
        PrintWriter pw=resp.getWriter();
        pw.println(JSONUtil.write(r));
        pw.flush();
    }
    public abstract Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}

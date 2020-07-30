package franks.servlet;

import franks.exception.BaseException;
import franks.model.Result;
import franks.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractBaseServlet extends HttpServlet {
    private static final ConcurrentMap<String,Integer> Map=new ConcurrentHashMap<>();
private static final ConcurrentMap<String, AtomicInteger> Map2=new ConcurrentHashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");


        //前端约定好的统一返回的json数据格式
        Result result=new Result();
        try {
            Object data=process(req,resp);
            //正确返回业务数据
            // List<Article> articles=testDate();
            result.setSuccess(true);
            result.setData(data);
        }catch (Exception e){//捕获异常，需要设置前端需要错误信息，和堆栈信息
            if(e instanceof BaseException){
                BaseException be=(BaseException) e;
                result.setMessage("错误码："+be.getMessage()+",错误信息："+be.getMessage());
            }else{
                result.setMessage(" 未知异常，服务器异常 ");
            }
            //result.setSuccess(false);
            //result.setMessage(e.getMessage());
            StringWriter sw=new StringWriter();
            PrintWriter epw=new PrintWriter(sw);
            e.printStackTrace(epw);
            result.setStackTrace(sw.toString());
        }
        PrintWriter pw=resp.getWriter();
        pw.println((JSONUtil.serialize(result)));//返回json字符串
        pw.flush();

//        System.out.println("scheme: "+req.getScheme());//协议号：http
//        System.out.println("ServletPath :"+req.getServletPath());//请求Servlet的路径：/articleDetail
//        System.out.println("ContextPath: "+req.getContextPath());//上下文路径，应用部署路径：/blog
//        System.out.println("RequestURL: "+req.getRequestURL());//请求的全路径： http://localhost:8080/blog/articleDetail
//        System.out.println("RequestURI: "+req.getRequestURI());//ContextPath+ServletPath：/blog/articleDetail
//        System.out.println("PathInfo: "+req.getPathInfo());//null： null

        ///1
        synchronized (Map) {
           String path = req.getServletPath();
           Integer count = Map.get(path);
           if (null == count) {
               count = 1;
           } else {
               count++;
           }
           Map.put(path, count);
       }


       //2通过AtomicInteger结合concurrentHashMap来保证线程安全
        String path=req.getServletPath();
        //ConcurrentHashMap.putIfAbsent() 如果没用键存在，就保持给定的键值对，返回null
        //如果有键有映射关系，就直接返回对应的值
        AtomicInteger count=Map2.putIfAbsent(path,new AtomicInteger(1));
        if(count!=null)
            count.incrementAndGet();
    }

    public abstract Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception;

    public static ConcurrentMap<String, Integer> getMap() {
        return Map;
    }

    public static ConcurrentMap<String, AtomicInteger> getMap2() {
        return Map2;
    }
}

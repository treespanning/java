package franks.servlet;

import franks.dao.ArticleDAO;
import franks.exception.BusinessException;
import franks.exception.ClientException;
import franks.model.Article;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/articleDetail")
public class ArticleDatailServlet extends AbstractBaseServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
       //获取请求数据中的id（文章id）
        String id=req.getParameter("id");
        Integer articleId;
        try {
            articleId=Integer.parseInt(id);

            //return testData();//测试代码 以后替换成数据库根据文章id查询文章信息
        } catch (Exception e) {
            throw new ClientException("001","请求参数错误[id="+id+"]");
        }
        Article article= ArticleDAO.query(articleId);
        if(article==null)
            throw new BusinessException("002","查询不到文章详情 id="+articleId);
        return article;

      //  return testData();
    }
    /*public static Article testData(){
        Article a1=new Article();
        a1.setId(1);
        a1.setTitle("AAAA");
        a1.setContent("YYYYYYYYyyyy");
        a1.setUserId(1);
        a1.setCreateTime(new Date());
        return a1;
    }*/
}

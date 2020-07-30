package franks.servlet;

import franks.dao.ArticleDAO;
import franks.model.Article;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/articleList")
public class ArticleListServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Article> articles=ArticleDAO.list();
        return articles;
        //return testDate();
    }

    /*//模拟相应数据
    public static List<Article> testDate(){
       List<Article> articles=new ArrayList<>();
       Article a1=new Article();
        a1.setId(1);
        a1.setTitle("AAAA");
        a1.setContent("YYYYYYYYyyyy");
        a1.setUserId(1);
        a1.setCreateTime(new Date());
        Article a2=new Article();
        a2.setId(2);
        a2.setTitle("xueiyizyc");
        a2.setContent("肖战必糊！！！");
        a2.setUserId(1);
        a2.setCreateTime(new Date());
        articles.add(a1);
        articles.add(a2);
        return articles;
    }*/
}

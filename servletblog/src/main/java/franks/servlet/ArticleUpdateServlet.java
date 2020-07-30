package franks.servlet;

import franks.dao.ArticleDAO;
import franks.exception.BusinessException;
import franks.model.Article;
import franks.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@WebServlet("/articleUpdate")
public class ArticleUpdateServlet extends AbstractBaseServlet {
    @Override

    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //文章修改：包含id title content

        InputStream is=req.getInputStream();
        Article article= JSONUtil.deserialize(is,Article.class);
        System.out.println("++++++++++++++++++++++\n"+article);
        //模拟数据库插入操作
        int num= ArticleDAO.update(article);
        if(num!=1)
            throw new BusinessException("004","更新文章失败");

        return null;
    }



}

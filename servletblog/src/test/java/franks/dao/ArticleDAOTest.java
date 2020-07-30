package franks.dao;

import franks.model.Article;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ArticleDAOTest {

    @Test
    public void insert() throws ParseException {
        Article article=new Article();
        article.setTitle("软件测试");
        article.setContent("软件单元测试啦啦啦啦啦~");
        article.setUserId(1);
        java.text.SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s="2020-06-23 13:00:00";
        Date date=format.parse(s);
        ArticleDAO articleDAO=new ArticleDAO();
        int flag=articleDAO.insert(article);
        System.out.println(flag);

    }

    @Test
    public void list() {
        List<Article> articleArrayList = new ArrayList<>();
        ArticleDAO articleDAO=new ArticleDAO();
        articleArrayList=articleDAO.list();
        System.out.println("数据库中的文章数:"+articleArrayList.size());
        for(Article article:articleArrayList){
            System.out.println("文章名称："+article.getTitle());
        }
    }

    @Test
    public void query() {
        ArticleDAO articleDAO=new ArticleDAO();
        Article article=new Article();
        article=articleDAO.query(7);
        System.out.println("文章名称："+article.getTitle());
        System.out.println("文章内容："+article.getContent());
    }

    @Test
    public void update() {
        Article article=new Article();
        article.setId(7);
        article.setTitle("软件单元测试");
        article.setContent("软件单元测试是否成功");
        ArticleDAO articleDAO=new ArticleDAO();
        articleDAO.update(article);
        System.out.println("更改之后文章标题："+article.getTitle());
        System.out.println("更改之后文章内容："+article.getContent());
    }

    @Test
    public void delete() {
        int[] ids={1};
        Article article=new Article();
        ArticleDAO articleDAO=new ArticleDAO();
        int flag=articleDAO.delete(ids);
        System.out.println("flag:"+flag);
    }
}
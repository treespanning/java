package franks.servlet;

import franks.DAO.BorrowRecordDAO;
import franks.model.BorrowRecord;
import franks.model.Page;
import franks.util.Util;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@WebServlet("/borrowRecord/query")
public class BorrowRecordQueryServlet extends AbstractBaseServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
       //url中的请求体数据，通过getParameter获取
       //以这种格式解析searchText=&sortOrder=asc&pageSize=7&pageNumber=1
        //不能使用反序列化

        //分页实现
        Page p= Util.parse(req);

        List<BorrowRecord> records= BorrowRecordDAO.query(p);
        return records;
    }
}

package franks.servlet;

import franks.DAO.BorrowRecordDAO;
import franks.exception.BusinessException;
import franks.model.BorrowRecord;
import franks.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/borrowRecord/delete")
public class BorrowRecordDeleteServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String[]ids =req.getParameterValues("ids");//ids=4&id=5...
        int num= BorrowRecordDAO.delete(ids);
        if(num!=ids.length){
           throw new BusinessException("000010","删除图书借阅信息数据出错");
        }
        return null;
    }
}

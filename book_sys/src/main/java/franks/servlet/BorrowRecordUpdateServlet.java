package franks.servlet;

import franks.DAO.BorrowRecordDAO;
import franks.exception.BusinessException;
import franks.model.BorrowRecord;
import franks.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/borrowRecord/update")
public class BorrowRecordUpdateServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        BorrowRecord record= JSONUtil.read(req.getInputStream(),BorrowRecord.class);
        int num= BorrowRecordDAO.update(record);
        if(num!=1){
            throw new BusinessException("000009","更新图书借阅信息数量异常");
        }
        return null;
    }
}

package franks.servlet;

import franks.DAO.BorrowRecordDAO;
import franks.exception.BusinessException;
import franks.model.BorrowRecord;
import franks.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/borrowRecord/add")
public class BorrowRecordAddServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        BorrowRecord record= JSONUtil.read(req.getInputStream(),BorrowRecord.class);
        int num= BorrowRecordDAO.insert(record);
        if(num==1){
            return num;
        }else{
            throw new BusinessException("000008","插入图书借阅信息数量异常");
        }

    }
}

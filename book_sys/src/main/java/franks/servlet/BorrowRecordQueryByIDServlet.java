package franks.servlet;

import franks.DAO.BorrowRecordDAO;
import franks.model.BorrowRecord;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/borrowRecord/queryById")
public class BorrowRecordQueryByIDServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id=Integer.parseInt(req.getParameter("id"));
        BorrowRecord record=BorrowRecordDAO.queryByID(id);
        return record;
    }
}

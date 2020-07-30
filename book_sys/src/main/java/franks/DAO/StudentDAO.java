package franks.DAO;

import franks.exception.SystemException;
import franks.model.Student;
import franks.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public static List<Student> queryAsDict(int id) {
        List<Student> students=new ArrayList<>();
        Connection c=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            c=DBUtil.getConnection();
            String sql="select id, student_name, student_no, id_card" +
                    " from student" +
                    " where classes_id= ?";
            ps=c.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while(rs.next()){
                Student student=new Student();
                student.setDictionaryTagKey(String.valueOf(rs.getInt("id")));
                student.setDictionaryTagValue(rs.getString("student_name"));
                student.setStudentNo(rs.getString("student_no"));
                student.setIdCard(rs.getString("id_card"));
                students.add(student);
            }
        }catch (Exception e){
            throw new SystemException("000005","查询学生信息数据字典错误",e);
        } finally {
            DBUtil.close(c,ps,rs);
        }
        return students;
    }
}

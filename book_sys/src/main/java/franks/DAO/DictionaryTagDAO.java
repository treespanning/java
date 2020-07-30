package franks.DAO;

import franks.exception.SystemException;
import franks.model.DictionaryTag;
import franks.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DictionaryTagDAO {

    public static List<DictionaryTag> query(String key) {
        List<DictionaryTag> tags=new ArrayList<>();
        Connection c=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            c=DBUtil.getConnection();
            String sql="select concat(d.dictionary_key, dt.dictionary_tag_key) dictionary_tag_key," +
                    "       dt.dictionary_tag_value" +
                    " from dictionary d" +
                    "         join dictionary_tag dt on d.id = dt.dictionary_id" +
                    " where d.dictionary_key = ?";
            //concat:字符串拼接
            ps=c.prepareStatement(sql);
            ps.setString(1,key);
            rs=ps.executeQuery();
            while (rs.next()){
                DictionaryTag tag=new DictionaryTag();
                tag.setDictionaryTagKey(rs.getString("dictionary_tag_key"));
                tag.setDictionaryTagValue(rs.getString("dictionary_tag_value"));
                tags.add(tag);
            }

        }catch (Exception e){
            throw new SystemException("00002","查询数据字典错误",e);
        }finally {
            DBUtil.close(c,ps,rs);
        }
        return tags;
    }
}

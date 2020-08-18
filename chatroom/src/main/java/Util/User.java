package Util;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
//一个User对象就用来表示一条数据库的记录
//对象的属性基本和数据库的表结构一致。(大致一致即可)
@Getter
@Setter
@ToString

public class User {
    private Integer userId;
    private String name;
    private String password;
    private String nickname;
    Timestamp lastLogout;
}

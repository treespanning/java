package Util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
@Setter
@Getter
@ToString
public class Message {
    private Integer messageId;
    private Integer userId ;
    private Integer channelId;
    private String content;
    Timestamp sendTime;

    //此处再新增一个字段，此处这里的nickName就会userId是对应的
    //直接把用户昵称放到这里方便后面界面的显示
    //nickName在Message表中并不存在，可以根据userID在user表中查询
    private String nickName;
}

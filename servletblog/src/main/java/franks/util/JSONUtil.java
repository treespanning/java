package franks.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class JSONUtil {
    //单例模式设计

    private static volatile ObjectMapper MAPPER;
    public static ObjectMapper get(){
        if(MAPPER==null){//双重校验锁，线程安全式的单例
            synchronized (JSONUtil.class){
                if(MAPPER==null){
                    MAPPER=new ObjectMapper();
                }
            }
        }
        return MAPPER;
    }

    /*Java对象序列化为json对象
    o为对象
    json字符串*/
    public static String serialize(Object o){
        try {
            return get().writeValueAsString(o);
        } catch (JsonProcessingException e) {//编译时异常转换为运行时异常，编译时不处理
            throw new RuntimeException("JSON序列化失败，对象为"+o,e);
        }
    }




    /*json反序列化为java对象
    json字符串
    clazz java类型
    <T> 泛型
    return 反序列后的java对象
    */
    public static <T>T deserialize(String json,Class<T> clazz){
        try {
            return get().readValue(json,clazz);
        } catch (IOException e) {
            throw new RuntimeException("JSON反序列化失败，JSON字符串为"+json,e);
        }
    }

    public static <T>T deserialize(InputStream is, Class<T> clazz){
        try {
            return get().readValue(is,clazz);
        } catch (IOException e) {
            throw new RuntimeException("JSON反序列化失败",e);
        }
    }

}

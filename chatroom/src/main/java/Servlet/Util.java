package Servlet;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

public class Util {
    public static String readBody(HttpServletRequest request){
        //body的长度，单位是字节
        int contentLength=request.getContentLength();

        //缓冲区buffer来保存读到的body内容。
        byte[] buffer=new byte[contentLength];

        //读取数据
        try(InputStream inputStream=request.getInputStream()){
            inputStream.read(buffer,0,contentLength);
        }catch (IOException e){
            e.printStackTrace();
        }
        return new String(buffer);
    }
}

package franks.util;

import franks.model.ResponseResult;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

public class JSONUtilTest {
    @Test
    public void testRead(){
        InputStream is=getClass().getClassLoader().getResourceAsStream("response.json");

        ResponseResult r=JSONUtil.read(is,ResponseResult.class);
        System.out.println(r);
        Assert.assertNotNull(r);

    }

    @Test
    public void testWrite(){
        ResponseResult r=new ResponseResult();
        r.setCode("k3000");
        r.setMessage("错误内容");
        r.setSuccess(true);
        r.setTotal(10);
        String s=JSONUtil.write(r);
        System.out.println(r);
        Assert.assertNotNull(r);
        Assert.assertTrue(s.trim().length()>0);
        //.trim()去除前后空格
    }
}

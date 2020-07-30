package franks.util;

public class CountHolder {
    private static final ThreadLocal<Integer> COUNT=new ThreadLocal<>();
    //1.可以直接返回ThreadLocal变量


    //2.建立提供get、set、remove
    public static void set(Integer count){
        COUNT.set(count);
    }

    public static Integer get(){
        return COUNT.get();
    }

    public static void remove(){
        COUNT.remove();
    }
}

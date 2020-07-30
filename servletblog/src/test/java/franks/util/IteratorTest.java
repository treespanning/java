package franks.util;
public class IteratorTest {
    public static void main(String[] args) {
//非线程安全的数据结构，内部的迭代器都是一种fail-fast快速失效的迭代器
        //在迭代操作数据结构时，修改数据操作，会导致下一次的迭代抛异常
        //多线程对同一个非线程安全的数据结构操作，一个线程遍历，如果另一个线程修改了，下次迭代时也会抛异常


        //实现了Iterable接口的数据结构，都可以使用for循环遍历，底层实现是基于迭代器遍历的


        //map遍历是通过内部的Entry来遍历的，也适用于上边说的fail-fast快速失效迭代器
    }
}




/*
public class IteratorTest {
    private  static Map<String,Integer> MAP=new HashMap<>();

    static {
        for(int i=0;i<100000;i++){
            MAP.put("X"+i,i);
        }
    }
    public static void main(String[] args) {
        new Thread(()->{
            int i=0;

            try {
                Thread.sleep(3000);
                MAP.put("A",1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                Thread.sleep(3000);
                MAP.put("B",1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
public class Main {
    public static ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> list=new ArrayList<>();
        if(input==null||k<=0||input.length<k){
            return list;
        }
        PriorityQueue<Integer> q=new PriorityQueue<>(k,Collections.reverseOrder());//PriorityQueue优先级队列
        //reverseOrder() 这个方法返回一个比较器，它强行逆转实现了 Comparable 接口的对象 collection 的自然顺序。

        for(int i=0;i<input.length;i++){
            if(i<k){
                q.offer(input[i]);
            }else{
                //i大于等于k，接下来需要比较插入
                if(input[i]<q.peek()){
                    //当前值小于当前堆（大堆）中的最大的根结点
                    //交换+调整维持大堆
                    q.poll();
                    q.offer(input[i]);
                }
            }
        }
        for(int i=0;i<k;i++){
            list.add(q.poll());//优先级队列出来的元素是当前最小元素
        }
        return list;
    }

    public static void main(String[] args) {
        int[] array={1,4,5,3,6,2,8,9,7};
        int k=4;
        System.out.println(GetLeastNumbers_Solution(array,k));
    }
}
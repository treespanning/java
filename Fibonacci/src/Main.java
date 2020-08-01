
import java.util.*;
public class Main {
    private static Map<Integer,Integer> filter=new HashMap<>();
    public static int Fibonacci(int n) {
        //f(n-1)+f(n-2)=f(n)
        if(0==n||1==n){
            return n;
        }
        int ppre=0;//第n-2斐波那契数
        if(filter.containsKey(n-2)){
            ppre=filter.get(n-2);
        }else{
            ppre=Fibonacci(n-2);
            filter.put(n-2,ppre);
        }
        int pre=0;//第n-1斐波那契数
        if(filter.containsKey(n-1)){
            pre=filter.get(n-1);
        }else{
            pre=Fibonacci(n-1);
            filter.put(n-1,pre);
        }
        return pre+ppre;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        System.out.println(Fibonacci(n));
    }
}

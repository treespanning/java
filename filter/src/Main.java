
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
public class Main {
    private static Map<Integer,Integer> filter=new HashMap<>();
    public static int Fibonacci1(int n) {
        //迭代算法
        //f(n-1)+f(n-2)=f(n)
        if(0==n){
            return 0;
        }
        int first=1;
        int second=1;
        int third=1;
        while(n>2){
            third=first+second;
            first=second;
            second=third;
            --n;
        }
        return third;
    }
    public static int Fibonacci(int n){
        if(0==n||1==n){
            return n;
        }
        int ppre=0;
        if(filter.containsKey(n-2)){
            ppre=filter.get(n-2);
        }else{
            ppre=Fibonacci(n-2);
            filter.put(n-2,ppre);
        }
        int pre=0;
        if(filter.containsKey(n-1)){
            pre=filter.get(n-1);
        }else{
            pre=Fibonacci(n-1);
            filter.put(n-1,pre);
        }
        return ppre+pre;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        System.out.println(Fibonacci(n));
    }
}

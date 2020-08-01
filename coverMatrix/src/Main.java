/*1.定义状态：
f(n):覆盖2* n的方法数

2.状态转移方程
f(n)=f(n-1)+f(n-2)

3.设置初始值
f(1)=1;
f(2)=2;*/
import java.util.Scanner;
public class Main {
    public static int RectCover(int target) {
        if(target<=2){
            return target;
        }
        //f(n):2*n覆盖的方法数
        //f(1)=1;
        //f(2)=2;
        //f(n)=f(n-1)+f(n-2);
        int[]dp=new int[target+1];
        dp[1]=1;
        dp[2]=2;
        for(int i=3;i<target+1;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[target];
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        System.out.println(RectCover(n));
    }
}

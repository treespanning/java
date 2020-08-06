import java.util.Scanner;

public class Main {
    //进阶版跳台阶
    public static int JumpFloorII(int target) {
        //f(n)=f(n-1)+f(n-2)+f(n-3)+...
        //f(n-1)=f(n-2)+f(n-3)+f(n-4)+...
        //f(n)=2*f(n-1)
        int[]dp=new int[target+1];
        dp[0]=0;
        dp[1]=1;
        for(int i=2;i<dp.length;i++){
            dp[i]=dp[i-1]*2;
        }
        return dp[target];
    }
    //青蛙跳台阶1
        public static int JumpFloor(int target) {
            if(target<=2){
                return target;
            }
            //f(n)=f(n-1)+f(n-2)
            //f(0)=1,f(1)=1,f(2)=1;
            int[]dp=new int[target+1];
            dp[0]=1;
            dp[1]=1;
            dp[2]=2;
            for(int i=2;i<=target;i++){
                dp[i]=dp[i-1]+dp[i-2];
            }
            return dp[target];
        }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println(JumpFloor(sc.nextInt()));
        System.out.println(JumpFloorII(sc.nextInt()));

    }

}

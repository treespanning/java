import java.util.Scanner;
public class Main {
    private static int count=0;
    private static int n;
    private static int[]arr;
    private static void pack(int s,int n) {
        if(s==0){//符号条件
             count++;
             return;
        }
        if(s<0||(s>0&&n<0)){
            //不符合条件
            return;
        }
        pack(s-arr[n],n-1);//从最后一个匹配到了S count++完成 开始下一次count的寻找
        pack(s,n-1);//开始下一次count的寻找
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            n=sc.nextInt();
            arr=new int[n+1];
            for(int i=1;i<=n;i++)
            {
                arr[i]=sc.nextInt();
            }
        }
        pack(40,n);
        System.out.println(count);
    }
}
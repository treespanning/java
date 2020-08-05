import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static boolean IsPopOrder(int [] pushA,int [] popA) {
        if(pushA==null||popA==null||pushA.length!=popA.length){
            return false;
        }
        int i=0;//pushA的游标
        int j=0;//popA的游标
        Stack<Integer> st=new Stack<>();
        for(;i<pushA.length;i++){
            st.push(pushA[i]);//不相等则一直压栈
            while(!st.empty()&&st.peek()==popA[j]){
                st.pop();//相等则一直出栈
                j++;
            }
        }
        return st.empty();
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int[]pushA=new int[10];
        int[]popA=new int[10];
        for(int i=0;i<10;i++){
            pushA[i]=sc.nextInt();
        }
        for(int i=0;i<10;i++){
            popA[i]=sc.nextInt();
        }
        System.out.println(IsPopOrder(pushA,popA));
    }
}

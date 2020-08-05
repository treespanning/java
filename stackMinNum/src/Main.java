
import java.util.Stack;
import java.util.Scanner;

public class Main {
    private static Stack<Integer> s1=new Stack<>();//数据栈
    private static Stack<Integer> ss=new Stack<>();//辅助栈
    public static void push(int node) {
        if(ss.empty()||ss.peek()>node){
            ss.push(node);
        }else{//ss不等于空，并且插入的元素数大于当前辅助栈的最小值
            ss.push(ss.peek());
        }
        s1.push(node);
    }

    public static void pop() {
        s1.pop();
        ss.pop();
    }

    public static int top() {
        return s1.peek();
    }

    public static int min() {
        return ss.peek();
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        for(int i=0;i<n;i++){
            push(sc.nextInt());
        }
        System.out.println(min());
    }
}
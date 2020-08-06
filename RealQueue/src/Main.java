import java.util.Stack;

public class Main {
   static Stack<Integer> stack1 = new Stack<Integer>();
   static Stack<Integer> stack2 = new Stack<Integer>();

    public static void push(int node) {
        stack1.push(node);
    }

    public static int pop() {
        if(stack2.empty()){
            while(!stack1.empty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public static void main(String[] args) {
        push(1);
        push(3);
        push(-1);
        push(8);
        pop();
        pop();
        pop();
    }

}
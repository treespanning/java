import java.util.Stack;
public class Main{
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    public void push(int node)
    {
        stack1.push(node);
    }
    public int pop()
    {
        int temp;
        if(stack2.isEmpty())
        {
            while(!stack1.isEmpty()){
                stack2.push(stack1.peek());
                stack1.pop();
            }
            temp=stack2.peek();
            stack2.pop();
        }
        else{
            temp=stack2.peek();
            stack2.pop();
        }
        return temp;
    }
}
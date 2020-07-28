import java.util.ArrayList;
import java.util.Stack;

class ListNode{
    int val;
    ListNode next=null;
    ListNode(int val){
        this.val=val;
    }
}
public class Main {
    private ListNode head=null;
    public void printListFromTailToHeader(ListNode listNode,ArrayList<Integer> list){
        if(null==listNode){
            return;
        }
        printListFromTailToHeader(listNode.next,list);
        list.add(listNode.val);
    }
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list=new ArrayList<Integer>();

        printListFromTailToHeader(listNode,list);
        return list;
    }
    public ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
        ArrayList<Integer> list=new ArrayList<>();
        if(null==listNode){
            return list;
        }
        Stack<Integer> st=new Stack<>();
        while(listNode!=null){
            st.push(listNode.val);
            listNode=listNode.next;
        }
        while(!st.empty()){
            list.add(st.pop());
        }
        return list;
    }

    public void addFirst(int val){
        ListNode node=new ListNode(val);
        if(this.head==null){
            this.head=node;
        }else{
            node.next=this.head;
            this.head=node;
        }
    }

    public void display(ListNode head){
        ListNode cur=head;
        while(cur!=null){
            System.out.println(cur.val+" ");
            cur=cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

    }
}

import java.util.Scanner;
class ListNode {
    int val;
    ListNode next = null;
    ListNode(int val) {
        this.val = val;
    }
}
public class Main {
    public static ListNode FindKthToTail(ListNode head,int k) {
        if(k<0||head==null){
            return null;
        }
        ListNode f1=head;
        ListNode f2=head;
        while(f1!=null&k>0){
            k--;
            f1=f1.next;
        }
        while(f1!=null){
            f1=f1.next;
            f2=f2.next;
        }
        return k>0?null:f2;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int k=sc.nextInt();

    }
}

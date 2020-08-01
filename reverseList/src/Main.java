 class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
public class Main {
    public ListNode ReverseList(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }
        /*
        1.
        ListNode cur=head;
        ListNode pre=null;
        ListNode newHead=null;
        ListNode curNext=null;
        while(cur!=null){
            curNext=cur.next;
            if(curNext==null){
                newHead=cur;
            }
            cur.next=pre;
            pre=cur;
            cur=curNext;
        }
        return newHead;*/
        /*
        2.
        ListNode left=head;
        ListNode mid=left.next;
        ListNode right=mid.next;
        while(right!=null){
            mid.next=left;
            left=mid;
            mid=right;
            right=right.next;
        }
        mid.next=left;
        head.next=null;
        head=mid;
        return head;*/
        ListNode newHead=null;
        while(head!=null){
            //把原链表第一个节点拿下了
            ListNode p=head;
            head=head.next;
            //把节点头插新列表
            p.next=newHead;
            newHead=p;
        }
        return newHead;
    }

    public static void main(String[] args) {

    }
}

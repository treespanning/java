class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
public class Main {
    public ListNode Merge(ListNode list1,ListNode list2) {
        /*
        1.
        ListNode a=list1;
        ListNode b=list2;
        if(list1==null){
            return list2;
        }else if(list2==null){
            return list1;
        }
        ListNode newHead=new ListNode(-1);
        ListNode cur=newHead;
        while(a!=null&&b!=null){
            if(a.val<b.val){
                cur.next=a;
                a=a.next;
            }else{
                cur.next=b;
                b=b.next;
            }
            cur=cur.next;
        }
        if(a==null){
            cur.next=b;
        }else{
            cur.next=a;
        }
        return newHead.next;
        */
        //2.
        if(null==list2){
            return list1;
        }
        if(null==list1){
            return list2;
        }
        ListNode newHead=null;
        ListNode newTail=null;
        while(null!=list2&&null!=list1){
            ListNode p=list2.val>list1.val?list1:list2;
            if(p==list1){
                list1=list1.next;
            }else{
                list2=list2.next;
            }
            if(null==newHead){
                newHead=p;
                newTail=p;
            }else{
                newTail.next=p;
                newTail=newTail.next;
            }
        }
        if(null==list2){
            newTail.next=list1;
        }else{
            newTail.next=list2;
        }
        return newHead;
    }
    public static void main(String[] args) {

    }
}

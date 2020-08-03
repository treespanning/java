class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
public class Main {
        public ListNode deleteDuplication(ListNode pHead)
        {
            if(pHead==null){
                return pHead;
            }
            ListNode head=new ListNode(-1);
            head.next=pHead;

            ListNode prev=head;//重复节点的前驱节点
            ListNode last=prev.next;

            while(last!=null){
                //先找到重复区域的起始位置
                while(last.next!=null&&last.val!=last.next.val){
                    prev=prev.next;
                    last=last.next;
                }

                //2.确立重复的范围
                while(last.next!=null&&last.val==last.next.val){//此时prev在重复节点第一个位置的前一个位置
                    last=last.next;
                    //last则指向重复区域的最后一个节点
                }

                //三种情况：
                //1.last.next!=null 且（prev，last]限定的一段重复距离，去重
                //2.last.next==null&&（prev，last]限定的一段重复距离,重复的节点直到链表结束
                //prev.next=null;
                //3.last.next==null&&prev.next==last,从本次循环开始，节点就一直没有重复的情况，不需要去重
                if(prev.next!=last){
                    //具有范围，需要去重
                    prev.next=last.next;
                }
                last=last.next;//保证恢复到和最开始一致
            }
            return head.next;
        }

}

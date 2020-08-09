class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}

public class Main {
    public RandomListNode Clone(RandomListNode pHead)
    {
        if(pHead==null){
            return null;
        }

        RandomListNode cur=pHead;
        while(cur!=null){
            //1.把每个指针都完全的复制一次
            RandomListNode node=new RandomListNode(cur.label);
            RandomListNode temp=cur.next;
            //2.将所有结点串联起来
            cur.next=node;
            node.next=temp;
            cur=temp;
        }
        cur=pHead;
        //3.解决random指向
        while(cur!=null){
            if(cur.random!=null){
                cur.next.random=cur.random.next;
            }else{
                cur.next.random=null;
            }
            cur=cur.next.next;
        }

        //4.拆开原链表和复制链表

        cur=pHead;//从原链表遍历
        RandomListNode newHead=pHead.next;//复制链表遍历
        while(cur.next!=null){
            RandomListNode cloneNode=cur.next;
            cur.next=cloneNode.next;
            if(cloneNode.next!=null){//下一个位置不为空，证明原链表还有数值，则cloneNode.next.next一定存在
                cloneNode.next=cloneNode.next.next;
            }else{
                //不存在下一个
                cloneNode.next=null;
            }
            cloneNode=cloneNode.next;
        }
        return newHead;
    }

    public static void main(String[] args) {

    }
}

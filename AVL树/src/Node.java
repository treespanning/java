public class Node {

    int key;
    int df;

    Node left;//包类型
    Node right;

    /*
     * 记录结点的父亲结点，如果结点是根节点，则parent==null*/
        Node parent;
        Node(int key,Node parent){
            this.parent=parent;
            this.key=key;
            this.df=0;
            left=null;
            right=null;
        }
}

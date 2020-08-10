class TreeNode {
 int val = 0;
 TreeNode left = null;
 TreeNode right = null;

 public TreeNode(int val) {
 this.val = val;

 }

 }

public class Main {
    //将该二叉搜索树转换成一个排序的双向链表
    TreeNode pre=null;
    TreeNode root=null;//作为双向链表的最左边的头结点
    TreeNode last=null;//作为双向链表的最右边的头结点
    public TreeNode Convert(TreeNode pRootOfTree) {
        //搜索二叉树——左根右为有序，则需要左根右遍历，依次改变指向
        //由于无父亲结点，则需要另一个指针
        if(pRootOfTree==null){
            return null;
        }
        Convert(pRootOfTree.left);
        if(root==null){
            root=pRootOfTree;//保存的是递归到最底层的，最左子树的结点，作为头结点
        }

        if(pre!=null){
            pRootOfTree.left=pre;//pre为当前pRootOfTree的前结点
            pre.right=pRootOfTree;//将pre的后指向当前的pRootOfTree，完成两个结点的链接
        }
        pre=pRootOfTree;//遍历完当前pRootOfTree的左子树，pre向后走
        Convert(pRootOfTree.right);
        /*if(last==null){
            last=pRootOfTree;//从右向左正确，从左向右只留了一个最后的结点
            //证明需要返回的头结点得是左边的
        }*/
        return root;
    }

    public static void main(String[] args) {

    }
}
//由于Main中是左根右遍历的，导致结点的前置结点的pre经过调整之后，会到达最右边，成为尾结点，
//则还需要额外的空间创建一个结点，用于保存最右端的“头结点”

//而当将二叉搜索树由右左根遍历时，pre则会到大最右的结点，可以直接返回，无需其他结点


public class Test {
    TreeNode pre=null;
    public TreeNode Convert(TreeNode pRootOfTree) {
        if(pRootOfTree==null){
            return null;
        }
        Convert(pRootOfTree.right);
        if(pre!=null){
            pRootOfTree.right=pre;//由于是从右到左，则此处需要和Main不一样，改动前后结点，将右边视为前结点
            pre.left=pRootOfTree;
        }
        pre=pRootOfTree;
        Convert(pRootOfTree.left);
        return pre;
    }

    public static void main(String[] args) {

    }
}

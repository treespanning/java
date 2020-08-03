class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
    public TreeNode(int val) {
        this.val = val;
    }
}
public class Main {
    private static TreeNode root;
        //自上向下镜像
   public static void Mirror2(TreeNode root) {
            if(root==null){
                return;
            }
            TreeNode temp=root.left;
            root.left=root.right;
            root.right=temp;
            Mirror2(root.left);
            Mirror2(root.right);
   }
    //从下往上镜像
    public static void Mirror1(TreeNode root) {
        if(root==null){
            return;
        }
        Mirror1(root.left);
        Mirror1(root.right);
        TreeNode temp=root.left;
        root.left=root.right;
        root.right=temp;
    }

    public static void main(String[] args) {

    }
}

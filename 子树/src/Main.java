 class TreeNode {
     int val = 0;
     TreeNode left = null;
     TreeNode right = null;

     public TreeNode(int val) {
         this.val = val;

     }
 }
public class Main {
        //2.在已经确定起始位置的情况下，比较左右子树是否相等
        public boolean isSame(TreeNode root1,TreeNode root2){
            if(root2==null){//root2已经比较完成，没有要比较的东西了
                return true;
            }
            if(root1==null){//root1不断往下走，但是root2还有数据，root1已经为空
                return false;
            }
            if(root1.val!=root2.val){
                return false;
            }

            //说明当前节点是相等的
            return isSame(root1.left,root2.left)&&isSame(root1.right,root2.right);
        }
        public boolean HasSubtree(TreeNode root1,TreeNode root2) {
            if(root1==null||root2==null)
                return false;
            //1.先找起始位置
            boolean result=false;
            if(root1.val==root2.val){
                //找到了起始位置
                result=isSame(root1,root2);//判定指定两个树，各自的左右子树是否相等
            }
            if(result!=true){
                result=HasSubtree(root1.left,root2);
            }
            if(result!=true){
                result=HasSubtree(root1.right,root2);
            }
            return result;
        }

    public static void main(String[] args) {

    }

}

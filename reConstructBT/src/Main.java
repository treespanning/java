import java.util.Scanner;
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
public class Main {
    public TreeNode reConstructBinaryTreeCore(int[]pre,int pre_start,int pre_end,int[] in,int in_start,int in_end){
        if(pre_start>pre_end||in_start>in_end){
            return null;
        }
        TreeNode root=new TreeNode(pre[pre_start]);

        for(int i=in_start;i<=in_end;i++){
            if(in[i]==pre[pre_start]){
                //前序的第一个节点是根节点，
                //则在中序中找到根节点，根节点之前的则是左子树，之后的则是右子树
                //左子树的节点个数：i-in_start
                //左子树范围：pre_start+1开始，连续i-in_start个节点，就是左子树的前序序列
                root.left=reConstructBinaryTreeCore(pre,pre_start+1,i-in_start+pre_start,in,in_start,i-1);

                //右子树节点个数：从左子树结束i-in_start+pre_start之后的第一位开始，到数组结束

                root.right=reConstructBinaryTreeCore(pre,i-in_start+pre_start+1,pre_end,in,i+1,in_end);
                break;
            }
        }
        return root;
    }
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if(pre==null||in==null||pre.length!=in.length){
            return null;
        }
        return reConstructBinaryTreeCore(pre,0,pre.length-1,in,0,in.length-1);
    }

    public static void main(String[] args) {

    }
}

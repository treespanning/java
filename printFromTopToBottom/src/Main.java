import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
class TreeNode {
     int val = 0;
     TreeNode left = null;
     TreeNode right = null;

     public TreeNode(int val) {
     this.val = val;
     }
 }

public class Main {
    public  ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        if(root==null)
            return new ArrayList<Integer>();
        Queue<TreeNode> q=new LinkedList<>();

        ArrayList<Integer> result=new ArrayList<>();
        q.offer(root);//压入根结点
        while(!q.isEmpty()){
            TreeNode cur=q.poll();
            result.add(cur.val);//处理根结点
            if(cur.left!=null){//压入根的左
                q.offer(cur.left);
            }
            if(cur.right!=null){//压入根的右
                q.offer(cur.right);
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
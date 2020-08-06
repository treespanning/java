import java.util.ArrayList;
class TreeNode {
     int val = 0;
     TreeNode left = null;
     TreeNode right = null;

     public TreeNode(int val) {
        this.val = val;
     }
 }
public class Main {
    public void FindPathDFS(TreeNode root,int target,ArrayList<ArrayList<Integer>> result,ArrayList<Integer>list){
        if(root==null){
            return;
        }
        list.add(root.val);//把一条路径放入待选集
        target=target-root.val;
        //已经是叶子结点
        //从root到该叶子节点之和是target
        //3.是叶子结点但不满足结点，直接回退
        if(root.left==null&&root.right==null&&target==0){
            result.add(new ArrayList<Integer>(list));
            //此路径满足要求
        }
        //如果还不是叶子结点，继续深度遍历
        FindPathDFS(root.left,target,result,list);
        FindPathDFS(root.right,target,result,list);

        //无论是否合法，判断之后都将回退
        //remove(obj)会删除list中的第一个该元素，remove(index)会删除该下标的元素
        list.remove(list.size()-1);
    }
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list=new ArrayList<>();
        FindPathDFS(root,target,result,list);
        return result;

    }

    public static void main(String[] args) {

    }
}
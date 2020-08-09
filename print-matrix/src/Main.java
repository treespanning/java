import java.util.ArrayList;
public class Main {
    public static ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> list=new ArrayList<>();
        if(matrix==null||matrix.length==0||matrix[0].length==0)
            return list;
        if(matrix.length==1&&matrix[0].length!=0){
            //一行的时候
            for(int i=0;i<matrix[0].length;i++){
                list.add(matrix[0][i]);
            }
            return list;
        }
        int up=0;//上边界
        int down=matrix.length-1;//下边界
        int left=0;//左边界
        int right=matrix[0].length-1;//右边届
        while(true){
            for(int col=left;col<=right;col++){
                list.add(matrix[up][col]);
            }
            up++;
            if(up>down){
                break;
            }//从左向右打印最上边的，一行的打印直至right

            for(int row=up;row<=down;row++){
                list.add(matrix[row][right]);
            }
            right--;//从上往下打印最右边的
            if(right<left){
                break;
            }

            for(int col=right;col>=left;col--){
                list.add(matrix[down][col]);
            }
            down--;
            if(up>down){
                break;
            }//从右向左打印最下边的，一行的打印直至left

            for(int row=down;row>=up;row--){
                list.add(matrix[row][left]);
            }
            left++;//从下往上打印最左边的
            if(right<left){
                break;
            }
        }
        return list;
    }
    public static void main(String[] args) {
       int[][] matrix={{1,2,3,4},{12,13,14,5},{11,16,15,6},{10,9,8,7}};
        System.out.println(printMatrix(matrix));
    }
}
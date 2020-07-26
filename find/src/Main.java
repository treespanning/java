import java.util.Scanner;
public class Main {
    //
    public static boolean Find(int target, int [][] array) {
        //从右上往左下查找
        int i=0;
        int j=array[0].length-1;
        while(i<array.length&&j>=0){
                if(target==array[i][j]){
                    return true;
                }else if(target>array[i][j]){
                    i++;
                }else if(target<array[i][j]){
                    j--;
                }
            }
            return false;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("行和列？");
        int n=sc.nextInt();
        int m=sc.nextInt();
        int[][]array=new int[n][m];
        System.out.println("每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序,输入二维数组");
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                array[i][j]=sc.nextInt();
            }
        }
        System.out.println("查询数组中的数字");
        int target=sc.nextInt();
        boolean flag=Find(target,array);
        System.out.println(flag);
    }
}

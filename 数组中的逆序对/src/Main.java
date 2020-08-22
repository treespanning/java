import java.util.*;
public class Main {
    //时间复杂度太大O(n^2)
    public static int count(int[] A, int n) {
        // write code here
        int count=0;
        for(int i=0;i<n;i++)
        {
            int temp=A[i];
            for(int j=i+1;j<n;j++)
            {
                if(temp>A[j]){
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[]array={1,2,3,4,5,6,7,0};
        int n=8;
        System.out.println(count(array,n));
    }
}

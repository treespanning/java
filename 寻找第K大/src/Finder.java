import java.util.*;
public class Finder {
    public static int temp;
    public static void qsort(int[]arr,int start,int end){
        if(start>end)
            return;
        temp = arr[start];
        int left=start;
        int right=end;
        //快速排序
        while(left<right){
            while((left<right)&&(arr[right]>=temp))
            {
                right--;
            }
            while((left<right)&&(arr[left]<=temp))
            {
                left++;
            }
            if(left<right){
                int temp=arr[left];
                arr[left]=arr[right];
                arr[right]=temp;
            }
        }
        arr[start]=arr[left];
        arr[left]=temp;
        qsort(arr,start,right-1);
        qsort(arr,right+1,end);
    }
    public static int findKth(int[] a, int n, int K) {
        // write code here
        qsort(a,0,n-1);
        return a[n-K];
    }

    public static void main(String[] args) {
        int[]array={1,2,3,4,5,6,7};
        int n=7;
        int K=4;
        System.out.println(findKth(array,n,K));
    }
}
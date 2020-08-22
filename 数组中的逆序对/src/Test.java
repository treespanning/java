import java.util.*;
public class Test {
    public static void main(String[] args) {
        int[]array={1,2,3,4,5,6,7,0};
        int n=8;
        System.out.println(count(array,n));
    }

    private static int count(int[] A, int n) {
        //分治思想，归并排序的思路进行处理，
        // 先把数组分隔成子数组，先统计出子数组内部的逆序对的数目，
        // 然后再统计出两个相邻子数组之间的逆序对的数目。
        // 在统计逆序对的过程中，还需要对数组进行排序
        //逆序对的总数=左边数组中的逆序对的数量+右边数组中逆序对的数量+左右结合成新的顺序数组时中出现的逆序对的数量

        if(A==null||n==0){
            return 0;
        }
        return mergeSortRecursion(A,0,n-1);//归并排序
    }

    public static int mergeSortRecursion(int[]arr,int l,int r) {
        if(l==r){
            return 0;
        }
        int mid=(l+r)/2;
        //逆序对的总数=左边数组中的逆序对的数量+右边数组中逆序对的数量+左右结合成新的顺序数组时中出现的逆序对的数量
        return mergeSortRecursion(arr,l,mid)+mergeSortRecursion(arr,mid+1,r)+merge(arr,l,mid,r);
    }

    public static int merge(int[] arr, int left, int mid, int right) {
        int [] temp =new int[right-left+1];
        int index=0;
        int i=left;
        int j=mid+1;
        int count=0;//统计逆序对
        while(i<=mid&&j<=right){
            if(arr[i]<arr[j]){
                temp[index++]=arr[i];
                i++;
            }else{
                //此时left到mid，mid到right已经成升序，则
                //数字前半部分元素大于后半部分某一元素时，构成逆序对
                //arr[i]>arr[j],证明arr[i]到arr[mid]>arr[j]
                count+=(mid-i+1);
                temp[index++]=arr[j];
                j++;
            }
        }
        while (i<=mid){
            temp[index++]=arr[i++];
        }
        while(j<=right){
            temp[index++]=arr[j++];
        }
        for(int k=0;k<temp.length;k++){
            arr[left++]=temp[k];//搬回原数组
        }
        return count;
    }
}

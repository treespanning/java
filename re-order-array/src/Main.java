public class Main {
    public void reOrderArray(int [] array) {
        if(array.length==0||array==null){
            return;
        }
        int k=0;//插入的位置
        for(int i=0;i<array.length;i++){
            if((array[i]&1)==1){
                //是奇数
                int temp=array[i];//保存当前奇数
                int j=i;
                while(j>k){
                    array[j]=array[j-1];//当前奇数位置之前的（偶数序列）全部后移一个位置
                    //为什么是偶数，因为j>k,而k之前的全部都是放好的奇数
                    j--;
                }
                array[k++]=temp;//插入当前奇数
            }
        }
    }
}
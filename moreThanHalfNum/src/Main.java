import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("数组大小:");
        int n=sc.nextInt();
        System.out.println("数组内容:");
        int[]array=new int[n];
        for(int i=0;i<array.length;i++){
            array[i]=sc.nextInt();
        }
        System.out.println(MoreThanHalfNum_Solution(array));
    }
    public static int MoreThanHalfNum_Solution1(int [] array){
        if(array==null||array.length==0){
            return 0;
        }
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<array.length;i++){
            if(map.containsKey(array[i])){
                map.put(array[i],map.get(array[i])+1);
            }else{
                map.put(array[i],1);
            }
            if(map.get(array[i])>array.length>>1){
                return array[i];
            }
        }
        return 0;
    }
    public static int MoreThanHalfNum_Solution2(int [] array){
        /*9ms

        占用内存：9552k*/
        if(array==null||array.length==0){
            return 0;
        }
        Arrays.sort(array);
        int target=array[array.length>>1];
        int count=0;
        for(int i=0;i<array.length;i++){
            if(array[i]==target){
                count++;
            }
            if(count>array.length>>1){
                return target;
            }
        }
        return 0;
    }
    public static int MoreThanHalfNum_Solution(int [] array){
        /*运行时间：8ms

        占用内存：9400k*/
        if(array==null||array.length==0){
            return 0;
        }
        int number=array[0];
        int count=1;
        for(int i=0;i<array.length;i++){
            if(count==0){
                number=array[i];
                count=1;
            }
            if(number==array[i]){
                count++;
            }else{
                count--;
            }
        }

        //校验
        count=0;
        for(int i=0;i<array.length;i++){
            if(number==array[i]){
                count++;
            }
        }
        return count>array.length>>1?number:0;
    }
}

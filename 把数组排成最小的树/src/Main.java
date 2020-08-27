import java.util.ArrayList;
public class Main {
    public static String PrintMinNumber(int[] numbers) {
        if(numbers==null||numbers.length==0)return"";
        for(int i=0;i<numbers.length;i++){//排序
            //比较字符串a+b和b+a，谁更大，如果b+a更大，则a就应该放在前面，
            //按照此原理遍历整个数组，使得数组中元素以我们要的方式排列
            for(int j=i+1;j<numbers.length;j++){
                int sum1=Integer.valueOf(numbers[i]+""+numbers[j]);
                int sum2=Integer.valueOf(numbers[j]+""+numbers[i]);
                if(sum1>sum2){
                    int temp=numbers[j];
                    numbers[j]=numbers[i];
                    numbers[i]=temp;
                }
            }
        }//比较完成
        //依次把数组元素+到String之后。
        String str=new String("");
        for(int i=0;i<numbers.length;i++){
            str=str+numbers[i];
        }
        return str;
    }

    public static void main(String[] args) {
        int[]arr={134,23,54,0,65,342};
        System.out.println(PrintMinNumber(arr));
    }
}
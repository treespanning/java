import java.util.ArrayList;
import java.util.Collections;
public class Main {
    public static void Swap(char[] str, int start, int i){
        char temp=str[start];
        str[start]=str[i];
        str[i]=temp;
    }
    public static boolean IsExist(ArrayList<String> result, char[] str){
        return result.contains(String.valueOf(str));//返回包含判断
    }
    public static void PermutationHelper(char[] str, int start, ArrayList<String> result){
        if(start==str.length-1){

            //去重
            if(!IsExist(result,str)){
                result.add(new String(str));//如果没有重复，则把当前的数组str转为String 放入到结果集中
            }
            return;//退回到上一步
        }
        for(int i=start;i<str.length;i++){//start当前永远代表的是第一个元素，意味着i和start进行交换，就是以i作为开始

            Swap(str,start,i);//确定当前的第一个字母
            //以i对应的字符作为开始

            PermutationHelper(str,start+1,result);//依次轮排当前首字母之后的字符
            //以i开头的所有的可能性全部处理并保存到了result

            Swap(str,start,i);//回退
        }
    }
    public static ArrayList<String> Permutation(String str) {
        ArrayList<String> result = new ArrayList<String>();//结果集
        if(str!=null&&str.length()>0){
            PermutationHelper(str.toCharArray(),0,result);
            Collections.sort(result);
        }
        return result;
    }

    public static void main(String[] args) {
        String str="abc";
        System.out.println(Permutation(str));
    }
}
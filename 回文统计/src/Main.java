import java.util.Scanner;
public class Main{
    public static boolean isPri(String str) {
        StringBuffer Str2 = new StringBuffer(str);
        return str.equals(Str2.reverse().toString());
    }
    public static int inn(String A,String B){
        if(A==null&&B==null)
            return 1;
        else if(A.length()==0&&B.length()!=0)
            return 0;
        else if(A.length()!=0&&B.length()==0)
            return 0;
        else if(A!=null&&B!=null)
        {
            int count=0;
            for(int i=0;i<A.length();i++)
            {
                StringBuffer str=new StringBuffer(A);
                str.insert(i,B);
                //在相应的i位置插入B
                String str1=str.toString();
                // 判断是否为回文
                if(isPri(str1)){
                    count++;
                }
            }
            ////字符串A的最后一位也需要插入一次
            String Str2 = A.concat(B);
            if(isPri(Str2)){
                count++;
            }
            return count;
        }
        return 0;
    }
    public static void main(String []args){
        Scanner in=new Scanner(System.in);
        String A = in.nextLine();
        String B= in.nextLine();
        int count=inn(A,B);
        System.out.println(count);
    }
}
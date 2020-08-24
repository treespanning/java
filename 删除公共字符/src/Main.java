import java.util.Scanner;
public class Main {
    private static Boolean Con(String B,char ch){
        for(int i=0;i<B.length();i++)
        {
            if(B.charAt(i)==ch)
            {
                return true;
            }
        }
        return false;
    }
    private static String theStr(String A,String B){
        StringBuffer str=new StringBuffer();
        for(int i=0;i<A.length();i++)
        {
            if(!(Con(B,A.charAt(i)))){
                str.append(A.charAt(i));
            }
            // if(!(B.contains(A.charAt(i)+""))){
            // str.append(A.charAt(i));
            // }
        }
        return str.toString();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1=sc.nextLine();
        String str2=sc.nextLine();
        System.out.println(theStr(str1,str2));
    }
}

import java.util.Scanner;

public class Main {
    public static String replaceSpace1(StringBuffer str) {
            StringBuffer str1=new StringBuffer();
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)!=' '){
                    str1.append(str.charAt(i));
                }else{
                    str1.append("%20");
                }
            }
            return str1.toString();
        }
    public static String replaceSpace(StringBuffer str) {
        /*运行时间：16ms

        占用内存：9672k
         */
        int count=0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)==' '){
                count++;
            }
        }
        int newLong=str.length()+count*2;
        int old_end=str.length()-1;
        int new_end=newLong-1;
        str.setLength(newLong);//重新设置字符串长度，防止越界

        while(old_end>=0&&new_end>=0){
            if(str.charAt(old_end)==' '){
                str.setCharAt(new_end--,'0');
                str.setCharAt(new_end--,'2');
                str.setCharAt(new_end--,'%');
                old_end--;
            }else{
                str.setCharAt(new_end--,str.charAt(old_end--));
            }
        }
        return str.toString();
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("输入字符串?");
        StringBuffer str = new StringBuffer(sc.nextLine());
        System.out.println(replaceSpace(str));
    }
}

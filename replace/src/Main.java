import java.util.Scanner;

public class Main {
        public static String replaceSpace(StringBuffer str) {
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
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("输入字符串？");
        StringBuffer str = new StringBuffer(sc.nextLine());
        System.out.println(replaceSpace(str));
    }
}

import java.util.Scanner;
public class Main {
    public static String find1(String str){
        String ss=null;
        int end=0;
        int max=0;
        int count=0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)>='0'&&str.charAt(i)<='9'){
                count++;
                if(count>max){
                    max=count;
                    end=i;
                }
            }else {
                count=0;
            }
        }
        if(end!=0){
            ss=str.substring(end-max+1,end+1);
        }
        return ss;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String str=sc.nextLine();
        System.out.println(find1(str));
    }
}

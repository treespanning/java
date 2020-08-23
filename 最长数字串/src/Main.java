import java.util.Scanner;
public class Main {
    private static String lengNum(String str){
        int count=0;
        int max=0;
        int end=0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)>='0'&&str.charAt(i)<='9'){
                count++;
                if(count>max){
                    max=count;
                    end=i;
                    //定位到当前最大数字串的最后一位数字下标
                }
            }
            else{
                count=0;
            //重置
            }
        }
        String str1=str.substring(end-max+1,end+1);
        return str1;
    }
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        String str=input.nextLine();
        String str2=lengNum(str);
        System.out.println(str2);
    }
}

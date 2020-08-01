import java.util.Scanner;
public class Main {
        public static  int NumberOf1(int n) {
            int count=0;
            while(n!=0){
                n=n&n-1;
                count++;
            }
            return count;
        }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        System.out.println(NumberOf1(n));
    }
}

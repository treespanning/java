import java.util.Scanner;
public class text {
   public static boolean isPrime(int number)
   {
       int i=0;
       for( i=2;i<=Math.sqrt(number);i++)
       {
           if(number%i==0){
               break;
           }
       }
       if(i>Math.sqrt(number)) {
           return true;
       }
       return false;
   }
    public static void main(String[]args){
        System.out.println("please input a number");
        Scanner input=new Scanner(System.in);
        int number=input.nextInt();
        System.out.println(isPrime(number));
    }
}

import java.util.*;
public class Main {
    private static int waterMax(int n)
    {
        int count=0;//记录可喝瓶数
        while(n>1) {
            count = count + n / 3;
            n = n % 3 + n / 3;
            if (n == 2) {
                //借的
                n++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        while(input.hasNext()) {
            int num = input.nextInt();
            if (num > 0) {
                System.out.println(num / 2);
                //三个空瓶=1个空瓶+汽水
                // 汽水=三个空瓶-1个空瓶=2个空瓶
                System.out.println(waterMax(num));
            } else {
                break;
            }
        }
        input.close();
    }
}


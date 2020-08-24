import java.util.Scanner;
public class Main {
    //暴力解法
    //a表示6的包装袋
    //b为8的包装袋
    private static int pack(int n) {
        int count = 0;
        int min = 0;
        for (int a = 0; a <= (n / 6) + 1; a++) {
            for (int b = 0; b <= (n / 8) + 1; b++) {
                if ((a * 6 + b * 8) == n) {
                    count = a + b;
                    if (min != 0) {
                        if (min > count) {
                            min = count;
                        }
                    } else {
                        min = count;
                    }
                }
            }
        }
        if (min == 0)
            return -1;
        else
            return min;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        System.out.println(pack(n));
    }
}

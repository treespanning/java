import java.util.Scanner;

public class Main {
    public static double Power(double base, int exponent) {
        if(exponent<0){
            base=1/base;
            exponent=-exponent;//把指数转成正整数
        }
        double x=base;
        double ret=1.0;
        //x^6,6可以解析成110
        //对 base 进行自乘，导致 base 的指数每次都扩大 2 倍，这与二进制相同
        //6=0*2^0   +1*2^1  +1*2^2
        //x^6=x^(0*2^0   +1*2^1  +1*2^2)=x^1  *  x^(1*2^1)  *  x^(1*2^2)
        //遍历指数的二进制，遇到1就乘
        while(exponent>0){
            if((exponent&1)==1){
                //当前二进制位数为1，乘进答案
                ret*=x;
            }
            x*=x;//对应x^(0*2^0)这种情况
            exponent>>=1;
        }
        return ret;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        double base=sc.nextDouble();
        int exponent=sc.nextInt();
        System.out.println(Power(base,exponent));

    }
}

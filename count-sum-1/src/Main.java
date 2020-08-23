public class Main {
    public int NumberOf1Between1AndN_Solution(int n) {
        int count=0;
        for(int i=1;i<=n;i=i*10){
            //乘子i遍历数字的位置，i分别为1，10，100，1000……
            //例如n=3141592
            //当i=100;
            //n就分为31415，和 92两个部分
            //当百位大于1时，百位的前缀为3141，则3142*100（3142是因为百位不为1，多出来100个）
            //百位为0时，3141*100
            //判断百位>1?假设百位为x
            //(x+8)/10=1,则x>1;(x+8)/10=0,则x<1;

            //i=1000;
            //n分为a=3141 b=592
            //若千位大于1时，假设千位的前缀为314，则315*1000，
            //但此处千位为1，则为314*1000
            //count=314*1000+(0到592中1的个数)

            //a+8
            //个位2 ，当个位取值1时，前面的六位数字可由0~314159组成，即314160种情况
            //十位9，当十位取值1时，前面的五位数字可由0~31415组成，十位之后的一位可由0~9组成，组合情况31416*10=314160种情况
            //百位5，当百位取值为1时，前面的四位数字可由0~3141组成，百位之后的两位可由0~99组成，组合情况为3142*100=314200种情况
            //千位1，千位取值即1，前面的三位数字可由0~314组成，但是当前面的值为314时，后面的三位只有0~592种情况(特殊情况)
            //314*1000+593=314593种情况
            //之所以补8，是因为当百位为0，则a/10==(a+8)/10，当百位>=2，补8会产生进位位，效果等同于(a/10+1)
            int a=n/i;
            int b=n%i;
            count+=(a+8)/10*i+(a%10==1?b+1:0);
        }
        return count;
    }
}
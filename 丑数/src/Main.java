public class Main {
    public static int GetUglyNumber_Solution(int index) {
        //丑数：(2^x)*(3^y)*(5^m)
        //x,y,m均大于等于0
        if(index<=0)return 0;
        //一个丑数乘以[2,3,5]之中任何一个，可构成新的丑数，
        if(index<7)return index;
        //从前往后丑数为，1，2，3，4（2*2），5，6（2*3），10（2*5）
        //小于7的丑数均是自己

        //2，3，5三个数需要交错遍历
        int p2=0;
        int p3=0;
        int p5=0;//p2,p3,p5表示指向三个潜在成为最小丑数的位置
        int[]num=new int[index];
        num[0]=1;
        //第一个丑数
        //num为存储丑数的数组
        //num[i]为第i个丑数
        for(int i=1;i<index;i++){
            num[i]=Math.min(num[p2]*2,Math.min(num[p3]*3,num[p5]*5));
            if(num[i]==num[p2]*2) p2++;
            if(num[i]==num[p3]*3) p3++;
            if(num[i]==num[p5]*5) p5++;
        }
        return num[index-1];
    }

    public static void main(String[] args) {
        System.out.println(GetUglyNumber_Solution(36));
    }
}
public class Main {
    /*
    1.状态定义：
    f(i)：以i下标结尾的最大连续子序列和（必须包含i下标对应的元素）
    2.状态转移
    f(i)=Max（array[i]+f(i-1)，array[i]）；//“连续”
    3.初始值
    f(0)=array[0];
    4.确立容器：dp[n]*/
    public static int FindGreatestSumOfSubArray(int[] array) {
        if(array==null||array.length==0){
            return 0;
        }
        int[]dp=new int[array.length];
        dp[0]=array[0];
        int Vualemax=dp[0];
        for(int i=1;i<array.length;i++){
            dp[i]=Math.max(dp[i-1]+array[i],array[i]);
            if(Vualemax<dp[i]){
                Vualemax=dp[i];
            }
        }
        return Vualemax;
    }

    public static int FindGreatestSumOfSubArray1(int[] array) {
       /*
        f(i)：以i下标结尾的最大连续子序列和（必须包含i下标对应的元素）
        for循环检测以i为下标连续子序列的和
        用if来判断本次添入，之前的和是否总体值大于0，有利于整体增大
        如果小于0表明此处添加不利于整体增大，抛弃之前所有值（题目说连续）
        */
        if(array==null||array.length==0){
            return 0;
        }
        int Vualemax=array[0];
        int temp=array[0];//保存累计到当前i下标的和

        for(int i=1;i<array.length;i++){
            if(temp>=0){
                //如果之前total累计的和>=0,说明当前数据+total，有利于整体增大
                temp+=array[i];
            }else{
                temp=array[i];//之前的值小于0，直接舍弃，从当前重新开始
            }

            if(Vualemax<temp){
                Vualemax=temp;
            }
        }
        return  Vualemax;
    }

    public static void main(String[] args) {
        int[]array={6,-3,-2,7,-15,1,2,2};
        System.out.println( FindGreatestSumOfSubArray(array));
        System.out.println( FindGreatestSumOfSubArray1(array));
    }
}

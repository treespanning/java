#include<stdio.h>
//判断一个数是几位数
int NUM(int n)
{
	int b = 0;
	while (n){
		b++;
		n /= 10;
	}
	return b;
}

//乘方
	int Power(int y, int b){
		int i, x;
		x = 1;
		for (i = 0; i < b; i++){
			x = (y % 10)*x;
		}
		return x;
	}
	void Nar(int num, int b){
		int sum = 0;
		int i;
		int y, x;
		y = num;
		for (i = 0; i<b; i++){
			x = Power(y, b);
			sum = sum + x;
			y /= 10;
		}
		//3、sum==i   printf("%d\n",i);
		if (sum == num){
			printf("%d\n", num);
		}
	}
	int main()
	{
		int i = 0;
		for (i = 1; i <= 999999; i++)
		{
			//1、求i为几位数
			int tmp = i;
			int count = 0;
			while (tmp != 0)
			{
				count++;
				tmp = tmp / 10;
			}
			tmp = i;
			//2、tmp的每一位求出  然后进行次方运算 
			Nar(tmp,count);
			//123   123%10=3   123/10%10=2     1  +  sum
			//pow(x,y);-->x^y       
		}
		system("pause");
		return 0;
	}




#include<stdio.h>
//�ж�һ�����Ǽ�λ��
int NUM(int n)
{
	int b = 0;
	while (n){
		b++;
		n /= 10;
	}
	return b;
}

//�˷�
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
		//3��sum==i   printf("%d\n",i);
		if (sum == num){
			printf("%d\n", num);
		}
	}
	int main()
	{
		int i = 0;
		for (i = 1; i <= 999999; i++)
		{
			//1����iΪ��λ��
			int tmp = i;
			int count = 0;
			while (tmp != 0)
			{
				count++;
				tmp = tmp / 10;
			}
			tmp = i;
			//2��tmp��ÿһλ���  Ȼ����дη����� 
			Nar(tmp,count);
			//123   123%10=3   123/10%10=2     1  +  sum
			//pow(x,y);-->x^y       
		}
		system("pause");
		return 0;
	}




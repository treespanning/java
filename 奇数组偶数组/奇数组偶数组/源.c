/*
1.��������ʹ����ȫ����λ��ż��ǰ�档

��Ŀ��
����һ���������飬ʵ��һ��������
�����������������ֵ�˳��ʹ�����������е�����λ�������ǰ�벿�֣�
����ż��λ������ĺ�벿�֡�

#include<stdio.h>
void adjustment(int arr[], int len)
{
	int i = 0;
	int j = len - 1;
	for (int i = 0, j = len - 1; i <= j; )
	{
		if (arr[i] % 2 == 1 && arr[j]%2==0)
			
		{
			i++;
			j--;
		}
		else if (arr[i] % 2 == 1 && arr[j] == 1)
		{
			i++;
		}
		else if (arr[i] % 2 == 0 && arr[j] % 2 == 1)
		{
			int temp = 0;
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i++;
			j--;
		}
	}
}
void show(int arr[], int  len)
{
	int i = 0;
	for (i = 0; i < len; i++)
	{
		printf("%d ", arr[i]);
	}
	printf("\n");
}
int main()
{
	int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	int len = sizeof(arr) / sizeof(arr[0]);
	adjustment(arr, len);
	show(arr, len);
	system("pause");
	return 0;
}













2.
//���Ͼ��� 
��һ����ά����.
�����ÿ�д������ǵ����ģ�ÿ�д��ϵ����ǵ�����.
�������������в���һ�������Ƿ���ڡ�
ʱ�临�Ӷ�С��O(N);

���飺
1 2 3
2 3 4
3 4 5


1 3 4
2 4 5
4 5 6

1 2 3
4 5 6
7 8 9
*/
#include<stdio.h>
#define _CRT_SECURE_NO_WARNINGS 1
int find(int arr[3][3], int N,int number)
{
	if (number<arr[0][0] || number>arr[N - 1][N - 1])
		return 0;
	for (int row = 0, col = N - 1; row <= N - 1, col >= 0;)
	{
		if (number > arr[row][col])
		{
			row++;
		}
		else if (number < arr[row][col])
		{
			col--;
		}
		else return 1;
	}
	return 0;
}
int main()
{
	int arr[3][3] = { { 1, 2, 3 } ,{2, 4, 5}, {4, 5, 6} };
	int findenumber = 0;
	scanf("%d", & findenumber);
	if (find(arr, 3, findenumber))
	{
		prinf("yes");
	}
	else
	{
		printf("No");
	}
	system("pause");
	return 0;
}
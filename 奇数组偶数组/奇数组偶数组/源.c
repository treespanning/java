/*
1.调整数组使奇数全部都位于偶数前面。

题目：
输入一个整数数组，实现一个函数，
来调整该数组中数字的顺序使得数组中所有的奇数位于数组的前半部分，
所有偶数位于数组的后半部分。

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
//杨氏矩阵 
有一个二维数组.
数组的每行从左到右是递增的，每列从上到下是递增的.
在这样的数组中查找一个数字是否存在。
时间复杂度小于O(N);

数组：
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

#define _CRT_SECURE_NO_WARNINGS 1 
#include<stdio.h>
int main()
{
	char a[5] = {0};
	char i[5] = {0};
	int j,k,m;
	int flg = 1;
	printf("请输入一个5位的数作为密码：\n");
    scanf("%s", &a[m]);
	for (j = 3; j > 0; j--)
	{
		printf("你还有%d次机会输入密码\n", j);
		printf("请输入密码：\n");
		scanf("%s", &i[m]);
		for (k = 0; k < 5; k++)
		{
			if (a[k]!=i[k])
			{
				flg = 0;
				printf("密码错误\n");
				break;
			}
		}
		if (flg == 1)
		{
			printf("登陆成功！！！\n");
			break;
		}
	}
	if (flg == 0)
	{
		printf("退出程序\n");
	}
	system("pause");
	return 0;
}
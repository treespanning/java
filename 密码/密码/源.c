
#define _CRT_SECURE_NO_WARNINGS 1 
#include<stdio.h>
int main()
{
	char a[5] = {0};
	char i[5] = {0};
	int j,k,m;
	int flg = 1;
	printf("������һ��5λ������Ϊ���룺\n");
    scanf("%s", &a[m]);
	for (j = 3; j > 0; j--)
	{
		printf("�㻹��%d�λ�����������\n", j);
		printf("���������룺\n");
		scanf("%s", &i[m]);
		for (k = 0; k < 5; k++)
		{
			if (a[k]!=i[k])
			{
				flg = 0;
				printf("�������\n");
				break;
			}
		}
		if (flg == 1)
		{
			printf("��½�ɹ�������\n");
			break;
		}
	}
	if (flg == 0)
	{
		printf("�˳�����\n");
	}
	system("pause");
	return 0;
}
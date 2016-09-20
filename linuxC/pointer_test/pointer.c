/*************************************************************************
    > File Name: pointer.c
    > Author: Younix
    > Mail: foreveryounix@gmail.com 
    > Created Time: 2016年09月20日 星期二 10时00分54秒

	二维数组赋值给一维数组	 段错误问题 test
 ************************************************************************/

#include <stdio.h>
#include <stdlib.h>

int main(){

	int client_matrix[2][2] = {
		{	1,2,	},
		{	3,4,	}
	};
	int* array = (int*)malloc(5);
	int* matrix = array;
	int i ;
	for(i = 0; i < 2; i++)
	{
		*array = client_matrix[0][i];
		array++;
	}

	i = 2;
	while(i--){
		printf("%d ",*matrix++);
	}

    return 0;
}

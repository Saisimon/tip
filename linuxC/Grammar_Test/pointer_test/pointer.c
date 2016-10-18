/*************************************************************************
    > File Name: pointer.c
    > Author: Younix
    > Mail: foreveryounix@gmail.com 
    > Created Time: 2016年09月20日 星期二 10时00分54秒

	二维数组赋值给一维数组	 段错误问题 test
 ************************************************************************/

#include <stdio.h>
#include <stdlib.h>

//#define TEST_1

int main(){

	int _matrix[2][2] = {
		{	1,2,	},
		{	3,4,	}
	};
#ifdef TEST_1
	int** client_matrix = (int**)_matrix;	//强制将 二维数组指针 转为 指向指针的指针，错误
	//二维数组指针 只是一级指针，但是 二级指针 是 指向指针的指针
#else
	int* client_matrix = (int*)_matrix;
#endif

	int* array = (int*)malloc(5);
	int* matrix = array;
	int i ;
	int temp;
	for(i = 0; i < 4; i++)
	{
#ifdef TEST_1
//		*array = client_matrix[0][i]; // it works
//		*array = *(*(array + x) + y); 
		*array = *(*(client_matrix + 0) + i);	//seg faluts
//		*array++ =  *(*(_matrix + 0) + i); //it works
#else		
		*array = *(client_matrix + i);
#endif

	}

	i = 4;
	while(i--){
		printf("%d ",*matrix++);
	}

    return 0;
}

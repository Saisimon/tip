/*************************************************************************
    > File Name: main.c
    > Author: shift
    > Mail: open_shift@163.com 
    > Created Time: 2015年03月03日 星期二 20时30分38秒
 ************************************************************************/

#include<stdio.h>

#include "add.h"
#include "sub.h"

float sub(float x, float y)
{
	return x-y;
}

int main()
{
	int a = 10, b = 12;
	float x = 1.234f, y = 9.876f;

	printf("float x-y IS:%f\n", sub_float(x, y));
	printf("int a+b IS:%d\n", add_int(a, b));
	printf("int a-b IS:%d\n", sub_int(a, b));
	printf("float x+y IS:%f\n", add_float(x, y));
	printf("float x-y IS:%f\n", sub_float(x, y));
	printf("float x-y IS:%f\n", sub(x, y));

	return 0;
}

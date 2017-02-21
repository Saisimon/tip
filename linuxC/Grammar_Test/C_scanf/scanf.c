/*************************************************************************
    > File Name: scanf.c
    > Author: Younix
    > Mail: foreveryounix@gmail.com 
    > Created Time: Mon 20 Feb 2017 03:06:45 PM CST
 ************************************************************************/

#include <stdio.h>


int main(){

	int a;
	float b,c;

	scanf("%2d%3f%4f", &a,&b,&c);

	printf("\na=%d,b=%d,c=%f\n",a,b,c);
	printf("\na=%d,b=%f,c=%f\n",a,b,c);

	return 0;
}

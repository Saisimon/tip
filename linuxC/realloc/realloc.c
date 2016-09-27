/*************************************************************************
    > File Name: realloc.c
    > Author: Younix
    > Mail: foreveryounix@gmail.com 
    > Created Time: 2016年09月27日 星期二 20时37分07秒
 ************************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int main(){
	int size = 10;
	char* string = malloc(size * sizeof(char));
	char** elements = &string;

	memset(string , 51 , size);

	printf("string 字符串是     = %s\n",string);
	printf("string 字符串首字符 = %c\n",*string);
	printf("string 字符串首地址 = %p\n",string);
	printf("elements 字符串数组首地址是 %p\n",elements);
	printf("通过 elements 打印字符串数组第一个元素 %s\n",*elements);
	printf("通过 elements 打印字符串数组第一个元素首地址 %p\n",elements);
	printf("通过 elements 打印字符串数组第二个元素首地址 %p\n",(elements+1));//比上面多了8 //因为一个指针是 8 字节

	

	
	elements = NULL;
	free(string);

    return 0;
}

/*************************************************************************
    > File Name: get_memory.c
    > Author: Younix
    > Mail: foreveryounix@gmail.com 
    > Created Time: 2016年09月20日 星期二 20时07分28秒


	// 这是一段错误的代码，
	// 错在返回了局部变量
	//
	// 但是非常有趣的是， 25 行和 26行
 ************************************************************************/

#include <stdio.h>

char* get_memory()
{
	char p[] = "hello world";
	return p;
}

int main(void){
	char* str = NULL;
	str = get_memory();
	printf("%s\n",str);		//字符串是随机的
	printf("%c\n",*str);	//字符却是确定的为 h
    return 0;
}

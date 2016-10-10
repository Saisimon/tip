#include <stdio.h>
#include <stdarg.h>

/*
	There is Three Steps if we want to use Variable Parameter:
	1. create a Pointer(arg_ptr)
		va_list arg_ptr 
	2. set the arg_ptr point at the address of first Parameter(cnt)
		va_start(arg_ptr, cnt);
	3. set the arg_ptr point at the address of next Parameter(...)
		va_arg(arg_ptr, int);
	4. set the arg_ptr as 0
		va_end(arg_ptr);
*/

void ar_cnt(int cnt, ...);

void ar_cnt(int cnt, ...)
{
	int value = 0;
	int i = 0;

	va_list arg_ptr;

	printf("we have %d number in\n",cnt);		//8
	va_start(arg_ptr, cnt);	//获取第一个参数的首地址 arg_ptr

	for(i = 0; i < cnt; i++)
	{
		value = va_arg(arg_ptr,int); //获取下一个参数的地址
		printf("position %d = %d\n",i+1,value);
	}
	va_end(arg_ptr);
}

int main(int argc,char* argv[])
{
	//ar_cnt(int cnt, ...);
	ar_cnt(8,3,9,3,6,0,7,4,8,7);
	return 0;
}
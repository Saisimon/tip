/*************************************************************************
    > File Name: ex25.c
    > Author: Younix
    > Mail: foreveryounix@gmail.com 
    > Created Time: 2016年10月10日 星期一 11时27分09秒
 ************************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>	//让函数接受可变参数
#include "../dbg.h"

#define MAX_DATA 100

int read_string(char** out_string, int max_buffer)
{
	*out_string = calloc(1, max_buffer + 1);
	check_mem(*out_string);

	char *result = fgets(*out_string, max_buffer, stdin);
	check(result != NULL, "Input error.");

	return 0;

error:
	if(*out_string)
		free(*out_string);
	*out_string = NULL;
	return -1;
}

int read_int(int *out_int)
{
	char* input = NULL;
	int rc = read_string(&input, MAX_DATA);
	check(rc == 0, "Failed to read number.");

	*out_int = atoi(input);

	free(input);
	return 0;

error:
	if(input) 
		free(intput);
	return -1;
}

/*
* void va_start(va_list arg_ptr, prev_param);
* 功能: 以固定参数地址为起点 确定变参的内存起始地址，获取第一个参数的首地址
* 返回值：无
* 
* type va_arg(va_list arg_ptr, type);
* 功能： 获取下一个参数的地址
* 返回值：根据传入参数类型决定返回值类型
*
* void va_end(va_list arg_ptr);
* 功能： 将 arg_ptr 置 0
* 返回值： 无
*/
//read_scan 变参函数 eg. read_scan("size=%d",size,)
int read_scan(const char *fmt, ...){  //... 表示这个函数在 fmt 参数之后接受任何数量的参数
	int i = 0;
	int rc = 0;
	int *out_int = NULL;
	char *out_char = NULL;
	char **out_string = NULL;

	int max_buffer = 0;

  	va_list argp;
    va_start(argp, fmt);	//令 argp 指向 fmt

    for(i = 0; fmt[i] != '\0'; i++) {
        if(fmt[i] == '%') {
            i++;
            switch(fmt[i]) {
                case '\0':
                    sentinel("Invalid format, you ended with %%.");
                    break;

                case 'd':
                    out_int = va_arg(argp, int *);
                    rc = read_int(out_int);
                    check(rc == 0, "Failed to read int.");
                    break;

                case 'c':
                    out_char = va_arg(argp, char *);
                    *out_char = fgetc(stdin);
                    break;

                case 's':
                    max_buffer = va_arg(argp, int);
                    out_string = va_arg(argp, char **);
                    rc = read_string(out_string, max_buffer);
                    check(rc == 0, "Failed to read string.");
                    break;

                default:
                    sentinel("Invalid format.");
            }
        } else {
            fgetc(stdin);
        }
        check(!feof(stdin) && !ferror(stdin), "Input error.");
    }

    va_end(argp);
    return 0;

error:
    va_end(argp);
    return -1;
}



int main(int argc, char *argv[])
{
    char *first_name = NULL;
    char initial = ' ';
    char *last_name = NULL;
    int age = 0;

    printf("What's your first name? ");
    int rc = read_scan("%s", MAX_DATA, &first_name);
    check(rc == 0, "Failed first name.");

    printf("What's your initial? ");
    rc = read_scan("%c\n", &initial);
    check(rc == 0, "Failed initial.");

    printf("What's your last name? ");
    rc = read_scan("%s", MAX_DATA, &last_name);
    check(rc == 0, "Failed last name.");

    printf("How old are you? ");
    rc = read_scan("%d", &age);

    printf("---- RESULTS ----\n");
    printf("First Name: %s", first_name);
    printf("Initial: '%c'\n", initial);
    printf("Last Name: %s", last_name);
    printf("Age: %d\n", age);

    free(first_name);
    free(last_name);
    return 0;
error:
    return -1;
}
/*************************************************************************
    > File Name: ex18.c
    > Author: Younix
    > Mail: foreveryounix@gmail.com 
    > Created Time: 2016年09月27日 星期一 10时04分48秒
 ************************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>

void die(const char *message)
{
	if(errno){
		perror(message);
	}
	else {
		printf("ERROR: %s\n", message);
	}
	exit(1);
}

//typedef comparge_cb ——> int func(()
typedef int (*compare_cb)(int a,int b);

// 
int *bubble_sort(int *numbers,int count,compare_cb cmp)
{
	int temp = 0;
	int i = 0;
	int j = 0;
	int *target = malloc(count * sizeof(int));

	if(!target)
		die("Memory error");

	memcpy(target, numbers, count * sizeof(int));

	for(i = 0; i < count; i++)
	{
		for(j = 0; j < count - 1; j++)	
		{
			if(cmp(target[j], target[j+1]) > 0)
			{
				temp = target[j+1];
				target[j+1] = target[j];
				target[j] = temp;
			}
		}
	}
	return target;
}

int sorted_order(int a,int b)
{
	return a - b;
}

int reverse_order(int a, int b)
{
	return b - a;
}
int strange_order(int a ,int b )
{
	if(a == 0|| b == 0)
	{
		return 0;
	}else 
	{
		return a % b;
	}
}

/*
 *	test code
 */
void test_sorting(int *numbers, int count,compare_cb cmp)
{
	int i = 0;
	int *sorted = bubble_sort(numbers, count, cmp)	;

	if(!sorted) die("Failed to sort as requested");

	for(i = 0; i < count; i++)
	{
		printf("%d ",sorted[i]);
	}
	printf("\n");
	
	free(sorted);
}

int main(int argc, char* argv[])
{
	if(argc < 2) 
		die("USAGE: ex18 4 3 1 5 6");
	
	int count = argc - 1;
	int i = 0;
	char **inputs = argv + 1;
	int *numbers = malloc(count * sizeof(int));

	if(!numbers)
		die("Memory error");

	for(i = 0; i < count ; i++)
	{
		numbers[i] = atoi(inputs[i]);
	}

	test_sorting(numbers, count, sorted_order);
	test_sorting(numbers, count, reverse_order);
	//test_sorting(numbers, count, strange_order);
	test_sorting(numbers, count, NULL);

	free(numbers);

	return 0;
}

/*
1. 用十六进制编辑器打开ex18，接着找到函数起始处的十六进制代码序列，看看是否能在原始程序中找到函数。

2. 在你的十六进制编辑器中找到更多随机出现的东西并修改它们。重新运行你的程序看看发生了什么。字符串是你最容易修改的东西。

3. 将错误的函数传给compare_cb，并看看C编辑器会报告什么错误。
将NULL传给它，看看程序中会发生什么。然后运行Valgrind来看看它会报告什么。
编写另一个排序算法，修改test_sorting使它接收任意的排序函数和排序函数的比较回调。并使用它来测试两种排序算法。
* 1. 
*/





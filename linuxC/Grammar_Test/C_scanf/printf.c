/*************************************************************************
    > File Name: printf.c
    > Author: Younix
    > Mail: foreveryounix@gmail.com 
    > Created Time: Mon 20 Feb 2017 03:10:47 PM CST
 ************************************************************************/

#include <stdio.h>


int main(){
    
    int a = 98;
    float b = 765.000;
    float c = 4321.000;

    printf("a=%d,b=%d,c=%f\n",a,b,c);
    printf("a=%d,b=%f,c=%f\n",a,b,c);

    return 0;
}

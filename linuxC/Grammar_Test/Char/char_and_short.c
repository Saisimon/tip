/*************************************************************************
    > File Name: char_and_short.c
    > Author: Younix
    > Mail: foreveryounix@gmail.com 
    > Created Time: Sat 04 Mar 2017 02:10:22 PM CST
 ************************************************************************/

#include <stdio.h>
//#include <unistd.h>

union Test
{
    char a[4];
    short b;
};


int main(){  
    Test test;
    test.a[0] = 256;
    test.a[1] = 255;
    test.a[2] = 254;
    test.a[3] = 253;

    printf("%d \n",test.b);

    return 0;
}

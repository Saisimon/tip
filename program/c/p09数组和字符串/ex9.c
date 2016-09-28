#include <stdio.h>

int main(int argc, char *argv[]) {
    int number[4] = {0};
    char name[4] = {'a'};
    printf("numbers : %d, %d, %d, %d.\n", number[0], number[1], number[2], number[3]);
    printf("name char : %c, %c, %c, %c.\n", name[0], name[1], name[2], name[3]);
    printf("name is %s.\n", name);
    number[0] = 1;
    number[1] = 2;
    number[2] = 3;
    number[3] = 4;
    name[0] = 'S';
    name[1] = 'a';
    name[2] = 'i';
    name[3] = '\0';
    printf("numbers : %d, %d, %d, %d.\n", number[0], number[1], number[2], number[3]);
    printf("name char : %c, %c, %c, %c.\n", name[0], name[1], name[2], name[3]);
    printf("name is %s.\n", name);
    char *another = "Sai";
    printf("another char : %c, %c, %c, %c.\n", another[0], another[1], another[2], another[3]);
    printf("another is %s.\n", another);
    number[0] = 'A';
    number[1] = 'B';
    number[2] = 'D';
    number[3] = 'E';
    printf("numbers : %c, %c, %c, %c.\n", number[0], number[1], number[2], number[3]);
    name[0] = 1;
    name[1] = 2;
    name[2] = 3;
    name[3] = 4;
    printf("name char : %d, %d, %d, %d.\n", name[0], name[1], name[2], name[3]);
    printf("name is %s.\n", name);
    return 0;
}

#include <stdio.h>

int main(int argc, char *argv[]) {
    int areas[] = {10, 12, 13, 14, 20};
    char name[] = "Saisimon";
    char full_name[] = {'S', 'a', 'i', 's', 'i', 'm', 'o', 'n', '\0'};

    printf("The size of int is %ld\n", sizeof(int));
    printf("The size of areas is %ld\n", sizeof(areas));
    printf("The number of ints in areas is %ld\n", sizeof(areas) / sizeof(int));
    printf("The first and sencond area are %ld and %ld\n", areas[0], areas[1]);
    printf("The size of char is %ld\n", sizeof(char));
    printf("The size of name is %ld\n", sizeof(name));
    printf("The number of chars is %ld\n", sizeof(name) / sizeof(char));
    printf("The size of full_name is %ld\n", sizeof(full_name));
    printf("The number of chars is %ld\n", sizeof(full_name) / sizeof(char));
    return 0;
}

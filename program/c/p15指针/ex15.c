#include <stdio.h>

void printArray(int *cur_age, char **cur_name, int count);

int main(int argc, char *argv[]) {
    int ages[] = {18,23,31,11,79};
    char *names[] = {
        "A", "B", "C", "D", "E"
    };
    int count = sizeof(ages) / sizeof(int);
    int i = 0;
    printf("first way using indexing : \n");
    for (i = 0; i < count; i++) {
        printf("%s has %d years old.\n", names[i], ages[i]);
    }
    printf("===========================================================\n");
    int *cur_age = ages;
    char **cur_name = names;
    printf("second way using pointers : \n");
    for (i = 0; i < count; i++) {
        printf("%s has %d years old.\n", *(cur_name + i), *(cur_age + i));
    }
    printf("===========================================================\n");
    printf("third way, pointers are just arrays : \n");
    for (i = 0; i < count; i++) {
        printf("%s has %d years old.\n", cur_name[i], cur_age[i]);
    }
    printf("===========================================================\n");
    printArray(cur_age, cur_name, count);
    printf("fourth way with pointers in a stupid complex way : \n");
    for (cur_name = names, cur_age = ages; (cur_age - ages) < count; cur_name++, cur_age++) {
        printf("%s has %d years old.\n", *cur_name, *cur_age);
    }
    printf("===========================================================\n");
    for (i = 0; i < argc; i++) {
        printf("arg %d : %s and adress is %p.\n", i, *(argv + i), &argv + i);
    }
    printf("===========================================================\n");
    cur_name = names, cur_age = ages;
    while (cur_age - ages < count) {
        printf("%s has %d years old.\n", *cur_name, *cur_age);
        cur_age++;
        cur_name++;
    }
    printf("===========================================================\n");
    return 0;
}

void printArray(int *cur_age, char **cur_name, int count) {
    for (int i = 0; i < count; i++) {
        printf("%s has %d years old.\n", *(cur_name + i), *(cur_age + i));
    }
    printf("===========================================================\n");
}
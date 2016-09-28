#include <stdio.h>
#include <ctype.h>
#include <string.h>

void print_letters(int arg_len, char arg[]);
//int can_print(char ch);

void print_arguments(int argc, char *argv[]) {
    for (int i = 0; i < argc; i++) {
        print_letters(strlen(argv[i]), argv[i]);
    }
}

void print_letters(int arg_len, char arg[]) {
    for (int i = 0; i < arg_len; i++) {
        char ch = arg[i];
//        if (can_print(ch)) {
        if (isalpha(ch) || isblank(ch)) {
            printf("'%c' == %d", ch, ch);
        }
    }
    printf("\n");
}

//int can_print(char ch) {
//    return isalpha(ch) || isblank(ch);
//}

int main(int argc, char *argv[]) {
    print_arguments(argc, argv);
    return 0;
}

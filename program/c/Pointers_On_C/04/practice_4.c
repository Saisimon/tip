#include <stdio.h>
#include <stdlib.h>

void copy_n(char dst[], char src[], int n) {
    int len = sizeof(dst);
    printf("len : %d\n", len);
    for (int i = 0; i < len; i++) {
        if (i <= n) {
            dst[i] = src[i];
        } else {
            dst[i] = '\0';
        }
    }
}

int main(int argc, char *argv[]) {
    char src[] = "hahaha";
    char dst[10];
    copy_n(dst, src, 9);
    for (int i = 0; i < 10; i++) {
        printf("%c ", dst[i]);
    }
    return 0;
}

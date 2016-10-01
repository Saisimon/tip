#include <stdio.h>
#include <stdlib.h>

int check(int a, int b, int c) {
    if (a + b > c && abs(a - b) < c) {
        return 1;
    }
    return 0;
}

int main(int argc, char *argv[]) {
    if (argc != 4) {
        printf("请输入3个数字！\n");
        exit(1);
    }
    int a, b, c;
    a = atoi(argv[1]);
    b = atoi(argv[2]);
    c = atoi(argv[3]);
    if (check(a,b,c) && check(a,c,b) && check(b,c,a)) {
        if (a == b && b == c) {
            printf("%d, %d, %d组成等边三角形。\n", a, b, c);
        } else if (a != b && b != c && c != a) {
            printf("%d, %d, %d组成不等边三角形。\n", a, b, c);
        } else {
            printf("%d, %d, %d组成等腰三角形。\n", a, b, c);
        }
    } else {
        printf("%d, %d, %d无法组成三角形。\n", a, b, c);
        exit(1);
    }
    return 0;
}

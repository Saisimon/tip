#include <stdio.h>
#include <stdlib.h>

int main(void) {
    int i;
    int flag;
    for (i = 1; i <= 100; i++) {
        flag = 1;
        for (int j = 2; j < i; j++) {
            if (i % j == 0) {
                flag = 0;
                break;
            }
        }
        if (flag) {
            printf("%d ", i);
        }
    }
    return 0;
}

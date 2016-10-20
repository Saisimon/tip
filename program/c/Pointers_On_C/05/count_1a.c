#include <stdio.h>
#include <stdlib.h>

int count_one_bits(unsigned value) {
    int ones;
    for (ones = 0; value != 0; value = value >> 1) {
        if (value % 2 != 0) {
            ones++;
        }
    }
    return ones;
}

int main(int argc, char *argv[]) {
    if (argc < 2) {
        printf("请输入一个参数！");
        exit(1);
    }
    int input = atoi(argv[1]);
    int ones = count_one_bits(input);
    printf("ones : %d\n", ones);
    return 0;
}

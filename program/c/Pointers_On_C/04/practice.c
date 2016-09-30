#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
    if (argc < 2) {
        printf("需要一个参数作为开方数");
        exit(1);
    }
    double input = atof(argv[1]);
    double res = 1;
    if (input < 0) {
        printf("开方数必须为正数");
        exit(1);
    } else if (input == 0) {
        res = 0;
    } else {
        while(res != (res + input / res) / 2) {
            res = (res + input / res) / 2;
        }
    }
    printf("%lf 开平方后为 %lf\n", input, res);
    return 0;
}


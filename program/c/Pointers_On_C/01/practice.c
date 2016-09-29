#include <stdio.h>
#include <string.h>

int main(int argc, char *argv[]) {
    /* 1 */
    printf("Hello World\n");

    /* 2 */
    char *input;
    scanf("%s", input);
    printf("您输入的为:%s\n", input);

    /* 3 */
    char *secord_input;
    signed char checksum = -1;
    scanf("%s", secord_input);
    int len = strlen(secord_input);
    printf("secord_input : %s.\n", secord_input);
    printf("len : %d.\n", len);
//    for (int i = 0; i < len; i++) {
//        checksum += input[i];
//    }
    printf("checksum : %d.\n", checksum);
    return 0;
}

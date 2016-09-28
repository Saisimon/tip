#include <stdio.h>

int main(int argc, char *argv[]) {
    if (argc == 1) {
        printf("No arg");
    } else if (argc > 1 && argc < 4) {
        int i = 1;
        printf("You arg : ");
        while(i < argc) {
            printf("%s", argv[i]);
            i++;
        }
        printf("\n");
    } else {
        printf("Boom!");
    }
    return 0;
}

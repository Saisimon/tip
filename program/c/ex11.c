#include <stdio.h>

int main(int argc, char *argv[]) {
    int i;
    char *states[] = {
        "Beijing", "Shanghai", "Guangzhou", "Shenzhen", NULL
    };
    while (i < argc) {
        printf("arg %d : %s\n", i , argv[i]);
        i++;
    }
    int num_states = 5;
    i = 0;
    while (i < argc && i < num_states) {
        states[i] = argv[i];
        i++;
    }
    i = 0;
    while (i < num_states) {
        printf("state %d : %s\n", i, states[i]);
        i++;
    }
    return 0;
}

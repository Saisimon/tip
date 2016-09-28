#include <stdio.h>

int main(int argc, char *argv[]) {
    int i;
    char *states[] = {
        "Beijing", "Shanghai", "Guangzhou", "Shenzhen", NULL
    };
    argv[0] = states[0];
    for (i = 0; i < argc; i++) {
        printf("arg %d : %s\n", i, argv[i]);
    }
    states[1] = argv[1];
    int num_states = 5;
    for (i = 0; i < num_states; i++) {
        printf("state %d : %s\n", i, states[i]);
    }
    return 0;
}

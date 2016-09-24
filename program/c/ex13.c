#include <stdio.h>

int main(int argc, char *argv[]) {
    if (argc != 2) {
        printf("Arg Error!\n");
        return 1;
    }
    char arg = argv[1][0];
    switch (arg) {
    case 'h':
    case 'H':
        printf("Help");
    break;
    case 'v':
    case 'V':
        printf("Version");
    break;
    default:
        printf("No such arg");
        break;
    }
    return 0;
}

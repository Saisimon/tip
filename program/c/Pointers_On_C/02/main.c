#include <stdio.h>
#include <stdlib.h>

int increment(int);
int negete(int);

int main(int argc, char *argv[]){
    int res;
    res = increment(10);
    printf("increment res : %d.\n", res);
    res = negete(10);
    printf("negete res : %d.\n", res);
    res = increment(0);
    printf("increment res : %d.\n", res);
    res = negete(0);
    printf("negete res : %d.\n", res);
    res = increment(-10);
    printf("increment res : %d.\n", res);
    res = negete(-10);
    printf("negete res : %d.\n", res);
    return 0;
}

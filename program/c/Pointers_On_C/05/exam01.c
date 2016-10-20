#include <stdio.h>
#include <stdlib.h>

int func (void) {
    static int counter = 1;
    return ++counter;
}

int main(int argc, char *argv[]) {
    int answer;
    answer = func() - func() * func();
    printf("%d\n", answer);
    int tmp = 1;
    int answer2 = (++tmp) - (++tmp) * (++tmp);
    printf("%d\n", answer2);
    return 0;
}

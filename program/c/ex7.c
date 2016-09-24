#include <stdio.h>

int main(int argv, char *argc[]) {
    int bugs = 100;
    double bug_rate = 1.2;
    printf("You have %d bugs at the imaginary rate of %f.\n", bugs, bug_rate);

    long universe_of_defects = 1L * 1024L * 1024L * 1024L;
    printf("The entire universe has %ld bugs .\n", universe_of_defects);

    double expected_bugs = bugs * bug_rate;
    printf("You are expected to have %f bugs.\n", expected_bugs);

    double part_of_universe = expected_bugs / universe_of_defects;
    printf("This is a only %e portion of the universe.\n", part_of_universe);

    char nul_byte = '\0';
    printf("\\0 is %c.\n", nul_byte);
    int care_percentage = bugs * nul_byte;
    printf("which mean you should care %d%%.\n", care_percentage);

    return 0;
}
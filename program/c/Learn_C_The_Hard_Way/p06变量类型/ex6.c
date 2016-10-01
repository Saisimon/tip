#include <stdio.h>

int main(int argc, char *argv[]) {
    int distance = 100;
    float power = 2.345f;
    double super_power = 56789.123;
    char inital = 'A';
    char first_name[] = "Saisimon";
    int hex = 0xff;
    int oct = 077;
    char empty[] = "";
    
    printf("You have %d miles away!\n", distance);
    printf("Power is %f.\n", power);
    printf("Super power is %f.\n", super_power);
    printf("The inital is %c.\n", inital);
    printf("My name is %s.\n", first_name);
    printf("Hex Number 0xff is %d.\n", hex);
    printf("Oct Number 077 is %d.\n", oct);
    printf("Empty string is \"%s\". \\t is \"\t\".\n", empty);
    return 0;
}
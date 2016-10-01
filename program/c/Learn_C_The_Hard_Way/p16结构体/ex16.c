#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <string.h>

struct Person {
    char *name;
    int age;
    int height;
    int weight;
};

struct Person *Person_create(char *name, int age, int height, int weight) {
    struct Person *who = malloc(sizeof(struct Person));
    assert(who != NULL);
    who -> name = strdup(name);
//    *who.name = strdup(name);
    who -> age = age;
    who -> height = height;
    who -> weight = weight;
    return who;
}

void Person_detory(struct Person *who) {
    assert(who != NULL);
    free(who -> name);
    free(who);
}

void Person_print(struct Person *who) {
    printf("Name : %s\n", who -> name);
    printf("\tAge : %d\n", who -> age);
    printf("\tHeight : %d\n", who -> height);
    printf("\tWeight : %d\n", who -> weight);
}

int main(int argc, char *argv[]) {
    struct Person *saisimon = Person_create("Saisimon", 23, 180, 190);
    struct Person *younix = Person_create("Younix", 23, 172, 120);
    printf("saisimon in memory is %p.\n", saisimon);
    Person_print(saisimon);
    printf("younix in memory is %p.\n", younix);
    Person_print(younix);

    saisimon -> age += 5;
    saisimon -> height += 5;
    saisimon -> weight -= 20;
    Person_print(saisimon);

    Person_detory(saisimon);
    Person_detory(younix);
    return 0;
}

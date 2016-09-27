/*
  Author : Saisimon
 */
#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>

struct Stack {
    int array_length;
    int element_count;
    char **elements;
};

void print(struct Stack *stack);

char *peek(struct Stack *stack) {
    int element_count = stack -> element_count;
    char **elements = stack -> elements;
    if (element_count == 0) {
        perror("stack is empty");
    }
    return elements[element_count - 1];
}

char *pop(struct Stack *stack) {
    int element_count = stack -> element_count;
    char **elements = stack -> elements;
    char *element = peek(stack);
    stack -> element_count = element_count - 1;
    stack -> elements[element_count - 1] = NULL;
    return element;
}

void push(struct Stack *stack, char *element) {
    int element_count = stack -> element_count;
    char **elements = stack -> elements;
    if (element_count + 1 - stack -> array_length > 0) {
        printf("too long.\n");
        printf("%s\n", element);
        print(stack);
        stack -> array_length *= 2;
        exit(1);
//        stack -> elements = realloc(stack -> elements, 2 * size * sizeof(char *));
    }
    stack -> elements[element_count] = element;
    stack -> element_count = element_count + 1;
}

int empty(struct Stack *stack) {
    return stack -> element_count == 0;
}

int size(struct Stack *stack) {
    return stack -> element_count;
}

void print(struct Stack *stack) {
    int len = size(stack);
    printf("length : %d\n", len);
    for (int i = 0; i < len; i++) {
        printf("%s", stack -> elements[i]);
    }
    printf("\n");
}

void init(struct Stack *stack, int init_size) {
    stack -> elements = malloc(init_size * sizeof(char *));
    stack -> array_length = init_size;
}

void detory(struct Stack *stack) {
    if (stack) {
        if (stack -> elements) {
            free(stack -> elements);
        }
        free(stack);
    }
}

int main(int argc, char *argv[]) {
    struct Stack *stack = malloc(sizeof(struct Stack));
    int stack_size = 10;
    if (argc > 1) {
        stack_size = atoi(argv[1]);
    }
    printf("%d\n", stack_size);
    init(stack, stack_size);
    push(stack, "a");
    push(stack, "b");
    push(stack, "c");
    push(stack, "d");
    push(stack, "e");
    push(stack, "f");
    print(stack);
    char* element = pop(stack);
    printf("pop : %s.\n", element);
    print(stack);
    element = peek(stack);
    printf("peek : %s.\n", element);
    print(stack);
    element = pop(stack);
    printf("pop : %s.\n", element);
    print(stack);
    element = pop(stack);
    printf("pop : %s.\n", element);
    print(stack);
    push(stack, "A");
    push(stack, "B");
    push(stack, "C");
    print(stack);
    detory(stack);
    return 0;
}

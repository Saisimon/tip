#include <stdio.h>
#include <ctype.h>
#include "dbg.h"

int print_a_message(const char *msg)
{
    printf("A STRING: %s\n", msg);

    return 0;
}


//int uppercase(const char *msg)
int uppercase(const char *msg,int msg_len)
{
    int i = 0;

    for(i = 0; i < msg_len; i++) {
        printf("%c", toupper(msg[i]));
    }

    printf("\n");

    return 0;
}

int lowercase(const char *msg,int msg_len)
{
    int i = 0;

    for(i = 0; i < msg_len; i++) {
        printf("%c", tolower(msg[i]));
    }

    printf("\n");

    return 0;
}
/*
int fail_on_purpose(const char *msg)
{
    return 1;
}*/

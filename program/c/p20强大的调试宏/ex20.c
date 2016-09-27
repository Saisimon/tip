#include "dbg.h"
#include <stdlib.h>
#include <stdio.h>


void test_debug()
{
    // notice you don't need the \n
    debug("I have Brown Hair.");

    // passing in arguments like printf
    debug("I am %d years old.", 37);
}

void test_log_err()
{
    log_err("I believe everything is broken.");
    log_err("There are %d problems in %s.", 0, "space");
}

void test_log_warn()
{
    log_warn("You can safely ignore this.");
    log_warn("Maybe consider looking at: %s.", "/etc/passwd");
}

void test_log_info()
{
    log_info("Well I did something mundane.");
    log_info("It happened %f times today.", 1.3f);
}

int test_check(char *file_name)
{
    FILE *input = NULL;
    char *block = NULL;

    block = malloc(100);
    check_mem(block); // should work

    input = fopen(file_name,"r");
    check(input, "Failed to open %s.", file_name);

    free(block);
    fclose(input);
    return 0;

error:
    if(block) free(block);
    if(input) fclose(input);
    return -1;
}

int test_sentinel(int code)
{
    char *temp = malloc(100);
    check_mem(temp);

    switch(code) {
        case 1:
            log_info("It worked.");
            break;
        default:
            sentinel("I shouldn't run.（sentinel可以放在函数的任何不应该执行的地方，比如 switch 的 default 语句中，它会打印错误信息并且跳到error:标签）");
    }

    free(temp);
    return 0;

error:
    if(temp) free(temp);
    return -1;
}

int test_check_mem()
{
    char *test = NULL;
    check_mem(test);

    free(test);
    return 1;

error:
    return -1;
}

int test_check_debug()
{
    int i = 0;
    check_debug(i != 0, "Oops, I was 0.");

    return 0;
error:
    return -1;
}

int main(int argc, char *argv[])
{
    check(argc == 2, "Need an argument.");

    test_debug();
    test_log_err();
    test_log_warn();
    test_log_info();

    log_err("现在开始检查 ex20.c 是否存在");
    check(test_check("ex20.c") == 0, "failed with ex20.c");
    log_err("现在开始检查 文件 argv[1] 是否存在");
    check(test_check(argv[1]) == -1, "failed with argv");

    log_err("现在开始测试 sentinel 是否可以进入正确分支");
    check(test_sentinel(1) == 0, "test_sentinel failed.");
    log_err("现在开始检查 sentinel 进入switch错误分支会如何");
    check(test_sentinel(100) == -1, "test_sentinel failed.");

    log_err("现在开始检查 内存分配失败会如何");
    check(test_check_mem() == -1, "test_check_mem failed.");
    log_err("现在开始检查 check_debug 宏");
    check(test_check_debug() == -1, "test_check_debug failed.");

    return 0;

error:
    return 1;
}
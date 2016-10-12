#include <stdio.h>
#include "dbg.h"
#include <dlfcn.h>

//函数指针，调用库中函数
typedef int (*lib_function)(const char *data);


int main(int argc, char *argv[])
{
    int rc = 0;
    check(argc == 4, "USAGE: ex29 libex29.so function data");

    char *lib_file = argv[1];
    char *func_to_run = argv[2];
    char *data = argv[3];

//dlopen 打开动态库，并返回 用于关闭动态链接库的局部
//编译时候要加入 -ldl (指定dl库)    
    void *lib = dlopen(lib_file, RTLD_NOW); //RTLD_NOW 表示在返回前，解析所有未定义的符号，解析不出来，会返回 NULL
    check(lib != NULL, "Failed to open the library %s: %s", lib_file, dlerror());

//dlsym 根据动态链接库操作句柄与符号
    lib_function func = dlsym(lib, func_to_run);
    check(func != NULL, "Did not find %s function in the library %s: %s", func_to_run, lib_file, dlerror());

    rc = func(data);
    check(rc == 0, "Function %s return %d for data: %s", func_to_run, rc, data);

    rc = dlclose(lib);
    check(rc == 0, "Failed to close %s", lib_file);

    return 0;

error:
    return 1;
}
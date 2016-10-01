#########################################################################
# File Name: make.sh
# Author: shift
# mail: open_shift@163.com
# Created Time: 2015年03月03日 星期二 20时39分49秒
#########################################################################
#!/bin/bash
#get add_int.o target
gcc -c add/add_int.c -o add/add_int.o -ggdb

#get add_float.o target
gcc -c add/add_float.c -o add/add_float.o -ggdb

#get sub_float.o target
gcc -c sub/sub_float.c -o sub/sub_float.o -ggdb

#get sub_int.o target
gcc -c sub/sub_int.c -o sub/sub_int.o -ggdb

#get main.o target
gcc -c main.c -o main.o -Iadd -Isub -ggdb

#get cacu bin file
gcc -o cacu add/add_int.o add/add_float.o sub/sub_int.o sub/sub_float.o main.o -ggdb

#get main.S target
gcc -S add/add_int.o add/add_float.o sub/sub_int.o sub/sub_float.o main.o

#output tags
ctags -R

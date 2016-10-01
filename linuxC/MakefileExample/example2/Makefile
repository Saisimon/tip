#get cacu bin file
cacu:add_int.o add_float.o sub_int.o sub_float.o main.o
	gcc -o cacu add/add_int.o add/add_float.o \
		sub/sub_int.o sub/sub_float.o main.o -ggdb

#get add_int.o target
add_int.o:add/add_int.c add/add.h
	gcc -c -o add/add_int.o add/add_int.c -ggdb

#get add_float.o target
add_float.o:add/add_float.c add/add.h
	gcc -c -o add/add_float.o add/add_float.c -ggdb

#get sub_int.o target
sub_int.o:sub/sub_int.c sub/sub.h
	gcc -c -o sub/sub_int.o sub/sub_int.c -ggdb

#get sub_float.o target
sub_float.o:sub/sub_float.c sub/sub.h
	gcc -c -o sub/sub_float.o sub/sub_float.c -ggdb

#get main.o target
main.o:main.c add/add.h sub/sub.h
	gcc -c -o main.o main.c -Iadd -Isub -ggdb

#clean project
clean:
	rm -f cacu add/add_int.o add/add_float.o \
		sub/sub_int.o sub/sub_float.o main.o

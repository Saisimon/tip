# CLAGS  C 编译器选项
# OPTFLAGS 扩展构建选项
# 其他人可以这么使用 OPTFLAGS：
#     $ make PREFIX=/tmp install  #修改了 PREFIX 路径为 /tmp
#     $ make OPTFLAGS=-pthread    #设置了 OPTFLAGS
CFLAGS=-g -O2 -Wall -Wextra -Isrc -rdynamic -DNDEBUG $(OPTFLAGS)
LIBS=-ldl $(OPTLIBS)
# 如果平台没有配置 PREFIX，则有效。目的在于使后面的程序安装在 /usr/local 下
PREFIX?=/usr/local

# wildcard 可以用来寻找所有 src及其二级目录下的 .c 文件
SOURCES=$(wildcard src/**/*.c src/*.c)
# patsubst 可以将之前搜索到的 .c 拓展成 .o 并赋值给 OBJECTS
OBJECTS=$(patsubst %.c,%.o,$(SOURCES))


TEST_SRC=$(wildcard tests/*_tests.c)
TESTS=$(patsubst %.c,%,$(TEST_SRC))

# 在这里自定义 静态库 的名字
TARGET=build/libYOUR_LIBRARY.a
# 动态库
SO_TARGET=$(patsubst %.a,%.so,$(TARGET))




# The Target Build
# 没有目标的时候默认运行第一个目标
# 这里的目标是  静态库、动态库、测试文件
all: $(TARGET) $(SO_TARGET) tests

# Wextra 对所有合法但值得怀疑的表达式发出警告
dev: CFLAGS=-g -Wall -Isrc -Wall -Wextra $(OPTFLAGS)
dev: all

# fPIC 不采用绝对地址，全部采用相对地址。
# “表示编译为位置独立的代码，不用此选项的话编译后的代码是位置相关的所以动态载入时是通过代码拷贝的方式来满足不同进程的需要，而不能达到真正代码段共享的目的。”
$(TARGET): CFLAGS += -fPIC
# 1.先完成 build，然后编译所有的 OBJECTS（.o）
# 2.运行实际创建 静态库 TARGET 的 ar 命令
# $@ $(XX) 的意思是，将当前目标的名称放在这里，并将 XX 的内容放在后面
# 在这里相当于  ar rcs $(TARGET) ==>  ar rcs build/libYOUR_LIBRARY.a
# 3.运行 ranlib 构建库
$(TARGET): build $(OBJECTS)
	ar rcs $@ $(OBJECTS)
	ranlib $@

# 4.运行创建动态库的 gcc -shared
$(SO_TARGET): $(TARGET) $(OBJECTS)
	$(CC) -shared -o $@ $(OBJECTS)



build:
	@mkdir -p build
	@mkdir -p bin

# The Unit Tests
# .PHONY 生成伪目标
	.PHONY: tests
# 每个测试文件会链接 TARGET 库,它会添加 build/libYOUR_LIBRARY.a 用于链接
	tests: CFLAGS += $(TARGET)
# 构建 TESTS 中的每个程序	
	tests: $(TESTS)
# 执行自动化测试脚本	
	sh ./tests/runtests.sh

# 利用  Valgrind 运行重复测试
# 日志在
valgrind:
	VALGRIND="valgrind --log-file=/tmp/valgrind-%p.log" $(MAKE)

# The Cleaner
clean:
	rm -rf build $(OBJECTS) $(TESTS)
	rm -f tests/tests.log
# 先查，-exec 表示执行..，这里执行 rm {} \; 
# {} \; 表示将 find 查到的结果发到此处		
	find . -name "*.gc*" -exec rm {} \;
	rm -rf `find . -name "*.dSYM" -print`

# The Install
install: all
	install -d $(DESTDIR)/$(PREFIX)/lib/
	install $(TARGET) $(DESTDIR)/$(PREFIX)/lib/

# The Checker
BADFUNCS='[^_.>a-zA-Z0-9](str(n?cpy|n?cat|xfrm|n?dup|str|pbrk|tok|_)|stpn?cpy|a?sn?printf|byte_)'
check:
	@echo Files with potentially dangerous functions.
	@egrep $(BADFUNCS) $(SOURCES) || true
CC = gcc
CFLAGS = -O2 -Iadd -Isub
TARGET = cacu
DIRS = sub add .
FILES = $(foreach dir, $(DIRS), $(wildcard $(dir)/*.c))
OBJS = $(patsubst %.c, %.o, $(FILES))
$(TARGET):$(OBJS)
	$(CC) -o $(TARGET) $(OBJS)

clean:
	-$(RM) $(TARGET)
	-$(RM) $(OBJS)

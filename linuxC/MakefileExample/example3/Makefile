CC = gcc
CFLAGS = -Iadd -Isub -O2
OBJS = add/add_int.o add/add_float.o sub/sub_float.o sub/sub_int.o main.o
TARGET = cacu
RM = rm -f

$(TARGET):$(OBJS)
	$(CC) -o $(TARGET) $(OBJS) $(CFLAGS)

$(OBJS):%o:%c
	$(CC) -c $(CFLAGS) $< -o $@

clean:
	$(RM) $(TARGET) $(OBJS)

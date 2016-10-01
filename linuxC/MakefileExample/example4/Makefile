CFLAGS = -Iadd -Isub -O2
OBJS = add/add_int.o add/add_float.o \
	   sub/sub_int.o sub/sub_float.o main.o
TARGET = cacu

$(TARGET):$(OBJS)
	$(CC) $^ -o $@ $(CFLAGS)

$(OBJS):%o:%c
	$(CC) -c $< -o $@ $(CFLAGS)

clean:
	-$(RM) $(TARGET) $(OBJS)

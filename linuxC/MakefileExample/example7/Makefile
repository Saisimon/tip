CC = gcc
CFLAGS = -O2
TARGET = cacu
export OBJSDIR = $(shell pwd)/objs

$(TARGET):$(OBJSDIR) main.o
	$(MAKE) -C add
	$(MAKE) -C sub
	$(CC) -o $(TARGET) $(OBJSDIR)/*.o

$(OBJSDIR):
	mkdir -p $@

main.o:%.o:%.c
	$(CC) -c $< -o $(OBJSDIR)/$@ $(CFLAGS) -Iadd -Isub

clean:
	-$(RM) $(TARGET)
	-$(RM) $(OBJSDIR)/*.o

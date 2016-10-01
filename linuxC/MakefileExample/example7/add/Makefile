OBJS = add_int.o add_float.o
all:$(OBJS)

$(OBJS):%.o:%.c
	$(CC) -c $< -o $(OBJSDIR)/$@ $(CFLAGS)

clean:
	$(RM) $(OBJS)

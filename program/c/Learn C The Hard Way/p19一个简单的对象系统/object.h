/*************************************************************************
    > File Name: object.h
    > Author: Younix
    > Mail: foreveryounix@gmail.com 
    > Created Time: 2016年09月27日 星期二 10时55分11秒
 ************************************************************************/

#ifndef _object_h
#define _object_h

typedef enum{
	NORTH, SOUTH, EAST, WEST
}Direction;

typedef struct{
	char* description;
    int (*init)(void *self);
    void (*describe)(void *self);
    void (*destroy)(void *self);
    void *(*move)(void *self, Direction direction);
    int (*attack)(void *self, int damage);
}Object;

int Object_init(void *self);
void Object_destroy(void *self);
void Object_describe(void *self);
void *Object_move(void *self, Direction direction);
int Object_attack(void *self, int damage);
void *Object_new(size_t size, Object proto, char *description);

//T##Proto 表示将  Proto 连接到 T 的末尾
//NEW(Room,"Hello."); 将被展开为 Object_new(sizeof(Room),RoomProto,"Hello");
#define NEW(T, N) Object_new(sizeof(T), T##Proto, N)

//为对象系统设计的 语法糖
//将 obj->proto.blah 写为 obj->_(blah)
//[Yo]但是这样做有什么意义呢？
#define _(N) proto.N

#endif
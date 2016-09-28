#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "object.h"
#include <assert.h>

void Object_destroy(void *self)
{
    assert(self != NULL);    
    Object *obj = self;

    if(obj) {
        if(obj->description) free(obj->description);
        free(obj);
    }
}

void Object_describe(void *self)
{
    assert(self != NULL);
    Object *obj = self;
    printf("%s.\n", obj->description);
}

int Object_init(void *self)
{
    assert(self != NULL);   
    // do nothing really
    return 1;
}

void *Object_move(void *self, Direction direction)
{
    assert(self != NULL);
    printf("You can't go that direction.\n");
    return NULL;
}

int Object_attack(void *self, int damage)
{
    assert(self != NULL);
    printf("You can't attack that.\n");
    return 0;
}

void *Object_new(size_t size, Object proto, char *description)
{
    assert(description != NULL);
    // setup the default functions in case they aren't set
    // 如果未在 proto 中实现，就会用如下默认的
    if(!proto.init) proto.init = Object_init;
    if(!proto.describe) proto.describe = Object_describe;
    if(!proto.destroy) proto.destroy = Object_destroy;
    if(!proto.attack) proto.attack = Object_attack;
    if(!proto.move) proto.move = Object_move;

    // this seems weird, but we can make a struct of one size,
    // then point a different pointer at it to "cast" it
    // 分配 1 个，长度为 size 的空间
    // 用 结构体指针 Object* el 指向这片空间，并用 proto 赋初值
    Object *el = calloc(1, size);
    assert(el != NULL);
    *el = proto;
    //根据 object.h 中的 NEW 宏发现，虽然我们所分配的大小是 sizeof(T)，但是我们是使用 Object *el 来操作它的


    // copy the description over
    // strdup 复制字符串到另一个只读常量区，然后将 el->description 指针指向该区域的首地址
    el->description = strdup(description);
    assert(el->description != NULL);
    // initialize it with whatever init we were given
    // 初始化成功返回 1，返回 el（proto对象）
    if(!el->init(el)) {
        // looks like it didn't initialize properly
        el->destroy(el);
        return NULL;
    } else {
        // all done, we made an object of any type
        return el;
    }
}
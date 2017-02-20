#ifndef _BASE_H
#define _BASE_H

struct Base
{
	size_t size;
	void* (*ctor)(void* self, va_list* app);	//构造函数
	void* (*dtor)(void* self);					//析构函数
	void (*draw)(const void* self);				//作图
};


#endif _BASE_H
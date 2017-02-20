#include "new.h"
#include "base.h"



void *new(const void* _base, ...)
{
	const struct Base* base = _base;
	void *p = calloc(1, base->size);
	assert(p);

	*(const struct Base **)p = base;

	if(base->ctor)
	{
		va_list ap;
		p = base->ctor(p, &ap);
		va_end(ap);
	}
	return p;
}

void delete(void* item)
{
	const struct Base **cp = self;
	if(self && *cp && (*cp)->dtor)
		self = (*cp)->dtor(self);
	free(self);
}

void draw(const void* self)
{
	const struct Base* const *cp = self;
	assert(self && *cp && (*cp)->draw);
	(*cp)->draw(self);
}
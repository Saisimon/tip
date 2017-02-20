#ifndef _POINT_H
#define _POINT_H

typedef struct Point point_STU;
typedef struct PointPrivate pointprivate_STU;
struct Point
{
	struct PointPrivate *pp_Ptr;
};
int get_x(point_STU *point_);
int get_y(point_STU *point_);

point_STU * point_Object();

void free_point_Object(point_STU *point_);

#endif //_POINT_H
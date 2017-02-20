#include "point.h"

struct PointPrivate
{
	int x;
	int y;
};
int get_x(point_STU *point_)
{
	return point_->pp_Ptr->x;
}
int get_y(point_STU *point_)
{
	return point_->pp_Ptr->y;
}


/*************************************************************************
    > File Name: double_while.c
    > Author: Younix
    > Mail: foreveryounix@gmail.com 
    > Created Time: 2016年09月28日 星期三 10时05分46秒
 ************************************************************************/

#include <stdio.h>
#include "../dbg.h"

int double_while(void)
{
	int i = 2 ;
	int j = 2 ;
	while(i)
	{
		j = 2;
		while(j)
		{
			if(j == 1)
			{
				log_info("i=%d,j=%d",i,j);
				break;//将会跳到 Line 29
				//continue;//将会直接去 Line15
			}
			log_info("i=%d,j=%d",i,j);
			j--;
		}
		log_info("i=%d,j=%d",i,j);
		i--;
	}
	
	log_info("i=%d,j=%d",i,j);
}

// 二重循环内  break 跳到上层循环内
// 二重循环内  continue 会直接跳到本层循环的判断条件去

int trible_while(void)
{
	int i = 3 ;
	int j = 3 ;
	int k = 3 ;
	while(i)
	{
		log_info("i=%d,j=%d,k=%d",i,j,k);
		j = 3;
		k = 3;
		while(j)
		{
			log_info("i=%d,j=%d,k=%d",i,j,k);
			k = 3;
			while(k)
			{
				log_info("i=%d,j=%d,k=%d",i,j,k);
				if(k == 2)
				{
					log_info("i=%d,j=%d,k=%d",i,j,k);
					//break;//跳到 65 去了
					continue; // 跳到 53 去循环了
				}
			log_info("i=%d,j=%d,k=%d",i,j,k);				
			k--;
			}
		log_info("i=%d,j=%d,k=%d",i,j,k);			
		j--;
		}
	log_info("i=%d,j=%d,k=%d",i,j,k);		
	i--;
	}	
log_info("i=%d,j=%d,k=%d",i,j,k);
}
//三重循环 break 只会跳出一层，跳到上层中去
//三重循环 continue 会继续本层循环

int main(){
 	//double_while();
 	trible_while();
    return 0;
}

/*--------------------------------------------*/
/* �ļ�����MazeMap.cpp                        */
/* �ļ����ݣ�����MazeMap��                    */
/* ���ߣ�Ԭ����                               */
/* ���ڣ�2015��6��2��                         */
/*--------------------------------------------*/

/*
 * �����ƣ�MazeMap   
 * ��  �ܣ����ɲ������Թ���ͼ
 * �����б�
 *		MazeMap() 
 *			���ܣ�Ĭ���Թ��Ĺ��캯��
 *		MazeMap(int *mazeMap, int row, int column)      
 *			���ܣ����������Թ����Ϳ�Ĺ��캯��
 *		void MazeMap::setMazeMap(int *mazeMap,int row,int column)
 *			���ܣ����������Թ���ͼ
 *		void MazeMap::defaultMode()
 *			���ܣ�Ĭ��ģʽ
 *		void drawMap() const                           
 *			���ܣ����������Թ���ͼ�ĺ���
 *		bool checkWallOrNot(int mazeX,int mazeY) const 
 *			���ܣ���������Ƿ������Թ�ǽ�ڵĺ���
 *		bool checkMazeDoor(int mazeX,int mazeY) const  
 *			���ܣ���������Ƿ������Թ����/���ڵĺ���MazeMap
 */
#include <iostream>

using namespace std;

#include "MazeMap.h"
//��ʼ����̬����
char MazeMap::mazeRoad = ' ';
int MazeMap::mazeWidth = 0;
int MazeMap::mazeHeight = 0;
int MazeMap::mazeMapArray[screenHeight][screenWidth];
/*
 * ���캯����MazeMap
 * �������ܣ�Ĭ�ϳ�ʼ���Թ�
 */
MazeMap::MazeMap():mazeWall('#')
{
	mazeWidth = 60;
	mazeHeight= 20;
}

/*
 * ���캯����MazeMap
 * �������ܣ���ʼ���Թ��Ŀ�͸ߣ�ǽ�ں�ͨ·Ĭ��
 * �����б�
 *		row���Թ���ͼ���������
 *		column���Թ���ͼ���������
 */
MazeMap::MazeMap(int *mazeMap, int row, int column):mazeWall('#')
{
	//�����Թ���ͼ�ĸߺͿ�
	mazeHeight = row;
	mazeWidth = column;
	//�����Թ���ͼ���ڲ�·��
	for(int i=0;i<row;i++)
	{
		for(int j=0;j<column;j++)
		{
			mazeMapArray[i][j] = *mazeMap;
			mazeMap++;
		}
	}
}

/*
 * �������ƣ�setMazeMap
 * �������ܣ����������Թ���ͼ
 * �������ݣ���
 * �����б�
 *		*mazeMap��Ҫ�������ַ����׵�ַ
 *		row���Թ���ͼ���и�
 *		column���Թ���ͼ���п�
 */
void MazeMap::setMazeMap(int *mazeMap,int row,int column)
{
	//�����Թ���ͼ�ĸߺͿ�
	mazeHeight = row;
	mazeWidth = column;
	//�����Թ���ͼ���ڲ�·��
	for(int i=0;i<row;i++)
	{
		for(int j=0;j<column;j++)
		{
			mazeMapArray[i][j] = *mazeMap;
			mazeMap++;
		}
	}
}


/*
 *
 */
void MazeMap::setMazeRoad(char road)
{
	mazeRoad = road;
}

/*
 *
 */
void MazeMap::setMazeWall(char wall)
{
	mazeWall = wall;
}

/*
 * �������ƣ�defaultMode
 * �������ܣ�ʹ��Ĭ�ϵ�ͼ
 * �������ݣ���
 */
void MazeMap::defaultMode()
{
	int mazeMap[6][6] = {
						{WALL,WALL,WALL,WALL,WALL,WALL},
						{WALL,WALL,WALL,WALL,ROAD,ROAD},
						{WALL,WALL,WALL,WALL,ROAD,WALL},
						{WALL,ROAD,ROAD,ROAD,ROAD,WALL},
						{WALL,ROAD,WALL,WALL,ROAD,WALL},
						{WALL,ROAD,WALL,WALL,WALL,WALL}
						};
	
	setMazeMap(&mazeMap[0][0],6,6);
}


/*
 * �������ƣ�drawMap
 * �������ܣ������Թ���ͼ
 * �������ݣ���
 */
void MazeMap::drawMap() const
{
	for(int i=0;i<mazeHeight;i++)
	{
		for(int j=0;j<mazeWidth;j++)
		{
			if(mazeMapArray[i][j]==1)
			{
				cout << mazeWall;
			}
			else
			{
				cout << mazeRoad;
			}
		}
		cout << endl;
	}
}

/*
 * �������ƣ�getRoadChar
 * �������ܣ���ȡ��ʾͨ·���ַ�
 * �������ݣ���ʾͨ·���ַ�
 */
char MazeMap::getRoadChar()
{
	return mazeRoad;
}

/*
 * �������ƣ�checkWallOrNot
 * �������ܣ���̬����������λ�����Ƿ���ǽ��
 * �������ݣ�true:��  false:����
 */
bool MazeMap::checkWallOrNot(int mazeX, int mazeY)
{
	if(mazeMapArray[mazeY][mazeX] == WALL)
	{
		return true;
	}
	else
	{
		return false;
	}
}

/*
 * �������ƣ�checkMazeDoor
 * �������ܣ���̬����������λ���Ƿ��ǳ���
 * �������ݣ�true:��  false:����
 */
bool MazeMap::checkMazeDoor(int mazeX,int mazeY)
{
	//����Թ���������
	if(mazeX == 0 || mazeX == (mazeWidth - 1))
	{
		if(mazeMapArray[mazeY][mazeX] == ROAD)
		{
			return true;
		}
	}
	//����Թ���������
	if(mazeY == 0 || mazeY == (mazeHeight - 1))
	{
		if(mazeMapArray[mazeY][mazeX] == ROAD)
		{
			return true;
		}
	}
	return false;
}


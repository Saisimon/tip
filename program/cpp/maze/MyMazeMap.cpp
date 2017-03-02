/*
 * �ļ����ƣ�MyMazeMap.cpp
 * �ļ����ܣ�ʵ�ִ�����MyMazeMap
 */

/*
 * �������ƣ�MyMazeMap
 * �������ܣ�������Ĺ��캯��
 */

#include "MazeMap.H"
#include "MyMazeMap.H"

MyMazeMap::MyMazeMap()//: mazeMap(new MazeMap)
{
	mazeMap = new MazeMap;
	//empty
}

/*
 * �������ƣ�MyMazeMap
 * �������ܣ�������Ĺ��캯��
 * �����б�
 *		*myMazeMap: �Թ���ͼ�������׵�ַ
 *		row: �Թ����������
 *		column: �Թ����������
 */
MyMazeMap::MyMazeMap(int *myMazeMap, int row, int column) //: mazeMap(new MazeMap(myMazeMap, row, column))
{
	mazeMap = new MazeMap(myMazeMap, row, column);
	//empty
}

/*
 * �������ƣ�defaultMode
 * �������ܣ�����ʹ��Ĭ�ϵ�ͼ
 */
void MyMazeMap::defaultMode()
{
	mazeMap->defaultMode();
}

/*
 * �������ƣ�drawMap
 * �������ܣ�����ʹ�û����Թ���ͼ
 */
void MyMazeMap::drawMap() const
{
	mazeMap->drawMap();
}

/*
 * �������ƣ�setMazeMap
 * �������ܣ�����ʹ�����ü����Թ���ͼ
 */
void MyMazeMap::setMazeMap(int *myMazeMap, int row, int column)
{
	mazeMap->setMazeMap(myMazeMap, row, column);
}

/*
 * �������ƣ�setMazeRoad
 * �������ܣ�����ʹ�����û����Թ���ͨ·�ַ�
 */
void MyMazeMap::setMazeRoad(char road)
{
	mazeMap->setMazeRoad(road);
}

/*
 * �������ƣ�setMazeWall
 * �������ܣ�����ʹ�����û����Թ���ǽ���ַ�
 */
void MyMazeMap::setMazeWall(char wall)
{
	mazeMap->setMazeWall(wall);
}

/*
 * �������ƣ�~MyMazeMap
 * �������ܣ��ͷ��ڴ����
 */
MyMazeMap::~MyMazeMap()
{
	delete mazeMap;
}
/*
 * �ļ����ƣ�MyMazer.cpp
 * �ļ����ܣ�ʵ��MazePerson��Ĵ�����
 */

/*
 * �����ƣ�MyMazer
 * �๦�ܣ�����MazePerson��
 */
#include "MyMazer.H"
#include "MazePerson.h"
/*
 * �������ƣ�MyMazer
 * �������ܣ�������Ĺ��캯��
 */
MyMazer::MyMazer():mazePerson(new MazePerson)
{
	//empty
}

/*
 * �������ƣ�MyMazer
 * �������ܣ�������Ĺ��캯��
 * �����б�
 *		currentX: ��ǰ���ڵĺ�����
 *      currentY: ��ǰ���ڵ�������
 */

MyMazer::MyMazer(int currentX, int currentY)
{
	mazePerson = new MazePerson(currentX, currentY);
}

/*
 * �������ƣ�MyMazer
 * �������ܣ�������Ĺ��캯��
 * �����б�
 *		currentX: ��ǰ���ڵĺ�����
 *      currentY: ��ǰ���ڵ�������
 *		myforward: ��ǰ������ǰ��
 */

MyMazer::MyMazer(int currentX,int currentY,objectiveDirections myforward)
{
	mazePerson = new MazePerson(currentX, currentY, myforward); 
}

/*
 * �������ƣ�~MyMazer
 * �������ܣ��ͷű����������ռ����Դ
 */

MyMazer::~MyMazer()
{
	delete mazePerson;
}

/*
 * �������ƣ�setPersonChar
 * �������ܣ��������������������ñ�ʾ�˵��ַ�
 * �������ƣ�setPersonPic
 * �������ܣ�һ�㺯��
 */

void MyMazer::setPersonPic(char personChar)
{
	mazePerson->setPersonPic(personChar);
}

MyMazer &MyMazer::setPersonChar(char personChar)
{
	mazePerson->setPersonChar(personChar);
	return *this;
}
/*
 * �������ƣ�setPersonForward,setPersonForwardDirection
 * �������ܣ��������������۵�ǰ����
 * �����б�
 *		myForward: SOUTH �� NORTH �� WEST �� EAST ��
 */
void MyMazer::setPersonForwardDirection(objectiveDirections myForward)
{
	mazePerson->setPersonForwardDirection(myForward);
}

MyMazer &MyMazer::setPersonForward(objectiveDirections myForward)
{
	mazePerson->setPersonForward(myForward);
	return *this;
}

/*
 * �������ƣ�setPersonPosition
 * �������ܣ������˵���ʼλ��
 * �����б�
 *		coordinateX: ����ʼλ�õĺ�����
 *		coordinateY: ����ʼλ�õ�������
 */
void MyMazer::setPersonPos(int coordinateX, int coordinateY)
{
	mazePerson->setPersonPos(coordinateX, coordinateY);
}

MyMazer &MyMazer::setPersonPosition(int coordinateX, int coordinateY)
{
	mazePerson->setPersonPosition(coordinateX, coordinateY);
	return *this;
}

/*
 * �������ƣ�setPersonSpeed
 * �������ܣ��������������˶����ٶ�
 * �����б�
 *		speed:  FAST ����  MIDD ����  SLOW ����
 */

void MyMazer::setPersonSpeed(int speed)
{
	mazePerson->setPersonSpeed(speed);
}

/*
 * �������ƣ�setPersonSpeed
 * �������ܣ��������������˶����ٶ�
 * �����б�
 *		speed: ֵԽ���ٶ�Խ��
 */

void MyMazer::setPersonSpeed(long speed)
{
	mazePerson->setPersonSpeed(speed);
}

/*
 * �������ƣ�start
 * �������ܣ�������ʹ�Թ��е��˿�ʼ�˶�
 */

void MyMazer::start()
{
	mazePerson->start();
}


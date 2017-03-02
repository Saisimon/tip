/*--------------------------------------------*/
/* �ļ�����MazePerson.h                       */
/* �ļ����ݣ�����MazePerson��                 */
/* ���ߣ�Ԭ����                               */
/* ���ڣ�2015��6��2��                         */
/*--------------------------------------------*/

/*
 * �����ƣ�MazePerson   
 * ��  �ܣ��Թ��е��˵���
 */

#ifndef MazePerson_H_
#define MazePerson_H_

#include <iostream>
#include <stdlib.h>
#include <windows.h>
#include "Public.H"
using namespace std;

/*enum objectiveDirections
{
	SOUTH,NORTH,WEST,EAST         //��(��)����(��)����(��)����(��)
};*/
//const int FAST = 1;
//const int MIDD = 2;
//const int SLOW = 3;
const bool YES = true;
const bool NO = false;

class MazePerson
{
	//ʹ��ö�����Ͷ���͹۷���
	
public:
	MazePerson();														     //�����˵���Ĺ��췽����Ĭ�ϳ�ʼ���˵�λ��
	MazePerson(int currentX, int currentY);									 //�����˵���Ĺ��췽�����趨�˵ĳ�ʼ��λ��
	MazePerson(int currentX, int currentY, objectiveDirections myForward);   //�����˵���Ĺ��췽�����趨�˵ĳ�ʼ��λ�úͷ���
	MazePerson &setPersonPosition(int coordinateX, int coordinateY);       //�����趨�˵�λ�õķ���
	MazePerson &setPersonChar(char personChar);                            //�����趨�˵ı�ʾ�ķ���
	MazePerson &setPersonForward(objectiveDirections myForward);           //�����趨�˵ķ���ķ���
	void setPersonPos(int coordX, int coordY);							     //�����趨�˵ı�ʾ�ķ���
	void setPersonPic(char personChar);									     //�����趨�˵ı�ʾ�ķ���
	void setPersonForwardDirection(objectiveDirections myForward);		     //�����趨�˵ķ���ķ���
	void setPersonSpeed(long speed);                                         //���������˵�ǰ�ٶȵķ�����ֱ��ʹ������
	void setPersonSpeed(int speed);                                          //���������˵�ǰ�ٶȵķ�����ʹ���趨��ֵ
	void start();                                                            //�����˿�ʼ�˶��ķ���

private:
	int preX;                         //����ǰһ��������
	int preY;                         //����ǰһ��������
	int positionX;                    //������ǰ������
	int positionY;                    //������ǰ������
	int personSpeed;                  //������ǰ�˵��ƶ��ٶ�
	char personPic;                   //������ʾ�˵��ַ�
	bool outOrNot;					  //�������Ƿ��߳��Թ�
	objectiveDirections forward;      //�����߼���ǰ��
	     
	void savePrePosition();           //����ǰһ������λ��
	void moveSouth();                 //���������ߵķ���
	void moveNorth();                 //�������ߵķ���
	void moveWest();                  //���������ߵķ���
	void moveEast();                  //�������ߵķ���
	void moveAhead();                 //�����ص�ǰ������ǰ�ߵķ���
	void turnLeft();                  //��������ת�ķ���
	void turnRight();				  //��������ת�ķ���
	void goAhead();					  //������ǰ����ǰ���ķ���
	bool moveForward();               //�������Խ��������ж����ǰ���ķ���
	void gotoxy(int x, int y);		  //�����趨���λ�õķ���
	void drawPerson();                //�����������Թ��ߵ����߶����ķ���
	

};

#endif //MazePerson_H_
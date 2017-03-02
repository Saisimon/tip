/*--------------------------------------------*/
/* �ļ�����MazePerson.cpp                        */
/* �ļ����ݣ�����MazePerson��                 */
/* ���ߣ�Ԭ����                                             */
/* ���ڣ�2015��6��2��                                 */
/*--------------------------------------------*/

/*
 * �����ƣ�MazePerson   
 * ��  �ܣ��Թ��е��˵���
 * �����б�
 *		MazePerson()
 *			���ܣ����캯������ʼ���˵ĺ����ꡢ�������ǰ������
 *		MazePerson(int currentX, int currentY)
 *			���ܣ����캯������ʼ���˵ĺ����ꡢ�������ǰ������
 *		MazePerson(int currentX, int currentY, objectiveDirection myDirection)
 *			���ܣ����캯������ʼ���˵ĺ����ꡢ�������ǰ������
 *		void savePrePosition()
 *			���ܣ�����ǰһ������λ��
 *		void moveSouth()
 *			���ܣ����������ߵķ���
 *		void moveNorth()
 *			���ܣ��������ߵķ���
 *		void moveWest()
 *			���ܣ����������ߵķ���
 *		void moveEast()
 *			���ܣ��������ߵķ���
 *		bool moveForward()
 *			���ܣ�����������ǰ�ķ���
 *		void turnRole()
 *			���ܣ������ı䷽��ķ���
 */
//#include <iostream>

//using namespace std;

#include "MazePerson.h"
#include "MazeMap.h"
//#include "MazeMap.cpp"
/*
 * �������ƣ�MazePerson
 * �������ܣ����캯������ʼ���˵ĺ����ꡢ�������ǰ������
 */

MazePerson::MazePerson():personPic('*')
{
	positionX = 1;
	positionY = 5;
	preX = 10000;
	preY = 10000;
	personSpeed = 10000;
	forward = NORTH;	
	outOrNot = false;
}

/*
 * �������ƣ�MazePerson
 * �������ܣ����캯������ʼ���˵ĺ����ꡢ�������ǰ������
 * �����б�
 *		currentX��������
 *		currentY��������
 */

MazePerson::MazePerson(int currentX, int currentY):personPic('*')
{
	positionX = currentX;
	positionY = currentY;
	preX = 10000;
	preY = 10000;
	forward = NORTH;
	outOrNot = false;
}

/*
 * �������ƣ�MazePerson
 * �������ܣ����캯������ʼ���˵ĺ����ꡢ�������ǰ������
 * �����б�
 *		currentX��������
 *      currentY��������
 *		myForward��ǰ����
 */

MazePerson::MazePerson(int currentX, int currentY, objectiveDirections myForward):personPic('*')
{
	positionX = currentX;
	positionY = currentY;
	preX = 10000;
	preY = 10000;
	forward = myForward;
	outOrNot = false;
}

/*
 * �������ƣ�savePrePosition
 * �������ܣ�����ǰһ��λ������
 * �������ݣ���
 */
void MazePerson::savePrePosition()
{
	preX = positionX;
	preY = positionY;
}

/*
 * �������ƣ�moveSouth
 * �������ܣ���͹۵��ϵķ�����
 * �����б���
 */
void MazePerson::moveSouth()
{
	//����ǰһ������λ��
	savePrePosition();
	//�����ƶ�һ��λ��
	positionX = positionX;
	positionY = positionY + 1;
}

/*
 * �������ƣ�moveNouth
 * �������ܣ���͹۵ı��ķ�����
 * �����б���
 */
void MazePerson::moveNorth()
{
	//����ǰһ������λ��
	savePrePosition();
	//���ƶ�һ��λ��
	positionX = positionX;
	positionY = positionY - 1;
}

/*
 * �������ƣ�moveWest
 * �������ܣ���͹۵����ķ�����
 * �����б���
 */
void MazePerson::moveWest()
{
	//����ǰһ������λ��
	savePrePosition();
	//�����ƶ�һ��λ��
	positionX = positionX - 1;
	positionY = positionY;
}

/*
 * �������ƣ�moveEast
 * �������ܣ���͹۵Ķ��ķ�����
 * �����б���
 */
void MazePerson::moveEast()
{
	//����ǰһ������λ��
	savePrePosition();
	//���ƶ�һ��λ��
	positionX = positionX + 1;
	positionY = positionY;
}

/*
 * �������ƣ�gotoxy
 * �������ܣ�ȷ������̨���ַ������λ��
 * �����б�
 *		x��������
 *		y��������
 */
void MazePerson::gotoxy(int x, int y)   
{   
	COORD cd;    
	cd.X   =   x; 
	cd.Y   =   y;     
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE),cd);     
};

/*
 * �������ƣ�drawPerson
 * �������ܣ��������Թ��ߵ����߶���
 * �������ݣ���
 */

void MazePerson::drawPerson()
{
	//�ڵ�ǰλ���ϻ�����
	char mazeRoad = MazeMap::getRoadChar();
	gotoxy(preX,preY);
	cout << mazeRoad;
	gotoxy(positionX,positionY);
	cout << personPic;
	//Ϊ��������Ч��ʹ��ʱ���ӳ�
	Sleep(personSpeed);
}

/*
 * �������ƣ�setPersonPosition
 * �������ܣ����������������˵�ǰ��λ��
 * �����б�
 *		coordinateX�������Թ��к������λ��
 *		coordinateY�������Թ����������λ��
 */

MazePerson &MazePerson::setPersonPosition(int coordinateX,int coordinateY)
{
	positionX = coordinateX;
	positionY = coordinateY;
	return *this;
}

void MazePerson::setPersonPos(int coordX, int coordY)
{
	positionX = coordX;
	positionY = coordY;
}


/*
 * �������ƣ�setPersonChar
 * �������ܣ����������������˵ı�ʾ
 * �����б�
 *		personChar����ʾ�˵��ַ�
 */

MazePerson &MazePerson::setPersonChar(char personChar)
{
	personPic = personChar;
	return *this;
}

void MazePerson::setPersonPic(char personChar)
{
	personPic = personChar;
}
/*
 * �������ƣ�setPersonForward
 * �������ܣ����������������˵�ǰ������
 * �����б�
 *		myForward����ʾ�˵�ǰ������
 */

MazePerson &MazePerson::setPersonForward(objectiveDirections myForward)
{
	forward = myForward;
	return *this;
}

void MazePerson::setPersonForwardDirection(objectiveDirections myForward)
{
	forward = myForward;
}

/*
 * �������ƣ�turnLeft
 * �������ܣ�����ת
 * �������ݣ���
 */
void MazePerson::turnLeft()
{
	//forward = (forward + 1) % 4;
	//cout << "turn left" << endl;
	switch(forward)
	{
	case NORTH: forward = WEST;  break;
	case WEST:	forward = SOUTH; break;
	case SOUTH:	forward = EAST;  break;
	case EAST:	forward = NORTH; break;
	default: cout << "�������" << endl;
	}
} 

/*
 * �������ƣ�turnRight
 * �������ܣ�����ת
 * �������ݣ���
 */
void MazePerson::turnRight()
{
	//cout << "turn right" << endl;
	switch(forward)
	{
	case NORTH: forward = EAST;  break;
	case WEST:  forward = NORTH; break;
	case SOUTH: forward = WEST;  break;
	case EAST:  forward = SOUTH; break;
	default: cout << "�������" << endl;
	}
}

/*
 * �������ƣ�goAhead
 * �������ܣ�����ǰ��ǰ����һ��
 * �������ݣ���
 */
void MazePerson::goAhead()
{
	switch(forward)
	{
	case NORTH: moveNorth(); break;
	case WEST:  moveWest();  break;
	case SOUTH: moveSouth(); break;
	case EAST:  moveEast();  break;
	default: cout << "���������޷�ǰ����" << endl;
	}
	//�γɶ���
	drawPerson();
	//����Ƿ񵽴����
	//���������ڣ�����ʾ
	if(MazeMap::checkMazeDoor(positionX, positionY))
	{
		gotoxy(0,22);
		cout << "���������ڳ�������" << endl;
		outOrNot = true;
	}
}

/*
 * �������ƣ�setPersonSpeed
 * �������ܣ�ͨ���ȼ��趨�ٶ�
 */
void MazePerson::setPersonSpeed(int speed)
{
	//����һ��ʱ���ӳٵ�ֵ
	personSpeed = speed * 500;
	
}

/*
 * �������ƣ�setPersonSpeed
 * �������ܣ�ͨ����ֵ�趨�ٶ�
 */
void MazePerson::setPersonSpeed(long speed)
{
	//����һ��ʱ���ӳٵ�ֵ
	personSpeed = speed;
	
}

/*
 *
 */
void MazePerson::start()
{
	int i = 0;
	while(!outOrNot)
	{
		gotoxy(0,15);
		cout << i;
		i++;
		moveForward();
	}
}

/*
 * �������ƣ�moveForward
 * �������ܣ�����ǰ��һ��
 * �������ݣ�YES:�ƶ�һ��  NO:û���ƶ�
 */
bool MazePerson::moveForward()
{
	
	//��鵱ǰλ���Ƿ�Ϸ�
	if(MazeMap::checkWallOrNot(positionX,positionY))
	{
		//��⵽��ǰλ����ǽ����ʾ�û���ƷǷ�������
		gotoxy(0,23);
		cout << "��⵽��ǰ�趨����ʼλ����ǽ�ڣ��������趨��" << endl;
		outOrNot = true;
		return NO;
	}
	//�ж����ǰһ��λ�õ�������10000����ֱ���ڵ�ǰλ�û�����
	if(preX == 10000 || preY == 10000)
	{
		gotoxy(positionX,positionY);
		cout << personPic;
		Sleep(5000);
	}
	//����Ҳ��Ƿ���ǽ�������ǽ�����ǰ���Ƿ���ǽ���������ǽ��ǰ����ǰ�������Ƿ��ǳ���
	int rightX;
	int rightY;

	int forwardX;
	int forwardY;
	
	switch(forward)
	{
	case SOUTH: rightX = positionX - 1; rightY = positionY; 
		forwardX = positionX; forwardY = positionY + 1; break; 
	case NORTH: rightX = positionX + 1; rightY = positionY; 
	    forwardX = positionX; forwardY = positionY - 1;	break;
	case WEST:  rightX = positionX; rightY = positionY - 1; 
		forwardX = positionX - 1; forwardY = positionY;	break;
	case EAST:  rightX = positionX; rightY = positionY + 1; 
		forwardX = positionX + 1; forwardY = positionY;	break;
	}
	//����Ҳ���ǽ

	//system("PAUSE");

	if(MazeMap::checkWallOrNot(rightX,rightY))
	{

		//���ǰ��Ҳ��ǽ��
		if(MazeMap::checkWallOrNot(forwardX,forwardY))
		{
			//����ʱ�뻻һ������
			turnLeft();
			return NO;
		}
		//���ǰ����ͨ·
		else
		{
			//��ǰ�ƶ�
			goAhead();
			return YES;
		}
	}
	//����Ҳ���ͨ·
	else
	{
		Sleep(3000);
		turnRight();
		goAhead();
		return YES;
	}

}

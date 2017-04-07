package net.saisimon.main.concurrent;

import java.util.concurrent.Phaser;

public class MyPhaser extends Phaser {

	@Override
	protected boolean onAdvance(int phase, int registeredParties) {
		switch (phase) {
		case 0:
			return studentsArrived();
		case 1:
			return finishFirstExercise();
		case 2:
			return finishSecondExercise();
		case 3:
			return finishExam();
		default:
			return true;
		}
	}
	
	// 返回false值来表明phaser将继续执行
	private boolean studentsArrived() {
		System.out.printf("Phaser: The exam are going to start. The students are ready.\n");
		System.out.printf("Phaser: We have %d students.\n", getRegisteredParties());
		return false;
	}
	
	// 返回false值来表明phaser将继续执行
	private boolean finishFirstExercise() {
		System.out.printf("Phaser: All the students have finished the first exercise.\n");
		System.out.printf("Phaser: It's time for the second one.\n");
		return false;
	}
	
	// 返回false值来表明phaser将继续执行
	private boolean finishSecondExercise() {
		System.out.printf("Phaser: All the students have finished the second exercise.\n");
		System.out.printf("Phaser: It's time for the third one.\n");
		return false;
	}
	
	private boolean finishExam() {
		System.out.printf("Phaser: All the students have finished the exam.\n");
		System.out.printf("Phaser: Thank you for your time.\n");
		return true;
	}
	
}

package net.saisimon.main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;
import java.util.concurrent.TimeUnit;

import net.saisimon.main.concurrent.Calculator;
import net.saisimon.main.concurrent.FileSearch;
import net.saisimon.main.concurrent.PrimeGenerator;

public class Main {
	
	public static void main(String[] args) {
//		calculatorMain();
//		primeGeneratorMain();
		fileSearchMain();
	}
	
	private static final int THREAD_SIZE = 10;
	
	/**
	 * http://ifeve.com/thread-management-2/
	 * http://ifeve.com/thread-management-3/
	 * 
	 * @see Calculator
	 */
	public static void calculatorMain() {
		Thread[] threads = new Thread[THREAD_SIZE];
		State[] status = new State[THREAD_SIZE];
		
		for (int i = 0; i < THREAD_SIZE; i++) {
			threads[i] = new Thread(new Calculator(i));
			if (i % 2 == 0) {
				threads[i].setPriority(Thread.MAX_PRIORITY);
			} else {
				threads[i].setPriority(Thread.MIN_PRIORITY);
			}
			threads[i].setName("Thread " + i);
		}
		
		try (FileWriter fileWriter = new FileWriter(".\\data\\log.txt"); PrintWriter printWriter = new PrintWriter(fileWriter);) {
			for (int i = 0; i < THREAD_SIZE; i++) {
				printWriter.println("Main : Status of Thread " + i + " : " + threads[i].getState());
				status[i] = threads[i].getState();
			}
			printWriter.println();
			
			for (int i = 0; i < THREAD_SIZE; i++) {
				threads[i].start();
			}
			
			boolean finish = false;
			while (!finish) {
				for (int i = 0; i < THREAD_SIZE; i++) {
					if (threads[i].getState() != status[i]) {
						writeThreadInfo(printWriter, threads[i], status[i]);
						status[i] = threads[i].getState();
					}
				}
				
				finish = true;
				for (int i = 0; i < THREAD_SIZE; i++) {
					finish = finish && (threads[i].getState() == State.TERMINATED);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeThreadInfo(PrintWriter printWriter, Thread thread, State state) {
		printWriter.printf("Main : Id %d - %s\n", thread.getId(), thread.getName());
		printWriter.printf("Main : Priority: %d\n", thread.getPriority());
		printWriter.printf("Main : Old State: %s\n", state);
		printWriter.printf("Main : New State: %s\n", thread.getState());
		printWriter.printf("Main : *****************************************************\n");
	}
	
	/**
	 * http://ifeve.com/thread-management-4/
	 * 
	 * @see PrimeGenerator
	 */
	public static void primeGeneratorMain() {
		Thread task = new PrimeGenerator();
		task.start();
		try {
			// 等待5秒
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 中断 PrimeGenerator
		task.interrupt();
	}
	
	/**
	 * http://ifeve.com/thread-management-5/
	 * 
	 * @see FileSearch
	 */
	public static void fileSearchMain() {
		FileSearch fileSearch = new FileSearch("C:\\", "setup.exe");
		Thread thread = new Thread(fileSearch);
		thread.start();
		// 等待10秒，然后中断线程
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread.interrupt();
	}
	
}

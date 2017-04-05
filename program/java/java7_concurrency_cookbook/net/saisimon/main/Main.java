package net.saisimon.main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

import net.saisimon.main.concurrent.Calculator;
import net.saisimon.main.concurrent.CleanerTask;
import net.saisimon.main.concurrent.DataSourcesLoader;
import net.saisimon.main.concurrent.Event;
import net.saisimon.main.concurrent.ExceptionHandler;
import net.saisimon.main.concurrent.FileClock;
import net.saisimon.main.concurrent.FileSearch;
import net.saisimon.main.concurrent.NetworkConnectionsLoader;
import net.saisimon.main.concurrent.PrimeGenerator;
import net.saisimon.main.concurrent.ThrowUncaughtExceptionTask;
import net.saisimon.main.concurrent.WriterTask;

/**
 * http://ifeve.com/java-7-concurrency-cookbook/
 * 
 * @author Saisimon
 *
 */
public class Main {
	
	public static void main(String[] args) {
//		calculatorMain();
//		primeGeneratorMain();
//		fileSearchMain();
//		fileClockMain();
//		dateSourcesLoaderAndNetworkConnectionsLoaderMain();
//		writerTaskAndCleanerTaskMain();
		throwUncaughtExceptionTaskMain();
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
	
	/**
	 * http://ifeve.com/thread-management-6/
	 * 
	 * @see FileClock
	 */
	public static void fileClockMain() {
		FileClock fileClock = new FileClock();
		Thread thread = new Thread(fileClock);
		thread.start();
		// 等待5秒，然后中断线程
		try {
			Thread.sleep(5500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread.interrupt();
	}
	
	/**
	 * http://ifeve.com/thread-management-7/
	 * 
	 * @see DataSourcesLoader
	 * @see NetworkConnectionsLoader
	 */
	public static void dateSourcesLoaderAndNetworkConnectionsLoaderMain() {
		DataSourcesLoader dataSourcesLoader = new DataSourcesLoader();
		Thread dataSourcesLoaderThread = new Thread(dataSourcesLoader, "DataSourcesLoaderThread");
		NetworkConnectionsLoader networkConnectionsLoader = new NetworkConnectionsLoader();
		Thread networkConnectionsLoaderThread = new Thread(networkConnectionsLoader, "NetworkConnectionsLoaderThread");
		
		dataSourcesLoaderThread.start();
		networkConnectionsLoaderThread.start();
		
		// 2个线程都使用 join() 方法等待终结
		try {
			dataSourcesLoaderThread.join();
			networkConnectionsLoaderThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Main : Configuration has been loaded : %s\n", new Date());
	}
	
	/**
	 * http://ifeve.com/thread-management-8/
	 * 
	 * @see WriterTask
	 * @see CleanerTask
	 */
	public static void writerTaskAndCleanerTaskMain() {
		Deque<Event> eventDeque = new ArrayDeque<>();
		WriterTask writerTask = new WriterTask(eventDeque);
		for (int i = 0; i < 3; i++) {
			Thread writerThread = new Thread(writerTask);
			writerThread.start();
		}
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		CleanerTask cleanerTask = new CleanerTask(eventDeque);
		Thread cleanerThread = new Thread(cleanerTask);
		cleanerThread.start();
	}
	
	/**
	 * http://ifeve.com/thread-management-9/
	 * 
	 * @see ThrowUncaughtExceptionTask
	 * @see ExceptionHandler
	 */
	public static void throwUncaughtExceptionTaskMain() {
		ThrowUncaughtExceptionTask task = new ThrowUncaughtExceptionTask();
		Thread thread = new Thread(task);
		thread.setUncaughtExceptionHandler(new ExceptionHandler());
		thread.start();
	}
	
}

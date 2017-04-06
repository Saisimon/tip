package net.saisimon.main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

import net.saisimon.main.concurrent.Account;
import net.saisimon.main.concurrent.Bank;
import net.saisimon.main.concurrent.Calculator;
import net.saisimon.main.concurrent.Cinema;
import net.saisimon.main.concurrent.CleanerTask;
import net.saisimon.main.concurrent.Company;
import net.saisimon.main.concurrent.Consumer;
import net.saisimon.main.concurrent.DataSourcesLoader;
import net.saisimon.main.concurrent.Event;
import net.saisimon.main.concurrent.EventStorage;
import net.saisimon.main.concurrent.ExceptionHandler;
import net.saisimon.main.concurrent.FileClock;
import net.saisimon.main.concurrent.FileSearch;
import net.saisimon.main.concurrent.MyThreadFactory;
import net.saisimon.main.concurrent.MyThreadFactoryTask;
import net.saisimon.main.concurrent.MyThreadGroup;
import net.saisimon.main.concurrent.MyThreadGroupTask;
import net.saisimon.main.concurrent.NetworkConnectionsLoader;
import net.saisimon.main.concurrent.PrimeGenerator;
import net.saisimon.main.concurrent.Producer;
import net.saisimon.main.concurrent.Result;
import net.saisimon.main.concurrent.SafeTask;
import net.saisimon.main.concurrent.SearchTask;
import net.saisimon.main.concurrent.ThrowUncaughtExceptionTask;
import net.saisimon.main.concurrent.TicketOffice1;
import net.saisimon.main.concurrent.TicketOffice2;
import net.saisimon.main.concurrent.UnsafeTask;
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
//		throwUncaughtExceptionTaskMain();
//		unsafeTaskMain();
//		safeTaskMain();
//		searchTaskMain();
//		myThreadGroupTaskMain();
//		myThreadFactoryTaskMain();
//		bankAndCompanyMain();
//		cinemaAndTicketOfficeMain();
		producerAndConsumerMain();
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
	
	/**
	 * http://ifeve.com/thread-management-10/
	 * 
	 * @see UnsafeTask
	 */
	public static void unsafeTaskMain() {
		UnsafeTask task = new UnsafeTask();
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(task);
			thread.start();
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * http://ifeve.com/thread-management-10/
	 * 
	 * @see SafeTask
	 */
	public static void safeTaskMain() {
		SafeTask task = new SafeTask();
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(task);
			thread.start();
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * http://ifeve.com/thread-management-11/
	 * 
	 * @see SearchTask
	 */
	public static void searchTaskMain() {
		// 线程组
		ThreadGroup threadGroup = new ThreadGroup("Searcher");
		Result result = new Result();
		SearchTask task = new SearchTask(result);
		for (int i = 0; i < 5; i++) {
			Thread thread = new Thread(threadGroup, task);
			thread.start();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("Number of Threads: %d\n", threadGroup.activeCount());
		System.out.printf("Information about the Thread Group\n");
		threadGroup.list();
		Thread[] threads = new Thread[threadGroup.activeCount()];
		threadGroup.enumerate(threads);
		for (int i = 0; i < threadGroup.activeCount(); i++) {
			System.out.printf("Thread %s: %s\n", threads[i].getName(), threads[i].getState());
		}
		waitFinish(threadGroup);
		threadGroup.interrupt();
	}
	
	private static void waitFinish(ThreadGroup threadGroup) {
		while (threadGroup.activeCount() > 9) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * http://ifeve.com/thread-management-12/
	 * 
	 * @see MyThreadGroup
	 * @see MyThreadGroupTask
	 */
	public static void myThreadGroupTaskMain() {
		MyThreadGroup myThreadGroup = new MyThreadGroup("myThreadGroup");
		MyThreadGroupTask task = new MyThreadGroupTask();
		for (int i = 0; i < 2; i++) {
			Thread thread = new Thread(myThreadGroup, task);
			thread.start();
		}
	}
	
	/**
	 * http://ifeve.com/thread-management-13/
	 * 
	 * @see MyThreadFactory
	 * @see MyThreadFactoryTask
	 */
	public static void myThreadFactoryTaskMain() {
		MyThreadFactory factory = new MyThreadFactory("myThreadFactory");
		MyThreadFactoryTask task = new MyThreadFactoryTask();
		System.out.println("Starting the Threads...");
		for (int i = 0; i < 10; i++) {
			Thread thread = factory.newThread(task);
			thread.interrupt();
		}
		System.out.println("Factory Stats : ");
		System.out.println(factory.getStatistics());
	}
	
	/**
	 * http://ifeve.com/basic-thread-synchroinzation-2/
	 * 
	 * @see Account
	 * @see Bank
	 * @see Company
	 */
	public static void bankAndCompanyMain() {
		Account account = new Account(1000);
		Company company = new Company(account);
		Thread companyThread = new Thread(company, "company");
		Bank bank = new Bank(account);
		Thread bankThread = new Thread(bank, "bank");
		System.out.printf("Account : Initial Balance: %.2f\n", account.getBalance());
		companyThread.start();
		bankThread.start();
		try {
			companyThread.join();
			bankThread.join();
			System.out.printf("Account : Final Balance: %.2f\n", account.getBalance());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * http://ifeve.com/basic-thread-synchronization-3/
	 * 
	 * @see Cinema
	 * @see TicketOffice1
	 * @see TicketOffice2
	 */
	public static void cinemaAndTicketOfficeMain() {
		Cinema cinema = new Cinema();
		TicketOffice1 office1 = new TicketOffice1(cinema);
		TicketOffice2 office2 = new TicketOffice2(cinema);
		Thread thread1 = new Thread(office1);
		Thread thread2 = new Thread(office2);
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Room 1 Vacancies: %d\n",cinema.getVacanciesCinema1());
		System.out.printf("Room 2 Vacancies: %d\n",cinema.getVacanciesCinema2());

	}
	
	/**
	 * http://ifeve.com/basic-thread-synchronization-4/
	 * 
	 * 生产者与消费者问题
	 * 
	 * @see EventStorage
	 * @see Producer
	 * @see Consumer
	 */
	public static void producerAndConsumerMain() {
		EventStorage eventStorage = new EventStorage();
		Producer producer = new Producer(eventStorage);
		Consumer consumer = new Consumer(eventStorage);
		Thread consumerThread = new Thread(consumer);
		consumerThread.start();
		Thread producerThread = new Thread(producer);
		producerThread.start();
	}
	
}

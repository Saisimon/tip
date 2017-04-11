package net.saisimon.main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.Phaser;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import net.saisimon.main.concurrent.Account;
import net.saisimon.main.concurrent.Bank;
import net.saisimon.main.concurrent.Buffer;
import net.saisimon.main.concurrent.BufferConsumer;
import net.saisimon.main.concurrent.BufferProducer;
import net.saisimon.main.concurrent.Calculator;
import net.saisimon.main.concurrent.CancelTask;
import net.saisimon.main.concurrent.Cinema;
import net.saisimon.main.concurrent.CleanerTask;
import net.saisimon.main.concurrent.Company;
import net.saisimon.main.concurrent.Consumer;
import net.saisimon.main.concurrent.DataSourcesLoader;
import net.saisimon.main.concurrent.Document;
import net.saisimon.main.concurrent.DocumentTask;
import net.saisimon.main.concurrent.Event;
import net.saisimon.main.concurrent.EventStorage;
import net.saisimon.main.concurrent.ExceptionHandler;
import net.saisimon.main.concurrent.ExchangerConsumer;
import net.saisimon.main.concurrent.ExchangerProducer;
import net.saisimon.main.concurrent.ExecutableTask;
import net.saisimon.main.concurrent.ExecutorAllTask;
import net.saisimon.main.concurrent.ExecutorResult;
import net.saisimon.main.concurrent.ExecutorServer;
import net.saisimon.main.concurrent.ExecutorTask;
import net.saisimon.main.concurrent.FactorialCalculator;
import net.saisimon.main.concurrent.FileClock;
import net.saisimon.main.concurrent.FileMock;
import net.saisimon.main.concurrent.FileSearch;
import net.saisimon.main.concurrent.FileSearchPhaser;
import net.saisimon.main.concurrent.Grouper;
import net.saisimon.main.concurrent.Job;
import net.saisimon.main.concurrent.JobSemaphore;
import net.saisimon.main.concurrent.JobSemaphoreMultiple;
import net.saisimon.main.concurrent.MatrixMock;
import net.saisimon.main.concurrent.MyPhaser;
import net.saisimon.main.concurrent.MyThreadFactory;
import net.saisimon.main.concurrent.MyThreadFactoryTask;
import net.saisimon.main.concurrent.MyThreadGroup;
import net.saisimon.main.concurrent.MyThreadGroupTask;
import net.saisimon.main.concurrent.NetworkConnectionsLoader;
import net.saisimon.main.concurrent.Participant;
import net.saisimon.main.concurrent.PricesInfo;
import net.saisimon.main.concurrent.PrimeGenerator;
import net.saisimon.main.concurrent.PrintQueue;
import net.saisimon.main.concurrent.PrintQueueSemaphore;
import net.saisimon.main.concurrent.PrintQueueSemaphoreMultiple;
import net.saisimon.main.concurrent.Producer;
import net.saisimon.main.concurrent.Product;
import net.saisimon.main.concurrent.ProductListGenerator;
import net.saisimon.main.concurrent.Reader;
import net.saisimon.main.concurrent.RecursiveActionTask;
import net.saisimon.main.concurrent.RejectedTask;
import net.saisimon.main.concurrent.RejectedTaskController;
import net.saisimon.main.concurrent.ReportGenerator;
import net.saisimon.main.concurrent.ReportProcessor;
import net.saisimon.main.concurrent.ReportRequest;
import net.saisimon.main.concurrent.Result;
import net.saisimon.main.concurrent.ResultTask;
import net.saisimon.main.concurrent.Results;
import net.saisimon.main.concurrent.SafeTask;
import net.saisimon.main.concurrent.ScheduledRunnable;
import net.saisimon.main.concurrent.ScheduledTask;
import net.saisimon.main.concurrent.SearchTask;
import net.saisimon.main.concurrent.Searcher;
import net.saisimon.main.concurrent.Student;
import net.saisimon.main.concurrent.TaskValidator;
import net.saisimon.main.concurrent.ThrowUncaughtExceptionTask;
import net.saisimon.main.concurrent.TicketOffice1;
import net.saisimon.main.concurrent.TicketOffice2;
import net.saisimon.main.concurrent.UnsafeTask;
import net.saisimon.main.concurrent.UserValidator;
import net.saisimon.main.concurrent.VideoConference;
import net.saisimon.main.concurrent.Writer;
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
//		producerAndConsumerMain();
//		printQueueAndJobMain();
//		readerAndWriterLockMain();
//		printQueueTrueAndJobMain();
//		fileMockAndBufferMain();
//		printQueueAndJobAndSemaphoreMain();
//		printQueueAndJobAndSemaphoreMultipleMain();
//		videoConferenceAndParticipantMain();
//		matrixMockAndCyclicBarrierMain();
//		fileSearchAndPhaserMain();
//		myPhaserAndStudentMain();
//		producerAndConsumerAndExchangerMain();
//		executorAndTaskAndServerMain();
//		fatorialCalculatorMain();
//		userAndTaskValidate();
//		executorResultAndAllTaskMain();
//		scheduledTaskMain();
//		scheduledTaskAndScheduledFutureMain();
//		cancelTaskMain();
//		executableAndResultTaskMain();
//		reportGeneratorAndRequestAndProcessorMain();
//		rejectedTaskControllerMain();
//		productAndRecursiveActionTaskMain();
		documentAndLineTaskMain();
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
	
	/**
	 * http://ifeve.com/basic-thread-synchronization-5/
	 * 
	 * private final Lock queueLock = new ReentrantLock();
	 * 
	 * @see PrintQueue
	 * @see Job
	 */
	public static void printQueueAndJobMain() {
		PrintQueue printQueue = new PrintQueue();
		Thread[] threads = new Thread[10];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new Job(printQueue), "Thread " + i);
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
	}
	
	/**
	 * http://ifeve.com/basic-thread-synchronization-6/
	 * 
	 * @see PricesInfo
	 * @see Reader
	 * @see Writer
	 */
	public static void readerAndWriterLockMain() {
		PricesInfo pricesInfo = new PricesInfo();
		Thread[] threadReaders = new Thread[2];
		for (int i = 0; i < threadReaders.length; i++) {
			threadReaders[i] = new Thread(new Reader(pricesInfo), "Reader Thread" + i);
		}
		Thread threadWriter = new Thread(new Writer(pricesInfo), "Writer Thread");
		for (int i = 0; i < threadReaders.length; i++) {
			threadReaders[i].start();
		}
		threadWriter.start();
	}
	
	/**
	 * http://ifeve.com/basic-thread-synchronization-7/
	 * 
	 * private final Lock queueLock = new ReentrantLock(true);
	 * 
	 * @see PrintQueue
	 * @see Job
	 */
	public static void printQueueTrueAndJobMain() {
		PrintQueue printQueue = new PrintQueue();
		Thread[] threads = new Thread[10];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new Job(printQueue), "Thread " + i);
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * http://ifeve.com/basic-thread-synchronization-8/
	 * 
	 * 生产者与消费者问题
	 * 
	 * @see FileMock
	 * @see Buffer
	 * @see BufferProducer
	 * @see BufferConsumer
	 */
	public static void fileMockAndBufferMain() {
		FileMock fileMock = new FileMock(100, 10);
		Buffer buffer = new Buffer(20);
		BufferProducer producer = new BufferProducer(fileMock, buffer);
		Thread producerThread = new Thread(producer, "producer");
		Thread[] consumerThreads = new Thread[3];
		for (int i = 0; i < consumerThreads.length; i++) {
			consumerThreads[i] = new Thread(new BufferConsumer(buffer), "consumer " + i);
		}
		producerThread.start();
		for (int i = 0; i < consumerThreads.length; i++) {
			consumerThreads[i].start();
		}
	}
	
	/**
	 * http://ifeve.com/thread-synchronization-utilities-2/
	 * 
	 * @see PrintQueueSemaphore
	 * @see JobSemaphore
	 */
	public static void printQueueAndJobAndSemaphoreMain() {
		PrintQueueSemaphore printQueue = new PrintQueueSemaphore();
		Thread[] threads = new Thread[10];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new JobSemaphore(printQueue));
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
	}
	
	/**
	 * http://ifeve.com/thread-synchronization-utilities-3/
	 * 
	 * @see PrintQueueSemaphoreMultiple
	 * @see JobSemaphoreMultiple
	 */
	public static void printQueueAndJobAndSemaphoreMultipleMain() {
		PrintQueueSemaphoreMultiple printQueue = new PrintQueueSemaphoreMultiple();
		Thread[] threads = new Thread[10];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new JobSemaphoreMultiple(printQueue));
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
	}
	
	/**
	 * http://ifeve.com/thread-synchronization-utilities-4/
	 * 
	 * @see VideoConference
	 * @see Participant
	 */
	public static void videoConferenceAndParticipantMain() {
		VideoConference videoConference = new VideoConference(10);
		Thread videoConferenceThread = new Thread(videoConference);
		videoConferenceThread.start();
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(new Participant(videoConference, "Participant " + i));
			thread.start();
		}
	}
	
	/**
	 * http://ifeve.com/thread-synchronization-utilities-5/
	 * 
	 * @see MatrixMock
	 * @see Results
	 * @see Grouper
	 * @see Searcher
	 */
	public static void matrixMockAndCyclicBarrierMain() {
		final int ROWS = 10000;
		final int NUMBERS = 3000;
		final int SEARCH = 14;
		final int PARTICIPANTS = 5;
		final int LINES_PARTICIPANT = 2000;
		CountDownLatch countDownLatch = new CountDownLatch(PARTICIPANTS);

		MatrixMock mock = new MatrixMock(ROWS, NUMBERS, SEARCH);
		long startTime = System.currentTimeMillis();
		Results results = new Results(ROWS);
		Grouper grouper = new Grouper(results);
		CyclicBarrier barrier = new CyclicBarrier(PARTICIPANTS, grouper);
		Searcher searchers[] = new Searcher[PARTICIPANTS];
		for (int i = 0; i < PARTICIPANTS; i++) {
			searchers[i] = new Searcher(i * LINES_PARTICIPANT, (i * LINES_PARTICIPANT) + LINES_PARTICIPANT, mock,
					results, SEARCH, barrier, countDownLatch);
			Thread thread = new Thread(searchers[i]);
			thread.start();
		}
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Main: The main thread has finished.Time : %d ms\n", (System.currentTimeMillis() - startTime));
		
		startTime = System.currentTimeMillis();
		int[][] data = mock.getData();
		int count = 0;
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				if (data[i][j] == SEARCH) {
					count++;
				}
			}
		}
		System.out.printf("Total result: %d\n", count);
		System.out.printf("Another Time : %d ms\n", (System.currentTimeMillis() - startTime));
	}
	
	/**
	 * http://ifeve.com/thread-synchronization-utilities-6-2/
	 * 
	 * @see FileSearchPhaser
	 */
	public static void fileSearchAndPhaserMain() {
		// 创建 含3个参与者的 Phaser 对象
		Phaser phaser = new Phaser(3);
		// 创建3个 FileSearch 对象
		FileSearchPhaser system = new FileSearchPhaser("C:\\Windows", "log", phaser);
		FileSearchPhaser apps = new FileSearchPhaser("C:\\Program Files", "log", phaser);
		FileSearchPhaser documents = new FileSearchPhaser("C:\\Documents And Settings", "log", phaser);
		Thread systemThread = new Thread(system, "System");
		systemThread.start();
		Thread appsThread = new Thread(apps, "Apps");
		appsThread.start();
		Thread documentsThread = new Thread(documents, "Documents");
		documentsThread.start();
		try {
			// 等待3个线程结束
			systemThread.join();
			appsThread.join();
			documentsThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Terminated: " + phaser.isTerminated());
	}
	
	/**
	 * http://ifeve.com/thread-synchronization-utilities-7/
	 * 
	 * @see MyPhaser
	 * @see Student
	 */
	public static void myPhaserAndStudentMain() {
		Phaser phaser = new MyPhaser();
		Student[] students = new Student[5];
		for (int i = 0; i < students.length; i++) {
			students[i] = new Student(phaser);
			phaser.register();
		}
		
		Thread[] threads = new Thread[5];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(students[i], "Student " + i);
			threads[i].start();
		}
		
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.printf("Main: The phaser has finished: %s.\n", phaser.isTerminated());
	}
	
	/**
	 * http://ifeve.com/thread-synchronization-utilities-8/
	 * 
	 * @see ExchangerProducer
	 * @see ExchangerConsumer
	 */
	public static void producerAndConsumerAndExchangerMain() {
		List<String> buffer1 = new ArrayList<>();
		List<String> buffer2 = new ArrayList<>();
		
		Exchanger<List<String>> exchanger = new Exchanger<>();
		
		ExchangerProducer producer = new ExchangerProducer(buffer1, exchanger);
		ExchangerConsumer consumer = new ExchangerConsumer(buffer2, exchanger);
		
		Thread producerThread = new Thread(producer);
		Thread consumerThread = new Thread(consumer);
		
		producerThread.start();
		consumerThread.start();
	}
	
	/**
	 * http://ifeve.com/thread-executors-2/
	 * http://ifeve.com/thread-executors-3/
	 * 
	 * @see ExecutorServer
	 * @see ExecutorTask
	 */
	public static void executorAndTaskAndServerMain() {
		ExecutorServer server = new ExecutorServer();
		for (int i = 0; i < 100; i++) {
			ExecutorTask task = new ExecutorTask("Task " + i);
			server.executeTask(task);
		}
		server.endServer();
	}
	
	/**
	 * http://ifeve.com/thread-executors-4/
	 * 
	 * @see FactorialCalculator
	 */
	public static void fatorialCalculatorMain() {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		List<Future<Integer>> results = new ArrayList<>();
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			int number = rand.nextInt(10);
			FactorialCalculator calculator = new FactorialCalculator(number);
			results.add(executor.submit(calculator));
		}
		do {
			System.out.printf("Main: Number of Completed Tasks:%d\n", executor.getCompletedTaskCount());
			for (int i = 0; i < results.size(); i++) {
				Future<Integer> result = results.get(i);
				System.out.printf("Main: Task %d: %s\n", i, result.isDone());
			}
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (executor.getCompletedTaskCount() < results.size());
		System.out.printf("Main: Results\n");
		for (int i = 0; i < results.size(); i++) {
			Future<Integer> result = results.get(i);
			try {
				System.out.printf("Main: Task %d: %d\n", i, result.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		executor.shutdown();
	}
	
	/**
	 * http://ifeve.com/thread-executors-5/
	 * 
	 * @see UserValidator
	 * @see TaskValidator
	 */
	public static void userAndTaskValidate() {
		String name = "test";
		String password = "test";
		
		UserValidator ldapValidator = new UserValidator("ldap");
		UserValidator dbValidator = new UserValidator("db");

		TaskValidator ldapTask = new TaskValidator(ldapValidator, name, password);
		TaskValidator dbTask = new TaskValidator(dbValidator, name, password);
		
		List<TaskValidator> tasks = new ArrayList<>();
		tasks.add(ldapTask);
		tasks.add(dbTask);
		
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		String result = null;
		try {
			result = executor.invokeAny(tasks);
			System.out.printf("Main: Result: %s\n", result);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		executor.shutdown();
		System.out.printf("Main: End of the Execution\n");
	}
	
	/**
	 * http://ifeve.com/thread-executors-6/
	 * 
	 * @see ExecutorAllTask
	 * @see ExecutorResult
	 */
	public static void executorResultAndAllTaskMain() {
		ExecutorService service = Executors.newCachedThreadPool();
		List<ExecutorAllTask> tasks = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			ExecutorAllTask task = new ExecutorAllTask(i + "");
			tasks.add(task);
		}
		List<Future<ExecutorResult>> results = null;
		try {
			// 执行所有任务，直到所有任务返回
			results = service.invokeAll(tasks);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		service.shutdown();
		System.out.println("Main: Printing the results");
		for (int i = 0; i < results.size(); i++) {
			Future<ExecutorResult> result = results.get(i);
			try {
				ExecutorResult er = result.get();
				System.out.println(er.getName() + ": " + er.getValue());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * http://ifeve.com/thread-executors-7/
	 * 
	 * @see ScheduledTask
	 */
	public static void scheduledTaskMain() {
		ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
		System.out.printf("Main : Start at : %s\n", new Date());
		for (int i = 0; i < 5; i++) {
			ScheduledTask task = new ScheduledTask("Task " + i);
			// 延迟执行
			executor.schedule(task, i + 1, TimeUnit.SECONDS);
		}
		executor.shutdown();
		try {
			// 等待所有任务完成
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Main : End at : %s", new Date());
	}
	
	/**
	 * http://ifeve.com/thread-executors-8/
	 * 
	 * @see ScheduledRunnable
	 */
	public static void scheduledTaskAndScheduledFutureMain() {
		ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
		System.out.printf("Main : Start at : %s\n", new Date());
		ScheduledRunnable task = new ScheduledRunnable("Task");
		// 延迟 1 秒，间隔 2 秒周期运行任务
		ScheduledFuture<?> result = executor.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);
		for (int i = 0; i < 10; i++) {
			// 获取下次执行任务的时间
			System.out.printf("Main : Delay : %d\n", result.getDelay(TimeUnit.MILLISECONDS));
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		executor.shutdown();
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Main : Finished at : %s\n", new Date());
	}
	
	/**
	 * http://ifeve.com/thread-executors-9/
	 * 
	 * @see CancelTask
	 */
	public static void cancelTaskMain() {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		CancelTask task = new CancelTask();
		System.out.printf("Main: Executing the Task\n");
		// 提交任务
		Future<String> result = executor.submit(task);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Main: Canceling the Task\n");
		// 取消任务
		result.cancel(true);
		System.out.printf("Main: Canceled: %s\n", result.isCancelled());
		System.out.printf("Main: Done: %s\n", result.isDone());
		executor.shutdown();
		System.out.printf("Main: The executor has finished\n");
	}
	
	/**
	 * http://ifeve.com/thread-executors-10/
	 * 
	 * @see ResultTask
	 * @see ExecutableTask
	 */
	public static void executableAndResultTaskMain() {
		ExecutorService executor = Executors.newCachedThreadPool();
		ResultTask[] resultTasks = new ResultTask[5];
		for (int i = 0; i < resultTasks.length; i++) {
			ExecutableTask task = new ExecutableTask("Task " + i);
			resultTasks[i] = new ResultTask(task);
			executor.submit(resultTasks[i]);
		}
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < resultTasks.length; i++) {
			resultTasks[i].cancel(true);
		}
		for (int i = 0; i < resultTasks.length; i++) {
			try {
				if(!resultTasks[i].isCancelled()) {
					System.out.printf("%s\n", resultTasks[i].get());
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		executor.shutdown();
	}
	
	/**
	 * http://ifeve.com/thread-executors-11/
	 * 
	 * @see ReportGenerator
	 * @see ReportRequest
	 * @see ReportProcessor
	 */
	public static void reportGeneratorAndRequestAndProcessorMain() {
		ExecutorService executor = Executors.newCachedThreadPool();
		CompletionService<String> service = new ExecutorCompletionService<>(executor);
		ReportRequest faceReport = new ReportRequest("Face", service);
		ReportRequest onlineReport = new ReportRequest("Online", service);
		ReportProcessor processor = new ReportProcessor(service);
		Thread faceThread = new Thread(faceReport);
		Thread onlineThread = new Thread(onlineReport);
		Thread processorThread = new Thread(processor);
		System.out.printf("Main: Starting the Threads\n");
		faceThread.start();
		onlineThread.start();
		processorThread.start();
		try {
			System.out.printf("Main: Waiting for the report generators.\n");
			faceThread.join();
			onlineThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Main: Shutting down the executor.\n");
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		processor.setEnd(true);
		System.out.println("Main: Ends");
	}
	
	/**
	 * http://ifeve.com/thread-executors-12/
	 * 
	 * @see RejectedTaskController
	 * @see RejectedTask
	 */
	public static void rejectedTaskControllerMain() {
		RejectedTaskController controller = new RejectedTaskController();
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		executor.setRejectedExecutionHandler(controller);
		System.out.printf("Main: Starting.\n");
		for (int i = 0; i < 3; i++) {
			RejectedTask task = new RejectedTask("Task " + i);
			executor.submit(task);
		}
		System.out.printf("Main: Shutting down the Executor.\n");
		executor.shutdown();
		System.out.printf("Main: Sending another Task.\n");
		RejectedTask task=new RejectedTask("RejectedTask");
		// 关闭执行器后再提交任务
		executor.submit(task);
		System.out.printf("Main: End\n");
	}
	
	/**
	 * http://ifeve.com/fork-join-2/
	 * 
	 * @see ProductListGenerator
	 * @see Product
	 * @see RecursiveActionTask
	 */
	public static void productAndRecursiveActionTaskMain() {
		ProductListGenerator generator = new ProductListGenerator();
		List<Product> products = generator.generate(10000);
		if (products != null) {
			RecursiveActionTask task = new RecursiveActionTask(products, 0, products.size(), 0.2);
			ForkJoinPool pool = new ForkJoinPool();
			pool.execute(task);

			do {
				System.out.printf("Main: Thread Count: %d\n", pool.getActiveThreadCount());
				System.out.printf("Main: Thread Steal: %d\n", pool.getStealCount());
				System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
				try {
					TimeUnit.MILLISECONDS.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} while (!task.isDone());
			pool.shutdown();
			if (task.isCompletedNormally()) {
				System.out.printf("Main: The process has completed normally.\n");
			}
			for (int i = 0; i < products.size(); i++) {
				Product product = products.get(i);
				if (product.getPrice() != 12) {
					System.out.printf("Product %s: %f\n", product.getName(), product.getPrice());
				}
			}
			System.out.println("Main: End of the program.\n");
		}
	}
	
	/**
	 * http://ifeve.com/fork-join-3/
	 * 
	 * @see Document
	 * @see DocumentTask
	 * @see LineTask
	 */
	public static void documentAndLineTaskMain() {
		Document document = new Document();
		String targetWord = "the";
		String[][] lines = document.generateDocument(100, 10000, targetWord);
		long startTime = System.currentTimeMillis();
		DocumentTask task = new DocumentTask(lines, 0, lines.length, targetWord);
		ForkJoinPool pool = new ForkJoinPool();
		pool.execute(task);
		do {
//			System.out.printf("******************************************\n");
//			System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
//			System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
//			System.out.printf("Main: Task Count: %d\n", pool.getQueuedTaskCount());
//			System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
//			System.out.printf("******************************************\n");
//			try {
//				TimeUnit.SECONDS.sleep(1);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		} while (!task.isDone());
		pool.shutdown();
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			System.out.printf("Main: The word appears %d in the document\n", task.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		System.out.printf("Main: Total Time : %d ms\n", System.currentTimeMillis() - startTime);
		
		startTime = System.currentTimeMillis();
		int counter = 0;
		for (int i = 0; i < lines.length; i++) {
			for (int j = 0; j < lines[i].length; j++) {
				if (lines[i][j].equals(targetWord)) {
					counter++;
				}
			}
		}
		System.out.printf("Main: The word appears %d in the document\n", counter);
		System.out.printf("Main: Total Time : %d ms\n", System.currentTimeMillis() - startTime);
	}
}

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
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Phaser;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;

import net.saisimon.main.concurrent.*;

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
//		documentAndLineTaskMain();
//		folderProcessorMain();
//		arrayTaskMain();
//		arrayGeneratorAndTaskManagerMain();
//		addAndPollTaskMain();
//		clientMain();
//		priorityEventAndTask();
//		delayedEventAndTask();
//		contactTaskMain();
//		taskLocalRandomMain();
//		atomicAccountAndBankAndCompanyMain();
//		incrementerAndDecrementMain();
//		myExecutorAndSleepTwoSecondsTaskMain();
//		myPriorityTaskMain();
//		myThreadAndThreadFactoryAndTaskMain();
//		myThreadAndThreadFactoryAndTaskAndExecutorMain();
//		myScheduledTaskAndThreadPoolExecutorMain();
//		myWorkThreadAndFactoryAndRecursiveTaskMain();
//		myWorkTaskMain();
//		myLockTaskAndAbstractQueuedSynchronizerMain();
//		myPriorityTransferQueueAndEventAndProducerAndConsumerMain();
		parkingCounterAndSensorMain();
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
	
	/**
	 * http://ifeve.com/fork-join-4/
	 * 
	 * @see FolderProcessor
	 */
	public static void folderProcessorMain() {
		ForkJoinPool pool = new ForkJoinPool();
		FolderProcessor system = new FolderProcessor("C:\\Windows", "log");
		FolderProcessor apps = new FolderProcessor("C:\\Program Files", "log");
		FolderProcessor documents = new FolderProcessor("C:\\Documents And Settings", "log");
		pool.execute(system);
		pool.execute(apps);
		pool.execute(documents);
		do {
			System.out.printf("******************************************\n");
			System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
			System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
			System.out.printf("Main: Task Count: %d\n", pool.getQueuedTaskCount());
			System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
			System.out.printf("******************************************\n");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while ((!system.isDone()) || (!apps.isDone()) || (!documents.isDone()));
		pool.shutdown();
		List<String> results = system.join();
		System.out.printf("System: %d files found.\n", results.size());
		results = apps.join();
		System.out.printf("Apps: %d files found.\n", results.size());
		results = documents.join();
		System.out.printf("Documents: %d files found.\n", results.size());
	}
	
	/**
	 * http://ifeve.com/fork-join-5/
	 * 
	 * @see ArrayTask
	 */
	public static void arrayTaskMain() {
		int[] array = new int[100];
		ArrayTask task = new ArrayTask(array, 0, array.length);
		ForkJoinPool pool = new ForkJoinPool();
		pool.execute(task);
		pool.shutdown();
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (task.isCompletedAbnormally()) {
			System.out.printf("Main: An exception has ocurred\n");
			System.out.printf("Main: %s\n", task.getException());
		}
		System.out.printf("Main: Result: %d", task.join());
	}
	
	/**
	 * http://ifeve.com/fork-join-6/
	 * 
	 * @see ArrayGenerator
	 * @see TaskManager
	 * @see SearchNumberTask
	 */
	public static void arrayGeneratorAndTaskManagerMain() {
		ArrayGenerator generator = new ArrayGenerator();
		int[] array = generator.generateArray(1000);
		TaskManager manager = new TaskManager();
		int targerNumber = 5;
		SearchNumberTask task = new SearchNumberTask(array, 0, array.length, manager, targerNumber);
		manager.addTask(task);
		ForkJoinPool pool = new ForkJoinPool();
		pool.execute(task);
		pool.shutdown();
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Main: The program has finished\n");
	}
	
	/**
	 * http://ifeve.com/concurrent-collections-2/
	 * 
	 * @see AddTask
	 * @see PollTask
	 */
	public static void addAndPollTaskMain() {
		// 非阻塞线程安全列表
		ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();
		Thread[] threads = new Thread[100];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new AddTask(list));
			threads[i].start();
		}
		System.out.printf("Main: %d AddTask threads have been launched\n", threads.length);
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("Main: Size of the List: %d\n", list.size());
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new PollTask(list));
			threads[i].start();
		}
		System.out.printf("Main: %d PollTask threads have been launched\n", threads.length);
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("Main: Size of the List: %d\n", list.size());
	}
	
	/**
	 * http://ifeve.com/concurrent-collections-3/
	 * 
	 * @see Client
	 */
	public static void clientMain() {
		// 阻塞的线程安全列表
		LinkedBlockingDeque<String> list = new LinkedBlockingDeque<>();
		Client client = new Client(list);
		Thread thread = new Thread(client);
		thread.start();

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 3; j++) {
				try {
					// 从列表中获取字符串，如果列表为空，这个方法将阻塞线程的执行，直到列表中有元素
					String request = list.take();
					System.out.printf("Main: Request: %s at %s. Size:%d\n", request, new Date(), list.size());
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.printf("Main: End of the program.\n");
	}
	
	/**
	 * http://ifeve.com/concurrent-collections-4/
	 * 
	 * @see PriorityTask
	 * @see PriorityEvent
	 */
	public static void priorityEventAndTask() {
		// 带优先级的阻塞线程安全列表
		PriorityBlockingQueue<PriorityEvent> queue = new PriorityBlockingQueue<>();
		Thread[] threads = new Thread[5];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new PriorityTask(i, queue));
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("Main: Queue Size: %d\n", queue.size());
		for (int i = 0; i < threads.length * 1000; i++) {
			PriorityEvent event = queue.poll();
			System.out.printf("Thread %s: Priority %d\n", event.getThread(), event.getPriority());
		}
		System.out.printf("Main: Queue Size: %d\n", queue.size());
		System.out.printf("Main: End of the program\n");
	}
	
	/**
	 * http://ifeve.com/concurrent-collections-5/
	 * 
	 * @see DelayedTask
	 * @see DelayedEvent
	 */
	public static void delayedEventAndTask() {
		// 带有延迟元素的线程安全列表
		DelayQueue<DelayedEvent> queue = new DelayQueue<>();
		Thread[] threads = new Thread[5];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new DelayedTask(i, queue));
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		do {
			int counter = 0;
			DelayedEvent event = null;
			do {
				event = queue.poll();
				counter++;
			} while (event != null);
			System.out.printf("At %s you have read %d events\n", new Date(), counter);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (queue.size() > 0);
	}
	
	/**
	 * http://ifeve.com/concurrent-collections-6/
	 * 
	 * @see Contact
	 * @see ContactTask
	 */
	public static void contactTaskMain() {
		ConcurrentSkipListMap<String, Contact> map = new ConcurrentSkipListMap<>();
		Thread[] threads = new Thread[25];
		int counter = 0;
		for (char i = 'A'; i < 'Z'; i++) {
			ContactTask task = new ContactTask(map, String.valueOf(i));
			threads[counter] = new Thread(task);
			threads[counter].start();
			counter++;
		}
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("Main: Size of the map: %d\n", map.size());
		// 获取map的第一个实体
		Map.Entry<String, Contact> entry = map.firstEntry();
		Contact contact = entry.getValue();
		System.out.printf("Main: First Entry: %s: %s\n", contact.getName(), contact.getPhone());
		// 获取map的最后一个实体
		entry = map.lastEntry();
		contact = entry.getValue();
		System.out.printf("Main: Last Entry: %s: %s\n", contact.getName(), contact.getPhone());
		System.out.printf("Main: Submap from A1996 to B1002: \n");
		// 获取map的子map [A1996, B1002)
		ConcurrentNavigableMap<String, Contact> subMap = map.subMap("A1996", "B1002");
		do {
			// 返回并删除submap中的第一个Map.Entry对象
			entry = subMap.pollFirstEntry();
			if (entry != null) {
				contact = entry.getValue();
				System.out.printf("%s: %s\n", contact.getName(), contact.getPhone());
			}
		} while (entry != null);
	}
	
	/**
	 * http://ifeve.com/concurrent-collections-7/
	 * 
	 * @see TaskLocalRandom
	 */
	public static void taskLocalRandomMain() {
		Thread[] threads = new Thread[3];
		for (int i = 0; i < threads.length; i++) {
			TaskLocalRandom task = new TaskLocalRandom();
			threads[i] = new Thread(task);
			threads[i].start();
		}
	}
	
	/**
	 * http://ifeve.com/concurrent-collections-8/
	 * 
	 * Compare And Swap(CAS) 比较并交换
	 * 
	 * @see AtomicAccount
	 * @see AtomicBank
	 * @see AtomicCompany
	 */
	public static void atomicAccountAndBankAndCompanyMain() {
		AtomicAccount account = new AtomicAccount();
		account.setBalance(1000);
		AtomicCompany company = new AtomicCompany(account);
		Thread companyThread = new Thread(company);
		AtomicBank bank = new AtomicBank(account);
		Thread bankThread = new Thread(bank);
		System.out.printf("Account : Initial Balance: %d\n", account.getBalance());
		companyThread.start();
		bankThread.start();
		try {
			companyThread.join();
			bankThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Account : Final Balance: %d\n", account.getBalance());
	}
	
	/**
	 * http://ifeve.com/concurrent-collections-9/
	 * 
	 * @see Incrementer
	 * @see Decrementer
	 */
	public static void incrementerAndDecrementMain() {
		int threadSize = 100;
		// 原子数组
		AtomicIntegerArray vector = new AtomicIntegerArray(1000);
		Incrementer incrementer = new Incrementer(vector);
		Decrementer decrementer = new Decrementer(vector);
		
		Thread[] incrementerThreads = new Thread[threadSize];
		Thread[] decrementerThreads = new Thread[threadSize];
		
		for (int i = 0; i < threadSize; i++) {
			incrementerThreads[i] = new Thread(incrementer);
			decrementerThreads[i] = new Thread(decrementer);
			incrementerThreads[i].start();
			decrementerThreads[i].start();
		}
		
		for (int i = 0; i < threadSize; i++) {
			try {
				incrementerThreads[i].join();
				decrementerThreads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < vector.length(); i++) {
			if (vector.get(i) != 0) {
				System.out.println("Vector[" + i + "] : " + vector.get(i));
			}
		}
		System.out.println("Main: End of the example");
	}
	
	/**
	 * http://ifeve.com/customizing-concurrency-classes-2/
	 * 
	 * @see MyExecutor
	 * @see SleepTwoSecondsTask
	 */
	public static void myExecutorAndSleepTwoSecondsTaskMain() {
		MyExecutor executor = new MyExecutor(2, 4, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());
		List<Future<String>> results = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			SleepTwoSecondsTask task = new SleepTwoSecondsTask();
			Future<String> result = executor.submit(task);
			results.add(result);
		}
		for (int i = 0; i < 5; i++) {
			try {
				String result = results.get(i).get();
				System.out.printf("Main: Result for Task %d : %s\n", i, result);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		executor.shutdown();
		for (int i = 5; i < 10; i++) {
			try {
				String result = results.get(i).get();
				System.out.printf("Main: Result for Task %d : %s\n", i, result);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Main: End of the program.\n");
	}
	
	/**
	 * http://ifeve.com/customizing-concurrency-classes-3/
	 * 
	 * @see MyPriorityTask
	 */
	public static void myPriorityTaskMain() {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 1, TimeUnit.SECONDS,
				// 基于优先级的队列
				new PriorityBlockingQueue<Runnable>());
		for (int i = 0; i < 4; i++) {
			MyPriorityTask task = new MyPriorityTask(i, "Task" + i);
			executor.execute(task);
		}
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 4; i < 8; i++) {
			MyPriorityTask task = new MyPriorityTask(i, "Task" + i);
			executor.execute(task);
		}
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Main: End of the program.\n");
	}
	
	/**
	 * http://ifeve.com/customizing-concurrency-classes-4/
	 * 
	 * @see MyThreadThreadFactory\
	 * @see MyThreadTask
	 * @see MyThread
	 */
	public static void myThreadAndThreadFactoryAndTaskMain() {
		MyThreadThreadFactory factory = new MyThreadThreadFactory("myThreadFactory");
		MyThreadTask task = new MyThreadTask();
		Thread thread = factory.newThread(task);
		try {
			thread.start();
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Main: Thread information.\n");
		System.out.printf("%s\n", thread);
		System.out.printf("Main: End of the example.\n");
	}
	
	/**
	 * http://ifeve.com/customizing-concurrency-classes-5/
	 * 
	 * @see MyThreadThreadFactory\
	 * @see MyThreadTask
	 * @see MyThread
	 */
	public static void myThreadAndThreadFactoryAndTaskAndExecutorMain() {
		MyThreadThreadFactory factory = new MyThreadThreadFactory("myThreadFactory");
		ExecutorService executor = Executors.newCachedThreadPool(factory);
		MyThreadTask task = new MyThreadTask();
		executor.submit(task);
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Main: End of the program.\n");
	}
	
	/**
	 * http://ifeve.com/customizing-concurrency-classes-7-2/
	 * 
	 * @see MyScheduledThreadPoolExecutor
	 * @see MyTask
	 * @see MyScheduledTask
	 */
	public static void myScheduledTaskAndThreadPoolExecutorMain() {
		MyScheduledThreadPoolExecutor executor = new MyScheduledThreadPoolExecutor(2);
		MyTask task = new MyTask();
		System.out.printf("Main: %s\n", new Date());
		executor.schedule(task, 1, TimeUnit.SECONDS);
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		task = new MyTask();
		System.out.printf("Main: %s\n", new Date());
		executor.scheduleAtFixedRate(task, 1, 3, TimeUnit.SECONDS);
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Main: End of the program.\n");
	}
	
	/**
	 * http://ifeve.com/customizing-concurrency-classes-7/
	 * 
	 * @see MyWorkThreadFactory
	 * @see MyWorkThread
	 * @see MyRecursiveTask
	 */
	public static void myWorkThreadAndFactoryAndRecursiveTaskMain() {
		MyWorkThreadFactory factory = new MyWorkThreadFactory();
		ForkJoinPool pool = new ForkJoinPool(4, factory, null, false);
		int[] array = new int[100000];
		for (int i = 0; i < array.length; i++) {
			array[i] = i;
		}
		MyRecursiveTask task = new MyRecursiveTask(array, 0, array.length);
		pool.execute(task);
		pool.shutdown();
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			System.out.printf("Main: Result: %d\n", task.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		System.out.printf("Main: End of the program\n");
	}
	
	/**
	 * http://ifeve.com/customizing-concurrency-classes-9-2/
	 * 
	 * @see WorkTask
	 * @see MyWorkTask
	 */
	public static void myWorkTaskMain() {
		ForkJoinPool pool = new ForkJoinPool();
		int[] array = new int[10000];
		WorkTask task = new WorkTask("Task", array, 0, array.length);
		pool.execute(task);
		pool.shutdown();
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Main: End of the program.\n");
	}
	
	/**
	 * http://ifeve.com/customizing-concurrency-classes-9/
	 * 
	 * @see MyLock
	 * @see MyLockTask
	 * @see MyAbstractQueuedSynchronizer
	 */
	public static void myLockTaskAndAbstractQueuedSynchronizerMain() {
		MyLock lock = new MyLock();
		for (int i = 0; i < 10; i++) {
			MyLockTask task = new MyLockTask(lock, "Task" + i);
			new Thread(task).start();
		}
		boolean value;
		do {
			try {
				value = lock.tryLock(1, TimeUnit.SECONDS);
				if (!value) {
					System.out.printf("Main: Trying to get the Lock\n");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				value = false;
			}
		} while (!value);
		System.out.printf("Main: Got the lock\n");
		lock.unlock();
		System.out.printf("Main: End of the program\n");
	}
	
	/**
	 * http://ifeve.com/customizing-concurrency-classes-11-2/
	 * 
	 * @see MyPriorityTransferQueue
	 * @see MyProducer
	 * @see MyConsumer
	 * @see MyEvent
	 */
	public static void myPriorityTransferQueueAndEventAndProducerAndConsumerMain() {
		MyPriorityTransferQueue<MyEvent> buffer = new MyPriorityTransferQueue<>();
		MyProducer producer = new MyProducer(buffer);
		Thread[] threads = new Thread[10];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(producer);
			threads[i].start();
		}
		MyConsumer consumer = new MyConsumer(buffer);
		Thread consumerThread = new Thread(consumer);
		consumerThread.start();
		System.out.printf("Main: Buffer: Consumer count: %d\n", buffer.getWaitingConsumerCount());
		MyEvent myEvent = new MyEvent("Core Event", 0);
		try {
			buffer.transfer(myEvent);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Main: My Event has ben transfered.\n");
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Main: Buffer: Consumer count: %d\n", buffer.getWaitingConsumerCount());
		myEvent = new MyEvent("Core Event 2", 0);
		try {
			buffer.transfer(myEvent);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			consumerThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Main: End of the program\n");
	}
	
	/**
	 * http://ifeve.com/customizing-concurrency-classes-11/
	 * 
	 * @see ParkingCounter
	 * @see SensorA
	 * @see SensorB
	 */
	public static void parkingCounterAndSensorMain() {
		ParkingCounter counter = new ParkingCounter(100);
		SensorA sa = new SensorA(counter);
		SensorB sb = new SensorB(counter);
		Thread ta = new Thread(sa);
		Thread tb = new Thread(sb);
		ta.start();
		tb.start();
		try {
			ta.join();
			tb.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Main: Number of cars: %d\n", counter.get());
		System.out.printf("Main: End of the program.\n");
	}
}

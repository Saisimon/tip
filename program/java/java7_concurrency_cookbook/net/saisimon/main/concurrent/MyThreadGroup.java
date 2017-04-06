package net.saisimon.main.concurrent;

public class MyThreadGroup extends ThreadGroup {

	public MyThreadGroup(String name) {
		super(name);
	}
	
	// 重写 uncaughtException() 方法，对线程中出现的未检查异常进行处理
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.printf("The thread %s has thrown an Exception\n", t.getId());
		e.printStackTrace(System.out);
		System.out.printf("Terminating the rest of the Threads\n");
		interrupt();
	}
	
}

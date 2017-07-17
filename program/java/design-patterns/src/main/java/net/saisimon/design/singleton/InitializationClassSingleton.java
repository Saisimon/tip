package net.saisimon.design.singleton;

/**
 * @author Saisimon
 * 
 * 	使用类初始化机制，获取延迟加载并且线程安全的单例对象
 *
 */
public class InitializationClassSingleton {
	
	/**
	 * 隐藏直接实例化对象
	 */
	private InitializationClassSingleton() {}
	
	/**
	 * @author Saisimon
	 * 
	 * 通过 Initialization On Demand Holder idiom 方式来获取线程安全的单例对象。
	 * 
	 * 根据 Java 语言规范中规定在类初始化阶段，Java 虚拟机会去获取一个锁，这个锁可以同步多个线程对同一个类的初始化。
	 * 在获取锁的线程执行完类初始化之前，使用该类的其他线程会等待，初始化完成之后会获得锁的线程会唤醒所有使用该类等待的线程。
	 *
	 */
	private static class SingletonHolder {
		// final 修饰符防止 _instance 被修改
		final static InitializationClassSingleton _instance = new InitializationClassSingleton();
	}
	
	/**
	 * 静态方法获取单例对象
	 * 
	 * @return 单例对象
	 */
	public static InitializationClassSingleton getInstance() {
		// 会初始化 SingletonHolder 类
		return SingletonHolder._instance;
	}
	
}

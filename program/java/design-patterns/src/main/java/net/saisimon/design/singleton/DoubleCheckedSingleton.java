package net.saisimon.design.singleton;

/**
 * @author Saisimon
 * 
 * 	使用双重检查锁定并且防止代码重排的机制，获取延迟加载并且线程安全的单例对象
 * 
 */
public class DoubleCheckedSingleton {
	
	/**
	 * 使用 volatile 修饰符来防止代码重排
	 * 这里如果不加 volatile 修饰符，可能会产生有的线程获取到未被完全初始化的单例对象，问题的根源是代码的重排造成的。
	 * http://www.infoq.com/cn/articles/double-checked-locking-with-delay-initialization
	 * 文章中有详细的分析
	 */
	private volatile static DoubleCheckedSingleton _instance;
	
	/**
	 * 隐藏直接实例化对象
	 */
	private DoubleCheckedSingleton() {}
	
	public static DoubleCheckedSingleton getInstance() {
		if (null == _instance) { //第一次检查
			synchronized (DoubleCheckedSingleton.class) { // 加锁
				if (null == _instance) { //第二次检查
					_instance = new DoubleCheckedSingleton();
				}
			}
		}
		return _instance;
	}
	
}

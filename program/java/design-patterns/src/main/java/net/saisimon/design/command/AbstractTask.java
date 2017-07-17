package net.saisimon.design.command;

/**
 * @author Saisimon
 *
 *	抽象任务
 *
 */
public abstract class AbstractTask implements Task {
	
	/**
	 * 任务间隔
	 */
	private long timeInterval;
	/**
	 * 上次完成时间
	 */
	private long timeLastDone;
	
	public AbstractTask(long timeInterval) {
		super();
		this.timeInterval = timeInterval;
	}
	
	public long getTimeInterval() {
		return timeInterval;
	}
	public void setTimeInterval(long timeInterval) {
		this.timeInterval = timeInterval;
	}
	public long getTimeLastDone() {
		return timeLastDone;
	}
	public void setTimeLastDone(long timeLastDone) {
		this.timeLastDone = timeLastDone;
	}
	
}

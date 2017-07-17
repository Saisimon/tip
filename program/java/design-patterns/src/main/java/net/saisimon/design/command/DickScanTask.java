package net.saisimon.design.command;

import java.util.Date;

/**
 * @author Saisimon
 * 
 * 	硬盘扫描任务
 *
 */
public class DickScanTask extends AbstractTask {

	public DickScanTask(long timeInterval) {
		super(timeInterval);
	}

	public void taskPerform() {
		System.out.println("ScanDisk Task has been performed at " + new Date());
	}
	
}

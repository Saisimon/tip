package net.saisimon.design.command;

import java.util.Date;

/**
 * @author Saisimon
 * 
 * 	备份任务
 *
 */
public class BackupTask extends AbstractTask {

	public BackupTask(long timeInterval) {
		super(timeInterval);
	}

	public void taskPerform() {
		System.out.println("Backup Task has been performed at " + new Date());
	}
	
}

package net.saisimon.main.concurrent;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedEvent implements Delayed {
	
	private Date startDate;

	public DelayedEvent(Date startDate) {
		super();
		this.startDate = startDate;
	}

	@Override
	public int compareTo(Delayed o) {
		long result = this.getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
		if (result > 0) {
			return 1;
		} else if (result < 0) {
			return -1;
		} else {
			return 0;
		}
	}

	@Override
	public long getDelay(TimeUnit unit) {
		Date d = new Date();
		long diff = startDate.getTime() - d.getTime();
		return unit.convert(diff, TimeUnit.MILLISECONDS);
	}

}

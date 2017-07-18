package net.saisimon.design.bridge;

import java.util.Collection;
import java.util.Iterator;

public abstract class Report {
	
	// 组合抽象类或接口，建立桥梁
	private Reporter reporter;

	public Report(Reporter reporter) {
		this.reporter = reporter;
	}
	
	public Reporter getReporter() {
		return reporter;
	}
	
	public void addReportLine(Object obj) {
		getReporter().addLine(obj.toString());
	}
	
	public void addReportLine(Collection<?> objs) {
		Iterator<?> it = objs.iterator();
		while (it.hasNext()) {
			addReportLine(it.next());
		}
	}

}

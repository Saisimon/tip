package net.saisimon.design.bridge;

import java.util.ArrayList;
import java.util.List;

public class StockListReport extends Report {
	
	List<Object> list = new ArrayList<>();

	public StockListReport(Reporter reporter) {
		super(reporter);
	}
	
	public void addStock(Object stock) {
		list.add(stock);
		addReportLine(stock);
	}

}

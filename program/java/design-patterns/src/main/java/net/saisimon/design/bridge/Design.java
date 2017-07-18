package net.saisimon.design.bridge;

public class Design {
	
	public static void main(String[] args) {
		Report report = new StockListReport(new HTMLReporter());
		report.addReportLine("<h1>stock</h1>");
		report.addReportLine("<h1>goods</h1>");
		System.out.println("HTML : ");
		System.out.println(report.getReporter().getReportContent());
		
		System.out.println();
		
		report = new StockListReport(new ASCIIReporter());
		report.addReportLine("stock");
		report.addReportLine("goods");
		System.out.println("ASCII : ");
		System.out.println(report.getReporter().getReportContent());
	}
	
}

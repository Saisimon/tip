package net.saisimon.design.bridge;

public abstract class Reporter {
	
	private String header = "";
	private String trailer = "";
	private String report = "";
	
	public void setHeader(String header) {
		this.header = header;
	}
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	
	public abstract void addLine(String line);
	
	public String getReportContent() {
		return header + report + trailer;
	}
	
}

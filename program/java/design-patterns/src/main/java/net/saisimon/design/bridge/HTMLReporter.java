package net.saisimon.design.bridge;

public class HTMLReporter extends Reporter {
	
	public HTMLReporter() {
		setHeader("<!DOCTYPE html>\n<HTML>\n<HEAD></HEAD>\n<BODY>\n");
		setTrailer("</BODY>\n</HTML>");
	}

	@Override
	public void addLine(String line) {
		String report = getReport();
		report += line + "<BR>\n";
		setReport(report);
	}

}

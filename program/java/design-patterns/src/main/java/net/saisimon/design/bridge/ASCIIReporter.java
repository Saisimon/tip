package net.saisimon.design.bridge;

public class ASCIIReporter extends Reporter {

	@Override
	public void addLine(String line) {
		String report = getReport();
		report += line + "\n";
		setReport(report);
	}

}

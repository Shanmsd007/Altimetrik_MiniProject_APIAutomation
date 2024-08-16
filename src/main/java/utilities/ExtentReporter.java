package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {

	public static ExtentReports getExtentReportObj() {
		String path = System.getProperty("user.dir") + "//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Test execution reports");
		reporter.config().setDocumentTitle("API Automation report");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Shanmugam");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("JVM", System.getProperty("java.runtime.version"));
		extent.setSystemInfo("User Directory", System.getProperty("user.dir"));
		extent.setSystemInfo("User", System.getProperty("user.name"));
		return extent;
	}

}

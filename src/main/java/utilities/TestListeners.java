package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TestListeners implements ITestListener {

	ExtentTest test;
	ExtentReports extent = ExtentReporter.getExtentReportObj();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Case: " + result.getMethod().getMethodName() + " is passed.");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		extentTest.get().log(Status.FAIL, "Test Case: " + result.getMethod().getMethodName() + " is failed.");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.get().skip(result.getThrowable());
		extentTest.get().log(Status.SKIP, "Test Case: " + result.getMethod().getMethodName() + " is skipped.");

	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}

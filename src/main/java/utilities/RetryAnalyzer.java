package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	int failedcount = 0;
	int retrylimit = 3;

	@Override
	public boolean retry(ITestResult result) {

		if (failedcount < retrylimit) {
			++failedcount;
			return true;

		}

		return false;
	}

}

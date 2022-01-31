package Utility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import TestCases.base;



public class TestNGListener  extends base implements ITestListener,ISuiteListener{


	String screenshotPath;

	public void onTestStart(ITestResult result) {

	}

	public void onTestSuccess(ITestResult result) {
		test.log(LogStatus.PASS, "Login test is Passed"+result.getMethod().getMethodName());	

		Report.endTest(test);
		Report.flush();
	}

	public void onTestFailure(ITestResult result) {


		test.log(LogStatus.FAIL, "Login test is failed"+result.getMethod().getMethodName());
		test.log(LogStatus.FAIL,"Login test is failed"+result.getThrowable());

		try {
			screenshotPath = TakeScreenShot.getScreenShot(driver,result.getName());


		} catch (IOException e) {
			e.printStackTrace();
		}
		String imagePath=test.addScreenCapture(screenshotPath);
		test.log(LogStatus.FAIL, "Test Failed",imagePath);


		Report.endTest(test);
		Report.flush();
	}

	public void onTestSkipped(ITestResult result) {
		test.log(LogStatus.SKIP, "Login test is skipped"+result.getMethod().getMethodName());

		Report.endTest(test);
		Report.flush();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {

	}

	public void onStart(ISuite suite) {

	}

	public void onFinish(ISuite suite) {

	}

}












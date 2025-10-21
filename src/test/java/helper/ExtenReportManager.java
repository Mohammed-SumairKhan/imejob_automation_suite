package helper;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import driverproperties.BrowserHandler;
import utility.PropertiesReader;

public class ExtenReportManager implements ITestListener {
	ExtentSparkReporter extentSparkReporter; // handle UI reports
	ExtentReports extentReports; // used to add info in report
	ExtentTest extentTest; // creates/log test cases entries

	@Override
	public void onStart(ITestContext context) {
		String path = System.getProperty("user.dir") + "\\reports\\myReport.html";
		extentSparkReporter = new ExtentSparkReporter(path);
		extentSparkReporter.config().setDocumentTitle("Automation Testing");
		extentSparkReporter.config().setReportName("Functional & Navigation Testing");

		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);

		extentReports.setSystemInfo("Tester", "Mohammed Sumair Khan");
		extentReports.setSystemInfo("Project", "ImeJob");
		extentReports.setSystemInfo("Environment", "QA");
		extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
		extentReports.setSystemInfo("Browser", "Chrome"); // You can also read from config.properties
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest = extentReports.createTest(result.getName());
		extentTest.log(Status.PASS, "Test passed: " + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest = extentReports.createTest(result.getName());
		extentTest.log(Status.FAIL, "Test failed: " + result.getName());
		extentTest.log(Status.FAIL, "Reason for Fail: " + result.getThrowable());

		try {
			PropertiesReader propertiesReader = new PropertiesReader();
			String browserName = propertiesReader.getBrowserName();
			TakesScreenshot takesScreenshot = (TakesScreenshot) BrowserHandler.getBrowser(browserName);
			File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir") + "//screenshots//" + result.getName() + ".png";
			File targetFile = new File(path);
			sourceFile.renameTo(targetFile);
			extentTest.fail("ScreenShot of Failure: ", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest = extentReports.createTest(result.getName());
		extentTest.log(Status.FAIL, "Test is Skipped: " + result.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReports.flush();
	}

}

package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static final ExtentReports extent = ExtentReportManager.getInstance();
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String testDescription = result.getMethod().getDescription();
        ExtentTest test = extent.createTest(
            testName,
            testDescription != null ? testDescription : ""
        );
        extentTest.set(test);
        extentTest.get().log(Status.INFO, "Test Started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "✅ Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().log(Status.FAIL, "❌ Test Failed: " + result.getThrowable());
        try {
            Object testInstance = result.getInstance();
            if (testInstance instanceof BaseTest) {
                BaseTest base = (BaseTest) testInstance;
                if (base.driver != null) {
                    String path = ScreenshotUtil.captureScreenshot(
                        base.driver, result.getMethod().getMethodName()
                    );
                    extentTest.get().fail("Screenshot:",
                        MediaEntityBuilder.createScreenCaptureFromPath(path).build()
                    );
                }
            }
        } catch (Exception e) {
            extentTest.get().log(Status.WARNING, "Screenshot failed: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "⚠️ Test Skipped: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        System.out.println("\n📊 Extent Report generated: reports/ExtentReport.html\n");
    }
}
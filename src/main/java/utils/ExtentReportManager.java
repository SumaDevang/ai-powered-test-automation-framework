package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter =
                new ExtentSparkReporter("reports/ExtentReport.html");

            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setDocumentTitle("AI-Powered Test Automation Report");
            sparkReporter.config().setReportName("Regression Test Results");
            sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("Framework",  "Selenium WebDriver + TestNG");
            extent.setSystemInfo("Author",     "Suma Shekar");
            extent.setSystemInfo("Environment","QA");
            extent.setSystemInfo("Browser",    "Chrome");
            extent.setSystemInfo("AI Engine",  "OpenAI GPT-4");
        }
        return extent;
    }
}
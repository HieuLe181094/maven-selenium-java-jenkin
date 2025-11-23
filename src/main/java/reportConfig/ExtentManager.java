package reportConfig;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import commons.GlobalConstants;

import java.util.HashMap;
import java.util.Map;

public class ExtentManager {

    private static ExtentReports extentReports;
    private static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();

    // Tạo ExtentReports 1 lần duy nhất
    public synchronized static ExtentReports createExtentReports() {

        if (extentReports == null) {

            ExtentSparkReporter reporter = new ExtentSparkReporter(
                    GlobalConstants.EXTENT_PATH + "ExtentReport.html");

            reporter.config().setReportName("TechPanda HTML Report");
            reporter.config().setDocumentTitle("TechPanda HTML Report");
            reporter.config().setTimelineEnabled(true);
            reporter.config().setEncoding("utf-8");
            reporter.config().setTheme(Theme.DARK);

            extentReports = new ExtentReports();
            extentReports.attachReporter(reporter);

            extentReports.setSystemInfo("Company", "Automation FC");
            extentReports.setSystemInfo("Project", "TechPanda");
            extentReports.setSystemInfo("Team", "Basus VN");
            extentReports.setSystemInfo("JDK version", GlobalConstants.JAVA_VERSION);
            extentReports.setSystemInfo("OS version", GlobalConstants.OS_NAME);
        }

        return extentReports;
    }

    // Lấy ExtentReports
    public static synchronized ExtentReports getExtentReports() {
        return extentReports;
    }

    // Set ExtentTest cho từng thread
    public static synchronized void setTest(ExtentTest test) {
        extentTestMap.put((int) Thread.currentThread().getId(), test);
    }

    // Lấy ExtentTest hiện tại
    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }
}

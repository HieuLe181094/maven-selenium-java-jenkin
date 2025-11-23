package reportConfig;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.MediaEntityBuilder;

import commons.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static reportConfig.ExtentManager.getTest;

public class ExtentTestListener extends BaseTest implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        ExtentManager.createExtentReports();
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getExtentReports().flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentManager.setTest(
                ExtentManager.getExtentReports().createTest(result.getName())
        );
    }


    @Override
    public void onTestSuccess(ITestResult result) {
        getTest().log(Status.PASS,
                MarkupHelper.createLabel(result.getName() + " - Passed", ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();

        String base64Screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BASE64);

        getTest().fail(result.getThrowable(),
                MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());

        getTest().log(Status.FAIL,
                MarkupHelper.createLabel(result.getName() + " - Failed", ExtentColor.RED));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        getTest().log(Status.SKIP,
                MarkupHelper.createLabel(result.getName() + " - Skipped", ExtentColor.ORANGE));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
}

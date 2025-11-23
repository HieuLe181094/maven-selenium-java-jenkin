package commons;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Random;


public class BaseTest {
    public WebDriver getDriver() {
        return driver;
    }

    protected WebDriver driver;
    protected final Logger log;

    public BaseTest() {
        log = LogManager.getLogger(getClass());
    }

    protected int generateFakeNumber() {
        Random rand = new Random();
        return rand.nextInt(9999);
    }

    // Dev Environment
    protected WebDriver getBrowserDriver(String url, String browserName) {

        Path path = null;
        File extensionFilePath = null;

        BrowserType browserType = BrowserType.valueOf(browserName.toUpperCase());

        switch (browserName){
            case "edge":
                EdgeOptions edgeOption = new EdgeOptions();
                edgeOption.addArguments("--inprivate");
                driver = new EdgeDriver(edgeOption);
                break;

            case "chrome":
                ChromeOptions cOptions = new ChromeOptions();
                driver = new ChromeDriver(cOptions);
                break;

            case "coccoc":
                ChromeOptions ccOptions = new ChromeOptions();
                ccOptions.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
                driver = new ChromeDriver(ccOptions);
                break;

            case "internetExplorer":
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.destructivelyEnsureCleanSession();
                ieOptions.ignoreZoomSettings();
                ieOptions.introduceFlakinessByIgnoringSecurityDomains();
                driver = new InternetExplorerDriver(ieOptions);
                break;

            case "firefox":
                FirefoxOptions fOptions = new FirefoxOptions();
                driver = new FirefoxDriver(fOptions);
                break;

            case "safari":
                SafariOptions sOptions = new SafariOptions();
                driver = new SafariDriver(sOptions);
                break;
            default:
                throw new IllegalArgumentException("Browser name is not valid");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }

    protected void closeBrowserDriver() {
        String cmd = null;
        try {
            String osName = System.getProperty("os.name").toLowerCase();
            log.info("OS name = " + osName);

            String driverInstanceName = driver.toString().toLowerCase();
            log.info("Driver instance name = " + driverInstanceName);

            String browserDriverName = null;

            if (driverInstanceName.contains("chrome")) {
                browserDriverName = "chromedriver";
            } else if (driverInstanceName.contains("firefox")) {
                browserDriverName = "geckodriver";
            } else if (driverInstanceName.contains("edge")) {
                browserDriverName = "msedgedriver";
            } else if (driverInstanceName.contains("opera")) {
                browserDriverName = "operadriver";
            } else {
                browserDriverName = "safaridriver";
            }

            if (osName.contains("window")) {
                cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
            } else {
                cmd = "pkill " + browserDriverName;
            }

            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.quit();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected void assertTrue(boolean condition){
        Assert.assertTrue(verifyTrue(condition));
    }

    protected boolean verifyTrue(boolean condition){
        boolean status = true;
        try {
            Assert.assertTrue(condition);
            log.info("-----------------PASSED-----------------");
        } catch (Throwable e) {
            status = false;
            log.info("-----------------FAILED-----------------");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;
    }

    protected boolean verifyFalse(boolean condition){
        boolean status = true;
        try {
            Assert.assertFalse(condition);
            log.info("-----------------PASSED-----------------");
        } catch (Throwable e) {
            status = false;
            log.info("-----------------FAILED-----------------");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean status = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info("-----------------PASSED-----------------");
        } catch (Throwable e) {
            status = false;
            log.info("-----------------FAILED-----------------");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;
    }

    @BeforeSuite
    public void deleteFileInReport() {
        // Remove all file in ReportNG screenshot (image)
        deleteAllFileInFolder("reportNGImage");

        // Remove all file in Allure attachment (json file)
        deleteAllFileInFolder("allure-json");
        deleteAllFileInFolder("allure-results");

    }


    public void deleteAllFileInFolder(String folderName) {
        try {
            String pathFolderDownload = GlobalConstants.PROJECT_PATH + File.separator + folderName;
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            if (listOfFiles.length != 0) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
                        new File(listOfFiles[i].toString()).delete();
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}

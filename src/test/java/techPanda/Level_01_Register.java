package techPanda;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_01_Register {
    WebDriver driver;
    String emailAddress;


    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        emailAddress = "afc" + generateFakeNumber() + "@mail.vn";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Test
    public void Login_01_Empty_Email() throws InterruptedException {
        driver.get("https://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.cssSelector("button#send2")).click();

        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(),
                "This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(),
                "This is a required field.");

    }

    @Test
    public void Login_02_Invalid_Email() throws InterruptedException  {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.cssSelector("input#email")).sendKeys("123434234@12312.123123");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#send2")).click();

        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email-email")).getText(),
                "Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void Login_03_Invalid_Password() throws InterruptedException {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.cssSelector("input#email")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123");
        driver.findElement(By.cssSelector("button#send2")).click();

        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-password-pass")).getText(),
                "Please enter 6 or more characters without leading or trailing spaces.");

    }

//    @Test
//    public void Login_04_Incorrect_Email_Password() {
//        driver.get("http://live.techpanda.org/");
//        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
//        driver.findElement(By.cssSelector("input#email")).sendKeys("automationfc.13042024@gmail.com");
//        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456789");
//        driver.findElement(By.cssSelector("button#send2")).click();
//
//        Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(),
//                "Invalid login or password.");
//
//
//    }

    @Test
    public void TC_05 (){

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public int generateFakeNumber() {
        Random rand = new Random();
        return rand.nextInt(9999);
    }


}
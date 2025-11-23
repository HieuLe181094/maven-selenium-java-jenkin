package pageObjects.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class AdminProductPageObject extends BasePage {
    WebDriver driver;

    public AdminProductPageObject(WebDriver driver) {
        this.driver = driver;
    }
}

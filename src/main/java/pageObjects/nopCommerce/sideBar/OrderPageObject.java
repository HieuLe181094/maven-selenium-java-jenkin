package pageObjects.nopCommerce.sideBar;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class OrderPageObject extends SideBarPageObject {
    WebDriver driver;

    public OrderPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}

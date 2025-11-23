package pageObjects.nopCommerce.sideBar;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class AddressPageObject extends SideBarPageObject {
    WebDriver driver;

    public AddressPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}

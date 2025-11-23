package pageObjects.nopCommerce.sideBar;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class RewardPointPageObject extends SideBarPageObject{
    WebDriver driver;

    public RewardPointPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

}

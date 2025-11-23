package pageObjects.nopCommerce.sideBar;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.sideBar.CustommerPageUI;

public class UserCustomerInforPageObject extends SideBarPageObject {
    WebDriver driver;

    public UserCustomerInforPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Get Firstname textbox value ")
    public String getFirstNameTextboxValue() {
        waitForElementVisible(driver,CustommerPageUI.FIRST_NAME_TEXTBOX);
        return getElementAttribute(driver,CustommerPageUI.FIRST_NAME_TEXTBOX,"value");
    }

    @Step("Get Lastname textbox value ")
    public String getLastNameTextboxValue() {
        waitForElementVisible(driver,CustommerPageUI.LAST_NAME_TEXTBOX);
        return getElementAttribute(driver,CustommerPageUI.LAST_NAME_TEXTBOX,"value");
    }

    @Step("Get Email textbox value ")
    public String getEmailAddressTextboxValue() {
        waitForElementVisible(driver,CustommerPageUI.EMAIL_TEXTBOX);
        return getElementAttribute(driver,CustommerPageUI.EMAIL_TEXTBOX,"value");
    }



}




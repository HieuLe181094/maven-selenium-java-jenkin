package pageObjects.nopCommerce;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.nopCommerce.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage {
    WebDriver driver;

    public AdminLoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Enter to Email Textbox with value: {0}")
    public void enterToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, AdminLoginPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, AdminLoginPageUI.EMAIL_TEXTBOX, emailAddress);
    }

    @Step("Enter to Password Textbox with value: {0}")
    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, password);
    }

    @Step("Login into the Admin system after clicking on the LOGIN button")
    public AdminDashboardPageObject clickLoginButton() {
        waitForElementClickable(driver, AdminLoginPageUI.ADMIN_LOGIN_BUTTON);
        clickToElement(driver, AdminLoginPageUI.ADMIN_LOGIN_BUTTON);
        return PageGenerator.getPageInstance(AdminDashboardPageObject.class,driver);
    }
}

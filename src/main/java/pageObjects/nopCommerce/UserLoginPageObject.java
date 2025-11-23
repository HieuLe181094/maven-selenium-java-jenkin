package pageObjects.nopCommerce;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.nopCommerce.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {
    private WebDriver driver;

    public UserLoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

//    public LoginPageObject loginToSystem(String emailAddress, String password){
//        enterToEmailTextbox(emailAddress);
//        enterToPasswordTextbox(password);
//        clickToLoginButton();
//        return new LoginPageObject(driver);
//    }

    @Step("Enter to Email Textbox with value: {0}")
    public void enterToEmailTextbox(String email) {
        waitForElementVisible(driver, UserLoginPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX,email);
    }

    @Step("Enter to Password Textbox with value: {0}")
    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX,password);
    }

    @Step("Login to System after clicking on the LOGIN button")
    public UserHomePageObject clickToLoginButton() {
        waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getPageInstance(UserHomePageObject.class,driver);
    }
}

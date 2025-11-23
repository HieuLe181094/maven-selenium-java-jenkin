package pageObjects.nopCommerce;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.BasePageUI;
import pageUIs.nopCommerce.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage {
    private WebDriver driver;

    public UserRegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Enter to Firstname Textbox with value: {0}")
    public void enterToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
    }

    @Step("Enter to Lastname Textbox with value: {0}")
    public void enterToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX,lastName);
    }

    @Step("Enter to Email Textbox with value: {0}")
    public void enterToEmailTextbox(String email) {
        waitForElementVisible(driver, UserRegisterPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPageUI.EMAIL_TEXTBOX,email);
    }

    @Step("Enter to Password Textbox with value: {0}")
    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX,password);
    }

    public void enterToCompanyNameTextbox(String companyName) {
        waitForElementVisible(driver, UserRegisterPageUI.COMPANY_NAME_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPageUI.COMPANY_NAME_TEXTBOX, companyName);
    }

    @Step("Enter to Confirm Password Textbox with value: {0}")
    public void enterToConformPasswordTextbox(String confirmPassword) {
        waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendkeyToElement(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX,confirmPassword);
    }

    @Step("Register to System after clicking on the REGISTER button")
    public void clickToRegisterButton() {
        waitForElementClickable(driver, UserRegisterPageUI.REGISTER_BUTTON);
        clickToElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
    }

    @Step("Verify the Register success message is displayed")
    public String getRegisterSuccessMessage() {
        waitForElementVisible(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
    }

    @Step("Verify the Register Title Page is displayed")
    public String getRegisterPageTitle() {
        waitForElementVisible(driver, UserRegisterPageUI.REGISTER_PAGE_TITLE);
        return getElementText(driver, UserRegisterPageUI.REGISTER_PAGE_TITLE);
    }


}

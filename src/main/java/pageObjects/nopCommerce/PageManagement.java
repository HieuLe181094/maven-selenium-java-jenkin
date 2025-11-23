package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;
import pageObjects.nopCommerce.sideBar.UserCustomerInforPageObject;

public class PageManagement {

    // Áp dụng Factory Pattern để quản lý việc khởi tạo Page Object
    public static Object getPage(WebDriver driver, String pageName) {
        switch (pageName) {
            case "HomePage":
                return new UserHomePageObject(driver);
            case "LoginPage":
                return new UserLoginPageObject(driver);
            case "RegisterPage":
                return new UserRegisterPageObject(driver);
            case "CustomerInfoPage":
                return new UserCustomerInforPageObject(driver);
            default:
                return new IllegalArgumentException("Page Name is not valid.");
        }
    }
}

package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.nopCommerce.UserHomePageObject;
import pageObjects.nopCommerce.UserLoginPageObject;
import pageObjects.nopCommerce.UserRegisterPageObject;
import pageObjects.nopCommerce.sideBar.AddressPageObject;
import pageObjects.nopCommerce.sideBar.UserCustomerInforPageObject;

import java.lang.reflect.Constructor;

public class PageGenerator {

    public static <T extends BasePage> T getPageInstance(Class<T> pageClass, WebDriver driver){
        try {
            // Lấy constructor nhận WebDriver
            Constructor<T> constructor = pageClass.getConstructor(WebDriver.class);
            // Tạo instance mới của page class
            return constructor.newInstance(driver);
        } catch (Exception e){
            throw new RuntimeException("Can not init page class" + pageClass.getSimpleName(),e);
        }
    }

    // Viết các đoạn khởi tạo Page Object class thành các hàm (tái sử dụng) + tính chất đóng gói
    // Gọi qua các Re-Usable Method trong Page Generator này
    public static UserHomePageObject getHomePage(WebDriver driver){
        return new UserHomePageObject(driver);
    }

    public static UserLoginPageObject getLoginPage(WebDriver driver){
        return new UserLoginPageObject(driver);
    }

    public static UserRegisterPageObject getRegisterPage(WebDriver driver){
        return new UserRegisterPageObject(driver);
    }

    public static UserCustomerInforPageObject getCustomerInfoPage(WebDriver driver){
        return new UserCustomerInforPageObject(driver);
    }

    public static AddressPageObject getAddressPage(WebDriver driver){
        return new AddressPageObject(driver);
    }
}

package pageObjects.nopCommerce.sideBar;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.nopCommerce.BasePageUI;
import pageUIs.nopCommerce.sideBar.SideBarPageUI;

public class SideBarPageObject extends BasePage {
    WebDriver driver;

    public SideBarPageObject(WebDriver driver) {
        this.driver = driver;
    }

    // Option 1
    public Object openSideBarByPageName(String pageName){
        waitForElementClickable(driver, SideBarPageUI.DYNAMIC_SIDEBAR_LINK_BY_NAME, pageName);
        clickToElement(driver, SideBarPageUI.DYNAMIC_SIDEBAR_LINK_BY_NAME, pageName);

        switch (pageName) {
            case "Addresses":
                return PageGenerator.getPageInstance(AddressPageObject.class, driver);
            case "Reward points":
                return PageGenerator.getPageInstance(RewardPointPageObject.class, driver);
            case "Customer info":
                return PageGenerator.getPageInstance(UserCustomerInforPageObject.class, driver);
            case "Orders":
                return PageGenerator.getPageInstance(OrderPageObject.class, driver);
            default:
                return null;
        }
    }

    // Option 2
    public void openSideBar(String pageName){
        waitForElementClickable(driver, SideBarPageUI.DYNAMIC_SIDEBAR_LINK_BY_NAME, pageName);
        clickToElement(driver, SideBarPageUI.DYNAMIC_SIDEBAR_LINK_BY_NAME, pageName);
    }

    // Open page at the My Account Page side
    public AddressPageObject openAddressPage(WebDriver driver) {
        waitForElementVisible(driver, SideBarPageUI.ADDRESS_LINK);
        clickToElement(driver, SideBarPageUI.ADDRESS_LINK);
        return PageGenerator.getPageInstance(AddressPageObject.class, this.driver);
    }

    public RewardPointPageObject openRewardPointPage(WebDriver driver) {
        waitForElementVisible(driver, BasePageUI.REWARD_POINT_LINK);
        clickToElement(driver, SideBarPageUI.REWARD_POINT_LINK);
        return PageGenerator.getPageInstance(RewardPointPageObject.class,driver);
    }

    public UserCustomerInforPageObject openCustomerPage(WebDriver driver) {
        waitForElementVisible(driver, SideBarPageUI.CUSTOMER_INFO_LINK);
        clickToElement(driver, SideBarPageUI.CUSTOMER_INFO_LINK);
        return PageGenerator.getPageInstance(UserCustomerInforPageObject.class, driver);
    }

    public OrderPageObject openOrderPage(WebDriver driver) {
        waitForElementVisible(driver, SideBarPageUI.ORDER_LINK);
        clickToElement(driver, SideBarPageUI.ORDER_LINK);
        return PageGenerator.getPageInstance(OrderPageObject.class,driver);
    }

    public String getNewAddressesSuccessMessage() {
        waitForElementVisible(driver, SideBarPageUI.NEW_ADDRESSED_SUCCESS_MESSAGE);
        return getElementText(driver, SideBarPageUI.NEW_ADDRESSED_SUCCESS_MESSAGE);
    }
}

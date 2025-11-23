package pageUIs.nopCommerce;

public class BasePageUI {
    // User Site
    public static final String REWARD_POINT_LINK = "Xpath=//div[contains(@class,'account-navigation')]//a[text()='Reward points']";
    public static final String ADDRESS_LINK = "Xpath=//div[contains(@class,'account-navigation')]//a[text()='Addresses']";
    public static final String CUSTOMER_INFO_LINK = "Xpath=//div[contains(@class,'account-navigation')]//a[text()='Customer info']";
    public static final String ORDER_LINK = "Xpath=//div[contains(@class,'account-navigation')]//a[text()='Orders']";

    public static final String REGISTER_LINK = "Class=ico-register";
    public static final String USER_LOGIN_LINK = "Xpath=//a[@class='ico-login']";
    public static final String USER_LOGOUT_LINK = "Xpath=//a[@class='ico-logout']";
    public static final String USER_MY_ACCOUNT_LINK = "Xpath=//a[@class='ico-account']";

    // Admin Site
    public static final String ADMIN_LOGOUT_LINK = "Xpath=//a[text()='Logout']";
    public static final String ADMIN_PRODUCT_MENU = "Xpath=//p[text()=' Products']/ancestor::li[contains(@class,'has-treeview')]";
    public static final String ADMIN_PRODUCT_SUBMENU = "Xpath=//p[text()=' Products']";

    // Component
    public static final String TEXTBOX_BY_ID = "Xpath=//input[@id='%s']";
    public static final String BUTTON_BY_ID = "Xpath=//button[text()='%s']";
    public static final String CHECKBOX_BY_ID = "Xpath=//input[@id='%s']";
    public static final String RADIO_BY_ID = "Xpath=//input[@id='%s']";
    public static final String DROPDOWN_BY_ID = "Xpath=//select[@id='%s']";
    public static final String DYNAMIC_ADDRESSES_INFO_BY_TEXT = "Xpath=//ul[@class='info']/li[contains(text(),'%s')]";
    public static final String DYNAMIC_ADDRESSES_INFO_BY_LABEL = "Xpath=//ul[@class='info']/li/label[contains(text(),'%s')]";
    public static final String DYNAMIC_ADDRESSES_INFO_BY_CLASS = "Xpath=//ul[@class='info']/li[contains(@class='%s']";

    //
    public static final String FIREFOX_BUTTON_BY_TEXT = "Xpath=//button[text()='%s']";
    public static final String CHROME_BUTTON_BY_TEXT = "Xpath=//button[text()='%s']";
    public static final String EDGE_BUTTON_BY_TEXT = "Xpath=//button[text()='%s']";


}

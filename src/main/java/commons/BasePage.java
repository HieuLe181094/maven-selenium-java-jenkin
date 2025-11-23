package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.PageGenerator;
import pageObjects.nopCommerce.*;
import pageObjects.nopCommerce.sideBar.UserCustomerInforPageObject;
import pageUIs.HomePageUI;
import pageUIs.nopCommerce.*;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {

    // 1 hàm static có thể gọi trực tiếp từ class mà ko cần thông qua đối tượng
    public static BasePage getBasePage() {
        return new BasePage();
    }

    private WebDriver driver;

    private long LONG_TIMEOUT = 30;

    // Chứa các common (resuable) function cho các class bên Page Object
    // 1 - Access Modifier: public/ protected/ private/ default
    // 2 - Kiểu dữ liệu cua hàm (Data type): void/ int/ String/ boolean/ WebElement/ List<WebElements>.
    // Nó sẽ liên quan đến cái chức năng mình viết trong thân hàm
    // 3 - Tên hàm: Đặt tên có nghĩa theo chức năng đang cần viết
    // Convention tuân theo chuẩn của từng ngôn ngữ lập trình (Java)
    // cameClass: từ đầu tiên viết thường - chữ cái đầu tiên của các từ tiếp thoe sẽ viết ôa
    // 4 - Có tham số hay ko (tùy vào chức năng cần viết)
    // 5 - Kiểu dữ liệu trả về cho hàm
    // Nếu như có return dữ liệu thì sẽ khớp với kiểu dữ liệu ở số 2
    // Nếu như có return thì nó là các step cuối cùng

//    protected void clickToElement(By by){
//        driver.findElement(by).click();
//    }
//
//    protected void clickToElement(WebDriver driver, String locator){
//        WebDriver driverSecond;
//
//        if (driver.toString().contains("Chrome")){
//            WebDriver driverThird;
//        }
//
//        driver.findElement(By.xpath(locator)).click();
//    }
//
//    protected String getElementText(WebDriver driver, String locator){
//        return driver.findElement(By.xpath(locator)).getText();
//    }

    public void openPageUrl(WebDriver driver, String pageUrl){
        driver.get(pageUrl);
    }

    public String getPageTitle(WebDriver driver){
        return driver.getTitle();
    }

    public String getCurrentUrl(WebDriver driver){
       return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver){
       return driver.getPageSource();
    }

    public void backToPage(WebDriver driver){
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver){
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver){
        driver.navigate().refresh();
    }



    private Alert waitToAlertPresence(WebDriver driver){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver){
        waitToAlertPresence(driver).accept();
    }

    public void cancelAlert(WebDriver driver){
        waitToAlertPresence(driver).dismiss();
    }

    public void sendkeyToAlert(WebDriver driver, String valueToSend){
        waitToAlertPresence(driver).sendKeys(valueToSend);
    }

    public String getTextAlert(WebDriver driver){
        return waitToAlertPresence(driver).getText();
    }

    private void closeAllWindowWithoutParent(WebDriver driver, String parentID) throws InterruptedException {
        // Lấy hết toàn bộ ID của window/tab
        Set<String> allWindowIDs = driver.getWindowHandles();

        // Dùng vùng lặp duyệt qua từng ID
        for (String runWindows : allWindowIDs) {
            if (!runWindows.equals(parentID)){
                driver.switchTo().window(runWindows);
                sleepInSecond(LONG_TIMEOUT);
                driver.close();
            }
        }
        // Switch vào window duy nhất còn lại
        driver.switchTo().window(parentID);
    }

    private void switchWindowByTitle(String expectedPageTitle) throws InterruptedException {
        // Lấy hết toàn bộ ID của window/tab
        Set<String> allWindowIDs = driver.getWindowHandles();

        // Dùng vùng lặp duyệt qua từng ID
        for (String id : allWindowIDs) {
            // Mỗi lần duyệt sẽ cho nó switch vào trước
            driver.switchTo().window(id);
            sleepInSecond(LONG_TIMEOUT);

            // Get ra Title của window/tab hiện tại
            String pageTitle = driver.getTitle();

            // Kiểm tra title
            if (pageTitle.equals(expectedPageTitle)){
                break;
            }
        }
    }

    // Chỉ đúng với 2 Tab/window
    private void switchToWindowByID(String windowID) {
        // Lấy ra hết tất cả ID của window/tab hiện tại
        Set<String> allWindowIDs = driver.getWindowHandles();

        // Dùng vòng lặp để duyệt qua từng ID môt
        for (String id: allWindowIDs) {
            // Kiểm tra điều kiện: nếu mà ID nào khác với ID mong đợi thì switch qua
            if (!id.equals(windowID)){
                driver.switchTo().window(id);
            }
        }}

    private String castLocator(String locator, String... value){
        return String.format(locator, (Object[]) value);
    }

    private By getByXpath(String locator) {
        return By.xpath(locator);
    }

    public By getByLocator (String locator) {
        if (locator.isEmpty() || locator == null){
            throw new RuntimeException("Locator type cannot be null or empty");
        }

        switch (locator.split("=")[0].toLowerCase()){
            case "xpath":
                return By.xpath(locator.substring(6));
            case "css":
                return By.cssSelector(locator.substring(4));
            case "id":
                return By.id(locator.substring(3));
            case "class":
                return By.className(locator.substring(6));
            case "name":
                return By.name(locator.substring(5));
            default:
                throw new IllegalArgumentException("Locator type is not support.");
        }
    }

    public Set<Cookie>  getAllCookies(WebDriver driver){
        return driver.manage().getCookies();
    }

    public void setCookies(WebDriver driver, Set<Cookie> cookies){
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
        sleepInSecond(3);
    }


    protected WebElement getWebElement(WebDriver driver, String locator) {
        return driver.findElement(getByLocator(locator));
    }

    public  List<WebElement> getListWebElement(WebDriver driver, String locator) {
        return driver.findElements(getByLocator(locator));
    }

    public  List<WebElement> getListWebElement(WebDriver driver, String locator, String... restParam) {
        return driver.findElements(getByLocator(castLocator(locator, restParam)));
    }

    public void clickToElement(WebDriver driver, String locator){
        if (driver.toString().contains("InternetExplorer")){
            clickToElementByJS(driver, locator);
            sleepInSecond(2);
        } else {
            getWebElement(driver, locator);
        }
    }

    public void clickToElement(WebDriver driver, String locator, String... restParam){
        if (driver.toString().contains("InternetExplorer")){
            clickToElementByJS(driver, locator);
            sleepInSecond(2);
        } else {
            getWebElement(driver, locator);
        }
    }

    public void sendkeyToElement(WebDriver driver, String locator, String valueToSend) {
        Keys key = null;
        if (GlobalConstants.OS_NAME.startsWith("Windows")){
            key = Keys.CONTROL;
        } else {
            key = Keys.COMMAND;
        }
        getWebElement(driver,locator).sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.BACK_SPACE));
        sleepInSecond(1);
        getWebElement(driver,locator).sendKeys(valueToSend);
    }

    public void sendkeyToElement(WebDriver driver, String locator, String valueToSend, String... restParam){
        WebElement element = getWebElement(driver, castLocator(locator, restParam));
        element.clear();
        element.sendKeys(valueToSend);
    }

    public void selectItemDropdown(WebDriver driver, String locator, String textItem) {
        new Select(getWebElement(driver, locator)).selectByVisibleText(textItem);
    }

    public void selectItemDropdown(WebDriver driver, String locator, String textItem, String... restPram) {
        new Select(getWebElement(driver, castLocator(locator, restPram))).selectByVisibleText(textItem);
    }

    public String getSelectItemInDropdown(WebDriver driver, String locator) {
        return new Select(getWebElement(driver,locator)).getFirstSelectedOption().getText();
    }

    public String getSelectItemInDropdown(WebDriver driver, String locator, String... restParam) {
        return new Select(getWebElement(driver, castLocator(locator, restParam))).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        return new Select(getWebElement(driver,locator)).isMultiple();
    }

    public void selectItemCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String expectedItem)  {
      getWebElement(driver, parentLocator).click();
      sleepInSecond(2);

      List<WebElement> allItems = new  WebDriverWait(driver, Duration.ofSeconds(15))
              .until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
      sleepInSecond(2);

      for (WebElement item: allItems) {
          if (item.getText().trim().equals(expectedItem)){
              item.click();
              break;
          }
      }
    }

//    public void selectItemCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String textItem)  {
//        WebDriverWait explicitWait = new WebDriverWait(driver,Duration.ofSeconds(LONG_TIMEOUT));
//        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(parentLocator))).click();
//        sleepInSecond(2);
//
//        List<WebElement> allItems = waitForListElementPresence(driver,childLocator);
//        for (WebElement item: allItems){
//            if (item.getText().equals(textItem)){
//                item.click();
//                break;
//            }
//        }
//    }

    public void sleepInSecond(long timeInSecond)  {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getElementText(WebDriver driver, String locator) {
        return getWebElement(driver, locator).getText();
    }

    public String getElementText(WebDriver driver, String locator, String... restParam) {
        return getWebElement(driver, castLocator(locator, restParam)).getText();
    }

    public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
        return getWebElement(driver,locator).getAttribute(attributeName);
    }

    public String getElementAttribute(WebDriver driver, String locator, String attributeName, String... restParam) {
        return getWebElement(driver, castLocator(locator, restParam)).getAttribute(attributeName);
    }

    public String getCssValue(WebDriver driver, String locator, String propertyName) {
        return getWebElement(driver,locator).getCssValue(propertyName);
    }

    public String getCssValue(WebDriver driver, String locator, String propertyName, String... restParam) {
        return getWebElement(driver,castLocator(locator, restParam)).getCssValue(propertyName);
    }

    public String getHexaByRGB(String rgbValue) {
        return Color.fromString(rgbValue).asHex().toUpperCase();
    }

    public Dimension getElementSize(WebDriver driver, String locator){
        return getWebElement(driver, locator).getSize();
    }

    public int getListElementSize(WebDriver driver, String locator, String restParam) {
        return getListWebElement(driver, castLocator(locator, restParam)).size();
    }

    public void checkToCheckboxRadio(WebDriver driver, String locator) {
            if (!getWebElement(driver,locator).isSelected()){
                getWebElement(driver,locator).click();
            }
    }

    public void checkToCheckboxRadio(WebDriver driver, String locator, String... restParam) {
        if (!getWebElement(driver,castLocator(locator, restParam)).isSelected()){
            getWebElement(driver,castLocator(locator, restParam)).click();
        }
    }

    public void uncheckToCheckboxRadio(WebDriver driver, String locator ){
        if (getWebElement(driver,locator).isSelected()){
            getWebElement(driver,locator).click();
        }
    }

    public void uncheckToCheckboxRadio(WebDriver driver, String locator, String... restParam ){
        if (getWebElement(driver,castLocator(locator, restParam)).isSelected()){
            getWebElement(driver,castLocator(locator, restParam)).click();
        }
    }

    // Implicit wait
    // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
    public void overideGlobalTimeout(WebDriver driver, long timeInSecond){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSecond));
    }

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        List<WebElement> elements = getListWebElement(driver,locator);

        return getWebElement(driver, castLocator(locator)).isDisplayed();
    }

    public boolean isElementDisplayed(WebDriver driver, String locator, String... restParam) {
        return getWebElement(driver, castLocator(locator, restParam)).isDisplayed();
    }

    public boolean isElementUndisplayed(WebDriver driver, String locator){
        overideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        List<WebElement> elements = getListWebElement(driver, locator);
        overideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);

        if (elements.size() == 0){
            System.out.println("Case 3 - Verify Email Error Message is not displayed (non- present)");
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            System.out.println("Case 2 - Verify Email Error Message is not displayed (present)");
            return true;
        } else {
            System.out.println("Case 1 - Verify Email Error Message is displayed (visible)");
            return false;
        }
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getWebElement(driver,locator).isSelected();
    }

    public boolean isElementSelected(WebDriver driver, String locator, String... restParam) {
        return getWebElement(driver,castLocator(locator, restParam)).isSelected();
    }

    public boolean isElementEnable(WebDriver driver, String locator) {
        return getWebElement(driver,locator).isEnabled();
    }

    public WebDriver switchToIframe(WebDriver driver, String locator){
        return driver.switchTo().frame(getWebElement(driver,locator));
    }

    public WebDriver switchToDefaultContent(WebDriver driver){
        return driver.switchTo().defaultContent();
    }

    public void hoverToElement(WebDriver driver, String locator){
        new Actions(driver).moveToElement(getWebElement(driver,locator)).perform();
    }

    public void doubleToElement(WebDriver driver, String locator){
        new Actions(driver).doubleClick(getWebElement(driver,locator)).perform();
    }

    public void rightClickToElement(WebDriver driver, String locator){
        new Actions(driver).contextClick(getWebElement(driver,locator)).perform();
    }

    public void scrollToElement(WebDriver driver, String locator){
        new Actions(driver).scrollToElement(getWebElement(driver,locator)).perform();
    }

    public void sendkeyBoardToElement(WebDriver driver, String locator, Keys key){
        new Actions(driver).sendKeys(getWebElement(driver,locator),key).perform();
    }

    public void sendkeyBoardToElement(WebDriver driver, String locator, Keys key, String... values){
        new Actions(driver).sendKeys(getWebElement(driver,castLocator(locator, values)),key).perform();
    }

    public String getDomain(WebDriver driver) {
        return (String) ((JavascriptExecutor) driver).executeScript("return document.domain;");
    }

    public String getInnerText(WebDriver driver) {
        return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
    }

    public void hightlightElement(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver,locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void hightlightElement(WebDriver driver, String locator, String... restParam) {
        WebElement element = getWebElement(driver, castLocator(locator, restParam));
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver,String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver,locator));
        sleepInSecond(3);
    }

    public void clickToElementByJS(WebDriver driver,String locator, String... restParam) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
                getWebElement(driver, castLocator(locator, restParam)));
        sleepInSecond(3);
    }

    public String getElementTextByJS(WebDriver driver,String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;", getWebElement(driver,locator));
    }

    public void scrollToElementOnTop(WebDriver driver,String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver,locator));
    }

    public void scrollToElementOnDown(WebDriver driver,String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver,locator));
    }

    public void setAttributeInDOM(WebDriver driver,String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getWebElement(driver,locator));
    }

    public void removeAttributeInDOM(WebDriver driver,String locator, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver,locator));
    }

    public void sendkeyToElementByJS(WebDriver driver,String locator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver,locator));
    }

    public String getAttributeInDOM(WebDriver driver,String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(driver,locator));
    }

    public String getElementValidationMessage(WebDriver driver,String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver,locator));
    }

    public boolean isImageLoaded(WebDriver driver,String locator) {
        return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0"
                , getWebElement(driver,locator));
    }

    public Boolean waitForElementAttributeToBe(WebDriver driver,String locator, String attributeName, String attributeValue) {
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.attributeContains(getByLocator(locator), attributeName, attributeValue));
    }

    public Boolean waitForElementAttributeToBe(WebDriver driver,String locator, String attributeName, String attributeValue, String... restParam) {
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.attributeToBe(getByLocator(castLocator(locator, restParam)), attributeName, attributeValue));
    }

    public Boolean waitForElementAttributeContain(WebDriver driver,String locator, String attributeName, String attributeValue) {
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.attributeContains(getByLocator(locator), attributeName, attributeValue));
    }

    public Boolean waitForElementAttributeContain(WebDriver driver,String locator, String attributeName, String attributeValue, String... restParam) {
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.attributeContains(getByLocator(castLocator(locator, restParam)), attributeName, attributeValue));
    }

    public WebElement waitForElementVisible(WebDriver driver,String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public WebElement waitForElementVisible(WebDriver driver,String locator, String... restParam){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(getByLocator(castLocator(locator, restParam))));
    }

    public List<WebElement> waitForListElementVisible(WebDriver driver,String locator){
        return new WebDriverWait(driver,Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public boolean waitForElementInvisible(WebDriver driver,String locator){
        return new WebDriverWait(driver,Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    public boolean waitForElementInvisible(WebDriver driver,String locator, String... restParam){
        return new WebDriverWait(driver,Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castLocator(locator, restParam))));
    }

    public boolean waitForListElementInvisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).
                until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locator)));
    }

    public WebElement waitForElementClickable(WebDriver driver,String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public WebElement waitForElementClickable(WebDriver driver,String locator, String... restParam) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(getByLocator(castLocator(locator, restParam))));
    }

    public WebElement waitForElementPresence(WebDriver driver,String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
    }

    public List<WebElement> waitForListElementPresence(WebDriver driver,String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public boolean waitToElementSelected(WebDriver driver,String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).
                until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
    }

    public boolean waitToElementSelected(WebDriver driver,String locator, String... restParam){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).
                until(ExpectedConditions.elementToBeSelected(getByLocator(castLocator(locator, restParam))));
    }

    public void uploadMultipleFiles(WebDriver driver, String... fileNames){
        // Lấy ra đường dẫn của thư mục upload file
        String filePath = GlobalConstants.UPLOAD_PATH;

        // By inputBy = By.xpath("//input[@type='file']");
        // driver.findElement(inputBy).sendKeys(bodyPath + "\n" + heartPath + "\n" + intelligentPath);
        String fullFileName = "";

        // Dùng vòng lặp duyệt qua các file name
        for (String file: fileNames) {
            fullFileName += filePath +file + "\n";
        }

        // Cắt kí tự xuống dòng (\n) ở 2 đầu chuỗi đi
        fullFileName = fullFileName.trim();}}

        // Sendkey
//        getWebElement(driver, HomePageUI.UPLOAD_FILE_TYPE).sendKeys(fullFileName);
//    }

    // Only use for NopCommerce
//    public UserCustomerInforPageObject clickToMyAccountLinkAtUserSite(WebDriver driver) {
//        waitForElementClickable(driver, BasePageUI.USER_MY_ACCOUNT_LINK);
//        clickToElement(driver, BasePageUI.USER_MY_ACCOUNT_LINK);
//        return PageGenerator.getPageInstance(UserCustomerInforPageObject.class,driver);
//    }
//
//    public UserHomePageObject clickToLogoutLinkAtUserSite(WebDriver driver) {
//        waitForElementClickable(driver, BasePageUI.USER_LOGOUT_LINK);
//        clickToElement(driver, BasePageUI.USER_LOGOUT_LINK);
//        return PageGenerator.getPageInstance(UserHomePageObject.class,driver);
//    }
//
//    public AdminLoginPageObject openAdminSite(WebDriver driver, String adminUrl) {
//        openPageUrl(driver, adminUrl);
//        return  PageGenerator.getPageInstance(AdminLoginPageObject.class, driver);
//    }















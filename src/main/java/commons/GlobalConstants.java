package commons;

public class GlobalConstants {
    // HẰNG SỐ
    // static final: Java
    // public: access modifier vì nó tính chất toàn cục
    // Luôn viết hoa - nếu có nhiều từ thì phân cách bởi dấu _

    // Short/ Long Timeout
    public static  final long SHORT_TIMEOUT = 5;
    public static final long LONG_TIMEOUT = 30;

    // App Server Url
    public static final String DEV_URL = "https://demo.nopcommerce.com/";
    public static final String LOCAL_DEV_URL = "http://localhost:8086/";
    public static final String TEST_URL = "http://test.localhost/";
    public static final String LIVE_URL = "http://live.localhost/";

    // OS Info
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String JAVA_VERSION = System.getProperty("java.version");
    public static final String SEPARATOR = System.getProperty("file.separator");

    // Admin User/ Pass
    public static final String ADMIN_USERNAME = "hiuhiu@gmail.com";
    public static final String ADMIN_PASSWORD = "hiuhiu@gmail.com";

    // Upload/ Download
    public static final String UPLOAD_PATH = PROJECT_PATH + SEPARATOR + "uploadFiles" + SEPARATOR;
    public static final String DOWNLOAD_PATH = PROJECT_PATH + SEPARATOR + "downloadFiles" + SEPARATOR;

    // Browser Logs/ Extension
    public  static final String BROWSER_LOG_PATH = PROJECT_PATH + SEPARATOR + "browserLogs" + SEPARATOR;
    public  static final String BROWSER_EXTENSION_PATH = PROJECT_PATH + SEPARATOR + "browserExtensions" + SEPARATOR;

    // HTML Report Folder
    public static final String REPORTING_PATH = PROJECT_PATH + SEPARATOR + "htmlReportNG" + SEPARATOR;
    public static final String EXTENT_PATH = PROJECT_PATH + SEPARATOR + "htmlExtent" + SEPARATOR;
    public static final String ALLURE_PATH = PROJECT_PATH + SEPARATOR + "htmlAllure" + SEPARATOR;

    // Data Test/ Environment
    public static final String DATA_TEST_PATH = PROJECT_PATH + SEPARATOR + "dataTest" + SEPARATOR;
    public static final String ENVIRONMENT_CONFIG_PATH = PROJECT_PATH + SEPARATOR + "envConfig" + SEPARATOR;

    public static final String JIRA_SITE_URL = "https://lengoduchieu-auto.atlassian.net/";
    public static final String JIRA_USERNAME = "lengoduchieu@gmail.com";
    public static final String ATLASSIAN_API_TOKEN = System.getenv("ATLASSIAN_API_TOKEN");
    public static final String JIRA_PROJECT_KEY ="SCRUM";

}

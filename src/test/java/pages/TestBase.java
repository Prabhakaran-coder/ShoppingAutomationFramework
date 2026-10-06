package pages;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class TestBase{

    Playwright playwright;
    Browser browser;
    public Page page;
    public String BaseUrl;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        Properties SystemProps = new Properties();
        FileInputStream fis = new FileInputStream("src/test/java/resources/objects.properties");
        SystemProps.load(fis);
        playwright = Playwright.create();
        String env = System.getProperty("env")!=null ? System.getProperty("env") : SystemProps.getProperty("env");
        String BrowserName = System.getProperty("browser")!=null?System.getProperty("browser"): SystemProps.getProperty("browser");
        browser = switch (BrowserName) {
            case "chrome" -> playwright.chromium().launch();
            case "firefox" -> playwright.firefox().launch();
            
            case "safari" -> playwright.webkit().launch();
            default -> throw new IllegalArgumentException("Invalid browser name: " + SystemProps.getProperty("browser"));
        };
        BaseUrl = SystemProps.getProperty(env + ".url");
        //page.setViewportSize(1920, 1080);
        page = browser.newPage();
        //page.navigate(BaseUrl);
    } 

}

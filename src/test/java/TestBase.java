import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.*;

/**
 * Created by Noop on 22.06.2017.
 */
public class TestBase {

    @BeforeSuite
    public static void openBrowser() {
        setupBrowser("chrome");
        timeout = 5000;
        startMaximized = true;
        open("https://www.lebara.com/global/en");
    }

    @AfterSuite
    public static void closeBrowser() {
        close();
    }

    protected WebDriver getTab(int index) throws NoSuchWindowException {
        ArrayList<String> tabs = new ArrayList<>(getWebDriver().getWindowHandles());
        return getWebDriver().switchTo().window(tabs.get(index));
    }

    private static void setupBrowser(String browser) {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
        if (browser.equalsIgnoreCase(CHROME)) {
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
            options.addArguments("start-maximized", "--disable-infobars", "disable-extensions:true");
            ChromeDriverManager.getInstance().setup();
            WebDriverRunner.setWebDriver(new ChromeDriver(options));
        } else if (browser.equalsIgnoreCase(FIREFOX)) {
            FirefoxDriverManager.getInstance().setup();
            WebDriverRunner.setWebDriver(new FirefoxDriver());
        }
    }

    @Test
    public void test1() {
        $(".logo-main").should(exist);
    }


}

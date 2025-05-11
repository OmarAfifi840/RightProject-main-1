package One_Browser_In_Order;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.ConfigReader;

import java.time.Duration;

import static One_Browser_In_Order.AllActions.driver;

public class AllRequests {

    @BeforeTest
    public static void startBrowser() {
        String browser = ConfigReader.get("browser");
        String url = ConfigReader.get("url");
        long implicitWait = Long.parseLong(ConfigReader.get("implicitWait"));

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            throw new RuntimeException("Browser not supported");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.manage().window().maximize();
        driver.get(url);
    }

    @Test

    public void allrequests () throws InterruptedException{
        AllActions.performLogin();
        AllActions.Leave();
        AllActions.LeavesDetails();
        Thread.sleep(10000);
        AllActions.Mission();
        AllActions.MissionDetails();
        Thread.sleep(5000);
        AllActions.Permission();
        AllActions.PermissionDetails();
        AllActions.WFH();
        AllActions.WFHDetails();
    }
}

package Parallel.Singles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigReader;

import java.time.Duration;

public class WFHReq {

    public static WebDriver driver;
    ////////////////////////////// captureSwalMessage :) /////////////////////////////////
    @BeforeClass
    public static void captureSwalMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement swalPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-popup")));

            String title = "";
            try {
                WebElement swalTitle = swalPopup.findElement(By.className("swal2-title"));
                title = swalTitle.getText();
            } catch (Exception ignored) {}

            String message = "";
            try {
                WebElement swalText = swalPopup.findElement(By.className("swal2-html-container"));
                message = swalText.getText();
            } catch (Exception ignored) {}

            if (!title.isEmpty() || !message.isEmpty()) {
                System.out.println("Swal Message: " + title + " - " + message);
            } else {
                System.out.println("Swal popup appeared but no message was found.");
            }

            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.className("swal2-popup")));

        } catch (Exception e) {
            System.out.println("No Swal message appeared.");
        }
    }
    /////////////////////////// Permission Request :) /////////////////////////////////
    @Test

    public static void WFHRequest() throws InterruptedException {
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

        /////////////////////////// Perform Login :) /////////////////////////////////

        String userName = ConfigReader.get("userName");
        String password = ConfigReader.get("password");

        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#kt_login_signin_form > div.fv-row.mb-8 > input")).sendKeys(userName);
        driver.findElement(By.cssSelector("#kt_login_signin_form > div:nth-child(3) > input")).sendKeys(password);
        driver.findElement(By.id("kt_sign_in_submit")).click();

        System.out.println("Login");
        captureSwalMessage();

        /////////////////////////// WFH Request Screen :) /////////////////////////////////


            Actions actions = new Actions(driver);
            WebElement newRequest = driver.findElement(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > span > span.menu-title"));
            actions.moveToElement(newRequest).perform();

            WebElement timeManagement = driver.findElement(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > div > div:nth-child(3) > span > span.menu-title"));
            actions.moveToElement(timeManagement).perform();

            WebElement WFH = driver.findElement(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > div > div:nth-child(3) > div > div:nth-child(4) > a > span.menu-title"));
            actions.moveToElement(WFH).click().perform();

        String fromDate = ConfigReader.get("FromDateEmployeeWFH");
        String todate = ConfigReader.get("ToDateEmployeeWFH");
        String notes = ConfigReader.get("NotesWFHEmployee");
        if (driver.findElement(By.xpath("/html/body/div[1]/app-layout/div/div/div/div/div/app-content/div/app-wfhrequest/div/div/div[2]/div/form/div[1]/div[1]/div/app-ss-employee-select/div/mat-form-field/div/div[1]/div[3]/mat-select/div/div[1]/span/span")) != null) {
            Thread.sleep(10000);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(240));

            driver.findElement(By.xpath("/html/body/div[1]/app-layout/div/div/div/div/div/app-content/div/app-wfhrequest/div/div/div[2]/div/form/div[1]/div[2]/div/mat-form-field/div/div[1]/div[3]/input")).sendKeys(fromDate);
            driver.findElement(By.xpath("/html/body/div[1]/app-layout/div/div/div/div/div/app-content/div/app-wfhrequest/div/div/div[2]/div/form/div[1]/div[3]/div/mat-form-field/div/div[1]/div[3]/input")).sendKeys(todate);
            driver.findElement(By.xpath("/html/body/div[1]/app-layout/div/div/div/div/div/app-content/div/app-wfhrequest/div/div/div[2]/div/form/div[2]/div[4]/div/mat-form-field/div/div[1]/div[3]/textarea")).sendKeys(notes);
            driver.findElement(By.xpath("/html/body/div[1]/app-layout/div/div/div/div/div/app-content/div/app-wfhrequest/div/div/div[3]/button/span[1]")).click();

            System.out.println("WFH");

            System.out.println("WFH");
            captureSwalMessage();
        }
    }
}

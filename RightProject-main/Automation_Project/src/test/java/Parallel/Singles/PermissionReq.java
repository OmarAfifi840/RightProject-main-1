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

public class PermissionReq {

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

    public static void PermissionRequest() throws InterruptedException {

        /////////////////////////// Start Browser :) /////////////////////////////////

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

        /////////////////////////// Permission Request Screen :) /////////////////////////////////

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > span > span.menu-title")));

        Actions actions = new Actions(driver);
        WebElement newRequest = driver.findElement(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > span > span.menu-title"));
        actions.moveToElement(newRequest).perform();

        WebElement timeManagement = driver.findElement(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > div > div:nth-child(3) > span > span.menu-title"));
        actions.moveToElement(timeManagement).perform();

        WebElement permission = driver.findElement(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > div > div:nth-child(3) > div > div:nth-child(3) > a > span.menu-title"));
        actions.moveToElement(permission).click().perform();

        System.out.println("Permission");
        captureSwalMessage();

        /////////////////////////// Permission Request Details :) /////////////////////////////////

        String fromDate = ConfigReader.get("fromDatePerMissionRequest");
        String fromTime = ConfigReader.get("FromTime");
        String toTime = ConfigReader.get("ToTime");
        String notesPermission = ConfigReader.get("notesPermission");

        Thread.sleep(10000);
        WebElement fromDateField = driver.findElement(By.id("mat-input-1"));
        fromDateField.sendKeys("Test");
        fromDateField.clear();
        fromDateField.sendKeys(fromDate);

        Thread.sleep(10000);
        driver.findElement(By.cssSelector("#signInTimepicker")).sendKeys(fromTime);
        driver.findElement(By.cssSelector("#signOutTimepicker")).sendKeys(toTime);
        driver.findElement(By.cssSelector("#mat-input-24")).sendKeys(notesPermission);

        driver.findElement(By.cssSelector("#kt_app_content_container > app-permission-request > div > div > div.card-footer.d-flex.justify-content-end.pt-0 > button > span.indicator-label")).click();

        System.out.println("Permission");
        captureSwalMessage();


    }
}

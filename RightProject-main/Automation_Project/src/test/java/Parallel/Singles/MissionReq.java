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

public class MissionReq {

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
    /////////////////////////// Mission Request :) /////////////////////////////////
    @Test

    public static void MissionRequest() throws InterruptedException {
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

        /////////////////////////// Mission Request Screen :) /////////////////////////////////

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > span > span.menu-title")));

        Actions actions = new Actions(driver);
        WebElement newRequest = driver.findElement(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > span > span.menu-title"));
        actions.moveToElement(newRequest).perform();

        WebElement timeManagement = driver.findElement(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > div > div:nth-child(3) > span > span.menu-title"));
        actions.moveToElement(timeManagement).perform();

        WebElement mission = driver.findElement(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > div > div:nth-child(3) > div > div:nth-child(2) > a > span.menu-title"));
        actions.moveToElement(mission).click().perform();

        System.out.println("Mission");
        captureSwalMessage();

        /////////////////////////// Mission Request Details :) /////////////////////////////////

        String fromDate = ConfigReader.get("fromDateMissionRequest");
        String toDate = ConfigReader.get("toDateMissionRequest");
        String notesMission = ConfigReader.get("notesMission");

        Thread.sleep(10000);
        WebElement fromDateField = driver.findElement(By.id("mat-input-0"));
        fromDateField.sendKeys("Test");
        fromDateField.clear();
        fromDateField.sendKeys(fromDate);

        WebElement toDateField = driver.findElement(By.id("mat-input-1"));
        toDateField.sendKeys("Test");
        toDateField.clear();
        toDateField.sendKeys(toDate);

        Thread.sleep(10000);
        driver.findElement(By.xpath("/html/body/div[1]/app-layout/div/div/div/div/div/app-content/div/app-mission-request/div/div/div[2]/div/form/div[2]/div[1]/div/mat-form-field/div/div[1]/div[3]/mat-select/div/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div/mat-option[2]/span")).click();

        WebElement note = driver.findElement(By.cssSelector("#mat-input-13"));
        note.click();
        note.sendKeys(notesMission);

        driver.findElement(By.cssSelector("#kt_app_content_container > app-mission-request > div > div > div.card-footer.d-flex.justify-content-end.pt-0 > button > span.indicator-label")).click();

        System.out.println("Mission");
        captureSwalMessage();
    }
}

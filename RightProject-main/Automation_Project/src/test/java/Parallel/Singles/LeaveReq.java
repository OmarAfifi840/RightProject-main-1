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

public class LeaveReq {

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
    ////////////////////////////// Leave Request :) /////////////////////////////////
    @Test

    public static void LeaveRequest() throws InterruptedException {

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

       /////////////////////////// Leave Request Screen :) /////////////////////////////////

       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(240));
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > span > span.menu-title")));

       Actions actions = new Actions(driver);
       WebElement newRequest = driver.findElement(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > span > span.menu-title"));
       actions.moveToElement(newRequest).perform();

       WebElement timeManagement = driver.findElement(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > div > div:nth-child(3) > span > span.menu-title"));
       actions.moveToElement(timeManagement).perform();

       WebElement leaves = driver.findElement(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > div > div:nth-child(3) > div > div:nth-child(1) > a > span.menu-title"));
       actions.moveToElement(leaves).click().perform();

       System.out.println("Leave");
       captureSwalMessage();

       /////////////////////////// Leave Request Details :) /////////////////////////////////

        String fromDate = ConfigReader.get("fromDateLeaveRequest");
        String notesLeaves = ConfigReader.get("notesLeaves");
        String ToDate = ConfigReader.get("TodateLeavesRequest");

        Thread.sleep(10000);
        driver.findElement(By.xpath("/html/body/div[1]/app-layout/div/div/div/div/div/app-content/div/app-leave-request/div/div/div[2]/div/form/div[2]/div[1]/div/mat-form-field/div/div[1]/div[3]")).click();
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div/mat-option[1]/span")).click();

        WebElement fromDateField = driver.findElement(By.id("mat-input-3"));
        fromDateField.sendKeys("Test");
        fromDateField.clear();
        fromDateField.sendKeys(fromDate);

        WebElement ToDateField = driver.findElement(By.id("mat-input-4"));
        ToDateField.sendKeys("Test");
        ToDateField.clear();
        ToDateField.sendKeys(ToDate);

        Thread.sleep(10000);
        WebElement note = driver.findElement(By.cssSelector("#mat-input-7"));
        note.click();
        Thread.sleep(12000);
        note.sendKeys(notesLeaves);

        driver.findElement(By.cssSelector("#kt_app_content_container > app-leave-request > div > div > div.card-footer.d-flex.justify-content-end.pt-0 > button > span.indicator-label")).click();

        System.out.println("Leave");

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.className("swal2-loading")));
        WebElement swalMessage = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-html-container")));
        System.out.println("Swal Message: " + swalMessage.getText());
    }

}


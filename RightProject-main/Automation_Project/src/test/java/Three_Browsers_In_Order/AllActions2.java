package Three_Browsers_In_Order;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.Duration;

public class AllActions2 {

    private static final Logger logger = LogManager.getLogger(AllActions2.class);

    public static WebDriver driver;

    public static void clickSwalOkIfExists() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div/div[6]/button[1]")));
            okButton.click();
            System.out.println("OK button clicked Leave.");
        } catch (TimeoutException e1) {
            System.out.println("First OK button not found, trying second XPath...");
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
                WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[5]/div/div[6]/button[1]")));
                okButton.click();
                System.out.println("OK button clicked Mission.");
            } catch (TimeoutException e2) {
                try {
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
                    WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div/div[6]/button[1]")));
                    okButton.click();
                    System.out.println("OK button clicked Permission.");
                } catch (TimeoutException e3) {
                    System.out.println("Second OK button not found, trying Third XPath...");
                    System.out.println("No OK button appeared, continuing...");
                }  catch (Exception e3) {
                    System.out.println("Somehting went wrong with third Xpath" + e3.getMessage());
                }
            } catch (Exception e2) {
                System.out.println("Something went wrong with second XPath: " + e2.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Something went wrong with first XPath: " + e.getMessage());
        }
    }

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


    public static void performLogin() throws InterruptedException {
        String userName = ConfigReader.get("userName");
        String password = ConfigReader.get("password");

        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#kt_login_signin_form > div.fv-row.mb-8 > input")).sendKeys(userName);
        driver.findElement(By.cssSelector("#kt_login_signin_form > div:nth-child(3) > input")).sendKeys(password);
        driver.findElement(By.id("kt_sign_in_submit")).click();

        System.out.println("Login");
    }
    ///////////////////////////////// Leave Request :) /////////////////////////////////
    public static void Leave() {
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
    }

    public static void LeavesDetails() throws InterruptedException {
        String fromDate = ConfigReader.get("fromDateLeaveRequest");
        String ToDate = ConfigReader.get("todateLeavesRequest");
        String notesLeaves = ConfigReader.get("notesLeaves");

        //  By fieldLocator = By.id("mat-select-value-5");

        //   if (hasDataLoaded(driver, fieldLocator, 30)) {
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > span > span.menu-title")));
        if (driver.findElement(By.xpath("/html/body/div[1]/app-layout/div/div/div/div/div/app-content/div/app-leave-request/div/div/div[2]/div/form/div[1]/div[1]/div/app-ss-employee-select/div/mat-form-field/div/div[1]/div[3]/mat-select/div/div[1]/span/span")) != null) {}
        //Thread.sleep(10000);
        driver.findElement(By.xpath("/html/body/div[1]/app-layout/div/div/div/div/div/app-content/div/app-leave-request/div/div/div[2]/div/form/div[2]/div[1]/div/mat-form-field/div/div[1]/div[3]/mat-select")).click();
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
        Thread.sleep(10000);
        note.sendKeys(notesLeaves);

        driver.findElement(By.cssSelector("#kt_app_content_container > app-leave-request > div > div > div.card-footer.d-flex.justify-content-end.pt-0 > button > span.indicator-label")).click();

        //System.out.println("Leave");

        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(240));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("swal2-loading")));
        WebElement swalMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-html-container")));
        //System.out.println("Swal Message: " + swalMessage.getText());
        logger.info("Swal Message: " + swalMessage.getText());
        clickSwalOkIfExists();
    }


    ///////////////////////////////// Mission Request :) /////////////////////////////////
    public static void Mission() {
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > span > span.menu-title")));

        Actions actions = new Actions(driver);
        WebElement newRequest = driver.findElement(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > span > span.menu-title"));
        actions.moveToElement(newRequest).perform();

        WebElement timeManagement = driver.findElement(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > div > div:nth-child(3) > span > span.menu-title"));
        actions.moveToElement(timeManagement).perform();

        WebElement mission = driver.findElement(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > div > div:nth-child(3) > div > div:nth-child(2) > a > span.menu-title"));
        actions.moveToElement(mission).click().perform();

        //System.out.println("Mission");

    }

    public static void MissionDetails() throws InterruptedException {
        String fromDate = ConfigReader.get("fromDateMissionRequest");
        String toDate = ConfigReader.get("toDateMissionRequest");
        String notesMission = ConfigReader.get("notesMission");


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(240));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > span > span.menu-title")));
        if (driver.findElement(By.xpath("/html/body/div[1]/app-layout/div/div/div/div/div/app-content/div/app-mission-request/div/div/div[2]/div/form/div[1]/div[1]/div/app-ss-employee-select/div/mat-form-field/div/div[1]/div[3]/mat-select/div/div[1]/span/span")) != null) {
            Thread.sleep(10000);
            WebElement fromDateField = driver.findElement(By.id("mat-input-9"));
            fromDateField.sendKeys("Test");
            fromDateField.clear();
            fromDateField.sendKeys(fromDate);

            /// ///////////// ISSUE HERE

            WebElement toDateField = driver.findElement(By.id("mat-input-10"));
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
            //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("swal2-loading")));
            WebElement swalMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-html-container")));
            //System.out.println("Swal Message: " + swalMessage.getText());
            logger.info("Swal Message: " + swalMessage.getText());
            clickSwalOkIfExists();
        }
    }
    ///////////////////////////////// Permission Request :) /////////////////////////////////
    public static void Permission() {
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > span > span.menu-title")));

        Actions actions = new Actions(driver);
        WebElement newRequest = driver.findElement(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > span > span.menu-title"));
        actions.moveToElement(newRequest).perform();

        WebElement timeManagement = driver.findElement(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > div > div:nth-child(3) > span > span.menu-title"));
        actions.moveToElement(timeManagement).perform();

        WebElement permission = driver.findElement(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > div > div:nth-child(3) > div > div:nth-child(3) > a > span.menu-title"));
        actions.moveToElement(permission).click().perform();

        //System.out.println("Permission");

    }

    public static void PermissionDetails() throws InterruptedException {
        String fromDate = ConfigReader.get("PermissionRequestDate");
        String fromTime = ConfigReader.get("FromTime");
        String toTime = ConfigReader.get("ToTime");
        String notesPermission = ConfigReader.get("notesPermission");

        if (driver.findElement(By.xpath("/html/body/div[1]/app-layout/div/div/div/div/div/app-content/div/app-permission-request/div/div/div[2]/div/form/div[1]/div[1]/div/app-ss-employee-select/div/mat-form-field/div/div[1]/div[3]/mat-select/div/div[1]/span/span")) != null) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(240));
            //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > span > span.menu-title")));

            Thread.sleep(10000);
            WebElement fromDateField = driver.findElement(By.id("mat-input-16"));
            fromDateField.sendKeys("Test");
            fromDateField.clear();
            fromDateField.sendKeys(fromDate);

            Thread.sleep(10000);
            driver.findElement(By.cssSelector("#signInTimepicker")).sendKeys(fromTime);
            driver.findElement(By.cssSelector("#signOutTimepicker")).sendKeys(toTime);
            driver.findElement(By.cssSelector("#mat-input-24")).sendKeys(notesPermission);

            driver.findElement(By.cssSelector("#kt_app_content_container > app-permission-request > div > div > div.card-footer.d-flex.justify-content-end.pt-0 > button > span.indicator-label")).click();

            System.out.println("Permission");

            //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("swal2-loading")));
            WebElement swalMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-html-container")));
            //System.out.println("Swal Message: " + swalMessage.getText());
            logger.info("Swal Message: " + swalMessage.getText());
            clickSwalOkIfExists();
        }
    }
    ///////////////////////////////// WFH Request :) /////////////////////////////////
    public static void WFH () {
        Actions actions = new Actions(driver);
        WebElement newRequest = driver.findElement(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > span > span.menu-title"));
        actions.moveToElement(newRequest).perform();

        WebElement timeManagement = driver.findElement(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > div > div:nth-child(3) > span > span.menu-title"));
        actions.moveToElement(timeManagement).perform();

        WebElement WFH = driver.findElement(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > div > div:nth-child(3) > div > div:nth-child(4) > a > span.menu-title"));
        actions.moveToElement(WFH).click().perform();
    }

    public static void  WFHDetails ()throws InterruptedException{
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

            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("swal2-loading")));
            WebElement swalMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-html-container")));
            //System.out.println("Swal Message: " + swalMessage.getText());
            logger.info("Swal Message: " + swalMessage.getText());
            clickSwalOkIfExists();
        }

    }
}

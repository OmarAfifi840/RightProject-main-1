package Parallel.Employees_V3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import utilities.ConfigReader;

import java.time.Duration;


public class AllActionsEmployee1_V3 {

    private static final Logger logger = LogManager.getLogger(AllActionsEmployee1_V3.class);
    public static WebDriver driver1;
    public static Actions actions;

    private static  void SendPath(String Path,String Message){
        WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(3));
        WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(Path)));
        okButton.click();
        //System.out.println(Message);
        logger.error(Message); //Changed from Info To Error
    }

    //private static WebElement employeeCode(String reqType) {
//        Boolean Path = true;
//
//
//
//        while  ( Path == true )
//        {
//            driver1.findElement(By.xpath("/html/body/div[1]/app-layout/div/div/div/div/div/app-content/div/app-"+reqType+"/div/div/div[2]/div/form/div[1]/div[1]/div/app-ss-employee-select/div/mat-form-field/div/div[1]/div[3]/mat-select/div/div[1]/span/span")) ;
//
//        }
//
//
//        return null;
//    }



    private static WebElement employeeCode(String reqType) {
        WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(30)); // Wait max 10 seconds

        By locator = By.xpath("/html/body/div[1]/app-layout/div/div/div/div/div/app-content/div/app-"+reqType+"/div/div/div[2]/div/form/div[1]/div[1]/div/app-ss-employee-select/div/mat-form-field/div/div[1]/div[3]/mat-select/div/div[1]/span/span");

        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element;
        } catch (Exception e) {
            System.out.println("Element not found within timeout: " + e.getMessage());
            return null;
        }
    }


    private static void Infologger (String Message) {
        //System.out.println(Message);
        logger.info(Message);  //Changed from Error To Info
    }


//    private static void swalerrorMessage() {
//        String Employee1 = ConfigReader.get("userName1");
//        WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(15));
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("swal2-loading")));
//        WebElement swal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-html-container")));
//        logger.error("Swal Message: " + swal.getText() + " / UserName :" + Employee1); //Changed from Info To Error
//        clickSwalOkIfExists();
//    }

    private static void swalerrorMessage(String requestName) {
        String Employee1 = ConfigReader.get("userName1");
        WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("swal2-loading")));
        WebElement swal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-html-container")));

        logger.error("Request: " + requestName + " | Swal Message: " + swal.getText() + " / UserName: " + Employee1);
        clickSwalOkIfExists();
    }


    public static void clickSwalOkIfExists() {
      //  String Employee1 = ConfigReader.get("userName1");

        try {
            SendPath("swal2-actions", "OK button clicked.");
        } catch (TimeoutException e1) {}
//            Infologger("First OK button not found, trying second XPath... / UserName :" + Employee1);}
    }
//public static void clickSwalOkIfExists() {
//        String Employee1 = ConfigReader.get("userName1");
//
//        try {
//            SendPath("//*[@id=\"kt_app_body\"]/div[4]/div/div[6]/button[1]", "OK button clicked leave. / UserName :" + Employee1);
//            return;
//        } catch (TimeoutException e1) {
//            Infologger("First OK button not found, trying second XPath... / UserName :" + Employee1);
//        } catch (Exception e) {
//            Infologger("Something went wrong with first XPath: " + e.getMessage() + " / UserName :" + Employee1);
//        }
//
//        try {
//            SendPath("//*[@id=\"kt_app_body\"]/div[5]/div/div[6]/button[1]", "OK button clicked Mission. / UserName :" + Employee1);
//            return;
//        } catch (TimeoutException e2) {
//            Infologger("Second OK button not found, trying third XPath... / UserName :" + Employee1);
//        } catch (Exception e2) {
//            Infologger("Something went wrong with second XPath: " + e2.getMessage() + " / UserName :" + Employee1);
//        }
//
//        try {
//            SendPath("//*[@id=\"kt_app_body\"]/div[3]/div/div[6]/button[1]", "OK button clicked Permission. / UserName :" + Employee1);
//
//            return;
//        } catch (TimeoutException e3) {
//            Infologger("Third OK button not found, trying fourth XPath... / UserName :" + Employee1);
//        } catch (Exception e3) {
//            Infologger("Something went wrong with third XPath: " + e3.getMessage() + " / UserName :" + Employee1);
//        }
//
//        try {
//            SendPath("//*[@id=\"kt_app_body\"]/div[3]/div/div[6]/button[1]", "OK button clicked WFH. / UserName :" + Employee1);
//            return;
//        } catch (Exception e4) {
//            Infologger("Something went wrong with fourth XPath: " + e4.getMessage() + " / UserName :" + Employee1);
//        }
//
//        Infologger("No OK button appeared, continuing... / UserName :" + Employee1);
//    }


    public static void startBrowser() {
        String browser = ConfigReader.get("browser");
        String url = ConfigReader.get("url");
        long implicitWait = Long.parseLong(ConfigReader.get("implicitWait"));

        if (browser.equalsIgnoreCase("chrome")) {
            driver1 = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver1 = new FirefoxDriver();
        } else {
            throw new RuntimeException("Browser not supported");
        }

        driver1.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver1.manage().window().maximize();
        driver1.get(url);
        actions = new Actions(driver1);
    }

    @Test
    public void performAllActions() throws InterruptedException {
        startBrowser();
        login();
        submitLeaveRequest();
        submitMissionRequest();
        submitPermissionRequest();
        submitWFHRequest();
    }
    private void login() throws InterruptedException {
        String userName = ConfigReader.get("userName1");
        String password = ConfigReader.get("password1");

        Thread.sleep(1000);
        driver1.findElement(By.cssSelector("#kt_login_signin_form > div.fv-row.mb-8 > input")).sendKeys(userName);
        driver1.findElement(By.cssSelector("#kt_login_signin_form > div:nth-child(3) > input")).sendKeys(password);
        driver1.findElement(By.id("kt_sign_in_submit")).click();
    }

    private void submitLeaveRequest() throws InterruptedException {

        String Employee1 = ConfigReader.get("userName1");
        //WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(240));
        //WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(240));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='kt_app_header_menu']/div[2]/span/span[1]")));

        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#kt_app_header_menu > div:nth-child(2) > span > span.menu-title")));

        Actions actions = new Actions(driver1);
        WebElement newRequest = driver1.findElement(By.xpath("//*[@id=\"kt_app_header_menu\"]/div[2]/span/span[1]"));
        actions.moveToElement(newRequest).perform();

        WebElement timeManagement = driver1.findElement(By.xpath("//*[@id=\"kt_app_header_menu\"]/div[2]/div/div[3]/span/span[2]"));
        actions.moveToElement(timeManagement).perform();

        WebElement leaves = driver1.findElement(By.xpath("//*[@id=\"kt_app_header_menu\"]/div[2]/div/div[3]/div/div[1]/a/span[2]"));
        actions.moveToElement(leaves).click().perform();

        Infologger("Leave"+" / UserName :" + Employee1);

        WebElement ScreenNameLeave= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"kt_app_content_container\"]/app-leave-request/div/div/div[1]/div/h2")));


        employeeCode("leave-request");
//        Boolean Path = true;
//        while  ( Path == true )
//        {
//            employeeCode("leave-request");
//            System.out.print("Succeed");
//        }

//        if (employeeCode("leave-request") != null) {
            Thread.sleep(1000);
            driver1.findElement(By.xpath("/html/body/div[1]/app-layout/div/div/div/div/div/app-content/div/app-leave-request/div/div/div[2]/div/form/div[2]/div[1]/div/mat-form-field/div/div[1]/div[3]/mat-select")).click();
            Thread.sleep(1000);
            driver1.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div/mat-option[1]/span")).click();
            Thread.sleep(1000);
            driver1.findElement(By.id("mat-input-3")).sendKeys(ConfigReader.get("FromDateEmployee1Leave"));
            driver1.findElement(By.id("mat-input-3")).clear();
            driver1.findElement(By.id("mat-input-3")).sendKeys(ConfigReader.get("FromDateEmployee1Leave"));
            driver1.findElement(By.id("mat-input-4")).sendKeys(ConfigReader.get("ToDateEmployee1Leave"));
            driver1.findElement(By.id("mat-input-4")).clear();;
            driver1.findElement(By.id("mat-input-4")).sendKeys(ConfigReader.get("ToDateEmployee1Leave"));
            WebDriverWait NumberOfdays = new WebDriverWait(driver1, Duration.ofSeconds(10));

            wait.until(driver1 -> {
                try {
                    WebElement daysField = driver1.findElement(By.id("mat-input-6")); // adjust locator
                    String value = daysField.getAttribute("value");
                    if (value != null && !value.isEmpty()) {
                        int days = Integer.parseInt(value);
                        return days > 0;
                    }
                    return false;
                } catch (NumberFormatException | NoSuchElementException e) {
                    return false;
                }});

            WebElement note = driver1.findElement(By.id("mat-input-7"));
            note.sendKeys(ConfigReader.get("notesLeavesEmployee1Leave"));
            driver1.findElement(By.cssSelector("button span.indicator-label")).click();

            try {
                Thread.sleep(10000);
                swalerrorMessage("Leave");
            }
            catch (TimeoutException e1) {
                Infologger("Leave"+" Request is submitted successfully"+" / UserName :" + Employee1);
            }
       }

    //}

    private void submitMissionRequest() throws InterruptedException {

        String Employee1 = ConfigReader.get("userName1");
        WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(240));
        Actions actions = new Actions(driver1);
        WebElement newRequest = driver1.findElement(By.xpath("//*[@id=\"kt_app_header_menu\"]/div[2]/span/span[1]"));
        actions.moveToElement(newRequest).perform();

        WebElement timeManagement = driver1.findElement(By.xpath("//*[@id=\"kt_app_header_menu\"]/div[2]/div/div[3]/span/span[2]"));
        actions.moveToElement(timeManagement).perform();

        WebElement mission = driver1.findElement(By.xpath("//*[@id=\"kt_app_header_menu\"]/div[2]/div/div[3]/div/div[2]/a/span[2]"));
        actions.moveToElement(mission).click().perform();

        Infologger("Mission"+ " / UserName :" + Employee1);

        WebElement ScreenNameMission = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"kt_app_content_container\"]/app-mission-request/div/div/div[1]/div/h2")));

//        navigateTo("#kt_app_header_menu > div:nth-child(2) > span > span.menu-title");
//        navigateTo("#kt_app_header_menu > div:nth-child(2) > div > div:nth-child(3) > span > span.menu-title");
//        navigateTo("#kt_app_header_menu > div:nth-child(2) > div > div:nth-child(3) > div > div:nth-child(2) > a > span.menu-title");

//        if (employeeCode("mission-request") != null) {

//        Boolean Path2 = true;
//        while  ( Path2 == true )
//        {
            employeeCode("mission-request");
//            System.out.print("Succeed");
//        }
            Thread.sleep(1000);
            driver1.findElement(By.xpath("//*[@id=\"mat-input-9\"]")).sendKeys(ConfigReader.get("FromDateEmployee1Mission"));
            driver1.findElement(By.xpath("//*[@id=\"mat-input-9\"]")).clear();
            driver1.findElement(By.xpath("//*[@id=\"mat-input-9\"]")).sendKeys(ConfigReader.get("FromDateEmployee1Mission"));
            driver1.findElement(By.id("mat-input-10")).sendKeys(ConfigReader.get("ToDateEmployee1Mission"));
            driver1.findElement(By.id("mat-input-10")).clear();
            driver1.findElement(By.id("mat-input-10")).sendKeys(ConfigReader.get("ToDateEmployee1Mission"));


//            driver1.findElement(By.id("mat-select-value-11")).click();
//            driver1.findElement(By.cssSelector("#mat-option-23 > span")).click();
            driver1.findElement(By.xpath("/html/body/div[1]/app-layout/div/div/div/div/div/app-content/div/app-mission-request/div/div/div[2]/div/form/div[2]/div[1]/div/mat-form-field/div/div[1]/div[3]/mat-select/div/div[1]")).click();
            driver1.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div/mat-option[2]/span")).click();
            driver1.findElement(By.id("mat-input-13")).sendKeys(ConfigReader.get("NotesMissionEmployee1"));
            driver1.findElement(By.cssSelector("button span.indicator-label")).click();
            try {
                swalerrorMessage("Mission");
            }
            catch (TimeoutException e1) {
                Infologger("Mission" + " Request is submitted successfully" + " / UserName : " + Employee1);
            }
        }


    private void submitPermissionRequest() throws InterruptedException {
        String Employee1 = ConfigReader.get("userName1");
        Actions actions = new Actions(driver1);
        WebElement newRequest = driver1.findElement(By.xpath("//*[@id=\"kt_app_header_menu\"]/div[2]/span/span[1]"));
        actions.moveToElement(newRequest).perform();

        WebElement timeManagement = driver1.findElement(By.xpath("//*[@id=\"kt_app_header_menu\"]/div[2]/div/div[3]/span/span[2]"));
        actions.moveToElement(timeManagement).perform();

        WebElement permission = driver1.findElement(By.xpath("//*[@id=\"kt_app_header_menu\"]/div[2]/div/div[3]/div/div[3]/a/span[2]"));
        actions.moveToElement(permission).click().perform();

        Infologger("Permission"+ " / UserName :" + Employee1);

//        navigateTo("#kt_app_header_menu > div:nth-child(2) > span > span.menu-title");
//        navigateTo("#kt_app_header_menu > div:nth-child(2) > div > div:nth-child(3) > span > span.menu-title");
//        navigateTo("#kt_app_header_menu > div:nth-child(2) > div > div:nth-child(3) > div > div:nth-child(3) > a > span.menu-title");

//        if (employeeCode("permission-request") != null) {

//        Boolean Path3 = true;
//        while  ( Path3 == true )
//        {
            employeeCode("permission-request");
//        }

            driver1.findElement(By.id("mat-input-16")).sendKeys(ConfigReader.get("PermissionDateEmployee1"));
            driver1.findElement(By.id("mat-input-16")).clear();
            driver1.findElement(By.id("mat-input-16")).sendKeys(ConfigReader.get("PermissionDateEmployee1"));

            driver1.findElement(By.cssSelector("#signInTimepicker")).sendKeys(ConfigReader.get("FromTimePermissionEmployee1"));
            driver1.findElement(By.cssSelector("#signOutTimepicker")).sendKeys(ConfigReader.get("ToTimePermissionEmployee1"));
            driver1.findElement(By.id("mat-input-24")).sendKeys(ConfigReader.get("NotesPermissionEmployee1"));
            driver1.findElement(By.cssSelector("button span.indicator-label")).click();
            try {
                swalerrorMessage("Permission");
            }
            catch (TimeoutException e1) {
                Infologger("Permission" + " Request is submitted successfully" + " / UserName : " + Employee1);
            }
        }



    private void submitWFHRequest() throws InterruptedException {
        String Employee1 = ConfigReader.get("userName1");
        Actions actions = new Actions(driver1);
        WebElement newRequest = driver1.findElement(By.xpath("//*[@id=\"kt_app_header_menu\"]/div[2]/span/span[1]"));
        actions.moveToElement(newRequest).perform();

        WebElement timeManagement = driver1.findElement(By.xpath("//*[@id=\"kt_app_header_menu\"]/div[2]/div/div[3]/span/span[2]"));
        actions.moveToElement(timeManagement).perform();

        WebElement WFH = driver1.findElement(By.xpath("//*[@id=\"kt_app_header_menu\"]/div[2]/div/div[3]/div/div[4]/a/span[2]"));
        actions.moveToElement(WFH).click().perform();
        Infologger("WFH"+ " / UserName :" + Employee1);
//        navigateTo("#kt_app_header_menu > div:nth-child(2) > span > span.menu-title");
//        navigateTo("#kt_app_header_menu > div:nth-child(2) > div > div:nth-child(3) > span > span.menu-title");
//        navigateTo("#kt_app_header_menu > div:nth-child(2) > div > div:nth-child(3) > div > div:nth-child(4) > a > span.menu-title");

//        if (employeeCode("wfhrequest") != null) {
//        Boolean Path4 = true;
//        while  ( Path4 == true )
//        {
            employeeCode("wfhrequest");
//        }

            driver1.findElement(By.xpath("//app-wfhrequest//input[@formcontrolname='fromDate']")).sendKeys(ConfigReader.get("FromDateEmployee1WFH"));
            driver1.findElement(By.xpath("//app-wfhrequest//input[@formcontrolname='fromDate']")).clear();
            driver1.findElement(By.xpath("//app-wfhrequest//input[@formcontrolname='fromDate']")).sendKeys(ConfigReader.get("FromDateEmployee1WFH"));

            driver1.findElement(By.xpath("//app-wfhrequest//input[@formcontrolname='toDate']")).sendKeys(ConfigReader.get("ToDateEmployee1WFH"));
            driver1.findElement(By.xpath("//app-wfhrequest//input[@formcontrolname='toDate']")).clear();
            driver1.findElement(By.xpath("//app-wfhrequest//input[@formcontrolname='toDate']")).sendKeys(ConfigReader.get("ToDateEmployee1WFH"));

            driver1.findElement(By.xpath("//app-wfhrequest//textarea")).sendKeys(ConfigReader.get("NotesWFHEmployee1"));
            driver1.findElement(By.xpath("//*[@id=\"kt_app_content_container\"]/app-wfhrequest/div/div/div[3]/button/span[1]")).click();
            try {
                swalerrorMessage("WFH");
            }
            catch (TimeoutException e1) {
                Infologger("WFH"+" Request is submitted successfully" + " / UserName : " + Employee1);
            }
        }
    }



package Parallel.Employees_V3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
        logger.info(Message);
    }


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
        WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(30));
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
    
    public void performAllActions() throws Exception  {
        while (true) {
            try {
                startBrowser();
                break;
            } catch (Exception e) {
                // Retry
            }
        }

        while (true) {
            try {
                login();
                break;
            } catch (Exception e1) {
                // Retry
            }
        }

        while (true) {
            try {
                submitLeaveRequest();
                break;
            } catch (Exception e) {
                // Retry
            }
        }

        while (true) {
            try {
                submitMissionRequest();
                break;
            } catch (Exception e) {
                // Retry
            }
        }

        while (true) {
            try {
                submitPermissionRequest();
                break;
            } catch (Exception e) {
                // Retry
            }
        }

        while (true) {
            try {
                submitWFHRequest();
                break;
            } catch (Exception e) {
                // Retry
            }
        }
    }



        private void login() throws InterruptedException {
        String userName = ConfigReader.get("userName1");
        String password = ConfigReader.get("password1");

        Thread.sleep(1000);
        driver1.findElement(By.xpath("//*[@formcontrolname='email']")).sendKeys(userName);
        driver1.findElement(By.xpath("//*[@formcontrolname='password']")).sendKeys(password);
        driver1.findElement(By.id("kt_sign_in_submit")).click();
    }

    private void submitLeaveRequest() throws InterruptedException {

        String Employee1 = ConfigReader.get("userName1");
        WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(240));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"kt_app_header_wrapper\"]/app-navbar/div[5]/div")));


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
        Thread.sleep(1000);

        driver1.findElement(By.xpath("//*[@formcontrolname='vacCode']")).click();
        driver1.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div/mat-option[1]/span")).click();


        driver1.findElement(By.xpath("//*[@formcontrolname='dateFrom']")).sendKeys(ConfigReader.get("FromDateEmployee1Leave"));
        driver1.findElement(By.xpath("//*[@formcontrolname='dateFrom']")).clear();
        driver1.findElement(By.xpath("//*[@formcontrolname='dateFrom']")).sendKeys(ConfigReader.get("FromDateEmployee1Leave"));
        driver1.findElement(By.xpath("//*[@formcontrolname='dateTo']")).sendKeys(ConfigReader.get("ToDateEmployee1Leave"));
        driver1.findElement(By.xpath("//*[@formcontrolname='dateTo']")).clear();;
        driver1.findElement(By.xpath("//*[@formcontrolname='dateTo']")).sendKeys(ConfigReader.get("ToDateEmployee1Leave"));

            WebDriverWait NumberOfdays = new WebDriverWait(driver1, Duration.ofSeconds(10));

            wait.until(driver1 -> {
                try {
                    WebElement daysField = driver1.findElement(By.xpath("//*[@formcontrolname='totalDays']"));
                    String value = daysField.getAttribute("value");
                    if (value != null && !value.isEmpty()) {
                        int days = Integer.parseInt(value);
                        return days > 0;
                    }
                    return false;
                } catch (NumberFormatException | NoSuchElementException e) {
                    return false;
                }});
            WebElement note = driver1.findElement(By.xpath("//*[@formcontrolname='reason']"));
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

            employeeCode("mission-request");

            Thread.sleep(1000);
        driver1.findElement(By.xpath("//*[@formcontrolname='fromDate']")).sendKeys(ConfigReader.get("FromDateEmployee1Mission"));
        driver1.findElement(By.xpath("//*[@formcontrolname='fromDate']")).clear();
        driver1.findElement(By.xpath("//*[@formcontrolname='fromDate']")).sendKeys(ConfigReader.get("FromDateEmployee1Mission"));
//        driver1.findElement(By.xpath("//*[@formcontrolname='toDate']")).sendKeys(ConfigReader.get("ToDateEmployee1Mission"));
//        driver1.findElement(By.xpath("//*[@formcontrolname='toDate']")).clear();
//        driver1.findElement(By.xpath("//*[@formcontrolname='toDate']")).sendKeys(ConfigReader.get("ToDateEmployee1Mission"));

        driver1.findElement(By.xpath("//*[@formcontrolname='missionType']")).click();
        Thread.sleep(1000);


        driver1.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div/mat-option[1]/span")).click();
        Thread.sleep(50000);
//        driver1.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div/mat-option[2]/span")).click();

        driver1.findElement(By.xpath("//*[@formcontrolname='reason']")).sendKeys(ConfigReader.get("NotesMissionEmployee1"));
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

            employeeCode("permission-request");

        driver1.findElement(By.xpath("//*[@formcontrolname='permDate']")).sendKeys(ConfigReader.get("PermissionDateEmployee1"));
        driver1.findElement(By.xpath("//*[@formcontrolname='permDate']")).clear();
        driver1.findElement(By.xpath("//*[@formcontrolname='permDate']")).sendKeys(ConfigReader.get("PermissionDateEmployee1"));
        driver1.findElement(By.xpath("//*[@formcontrolname='newUpdatedFromTime']")).sendKeys(ConfigReader.get("FromTimePermissionEmployee1"));
        driver1.findElement(By.xpath("//*[@formcontrolname='newUpdatedToTime']")).sendKeys(ConfigReader.get("ToTimePermissionEmployee1"));
        driver1.findElement(By.xpath("//*[@formcontrolname='reason']")).sendKeys(ConfigReader.get("NotesPermissionEmployee1"));
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
        employeeCode("wfhrequest");

        //driver1.findElement(By.xpath("//*[@formcontrolname='reason']"));
            driver1.findElement(By.xpath("//app-wfhrequest//input[@formcontrolname='fromDate']")).sendKeys(ConfigReader.get("FromDateEmployee1WFH"));
            driver1.findElement(By.xpath("//app-wfhrequest//input[@formcontrolname='fromDate']")).clear();
            driver1.findElement(By.xpath("//app-wfhrequest//input[@formcontrolname='fromDate']")).sendKeys(ConfigReader.get("FromDateEmployee1WFH"));

            driver1.findElement(By.xpath("//app-wfhrequest//input[@formcontrolname='toDate']")).sendKeys(ConfigReader.get("ToDateEmployee1WFH"));
            driver1.findElement(By.xpath("//app-wfhrequest//input[@formcontrolname='toDate']")).clear();
            driver1.findElement(By.xpath("//app-wfhrequest//input[@formcontrolname='toDate']")).sendKeys(ConfigReader.get("ToDateEmployee1WFH"));

        driver1.findElement(By.xpath("//*[@formcontrolname='reason']")).sendKeys(ConfigReader.get("NotesWFHEmployee1"));
        driver1.findElement(By.xpath("//*[@id=\"kt_app_content_container\"]/app-wfhrequest/div/div/div[3]/button/span[1]")).click();
            try {
                swalerrorMessage("WFH");
            }
            catch (TimeoutException e1) {
                Infologger("WFH"+" Request is submitted successfully" + " / UserName : " + Employee1);
            }
        }
    }



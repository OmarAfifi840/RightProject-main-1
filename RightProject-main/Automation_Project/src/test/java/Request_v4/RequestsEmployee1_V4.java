package Request_v4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import utilities.ConfigReader;

import java.time.Duration;

public class RequestsEmployee1_V4 {
    private static final Logger logger = LogManager.getLogger(RequestsEmployee1_V4.class);
    static WebDriver driver;
    static String reqType;
    // Locaters
    static By UserName = By.xpath("//*[@formcontrolname='email']");
    static By Password = By.xpath("//*[@formcontrolname='password']");
    static By LoginButton = By.id("kt_sign_in_submit");
    static By EmpCode = By.xpath("//mat-select[@id='empCode']");
    // New Request valid on all requests
    static By NewRequest = By.xpath("//span[@class='menu-title' and .='New Request']");
    // Time Management valid on all requests
    static By TimeManagement = By.xpath("//span[@class='menu-title' and .=' Time Attendance ']");
    static By LeaveRequest = By.xpath("//span[@class='menu-title' and .=' Leaves Request ']");


    static By VacCode = By.xpath("//*[@formcontrolname='vacCode']");


    static By AnnualLeave = By.xpath("//span[@class='mat-option-text' and normalize-space()='01 - Annual Leave']");


    static By FromDateLeave = By.xpath("//*[@formcontrolname='dateFrom']");
    static By ToDateLeave = By.xpath("//*[@formcontrolname='dateTo']");
    static By NotesLeaves = By.xpath("//*[@formcontrolname='reason']");
    //Send Request is valid on all requests :)
    static By SendRequest = By.xpath("//span[@class='indicator-label' and normalize-space()='Send Request']");
    static By MissionRequest = By.xpath("//span[@class='menu-title' and .=' Mission Request ']");
    static By FromDateMission = By.xpath("//*[@formcontrolname='fromDate']");
    static By ToDateMission = By.xpath("//*[@formcontrolname='toDate']");
    static By MissionType = By.xpath("//*[@formcontrolname='missionType']");
    //2 Options of The Mission is now availble to use
    static By FullTimeMission = By.xpath("//span[@class='mat-option-text' and normalize-space() ='Full Time']");
    //In case of using Number of Hours please use From Time and To Time
    static By NoOfHours = By.xpath("//span[@class='mat-option-text' and normalize-space() ='No Of Hours']");
    static By FromTime = By.xpath("//*[@formcontrolname='newUpdatedFromTime']");
    static By ToTime = By.xpath("//*[@formcontrolname='newUpdatedToTime']");
    static By NotesMisssion = By.xpath("//*[@formcontrolname='reason']");


    static By PermmissionRequest = By.xpath("//span[@class='menu-title' and .=' Permission Request ']");

    static Actions actions;
    //---------------------------------------------------------//
    //WebDriverWait wait;

    private static void setupDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeDriverService service = new ChromeDriverService.Builder()
                .withLogOutput(System.out)
                .build();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(service, options);
        actions = new Actions(driver);
    }


    private static void SendPath(String Path, String Message) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(Path)));
        okButton.click();
        //System.out.println(Message);
        logger.info(Message);
    }

    static void screenname() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2")));
    }

    private static WebElement employeeCode(String reqType) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        By locator = EmpCode;

        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Element not found within timeout: " + e.getMessage());
            return null;
        }
    }

    private static void Infologger(String Message) {
        logger.info(Message);
    }

    public static void clickSwalOkIfExists() {
        try {
            SendPath("swal2-actions", "OK button clicked.");
        } catch (TimeoutException e1) {
            logger.debug("OK button not found");
        }
    }

    private static void swalerrorMessage(String requestName) {
        String Employee1 = ConfigReader.get("userName1");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("swal2-loading")));
        WebElement swal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-html-container")));

        logger.error("Request: " + requestName + " | Swal Message: " + swal.getText() + " / UserName: " + Employee1);
        clickSwalOkIfExists();
    }

    public static void startBrowser() {
        String browser = ConfigReader.get("browser");
        String url = ConfigReader.get("url");
        long implicitWait = Long.parseLong(ConfigReader.get("implicitWait"));

        if (browser.equalsIgnoreCase("chrome")) {
            // Already initialized in setupDriver()
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            throw new RuntimeException("Browser not supported");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.manage().window().maximize();
        driver.get(url);
        actions = new Actions(driver);


    }

    private static void login() {
        String userName = ConfigReader.get("userName1");
        String password = ConfigReader.get("password1");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(UserName));
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(UserName));
        driver.findElement(UserName).clear();
        driver.findElement(Password).clear();
        driver.findElement(UserName).sendKeys(userName);
        driver.findElement(Password).sendKeys(password);
        driver.findElement(LoginButton).click();
    }

    static void submitLeaveRequest() {
        String Employee1 = ConfigReader.get("userName1");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        //Open Screen
        wait.until(ExpectedConditions.visibilityOfElementLocated(NewRequest));
        WebElement newRequest = driver.findElement(NewRequest);
        actions.moveToElement(newRequest).perform();

        WebElement timeManagement = driver.findElement(TimeManagement);
        actions.moveToElement(timeManagement).perform();

        WebElement leaves = driver.findElement(LeaveRequest);
        actions.moveToElement(leaves).click().perform();
        //Log Data and Check Screen Openned
        Infologger("Leave" + " / UserName :" + Employee1);
        screenname();
        employeeCode("leave-request");
        //Field inputs
        wait.until(ExpectedConditions.elementToBeClickable(VacCode));
        driver.findElement(VacCode).click();
        wait.until(ExpectedConditions.elementToBeClickable(AnnualLeave));
        driver.findElement(AnnualLeave).click();
        driver.findElement(FromDateLeave).sendKeys(ConfigReader.get("FromDateEmployee1Leave"));
        driver.findElement(FromDateLeave).clear();
        driver.findElement(FromDateLeave).sendKeys(ConfigReader.get("FromDateEmployee1Leave"));
        driver.findElement(ToDateLeave).sendKeys(ConfigReader.get("ToDateEmployee1Leave"));
        driver.findElement(ToDateLeave).clear();
        driver.findElement(ToDateLeave).sendKeys(ConfigReader.get("ToDateEmployee1Leave"));


        WebDriverWait NumberOfdays = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(driver -> {
            try {
                WebElement daysField = driver.findElement(By.xpath("//*[@formcontrolname='totalDays']"));
                String value = daysField.getAttribute("value");
                if (value != null && !value.isEmpty()) {
                    int days = Integer.parseInt(value);
                    return days > 0;
                }
                return false;
            } catch (NumberFormatException | NoSuchElementException e) {
                return false;
            }
        });

        driver.findElement(NotesLeaves).sendKeys(ConfigReader.get("notesLeavesEmployee1Leave"));
        wait.until(ExpectedConditions.elementToBeClickable(SendRequest));
        driver.findElement(SendRequest).click();

        try {
            swalerrorMessage("Leave");
        } catch (TimeoutException e1) {
            Infologger("Leave" + " Request is submitted successfully" + " / UserName :" + Employee1);
        }
        wait.until(ExpectedConditions.elementToBeClickable(NewRequest));
    }


    static void submitMissionRequest() {
        String Employee1 = ConfigReader.get("userName1");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(240));
        //Open Screen
        WebElement newRequest = driver.findElement(NewRequest);
        actions.moveToElement(newRequest).perform();

        WebElement timeManagement = driver.findElement(TimeManagement);
        actions.moveToElement(timeManagement).perform();

        WebElement mission = driver.findElement(MissionRequest);
        actions.moveToElement(mission).click().perform();
        //Log Data and Check Screen Openned
        Infologger("Mission" + " / UserName :" + Employee1);
        screenname();
        employeeCode("mission-request");

        //Field inputs
        driver.findElement(FromDateMission).sendKeys(ConfigReader.get("FromDateEmployee1Mission"));
        driver.findElement(FromDateMission).clear();
        driver.findElement(FromDateMission).sendKeys(ConfigReader.get("FromDateEmployee1Mission"));

//        driver.findElement(ToDateMission).sendKeys(ConfigReader.get("ToDateEmployee1Mission"));
//        driver.findElement(ToDateMission).clear();
//        driver.findElement(ToDateMission).sendKeys(ConfigReader.get("ToDateEmployee1Mission"));


        //-----------------------------------------------------
        wait.until(ExpectedConditions.elementToBeClickable(MissionType));
        wait.until(d -> {
            Select select = new Select((WebElement) MissionType);
            return select.getOptions().size() > 0;
        });
        //----------------------------------------------------
        driver.findElement(MissionType).click();

        WebElement missionType = wait.until(ExpectedConditions.visibilityOfElementLocated(FullTimeMission));
        missionType.click();
        driver.findElement(FullTimeMission).click();

        driver.findElement(NotesMisssion).sendKeys(ConfigReader.get("NotesMissionEmployee1"));
        wait.until(ExpectedConditions.elementToBeClickable(SendRequest));
        driver.findElement(SendRequest).click();

        try {
            swalerrorMessage("Mission");
        } catch (TimeoutException e1) {
            Infologger("Mission" + " Request is submitted successfully" + " / UserName : " + Employee1);
        }
        wait.until(ExpectedConditions.elementToBeClickable(NewRequest));
    }

    @Test
    public void performAllActions() throws Exception {
        setupDriver();
        startBrowser();
        login();
        submitLeaveRequest();
        //submitMissionRequest();
    }
}

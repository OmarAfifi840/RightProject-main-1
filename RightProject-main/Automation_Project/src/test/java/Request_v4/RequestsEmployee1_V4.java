package Request_v4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import java.util.List;

public class RequestsEmployee1_V4 {
    private static final Logger logger = LogManager.getLogger(RequestsEmployee1_V4.class);

    static String reqType;
    // Locaters
    static By UserName = By.xpath("//*[@formcontrolname='email']");
    static By Password = By.xpath("//*[@formcontrolname='password']");
    static By LoginButton = By.id("kt_sign_in_submit");
    // New Request valid on all requests
    static By NewRequest = By.xpath("//span[@class='menu-title' and .='New Request']");
    //-----------------------------------//
    // Time Management valid on all requests
    static By TimeManagement = By.xpath("//span[@class='menu-title' and .=' Time Attendance ']");
    //-----------------------------------//
    static By LeaveRequest = By.xpath("//span[@class='menu-title' and .=' Leaves Request ']");
    static By wk_VacCode = By.xpath("//*[@formcontrolname='vacCode']");
    static By FromDateLeave = By.xpath("//*[@formcontrolname='dateFrom']");
    static By ToDateLeave = By.xpath("//*[@formcontrolname='dateTo']");
    static By NotesLeaves = By.xpath("//*[@formcontrolname='reason']");
    //Send Request is valid on all requests :)
    static By SendRequest = By.xpath("//span[@class='indicator-label' and normalize-space()='Send Request']");
    //-----------------------------------//
    static By MissionRequest = By.xpath("//span[@class='menu-title' and .=' Mission Request ']");
    static By FromDateMission = By.xpath("//*[@formcontrolname='fromDate']");
    static By ToDateMission = By.xpath("//*[@formcontrolname='toDate']");
    static By MissionType = By.xpath("//*[@formcontrolname='missionType']");
    //2 Options of The Mission is now availble to use
    static By FullTimeMission = By.xpath("//span[@class='mat-option-text' and normalize-space()='Full Time']");
    //In case of using Number of Hours please use From Time and To Time
    static By NoOfHours = By.xpath("//span[@class='mat-option-text' and normalize-space() ='No Of Hours']");
    static By FromTime = By.xpath("//*[@formcontrolname='newUpdatedFromTime']");
    static By ToTime = By.xpath("//*[@formcontrolname='newUpdatedToTime']");
    //-----------------------------------//
    static By NotesMisssion = By.xpath("//*[@formcontrolname='reason']");
    static WebDriver driver;
    static By PermmissionRequest = By.xpath("//span[@class='menu-title' and .=' Permission Request ']");
    static By PermissionDate = By.xpath("//*[@formcontrolname='permDate']");
    static By PermissionTimeFrom = By.xpath("//*[@formcontrolname='newUpdatedFromTime']");
    static By PermissionTimeTo = By.xpath("//*[@formcontrolname='newUpdatedToTime']");
    static By NotesPermission = By.xpath("//*[@formcontrolname='reason']");
    static By WorkFromHome = By.xpath("//span[@class='menu-title' and .=' Work From Home ']");
    static By FromDateWFH = By.xpath("//app-wfhrequest//input[@formcontrolname='fromDate']");
    static By ToDateWFH = By.xpath("//app-wfhrequest//input[@formcontrolname='toDate']");
    static By WFHNotes = By.xpath("//*[@formcontrolname='reason']");
    static Actions actions;

    //---------------------------------------------------------//
    static void setupDriver() {
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

    static void SendPath(String Path, String Message) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(Path)));
        okButton.click();
        //System.out.println(Message);
        logger.info(Message);
    }

    static void screenname() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2")));
    }

    static WebElement employeeCode(String reqType) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        By locator = By.xpath("/html/body/div[1]/app-layout/div/div/div/div/div/app-content/div/app-" + reqType + "/div/div/div[2]/div/form/div[1]/div[1]/div/app-ss-employee-select/div/mat-form-field/div/div[1]/div[3]/mat-select/div/div[1]/span/span");

        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Element not found within timeout: " + e.getMessage());
            return null;
        }
    }
    static void Infologger(String Message) {
        logger.info(Message);
    }

    public static void clickSwalOkIfExists() {
        try {
            SendPath("swal2-actions", "OK button clicked.");
        } catch (TimeoutException e1) {
            logger.debug("OK button not found");
        }
    }

    static void swalerrorMessage(String requestName) {
        String Employee1 = ConfigReader.get("userName4");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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
        String userName = ConfigReader.get("userName4");
        String password = ConfigReader.get("password4");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(UserName));
        wait.until(ExpectedConditions.visibilityOfElementLocated(UserName));
        driver.findElement(UserName).clear();
        driver.findElement(Password).clear();
        driver.findElement(UserName).sendKeys(userName);
        driver.findElement(Password).sendKeys(password);
        driver.findElement(LoginButton).click();
    }
    static void TimeMangementMenu () {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        //Open Screen
        wait.until(ExpectedConditions.visibilityOfElementLocated(NewRequest));
        WebElement newRequest = driver.findElement(NewRequest);
        actions.moveToElement(newRequest).perform();

        WebElement timeManagement = driver.findElement(TimeManagement);
        actions.moveToElement(timeManagement).perform();
    }

    static void submitLeaveRequest() {
        String Employee1 = ConfigReader.get("userName4");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        //Open Screen
        TimeMangementMenu();
        WebElement leaves = driver.findElement(LeaveRequest);
        actions.moveToElement(leaves).click().perform();

        //Log Data and Check Screen Openned
        Infologger("Leave" + " / UserName :" + Employee1);
        screenname();
        employeeCode("leave-request");
        WebElement typeDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@formcontrolname='vacCode']")));
        typeDropdown.click();
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//mat-option//span[@class='mat-option-text']")
        ));
        WebElement remainField;
        if (!options.isEmpty()) {
            for (WebElement option : options) {
            }
            WebElement annualLeave = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[@class='mat-option-text' and normalize-space()='01 - Annual Leave']")
            ));
            annualLeave.click();
            remainField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("mat-input-5")
            ));
            try {
                // Wait for the element to have a non-zero value
                wait.until(d -> {
                    String remainValue = remainField.getAttribute("value");
                    return remainValue != null && !remainValue.equals("0") && !remainValue.isEmpty();
                });

                String remainValue = remainField.getAttribute("value");
                Infologger("Remain value: " + remainValue);

            } catch (TimeoutException e) {
                Infologger("Remain Not loaded or value is zero");
            }
        } else {
            remainField = null;
        }
        //Field inputs
        driver.findElement(FromDateLeave).sendKeys(ConfigReader.get("FromDateEmployee4Leave"));
        driver.findElement(FromDateLeave).clear();
        driver.findElement(FromDateLeave).sendKeys(ConfigReader.get("FromDateEmployee4Leave"));
        driver.findElement(ToDateLeave).sendKeys(ConfigReader.get("ToDateEmployee4Leave"));
        driver.findElement(ToDateLeave).clear();
        driver.findElement(ToDateLeave).sendKeys(ConfigReader.get("ToDateEmployee4Leave"));
        driver.findElement(NotesLeaves).sendKeys(ConfigReader.get("notesLeavesEmployee4Leave"));
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
        String Employee1 = ConfigReader.get("userName4");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(240));
        //Open Screen
        TimeMangementMenu();
        WebElement mission = driver.findElement(MissionRequest);
        actions.moveToElement(mission).click().perform();
        //Log Data and Check Screen Openned

        Infologger("Mission" + " / UserName :" + Employee1);
        screenname();
        employeeCode("mission-request");
                driver.findElement(FromDateMission).sendKeys(ConfigReader.get("FromDateEmployee4Mission"));
        driver.findElement(FromDateMission).clear();
        driver.findElement(FromDateMission).sendKeys(ConfigReader.get("FromDateEmployee4Mission"));
        driver.findElement(ToDateMission).sendKeys(ConfigReader.get("ToDateEmployee4Mission"));
        driver.findElement(ToDateMission).clear();
        driver.findElement(ToDateMission).sendKeys(ConfigReader.get("ToDateEmployee4Mission"));
        //-----------------------------------------------------
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(MissionType));
        driver.findElement(MissionType).click();

        WebElement FullTime = wait.until(ExpectedConditions.elementToBeClickable(FullTimeMission));
        driver.findElement(FullTimeMission).click();

        driver.findElement(NotesMisssion).sendKeys(ConfigReader.get("NotesMissionEmployee4"));
        wait.until(ExpectedConditions.elementToBeClickable(SendRequest));
        driver.findElement(SendRequest).click();

        try {
            swalerrorMessage("Mission");
        } catch (TimeoutException e1) {
            Infologger("Mission" + " Request is submitted successfully" + " / UserName : " + Employee1);
        }
        wait.until(ExpectedConditions.elementToBeClickable(NewRequest));
    }

        static void submitPermissionRequest() {
        String Employee1 = ConfigReader.get("userName4");
        TimeMangementMenu();
        WebElement permission = driver.findElement(PermmissionRequest);
        actions.moveToElement(permission).click().perform();

        Infologger("Permission" + " / UserName :" + Employee1);

        employeeCode("permission-request");

        driver.findElement(PermissionDate).sendKeys(ConfigReader.get("PermissionDateEmployee4"));
        driver.findElement(PermissionDate).clear();
        driver.findElement(PermissionDate).sendKeys(ConfigReader.get("PermissionDateEmployee4"));
        driver.findElement(PermissionTimeFrom).sendKeys(ConfigReader.get("FromTimePermissionEmployee4"));
        driver.findElement(PermissionTimeTo).sendKeys(ConfigReader.get("ToTimePermissionEmployee4"));
        driver.findElement(NotesPermission).sendKeys(ConfigReader.get("NotesPermissionEmployee4"));
        driver.findElement(SendRequest).click();
        try {
            swalerrorMessage("Permission");
        } catch (TimeoutException e1) {
            Infologger("Permission" + " Request is submitted successfully" + " / UserName : " + Employee1);
        }
    }

    private void submitWFHRequest() {
        String Employee1 = ConfigReader.get("userName4");
        Actions actions = new Actions(driver);

        TimeMangementMenu();

        WebElement WFH = driver.findElement(WorkFromHome);

        actions.moveToElement(WFH).click().perform();

        screenname();

        Infologger("WFH" + " / UserName :" + Employee1);

        employeeCode("wfhrequest");

        driver.findElement(FromDateWFH).sendKeys(ConfigReader.get("FromDateEmployee4WFH"));
        driver.findElement(FromDateWFH).clear();
        driver.findElement(FromDateWFH).sendKeys(ConfigReader.get("FromDateEmployee4WFH"));

        driver.findElement(ToDateWFH).sendKeys(ConfigReader.get("ToDateEmployee4WFH"));
        driver.findElement(ToDateWFH).clear();
        driver.findElement(ToDateWFH).sendKeys(ConfigReader.get("ToDateEmployee4WFH"));

        driver.findElement(WFHNotes).sendKeys(ConfigReader.get("NotesWFHEmployee4"));
        driver.findElement(SendRequest).click();

        try {
            swalerrorMessage("WFH");
        } catch (TimeoutException e1) {
            Infologger("WFH" + " Request is submitted successfully" + " / UserName : " + Employee1);
        }
    }

    @Test
    public void performAllActions() {
        setupDriver();
        startBrowser();
        login();
        submitLeaveRequest();  //Done
        submitMissionRequest(); //Done
        submitPermissionRequest(); //Done
        submitWFHRequest(); //Pending
    }
}

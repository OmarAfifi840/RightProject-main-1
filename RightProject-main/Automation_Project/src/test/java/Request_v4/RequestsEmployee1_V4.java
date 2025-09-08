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
    //Personnel valid on all requests for the Personnel Requests
    static By Personnel = By.xpath("//span[@class='menu-title' and .=' Personnel ']");
    // Time Management valid on all requests for the Time Management Requests
    static By TimeManagement = By.xpath("//span[@class='menu-title' and .=' Time Attendance ']");
    static By LeaveRequest = By.xpath("//span[@class='menu-title' and .=' Leaves Request ']");
    static By wk_VacCode = By.xpath("//*[@formcontrolname='vacCode']");
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
    static WebDriver driver;

    static By PermmissionRequest = By.xpath("//span[@class='menu-title' and .=' Permission Request ']");

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
    static void TimeManagement (){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        //Open Screen
        wait.until(ExpectedConditions.visibilityOfElementLocated(NewRequest));
        WebElement newRequest = driver.findElement(NewRequest);
        actions.moveToElement(newRequest).perform();

        WebElement timeManagement = driver.findElement(TimeManagement);
        actions.moveToElement(timeManagement).perform();
    }

    static void Personnel(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        //Open Screen
        wait.until(ExpectedConditions.visibilityOfElementLocated(NewRequest));
        WebElement newRequest = driver.findElement(NewRequest);
        actions.moveToElement(newRequest).perform();

        WebElement PersonnelMod = driver.findElement(Personnel);
        actions.moveToElement(PersonnelMod).perform();
    }

    static void login() {
        String userName = ConfigReader.get("userName2");
        String password = ConfigReader.get("password2");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));

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
        TimeManagement();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
//        //Open Screen
//        wait.until(ExpectedConditions.visibilityOfElementLocated(NewRequest));
//        WebElement newRequest = driver.findElement(NewRequest);
//        actions.moveToElement(newRequest).perform();
//
//        WebElement timeManagement = driver.findElement(TimeManagement);
//        actions.moveToElement(timeManagement).perform();

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
//                Thread.sleep(10000);

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
        driver.findElement(FromDateLeave).sendKeys(ConfigReader.get("FromDateEmployee1Leave"));
        driver.findElement(FromDateLeave).clear();
        driver.findElement(FromDateLeave).sendKeys(ConfigReader.get("FromDateEmployee1Leave"));
        driver.findElement(ToDateLeave).sendKeys(ConfigReader.get("ToDateEmployee1Leave"));
        driver.findElement(ToDateLeave).clear();
        driver.findElement(ToDateLeave).sendKeys(ConfigReader.get("ToDateEmployee1Leave"));
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        TimeManagement();
        //Open Screen
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
        //-----------------------------------------------------
        wait.until(ExpectedConditions.elementToBeClickable(MissionType));
        driver.findElement(MissionType).click();
        //----------------------------------------------------
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



    static void submitDocumentRequest(){
        String Employee1 = ConfigReader.get("userName1");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        Personnel();


    }

    @Test
    public void performAllActions() {
        setupDriver();
        startBrowser();
        login();
        submitLeaveRequest();  //Done
        submitMissionRequest();  //Pending
    }
}

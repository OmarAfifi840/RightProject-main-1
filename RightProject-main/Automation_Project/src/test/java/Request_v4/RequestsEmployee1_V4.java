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
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class RequestsEmployee1_V4 {
    static final Logger logger = LogManager.getLogger(RequestsEmployee1_V4.class);
    static WebDriver driver;
    static Actions actions;
    static String reqType;
    // Locaters
    static By UserName = By.xpath("//*[@formcontrolname='email']");
    static By Password = By.xpath("//*[@formcontrolname='password']");
    static By LoginButton = By.id("kt_sign_in_submit");
    // New Request valid on all requests
    static By NewRequest = By.xpath("//span[@class='menu-title' and .='New Request']");
    //Send Request is valid on all requests :)
    static By SendRequest = By.xpath("//span[@class='indicator-label' and normalize-space()='Send Request']");
    //Personnel valid on all requests for the Personnel Requests
    static By Personnel = By.xpath("//span[@class='menu-title' and .=' Personnel ']");
    // Time Management valid on all requests for the Time Management Requests
    static By TimeManagement = By.xpath("//span[@class='menu-title' and .=' Time Attendance ']");
    //---------------Time Management--------------------//
    static By LeaveRequest = By.xpath("//span[@class='menu-title' and .=' Leaves Request ']");
    static By FromDateLeave = By.xpath("//*[@formcontrolname='dateFrom']");
    static By ToDateLeave = By.xpath("//*[@formcontrolname='dateTo']");
    static By NotesLeaves = By.xpath("//*[@formcontrolname='reason']");
    //-----------------------------------//
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
    //-----------------------------------//
    static By PermmissionRequest = By.xpath("//span[@class='menu-title' and .=' Permission Request ']");
    static By PermissionDate = By.xpath("//*[@formcontrolname='permDate']");
    static By PermissionTimeFrom = By.xpath("//*[@formcontrolname='newUpdatedFromTime']");
    static By PermissionTimeTo = By.xpath("//*[@formcontrolname='newUpdatedToTime']");
    static By NotesPermission = By.xpath("//*[@formcontrolname='reason']");
    //-----------------------------------//
    static By WorkFromHome = By.xpath("//span[@class='menu-title' and .=' Work From Home ']");
    static By FromDateWFH = By.xpath("//app-wfhrequest//input[@formcontrolname='fromDate']");
    static By ToDateWFH = By.xpath("//app-wfhrequest//input[@formcontrolname='toDate']");
    static By WFHNotes = By.xpath("//*[@formcontrolname='reason']");
    //----------------Personnel--------------------//
    static By DocumentRequest = By.xpath("//span[@class='menu-title' and .=' Employee Document ']");
    static By NotesDocument = By.xpath("//*[@formcontrolname ='notes']");
    //------------------------------------//
    static By FamilyRequest = By.xpath("//span[@class='menu-title' and .=' Family Medical Insurance ']");
    static By AddNewRecord = By.xpath("//*[@class='btn btn-primary'  and .=' Add New Record ']");
    static By RelativeName = By.xpath("//*[@formcontrolname='relativeName']");
    static By RelativeBirthDate = By.xpath("//*[@formcontrolname='birthDate']");
    static By ID = By.xpath("//*[@formcontrolname='id']");
    static By PasswportID = By.xpath("//*[@formcontrolname='passportId']");
    static By Save = By.xpath("//button[@type='submit']");
    static By Delete = By.xpath("//div[@comp-id='84']");

    //--------------------------------------------------------//
    static void setupDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeDriverService service = new ChromeDriverService.Builder().withLogOutput(System.out).build();
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
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

    static void TimeMangementMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        //Open Screen
        wait.until(ExpectedConditions.visibilityOfElementLocated(NewRequest));
        WebElement newRequest = driver.findElement(NewRequest);
        actions.moveToElement(newRequest).perform();

        WebElement timeManagement = driver.findElement(TimeManagement);
        actions.moveToElement(timeManagement).perform();
    }

    static void Personnel() {
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
//        //Open Screen
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

            //span[@class='mat-option-text' and normalize-space()='01 - اجازة سنوية']
            //span[@class='mat-option-text' and normalize-space()=' 01 - اجازة سنوية ']

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
        //Open Screen
        TimeMangementMenu();
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

    static void submitDocumentRequest() throws InterruptedException {

        String Employee1 = ConfigReader.get("userName1");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        Personnel();
        WebElement Document = driver.findElement(DocumentRequest);
        actions.moveToElement(Document).click().perform();
        //Log Data and Check Screen Name
        Infologger("Document" + " / UserName :" + Employee1);
        screenname();
        employeeCode("employee-document");
        //Fields inputs
        WebElement DoctypeDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@formcontrolname='docmentCode']")));
        DoctypeDropdown.click();
        Thread.sleep(5000);

        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//mat-option//span[@class='mat-option-text']")
        ));
        if (!options.isEmpty()) {
            WebElement DocName = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[@class='mat-option-text' and normalize-space()='شهادة الميلاد']")
            ));
            DocName.click();

            //-------------//
            //driver.findElement(DocumentNameField).click();
            //driver.findElement(DocumentName).click();
            driver.findElement(NotesDocument).sendKeys("Document Request");
            wait.until(ExpectedConditions.elementToBeClickable(SendRequest));
            driver.findElement(SendRequest).click();
            try {
                swalerrorMessage("Document Request");
            } catch (TimeoutException e1) {
                Infologger("Document Request" + " Request is submitted successfully" + " / UserName : " + Employee1);
            }
        }
    }

    static void OpenFamilyMedical(){
        String Employee1 = ConfigReader.get("userName1");
        Personnel();
        WebElement FamilyMedical = driver.findElement(FamilyRequest);
        actions.moveToElement(FamilyMedical).click().perform();
        //Log Data and Check Screen Name
        Infologger("Family Medical" + " / UserName :" + Employee1);
        screenname();
        employeeCode("employeefamily");
    }


    static void AddNewRecord() throws InterruptedException {
        String fileName = "ESS Issues.xlsx"; // Ensure correct filename + extension
        String absolutePath = "D:\\Omar Afifi\\SelfService\\" + fileName;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        driver.findElement(AddNewRecord).click();
        driver.findElement(RelativeName).sendKeys("Test");
        //-----Relative Degree------//
        WebElement RelativeDeg = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//*[@formcontrolname='relativeDegree']")));
        RelativeDeg.click();
        //Thread.sleep(5000);
        List<WebElement> Degreeoptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//*[@formcontrolname='relativeDegree']")));
        if (!Degreeoptions.isEmpty()) {
            WebElement Degree = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//mat-option//span[@class='mat-option-text']")
            ));
            Degree.click();
            //-----------//
            driver.findElement(RelativeBirthDate).sendKeys("9/9/2025");
            //------Gender------//
            WebElement Gender = wait.until(ExpectedConditions.elementToBeClickable
                    (By.xpath("//*[@formcontrolname='genderCode']")));
            Gender.click();
            //Thread.sleep(5000);
            List<WebElement> GenderOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.xpath("//*[@formcontrolname='genderCode']")));
            if (!GenderOptions.isEmpty()) {
                WebElement Male = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div//span[@class='mat-option-text' and normalize-space()='Male']")
//                  In Case of choosing Female use the following:
//                  By.xpath("//div//span[@class='mat-option-text' and normalize-space()='Female']")));
                ));
                Male.click();
                //------Nationality--------//
                WebElement Nationality = wait.until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//*[@formcontrolname='nationalityCode']")));
                Nationality.click();
                //Thread.sleep(5000);
                List<WebElement> NationalityOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//*[@formcontrolname='nationalityCode']")));

                if (!NationalityOptions.isEmpty()) {
                    WebElement NationalityChoice = wait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//div//span[@class='mat-option-text' and normalize-space()='Egyptian']")
//                  In Case of choosing Female use the following:
//                  By.xpath("//div//span[@class='mat-option-text' and normalize-space()='Female']")));
                    ));
                    NationalityChoice.click();
                }
            }
            driver.findElement(ID).sendKeys("30006248800499");
            driver.findElement(PasswportID).sendKeys("Test");
        }
        if (!new File(absolutePath).exists()) {
            throw new RuntimeException("File not found: " + absolutePath);
        }
        WebElement fileInput = driver.findElement(By.xpath("//*[@formcontrolname='attachFile']"));
        fileInput.sendKeys(absolutePath);
        // Verify upload success by checking if input is now "ng-dirty"
        try {
            wait.until(ExpectedConditions.attributeContains(
                    By.xpath("//*[@formcontrolname='attachFile']"),
                    "class",
                    // "ng-pristine"
                    "ng-dirty"  // Checks if "ng-dirty" exists in class attribute
            ));
        } catch (TimeoutException e) {
            System.err.println("❌ Upload failed");
            throw e;
        }
        driver.findElement(Save).click();
    }

    static void FamilyMedicalRequest() throws InterruptedException {
        OpenFamilyMedical();
        AddNewRecord();
        AddNewRecord();
        driver.findElement(SendRequest).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Locate the validation message (adjust locator if needed for your popup)
        WebElement messageElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='swal2-html-container']"))
        );

        String actualMessage = messageElement.getText().trim();
        String expectedMessage = "This ID already exist"; // expected value

        try {
            Assert.assertEquals(actualMessage, expectedMessage);
            System.out.println("✅ Validation PASSED: " + actualMessage);
        } catch (AssertionError e) {
            System.out.println("❌ Validation FAILED! Expected: " + expectedMessage
                    + " | But got: " + actualMessage);
            throw e; // let TestNG mark test as failed
        }

        // Close popup (ESC since no OK button)
        driver.switchTo().activeElement().sendKeys(Keys.ESCAPE);
        driver.findElement(Delete).click();
        driver.findElement(SendRequest).click();
    }

    @Test
    public void performAllActions() throws InterruptedException {
        setupDriver();
        startBrowser();
        login();
//        submitLeaveRequest();        //Done
//        submitMissionRequest();     //Done
//        submitPermissionRequest(); //Done
//        submitWFHRequest();        //Done
//        submitDocumentRequest();   //Done
        FamilyMedicalRequest();      //Done
    }
}




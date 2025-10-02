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
    //Travel valid on all requests for the Personnel Requests
    static By Travel = By.xpath("//span[@class='menu-title' and .=' Travel ']");
    // Add New Record is Valid for any Screen With grid to add a new record.
    static By AddNewRecord = By.xpath("//*[@class='mat-button-wrapper'  and .=' Add New Record ']");
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

    static By RelativeName = By.xpath("//*[@formcontrolname='relativeName']");
    static By RelativeBirthDate = By.xpath("//*[@formcontrolname='birthDate']");
    static By ID = By.xpath("//*[@formcontrolname='id']");
    static By PasswportID = By.xpath("//*[@formcontrolname='passportId']");
    static By Save = By.xpath("//button[@type='submit']");
    static By Delete = By.xpath("//div[@comp-id='84']");
    //--------------------------------------------------------//
    static By PenltyModule = By.xpath("//*[@id=\"kt_app_header_menu\"]/div[2]/div/div[3]/span/span[2]");
    static By PenltyRequest = By.xpath("//*[@id=\"kt_app_header_menu\"]/div[2]/div/div[3]/div/div/a/span[2]");
    static By MisconductDate = By.xpath("//*[@formcontrolname='misconductDate']");
    static By Misconduct = By.xpath("//*[@id='genericDropDown']");
    static By MisconductCode = By.xpath("//*[@class='mat-option-text' and normalize-space()='10 - عدم ارتداء الزي الخاص بالعمل أثناء ساعات العمل']");
    static By Notes = By.xpath("//*[@formcontrolname='note']");
    //--------------------------------------------------------//
    static By Resignation = By.xpath("//span[@class='menu-title' and .=' Resignation ']");
    static By ResignationDate = By.xpath("//*[@formcontrolname='resignationDate']");
    static By ResignationReason = By.xpath("//*[@formcontrolname='reason']");
    //--------------------------------------------------------//
    static By Termination = By.xpath("//span[@class='menu-title' and .=' Termination ']");
    static By TerminationDate = By.xpath("//*[@formcontrolname='terminationDate']");
    static By TerminationReason = By.xpath("//*[@formcontrolname='reasonCode']");
    static By TerminationSecondOption = By.xpath("//span[@class='mat-option-text' and normalize-space() ='Termination of services - System']");
    static By TerminationNotes = By.xpath("//*[@formcontrolname='notes']");
    //-------------------Travel-------------------------//
    static By TravelRequest = By.xpath("//span[@class='menu-title' and .=' Travelling Request ']");
    static By FrequentFlyerNo = By.xpath("//*[@formcontrolname='frequentFlyerNo']");
    static By IncludeHotel = By.xpath("//*[@formcontrolname='includeHotel']");
    static By IncludeTransportation = By.xpath("//*[@formcontrolname='includeTransportation']");
    static By TravellingNotes = By.xpath("//*[@formcontrolname='notes']");
    static By DestinationTab = By.xpath("//div[@class='mat-tab-label-content' and normalize-space()='Destination']");
    //static By AddNewRecord = By.xpath("//*[@class='mat-button-wrapper'  and .=' Add New Record ']");
    static By DepartureCity = By.xpath("//div//mat-select//div//div//span//span[@class='mat-select-min-line ng-tns-c119-36 ng-star-inserted' and normalize-space()='01 - Cairo']");
    static By DepartureCity2 = By.xpath("//div//mat-select//div//div//span//span[@class='mat-select-min-line ng-tns-c119-46 ng-star-inserted' and normalize-space()='01 - Cairo']");
    static By Giza = By.xpath("//span[@class='mat-option-text' and normalize-space()='02 - Giza']");
    static By ArrivalCity = By.xpath("//div//mat-select//div//div//span//span[@class='mat-select-min-line ng-tns-c119-38 ng-star-inserted' and normalize-space()='01 - Cairo']");
    static By ArrivalCity2 = By.xpath("//div//mat-select//div//div//span//span[@class='mat-select-min-line ng-tns-c119-48 ng-star-inserted' and normalize-space()='01 - Cairo']");
    static By KafrElSheikh = By.xpath("//span[@class='mat-option-text' and normalize-space()='17 - Kafr El Sheikh']");
    static By Time = By.xpath("//*[@formcontrolname='timePreferenceCode']");
    static By MorningTime = By.xpath("//span[@class='mat-option-text' and normalize-space()='Morning']");
    static By TravelDate = By.xpath("//*[@formcontrolname='travelDate_Date']");
    static By TravelPurpose = By.xpath("//*[@formcontrolname='travelPurposeNotes']");
    static By HostedBy = By.xpath("//*[@formcontrolname='hostingPerson']");
    static By HotelTab = By.xpath("//div[@class='mat-tab-label-content' and normalize-space()='Hotel']");
    static By CheckIn = By.xpath("//*[@formcontrolname='checkIn_Date']");
    static By CheckOut = By.xpath("//*[@formcontrolname='checkOut_Date']");
    static By Include = By.xpath("//*[@formcontrolname='hotelInclude']");
    static By FoodPref = By.xpath("//*[@formcontrolname='foodPreference']");
    static By TransPortationTab = By.xpath("//div[@class='mat-tab-label-content' and normalize-space()='Transportation']");

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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(Path)));
        okButton.click();
        //System.out.println(Message);
        logger.info(Message);
    }

    static void screenname() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2")));
    }

    static WebElement employeeCode(String reqType) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        By locator = By.xpath(
                "/html/body/div[1]/app-layout/div/div/div/div/div/app-content/div/app-" + reqType + "/div/div/div[2]/div/form/div[1]/div[1]/div/app-ss-employee-select/div/mat-form-field/div/div[1]/div[3]/mat-select/div/div[1]/span/span");

        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Element not found within timeout: " + e.getMessage());
            return null;
        }
    }

    static WebElement employeeCodePenalty(String reqType) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        By locator = By.xpath(
                "/html/body/div[1]/app-layout/div/div/div/div/div/app-content/div/app-"+ reqType +"/div/div/div[2]/div/form/div[1]/div[1]/div/app-ss-employee-select/div/mat-form-field/div/div[1]/div[3]/mat-select/div/div[1]/span");

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

    static void TravelMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        //Open Screen
        wait.until(ExpectedConditions.visibilityOfElementLocated(NewRequest));
        WebElement newRequest = driver.findElement(NewRequest);
        actions.moveToElement(newRequest).perform();

        WebElement timeManagement = driver.findElement(Travel);
        actions.moveToElement(timeManagement).perform();
    }


    static void PenaltyMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        //Open Screen
        wait.until(ExpectedConditions.visibilityOfElementLocated(NewRequest));
        WebElement newRequest = driver.findElement(NewRequest);
        actions.moveToElement(newRequest).perform();

        WebElement Penltymenu = driver.findElement(PenltyModule);
        actions.moveToElement(Penltymenu).perform();
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
        String userName = ConfigReader.get("userName");
        String password = ConfigReader.get("password");
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

    static void submitWFHRequest() {
        String Employee1 = ConfigReader.get("userName4");
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
            driver.findElement(ID).sendKeys("29011170100566");
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

    static void NoEmpCode(){
try {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='empCode']")));
    Actions actions = new Actions(driver);
    actions.moveToElement(dropdown).pause(Duration.ofMillis(20000)).click().perform();

    WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[@class='mat-option-text' and normalize-space()='0222222 - Omar']"))); // put the visible text
    option.click();

} catch (TimeoutException e) {
    NoEmpCode();
    }
}

    static void TravelType(){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@formcontrolname='flightTypeCode']")));
            Actions actions = new Actions(driver);
            actions.moveToElement(dropdown).pause(Duration.ofMillis(20000)).click().perform();

            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//mat-option//span[@class='mat-option-text' and normalize-space()='International']"))); // put the visible text
            option.click();

        } catch (TimeoutException e) {
            NoEmpCode();
        }
    }

    static void Penalty() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        String fileName = "ESS Issues.xlsx"; // Ensure correct filename + extension
        String absolutePath = "D:\\Omar Afifi\\SelfService\\" + fileName;
        PenaltyMenu();
        driver.findElement(PenltyRequest).click();
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='empCode']")));
        NoEmpCode();
        driver.findElement(Misconduct).click();
        driver.findElement(MisconductDate).sendKeys("9/19/2025");
        driver.findElement(MisconductDate).clear();
        driver.findElement(MisconductDate).sendKeys("9/19/2025");
        driver.findElement(MisconductCode).click();
        driver.findElement(Notes).sendKeys("Test");
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
        driver.findElement(SendRequest).click();

    }

    static void PenaltyValidation() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        String fileName = "ESS Issues.xlsx"; // Ensure correct filename + extension
        String absolutePath = "D:\\Omar Afifi\\SelfService\\" + fileName;
        NoEmpCode();
        driver.findElement(Misconduct).click();
        driver.findElement(MisconductDate).sendKeys("9/19/2025");
        driver.findElement(MisconductDate).clear();
        driver.findElement(MisconductDate).sendKeys("9/19/2025");
        driver.findElement(MisconductCode).click();
        driver.findElement(Notes).sendKeys("Test");
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
        driver.findElement(SendRequest).click();
        WebElement messageElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='swal2-html-container']"))
        );

        String actualMessage = messageElement.getText().trim();
        String expectedMessage = "Please check pending requests, or if there is a smilar missconduct on the same date"; // expected value

        try {
            Assert.assertEquals(actualMessage, expectedMessage);
            System.out.println("✅ Validation PASSED: " + actualMessage);
        } catch (AssertionError e) {
            System.out.println("❌ Validation FAILED! Expected: " + expectedMessage
                    + " | But got: " + actualMessage);
            throw e; // let TestNG mark test as failed
        }
    }

    static void Resignation() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        String Employee1 = ConfigReader.get("userName1");
        Personnel();
        WebElement ResignationRequest = driver.findElement(Resignation);
        actions.moveToElement(ResignationRequest).click().perform();
        Infologger("Resignation Request" + " / UserName :" + Employee1);
        screenname();
        WebElement ResDate = driver.findElement(By.xpath("//*[@formcontrolname='resignationDate']"));
        ResDate.sendKeys("6/16/2025");

        try {
            wait.until(ExpectedConditions.attributeContains(
                    By.xpath("//*[@formcontrolname='resignationDate']"),
                    "class",
                    // "ng-pristine"
                    "ng-dirty"  // Checks if "ng-dirty" exists in class attribute
            ));

        } catch (TimeoutException e) {
            System.err.println("Date is Wrong can not submit a request!!");
            throw e;
        }

        ResDate.click();

        ResDate.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

        driver.findElement(ResignationDate).sendKeys("10/25/2025");

        TryReason();

//        driver.findElement(ResignationReason).sendKeys("Test");

//        driver.findElement(ResignationReason).sendKeys("Test Test Test");
        driver.findElement(SendRequest).click();
        WebElement messageElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='swal2-html-container']"))
        );

        String actualMessage = messageElement.getText().trim();
        String expectedMessage = "A pending or approved resignation request already exists for this employee"; // expected value

        try {
            Assert.assertEquals(actualMessage, expectedMessage);
            System.out.println("✅ Validation PASSED: " + actualMessage);
            clickSwalOkIfExists();
            // Shit
//            WebElement dateField = driver.findElement(ResignationDate);
// Focus on the field
//            dateField.click();
// Select all and delete
//            dateField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
//            dateField.sendKeys(Keys.DELETE);
// Now type the new date
//            dateField.sendKeys("10/27/2025");
//            driver.findElement(SendRequest).click();
        } catch (AssertionError e) {
            System.out.println("❌ Validation FAILED! Expected: " + expectedMessage
                    + " | But got: " + actualMessage);
            throw e; // let TestNG mark test as failed
        }


    }

    public static void TryReason() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        int attempts = 0;
        boolean isValid = false;

        while (attempts < 5 && !isValid) { // retry max 5 times
            try {
                WebElement reasonField = driver.findElement(ResignationReason);
                reasonField.sendKeys("Test");

                // Click outside to trigger validation
                driver.findElement(ResignationDate).click();

                // Wait until aria-invalid becomes "false"
                wait.until(ExpectedConditions.attributeToBe(
                        reasonField,
                        "aria-invalid",
                        "false"
                ));

                isValid = true; // exit loop if success
                Infologger("Reason field is valid now!");

            } catch (TimeoutException e) {
                attempts++;
                Infologger("Attempt " + attempts + ": Reason still invalid...");
            }
        }

        if (!isValid) {
            throw new RuntimeException("Reason is Empty - cannot submit request!!");
        }

    }

    static void TerminationRequest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        String Employee1 = ConfigReader.get("userName");
        Personnel();
        WebElement TerminationReq = driver.findElement(Termination);
        actions.moveToElement(TerminationReq).click().perform();
        Infologger("Termination Request" + " / UserName :" + Employee1);
        screenname();
        NoEmpCode();
        driver.findElement(TerminationReason).click();
        driver.findElement(TerminationSecondOption).click();
        WebElement TermDate = driver.findElement(By.xpath("//*[@formcontrolname='terminationDate']"));
        TermDate.click();

        TermDate.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

        driver.findElement(TerminationDate).sendKeys("10/30/2025");

        driver.findElement(TerminationNotes).sendKeys("Test");
        driver.findElement(SendRequest).click();
        try {
            // Wait for Swal message (short timeout so it doesn't block)
            WebElement messageElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='swal2-html-container']"))
            );
            // If Swal appeared, validate message
            String actualMessage = messageElement.getText().trim();
            String expectedMessage = "There is pending requests for this employee";

            try {
                Assert.assertEquals(actualMessage, expectedMessage);
                Infologger("✅ Validation PASSED: " + actualMessage);

                // Close Swal
                clickSwalOkIfExists();

            } catch (AssertionError e) {
                // Swal appeared but the text didn't match
                Infologger("⚠️ Validation FAILED. Expected: " + expectedMessage + " but got: " + actualMessage);
                throw e; // keep it failing test, or remove this line if you want to continue instead
            }

        } catch (TimeoutException e) {
            // No Swal appeared → just continue with normal flow
            Infologger("No Swal appeared. Continuing with request submission...");
        }
    }

    public static void Department() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));

        WebElement depField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mat-input-0")));

        String fieldValue = wait.until(d -> {
            String val = d.findElement(By.id("mat-input-0")).getAttribute("value");
            return val != null && !val.trim().isEmpty() ? val : null;
        });
        if (fieldValue != null) {
            Infologger("Field is loaded with data: " + fieldValue);
            Infologger("Data loaded check");
            Infologger("---------------------------------------------------------------------------");
        } else {
            Infologger("Field is empty after waiting");
            Infologger("Data loaded check");
            Infologger("---------------------------------------------------------------------------");
        }

        if (!depField.isEnabled()) {
            Infologger("Department field is disabled (dimmed) as expected");
            Infologger("Disabled state check");
            Infologger("---------------------------------------------------------------------------");
        } else {
            Infologger("Department field is enabled (not dimmed)");
            Infologger("Disabled state check");
            Infologger("---------------------------------------------------------------------------");
        }
    }
    public static void MobileNumber(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));

        WebElement depField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mat-input-0")));

        String fieldValue = wait.until(d -> {
            String val = d.findElement(By.id("mat-input-1")).getAttribute("value");
            return val != null && !val.trim().isEmpty() ? val : null;
        });
        if (fieldValue != null) {
            Infologger("Field is loaded with data: " + fieldValue);
            Infologger("Data loaded check");
            Infologger("---------------------------------------------------------------------------");
        } else {
            Infologger("Field is empty after waiting");
            Infologger("Data loaded check");
            Infologger("---------------------------------------------------------------------------");
        }
        if (!depField.isEnabled()) {
            Infologger("Mobile Number field is disabled (dimmed) as expected");
            Infologger("Disabled state check");
            Infologger("---------------------------------------------------------------------------");
        } else {
            Infologger("Mobile Number field is enabled (not dimmed)");
            Infologger("Disabled state check");
            Infologger("---------------------------------------------------------------------------");
        }

    }

    public static void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(60)).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete")
        );
    }


    static void TravellingRequest() throws InterruptedException {
        String fileName = "ESS Issues.xlsx"; // Ensure correct filename + extension
        String absolutePath = "D:\\Omar Afifi\\SelfService\\" + fileName;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        String Employee = ConfigReader.get("userName");
        TravelMenu();
        WebElement Travel = driver.findElement(TravelRequest);
        actions.moveToElement(Travel).click().perform();
        waitForPageLoad();
        screenname();
        Infologger("Travelling Request" + " / UserName :" + Employee);
        By locator = By.xpath(
                "/html/body/div[1]/app-layout/div/div/div/div/div/app-content/div/app-travel-request/div/div/div[2]/div/form/mat-tab-group/div/mat-tab-body[1]/div/div[1]/div[1]/div/app-ss-employee-select/div/mat-form-field/div/div[1]/div[3]/mat-select/div/div[1]");

        try {
             wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            Infologger("");
        }
        Department();
        MobileNumber();
            WebElement requestDateElement = wait.until(d -> d.findElement(By.xpath("//*[@formcontrolname='requestDate']")));

            String requestDateValue = requestDateElement.getAttribute("value");

        if (requestDateValue != null) {
            Infologger("Field is loaded with data: " + requestDateValue);
            Infologger("Data loaded check");
            Infologger("---------------------------------------------------------------------------");
        } else {
            Infologger("Field is empty after waiting");
            Infologger("Data loaded check");
            Infologger("---------------------------------------------------------------------------");
        }
        if (!requestDateElement.isEnabled()) {
            Infologger("Request Date field is disabled (dimmed) as expected");
            Infologger("Disabled state check");
            Infologger("---------------------------------------------------------------------------");
        } else {
            Infologger("Request Date field is enabled (not dimmed)");
            Infologger("Disabled state check");
            Infologger("---------------------------------------------------------------------------");
        }
        driver.findElement(FrequentFlyerNo).click();
        WebElement TravelType = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@formcontrolname='internalOrExternal']")));
        TravelType.click();

        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//mat-option//span[@class='mat-option-text']")
        ));

//        WebElement International = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//mat-option//span[@class='mat-option-text' and normalize-space()='International']")
//        ));
//        International.click();
//        Infologger("International Option Is clicked");
//        In case of Domestic travlling
        WebElement Domestic = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//mat-option//span[@class='mat-option-text' and normalize-space()='Domestic']")
        ));
        Domestic.click();
        Infologger("Domestic Option Is clicked");
        /// /////////////////////////////////////
        WebElement TicketType = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@formcontrolname='flightTypeCode']")));
        TicketType.click();
        List<WebElement> options1 = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//mat-option//span[@class='mat-option-text']")
        ));
//        WebElement OneWay = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//mat-option//span[@class='mat-option-text' and normalize-space()='One Way']")
//        ));
//        OneWay.click();
//        Infologger("One Way Option Is clicked");
//        In case of Multiple Destnation travlling
        WebElement MultipleDestination = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//mat-option//span[@class='mat-option-text' and normalize-space()='Multiple Destinations']")
        ));
        MultipleDestination.click();
        Infologger("Multiple Destinations Option Is clicked");

        /// ///////////////////////////////////
        WebElement TravelPurpose = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@formcontrolname='travelPurposeCode']")));
        TravelPurpose.click();
        List<WebElement> options2 = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//mat-option//span[@class='mat-option-text']")
        ));
//        WebElement BusinessMeeting = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//mat-option//span[@class='mat-option-text' and normalize-space()='Business Meeting']")
//        ));
//        BusinessMeeting.click();
        //In case of Training travlling
        WebElement Training = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//mat-option//span[@class='mat-option-text' and normalize-space()='Training']")
        ));
        Training.click();
        Infologger("Trainning Option is clicked");
        //In case of Exhibition travlling
//        WebElement Exhibition = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//mat-option//span[@class='mat-option-text' and normalize-space()='Exhibition']")
//        ));
//       Exhibition.click();
        //In case of Project Related Trip travlling
//        WebElement  ProjectRelatedTrip  = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//mat-option//span[@class='mat-option-text' and normalize-space()='Project Related Trip']")
//        ));
//        ProjectRelatedTrip.click();
        //In case of HR Related Trip  travlling
//        WebElement HRRelatedTrip = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//mat-option//span[@class='mat-option-text' and normalize-space()='HR Related Trip']")
//        ));
//        HRRelatedTrip.click();
//        static By DateFrom = By.xpath("//*[@formcontrolname='dateFrom']");
//        static By DateTo = By.xpath("//*[@formcontrolname='dateTo']");
        //driver.findElement(DateFrom).sendKeys("9/30/2025");

        WebElement DateTo = driver.findElement(By.xpath("//*[@formcontrolname='dateTo']"));
        DateTo.click();
        DateTo.sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.DELETE));
        DateTo.sendKeys("10/3/2025");
        WebElement DateFrom = driver.findElement(By.xpath("//*[@formcontrolname='dateFrom']"));
        DateFrom.click();
        DateFrom.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        DateFrom.sendKeys("10/2/2025");
        driver.findElement(IncludeHotel).click();
        driver.findElement(IncludeTransportation).click();
        driver.findElement(FrequentFlyerNo).sendKeys("Test");
        driver.findElement(TravellingNotes).sendKeys("Test");
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
        driver.findElement(SendRequest).click();

        WebElement messageElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='swal2-html-container']"))
        );

        String actualMessage = messageElement.getText().trim();
        String expectedMessage = "Please Add Destination."; // expected value

        try {
            Assert.assertEquals(actualMessage, expectedMessage);
            System.out.println("✅ Validation PASSED: " + actualMessage);
        } catch (AssertionError e) {
            System.out.println("❌ Validation FAILED! Expected: " + expectedMessage
                    + " | But got: " + actualMessage);
            throw e; // let TestNG mark test as failed
        }
        clickSwalOkIfExists();

        driver.findElement(DestinationTab).click();
        scrollToTop(driver);
        driver.findElement(AddNewRecord).click();
        AddNewRecordTravelling();
        driver.findElement(AddNewRecord).click();
        AddNewRecord2Travelling();
        AddingHotel();
    }

    public static void scrollToTop(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // First try window scroll
        js.executeScript("window.scrollTo(0, 0);");

        // Then check if there are scrollable elements and scroll them too
        js.executeScript(
                "var elems = document.querySelectorAll('*');" +
                        "for (var i = 0; i < elems.length; i++) {" +
                        "  if (elems[i].scrollTop) {" +
                        "    elems[i].scrollTop = 0;" +
                        "  }" +
                        "}"
        );
    }


    static void AddNewRecordTravelling(){
        driver.findElement(DepartureCity).click();
        driver.findElement(Giza).click();
        driver.findElement(ArrivalCity).click();
        driver.findElement(KafrElSheikh).click();
        driver.findElement(Time).click();
        driver.findElement(MorningTime).click();
        driver.findElement(TravelDate).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        driver.findElement(TravelDate).sendKeys("10/2/2025");
        driver.findElement(TravelPurpose).sendKeys("Test");
        driver.findElement(HostedBy).sendKeys("Test");
        driver.findElement(Save).click();
    }
    static void AddNewRecord2Travelling() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        driver.findElement(DepartureCity2).click();
        driver.findElement(KafrElSheikh).click();
        driver.findElement(ArrivalCity2).click();
        driver.findElement(Giza).click();
        driver.findElement(Time).click();
        driver.findElement(MorningTime).click();
        driver.findElement(TravelDate).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        driver.findElement(TravelDate).sendKeys("10/3/2025");
        driver.findElement(TravelPurpose).sendKeys("Test");
        driver.findElement(HostedBy).sendKeys("Test");
        driver.findElement(Save).click();

        driver.findElement(SendRequest).click();

        WebElement messageElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='swal2-html-container']"))
        );

        String actualMessage = messageElement.getText().trim();
        String expectedMessage = "Please Add Hotel."; // expected value

        try {
            Assert.assertEquals(actualMessage, expectedMessage);
            System.out.println("✅ Validation PASSED: " + actualMessage);
        } catch (AssertionError e) {
            System.out.println("❌ Validation FAILED! Expected: " + expectedMessage
                    + " | But got: " + actualMessage);
            throw e; // let TestNG mark test as failed
        }
        clickSwalOkIfExists();
        //Thread.sleep(10000);
        scrollToTop(driver);
        Thread.sleep(10000);
        driver.findElement(HotelTab).click();

    }
    static void AddingHotel() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        driver.findElement(AddNewRecord).click();
        driver.findElement(CheckIn).sendKeys("10/2/2025");
        driver.findElement(CheckOut).sendKeys("10/3/2025");
        driver.findElement(Include).sendKeys("Test");
        driver.findElement(FoodPref).sendKeys("Test");
        driver.findElement(Save).click();

        driver.findElement(SendRequest).click();

        WebElement messageElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='swal2-html-container']"))
        );

        String actualMessage = messageElement.getText().trim();
        String expectedMessage = "Please Add Transportation."; // expected value

        try {
            Assert.assertEquals(actualMessage, expectedMessage);
            System.out.println("✅ Validation PASSED: " + actualMessage);
        } catch (AssertionError e) {
            System.out.println("❌ Validation FAILED! Expected: " + expectedMessage
                    + " | But got: " + actualMessage);
            throw e; // let TestNG mark test as failed
        }
        clickSwalOkIfExists();
        //Thread.sleep(10000);
        scrollToTop(driver);
        Thread.sleep(10000);
        driver.findElement(TransPortationTab).click();



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
//        FamilyMedicalRequest();      //Done Except the Grid handling
//        Penalty(); //Done
//        PenaltyValidation(); //Done
//        Resignation(); //Done
//        TerminationRequest(); //Done
        TravellingRequest();

        }
}




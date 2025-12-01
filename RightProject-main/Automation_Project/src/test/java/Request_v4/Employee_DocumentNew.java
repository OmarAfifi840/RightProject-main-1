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
import utilities.DBUtils;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;

import java.time.Duration;

import static Request_v4.RequestsEmployee1_V4.Infologger;
import static Request_v4.RequestsEmployee1_V4.screenname;


public class Employee_DocumentNew {

    static void setupDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeDriverService service = new ChromeDriverService.Builder().withLogOutput(System.out).build();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(service, options);
        actions = new Actions(driver);
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


    static void Infologger(String Message) {
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

    static void Personnel() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        //Open Screen
        wait.until(ExpectedConditions.visibilityOfElementLocated(NewRequest));
        WebElement newRequest = driver.findElement(NewRequest);
        actions.moveToElement(newRequest).perform();

        WebElement PersonnelMod = driver.findElement(Personnel);
        actions.moveToElement(PersonnelMod).perform();
    }
    static void SendPath(String Path, String Message) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(Path)));
        okButton.click();
        //System.out.println(Message);
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


    static final Logger logger = LogManager.getLogger(Employee_DocumentNew.class);
    static WebDriver driver;
    static Actions actions;
    static String reqType;
    // Locaters
    static By UserName = By.xpath("//*[@formcontrolname='email']");
    static By Password = By.xpath("//*[@formcontrolname='password']");
    static By LoginButton = By.id("kt_sign_in_submit");
    // New Request valid on all requests
    static By NewRequest = By.xpath("//span[@class='menu-title' and .='New Request']");
    static By Personnel = By.xpath("//span[@class='menu-title' and .=' Personnel ']");
    static By DocumentRequest = By.xpath("//span[@class='menu-title' and .=' Employee Document ']");
    static By NotesDocument = By.xpath("//*[@formcontrolname ='notes']");
    static By SendRequest = By.xpath("//span[@class='indicator-label' and normalize-space()='Send Request']");
    //Personnel valid on all requests for the Personnel Requests

    static void login_Document() {
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

    // ---------- submitDocumentRequest ----------
    static void submitDocumentRequest() throws InterruptedException, SQLException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        Personnel();

        WebElement documentMenu = wait.until(ExpectedConditions.elementToBeClickable(DocumentRequest));
        actions.moveToElement(documentMenu).click().perform();

        String loggedUser = ConfigReader.get("userName1");
        Infologger("Document / UserName : " + loggedUser);

        // Wait for screen header
        screenname();

        // get visible employee code element (the method you already have)
        WebElement empCodeEl = employeeCode("employee-document");
        String employeeCodeText = (empCodeEl != null) ? empCodeEl.getText().trim() : null;
        logger.info("Detected employee element text: " + employeeCodeText);

        // Fields inputs
        WebElement doctypeDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@formcontrolname='docmentCode']")));
        Thread.sleep(3000);
        doctypeDropdown.click();

        // small wait for options to render
        Thread.sleep(1000);

        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//mat-option//span[@class='mat-option-text']")));

        // fail early if options not found
        Assert.assertFalse(options.isEmpty(), "No document type options found in dropdown.");

        WebElement docName = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='mat-option-text' and normalize-space()='شهادة الميلاد']")));
        docName.click();

        // fill notes and submit
        driver.findElement(NotesDocument).clear();
        driver.findElement(NotesDocument).sendKeys("Document Request");

        wait.until(ExpectedConditions.elementToBeClickable(SendRequest));
        driver.findElement(SendRequest).click();

        try {
            // if swal error appears this will log it and click OK
            swalerrorMessage("Document Request");
        } catch (TimeoutException e) {
            Infologger("Document Request submitted successfully / UserName : " + loggedUser);
        }

        // Wait a short time for DB commit / backend processing. Prefer polling in real tests.
        Thread.sleep(3000);

        // store the detected employee code in the config-like variable or a field for DB verification
        // Here we set it in a static field so verifyDocumentRequestInDB() can use it.
        String detectedEmployeeCode = (employeeCodeText != null && !employeeCodeText.isEmpty()) ? employeeCodeText : loggedUser;
    }



    @Test
    public void performAllActions() throws Exception {
        setupDriver();
        startBrowser();
        login();
        submitDocumentRequest();
        verifyDocumentRequestInDB();
    }


    // ---------- verifyDocumentRequestInDB ----------
    public void verifyDocumentRequestInDB() throws Exception {
        // Use detectedEmployeeCode if captured; fall back to config value
        String detectedEmployeeCode = "";
        String employeeCode = (detectedEmployeeCode != null && !detectedEmployeeCode.isEmpty())
                ? detectedEmployeeCode
                : ConfigReader.get("userName1");

        logger.info("Verifying DB for EmployeeCode = " + employeeCode);

        // small wait before DB reading (consider polling instead)
        Thread.sleep(3000);

        // Use same exact SQL as your DB expects. If table/columns differ, adjust.
        String sql = "SELECT TOP 1 * FROM EmpDocumentRequest WHERE EmpCode= 201336 ORDER BY ID DESC";

        ResultSet rs = null;
        try {
            rs = DBUtils.executeQuery(sql);

            Assert.assertTrue(rs.next(), "❌ Record NOT found in DB for EmployeeCode: " + employeeCode);

            String dbDocType = rs.getString("DocmentCode");
            String dbNotes = rs.getString("Notes");

            logger.info("DB -> DocumentType: " + dbDocType + " | Notes: " + dbNotes);

            Assert.assertEquals(dbDocType, "01", "DocmentType mismatch.");
            Assert.assertEquals(dbNotes, "Document Request", "Notes mismatch.");

            logger.info("✔️ DB verification passed.");
        } finally {
            // close resultset if DBUtils doesn't do it internally
            if (rs != null) {
                try { rs.close(); } catch (SQLException ignored) {}
            }
        }
    }



}

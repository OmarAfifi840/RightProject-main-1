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
        assert DoctypeDropdown != null;
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
    @Test
    public void performAllActions() throws InterruptedException {
        setupDriver();
        startBrowser();
        login();
        submitDocumentRequest();

}
}
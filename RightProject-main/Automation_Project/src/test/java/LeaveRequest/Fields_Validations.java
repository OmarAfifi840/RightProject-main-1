package LeaveRequest;

import Parallel.Employees_V3.AllActionsEmployee1_V3;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigReader;

import java.io.File;
import java.util.List;
import java.time.Duration;

public class Fields_Validations {
    private static final Logger logger = LogManager.getLogger(Fields_Validations.class);
    public static WebDriver driver1;
    
    private static void Infologger (String Message) {
        //Infologger(Message);
        logger.info(Message);  //Changed from Error To Info
    }
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

    private static  void SendPath(String Path,String Message){
        WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(3));
        WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(Path)));
        okButton.click();
        //Infologger(Message);
        logger.info(Message);
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
    }

    public static void performLogin() throws InterruptedException {
        String userName = ConfigReader.get("userName");
        String password = ConfigReader.get("password");

        Thread.sleep(1000);
        driver1.findElement(By.cssSelector("#kt_login_signin_form > div.fv-row.mb-8 > input")).sendKeys(userName);
        driver1.findElement(By.cssSelector("#kt_login_signin_form > div:nth-child(3) > input")).sendKeys(password);
        driver1.findElement(By.id("kt_sign_in_submit")).click();

        Infologger("Login");
    }

    public static void submitLeaveRequest() throws InterruptedException {
        String Employee1 = ConfigReader.get("userName");
        WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(120));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"kt_app_header_wrapper\"]/app-navbar/div[5]/div")));



        Actions actions = new Actions(driver1);
        WebElement newRequest = driver1.findElement(By.xpath("//*[@id=\"kt_app_header_menu\"]/div[2]/span/span[1]"));
        actions.moveToElement(newRequest).perform();

        WebElement timeManagement = driver1.findElement(By.xpath("//*[@id=\"kt_app_header_menu\"]/div[2]/div/div[3]/span/span[2]"));
        actions.moveToElement(timeManagement).perform();

        WebElement leaves = driver1.findElement(By.xpath("//*[@id=\"kt_app_header_menu\"]/div[2]/div/div[3]/div/div[1]/a/span[2]"));
        actions.moveToElement(leaves).click().perform();
        Thread.sleep(30000);
    }

    public static void EmployeeCode() throws InterruptedException {
        Infologger("Start Fields Validation");
        WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(10));
        WebElement EmpCode = driver1.findElement(By.xpath("/html/body/div[1]/app-layout/div/div/div/div/div/app-content/div/app-leave-request/div/div/div[2]/div/form/div[1]/div[1]/div/app-ss-employee-select/div/mat-form-field/div/div[1]/div[3]/mat-select/div/div[1]/span/span"));
        String Field = EmpCode.getText();  // changed from getAttribute("value")
        //Thread.sleep(30000);

        if (Field != null && !Field.isEmpty()) {
            Infologger("Field is loaded with data: " + Field);
            Infologger("TC1 Pass");
            Infologger("---------------------------------------------------------------------------");
        } else {
            Infologger("Field is empty");
            Infologger("TC1 Fail");
            Infologger("---------------------------------------------------------------------------");
        }
        // Add in case User is a manager
    }

    public static void Department() {
        WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(10));

        WebElement depField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mat-input-0")));

        String fieldValue = wait.until(d -> {
            String val = d.findElement(By.id("mat-input-0")).getAttribute("value");
            return val != null && !val.trim().isEmpty() ? val : null;
        });
        if (fieldValue != null) {
            Infologger("Field is loaded with data: " + fieldValue);
            Infologger("TC2 Pass - Data loaded check");
            Infologger("---------------------------------------------------------------------------");
        } else {
            Infologger("Field is empty after waiting");
            Infologger("TC2 Fail - Data loaded check");
            Infologger("---------------------------------------------------------------------------");
        }

        if (!depField.isEnabled()) {
            Infologger("Department field is disabled (dimmed) as expected");
            Infologger("TC3 Pass - Disabled state check");
            Infologger("---------------------------------------------------------------------------");
        } else {
            Infologger("Department field is enabled (not dimmed)");
            Infologger("TC3 Fail - Disabled state check");
            Infologger("---------------------------------------------------------------------------");
        }
    }
    public static void HiringDate() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(10));
        WebElement HiringDate = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@formcontrolname='fHiringDate']")));
        String Field = wait.until(d -> {
        String val = d.findElement(By.xpath("//*[@formcontrolname='fHiringDate']")).getAttribute("value");
        return val != null && !val.trim().isEmpty() ? val : null;
        });
        if (Field != null){
            Infologger("Field is loaded with data: " + Field);
            Infologger("TC4 Pass");
            Infologger("---------------------------------------------------------------------------");
        }
        else {
            Infologger("Field is empty after waiting");
            Infologger("TC4  Fail");
            Infologger("---------------------------------------------------------------------------");
        }
        if (!HiringDate.isEnabled()){
            Infologger("Hiring Date field is disabled (dimmed) as expected");
            Infologger("TC5 Pass");
            Infologger("---------------------------------------------------------------------------");
        }
        else {
            Infologger("Hiring Date field is enabled (not dimmed)");
            Infologger("TC5  Fail");
            Infologger("---------------------------------------------------------------------------");
        }
    }

//    public static void CostCenter() throws InterruptedException {
//        WebDriverWait wait = new WebDriverWait(driver1,Duration.ofSeconds(10));
//        WebElement Costcenter = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mat-input-2")));
//        String Field = wait.until(d -> {
//        String val = d.findElement(By.id("mat-input-2")).getAttribute("value");
//        return val != null && !val.trim().isEmpty() ? val : null ;
//        });
//        if (Field != null){
//            Infologger("Field is loaded with data: " + Costcenter);
//            Infologger("TC5 Pass");
//        }
//        else {
//            Infologger("Field is empty after waiting");
//            Infologger("TC5  Fail");
//        }
//        if (!Costcenter.isEnabled()){
//            Infologger("Cost Center field is disabled (dimmed) as expected");
//            Infologger("TC5 Pass");
//        }
//        else {
//            Infologger("Cost Center field is enabled (not dimmed)");
//            Infologger("TC5  Fail");
//        }
//    }
//public static void CostCenter() {
//    WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(10));
//
//    // Wait for the element to be visible (not just present)
//    WebElement costCenter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mat-input-2")));
//
//    // Check if the field is populated
//    String fieldValue = wait.until(d -> {
//        String val = costCenter.getAttribute("value");
//        return val != null && !val.trim().isEmpty() ? val : null;
//    });
//
//    if (fieldValue != null) {
//        Infologger("Field is loaded with data: " + fieldValue);
//        Infologger("TC5 Pass");
//    } else {
//        Infologger("Field is empty after waiting");
//        Infologger("TC5 Fail");
//    }
//
//    if (!costCenter.isEnabled()) {
//        Infologger("Cost Center field is disabled (dimmed) as expected");
//        Infologger("TC5 Pass");
//    } else {
//        Infologger("Cost Center field is enabled (not dimmed)");
//        Infologger("TC5 Fail");
//    }
//}

//    public static void LeavesType() {
//        try {
//        WebDriverWait wait = new WebDriverWait(driver1,Duration.ofSeconds(15));
//        WebElement Type = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@formcontrolname='vacCode']")));
//        List<WebElement> options = Type.findElements(By.xpath("//*[@formcontrolname='vacCode']"));
//        WebElement Remain = driver1.findElement(By.id("mat-input-5"));
//            Thread.sleep(50000);
//        if (!options.isEmpty()) {
//            Infologger("Dropdown contains data");
//            Infologger(options);
//            Infologger("TC6 Pass");
//            Type.click();
//            WebElement AnnualLeave = driver1.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div/mat-option[1]/span"));
//            AnnualLeave.click();
//            Thread.sleep(20000);
//            if (!Remain.equals(0)){
//                Infologger(Remain);
//                Infologger("Remain loaded");
//                Infologger("TC7 Pass");
//            } else {
//                Infologger("Remain Not loaded");
//                Infologger("TC7 Fail");
//            }
//        } else {
//            Infologger("Dropdown is empty");
//            Infologger("TC6  Fail");
//        }
//        } catch (Exception e) {
//        Infologger("Error: " + e.getMessage());
//        }
//    }

    public static void LeavesType() {
        try {
            WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(15));

            WebElement typeDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@formcontrolname='vacCode']")
            ));
            typeDropdown.click();
            List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.xpath("//mat-option//span[@class='mat-option-text']")
            ));
            if (!options.isEmpty()) {
                Infologger("Dropdown contains data:");
                for (WebElement option : options) {
                    Infologger(option.getText());
                }
                Infologger("TC6 Pass");
                Infologger("---------------------------------------------------------------------------");
                WebElement annualLeave = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//mat-option//span[contains(text(),'Annual Leave')]")
                ));
                annualLeave.click();
                WebElement remainField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.id("mat-input-5")
                ));
                Thread.sleep(10000);
                String remainValue = remainField.getAttribute("value");
                if (!remainValue.equals(0)) {
                    Infologger("Remain value: " + remainValue); // Print actual value
                    Infologger("TC7 Pass");
                    Infologger("---------------------------------------------------------------------------");
                } else {
                    Infologger("Remain Not loaded");
                    Infologger("TC7 Fail");
                    Infologger("---------------------------------------------------------------------------");
                }
            } else {
                Infologger("Dropdown is empty");
                Infologger("TC6 Fail");
            }
        } catch (Exception e) {
            Infologger("Error: " + e.getMessage());
            Infologger("---------------------------------------------------------------------------");
        }
    }
    public static void uploadFile() {
        String fileName = "ESS Issues.xlsx"; // Ensure correct filename + extension
        String absolutePath = "D:\\Omar Afifi\\SelfService\\" + fileName;

        if (!new File(absolutePath).exists()) {
            throw new RuntimeException("File not found: " + absolutePath);
        }
        WebElement fileInput = driver1.findElement(By.xpath("//*[@formcontrolname='attachedFile']"));
        fileInput.sendKeys(absolutePath);

        // Verify upload success by checking if input is now "ng-dirty"
        WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.attributeContains(
                    By.xpath("//*[@formcontrolname='attachedFile']"),
                    "class",
                   // "ng-pristine"
                    "ng-dirty"  // Checks if "ng-dirty" exists in class attribute
            ));
            Infologger("✅ File uploaded: " + fileName);
            Infologger("TC7 Pass");
            Infologger("---------------------------------------------------------------------------");
        } catch (TimeoutException e) {
            System.err.println("❌ Upload failed");
            Infologger("TC7 Fail");
            Infologger("---------------------------------------------------------------------------");
            throw e;
        }
    }

    public static void FromBalance() {
        WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(20));
        WebElement FromBalance = driver1.findElement(By.xpath("//*[@formcontrolname='serial_LB']"));
        //boolean isReadOnly = FromBalance.getAttribute("readonly") != null;

        try { List<WebElement> fromBalanceField = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.xpath("//*[@formcontrolname='serial_LB']")));

            if (!fromBalanceField.isEmpty()) {
                Infologger("Dropdown contains data:");

                for (WebElement option : fromBalanceField) {
                    Infologger(option.getText());
                }}

                    Infologger("TC8 Pass");
            Infologger("---------------------------------------------------------------------------");

        } catch (TimeoutException e) {
            Infologger("❌ Timeout: Element not found or condition not met.");
            Infologger("TC8 Fail");
            Infologger("---------------------------------------------------------------------------");
            e.printStackTrace();
        }

//        if (isReadOnly) {
//            Infologger("Dropdown is disabled");
//            Infologger("TC9 Pass");
//            Infologger("---------------------------------------------------------------------------");
//        } else {
//            Infologger("Dropdown is enabled");
//            Infologger("TC9 Fail");
//            Infologger("---------------------------------------------------------------------------");
//        }
    }
    public static void Cancel(){
        WebElement Cancelbutton = driver1.findElement(By.xpath("//*[@id=\"kt_app_content_container\"]/app-leave-request/div/div/div[3]/a"));
        Cancelbutton.click();
        WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(120));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"kt_app_header_wrapper\"]/app-navbar/div[5]/div")));
        if (  element.isDisplayed() ) {
            Infologger("User clicked Cancel Button, Dashbord is openned. ");
            Infologger("TC10 Pass");
            Infologger("---------------------------------------------------------------------------");
        }
        else {
            Infologger("User clicked Cancel Button, But dashbord is openned. ");
            Infologger("TC10 Fail");
            Infologger("---------------------------------------------------------------------------");
        }
        Infologger("End of Fields Validation");

    }
}




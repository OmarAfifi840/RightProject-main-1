package LeaveRequest;

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
    public static WebDriver driver1;

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

        System.out.println("Login");
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
        WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(10));
        WebElement EmpCode = driver1.findElement(By.xpath("/html/body/div[1]/app-layout/div/div/div/div/div/app-content/div/app-leave-request/div/div/div[2]/div/form/div[1]/div[1]/div/app-ss-employee-select/div/mat-form-field/div/div[1]/div[3]/mat-select/div/div[1]/span/span"));
        String Field = EmpCode.getText();  // changed from getAttribute("value")
        //Thread.sleep(30000);

        if (Field != null && !Field.isEmpty()) {
            System.out.println("Field is loaded with data: " + Field);
            System.out.println("TC1 Pass");
            System.out.println("---------------------------------------------------------------------------");
        } else {
            System.out.println("Field is empty");
            System.out.println("TC1 Fail");
            System.out.println("---------------------------------------------------------------------------");
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
            System.out.println("Field is loaded with data: " + fieldValue);
            System.out.println("TC2 Pass - Data loaded check");
            System.out.println("---------------------------------------------------------------------------");
        } else {
            System.out.println("Field is empty after waiting");
            System.out.println("TC2 Fail - Data loaded check");
            System.out.println("---------------------------------------------------------------------------");
        }

        if (!depField.isEnabled()) {
            System.out.println("Department field is disabled (dimmed) as expected");
            System.out.println("TC3 Pass - Disabled state check");
            System.out.println("---------------------------------------------------------------------------");
        } else {
            System.out.println("Department field is enabled (not dimmed)");
            System.out.println("TC3 Fail - Disabled state check");
            System.out.println("---------------------------------------------------------------------------");
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
            System.out.println("Field is loaded with data: " + Field);
            System.out.println("TC4 Pass");
            System.out.println("---------------------------------------------------------------------------");
        }
        else {
            System.out.println("Field is empty after waiting");
            System.out.println("TC4  Fail");
            System.out.println("---------------------------------------------------------------------------");
        }
        if (!HiringDate.isEnabled()){
            System.out.println("Hiring Date field is disabled (dimmed) as expected");
            System.out.println("TC5 Pass");
            System.out.println("---------------------------------------------------------------------------");
        }
        else {
            System.out.println("Hiring Date field is enabled (not dimmed)");
            System.out.println("TC5  Fail");
            System.out.println("---------------------------------------------------------------------------");
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
//            System.out.println("Field is loaded with data: " + Costcenter);
//            System.out.println("TC5 Pass");
//        }
//        else {
//            System.out.println("Field is empty after waiting");
//            System.out.println("TC5  Fail");
//        }
//        if (!Costcenter.isEnabled()){
//            System.out.println("Cost Center field is disabled (dimmed) as expected");
//            System.out.println("TC5 Pass");
//        }
//        else {
//            System.out.println("Cost Center field is enabled (not dimmed)");
//            System.out.println("TC5  Fail");
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
//        System.out.println("Field is loaded with data: " + fieldValue);
//        System.out.println("TC5 Pass");
//    } else {
//        System.out.println("Field is empty after waiting");
//        System.out.println("TC5 Fail");
//    }
//
//    if (!costCenter.isEnabled()) {
//        System.out.println("Cost Center field is disabled (dimmed) as expected");
//        System.out.println("TC5 Pass");
//    } else {
//        System.out.println("Cost Center field is enabled (not dimmed)");
//        System.out.println("TC5 Fail");
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
//            System.out.println("Dropdown contains data");
//            System.out.println(options);
//            System.out.println("TC6 Pass");
//            Type.click();
//            WebElement AnnualLeave = driver1.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div/mat-option[1]/span"));
//            AnnualLeave.click();
//            Thread.sleep(20000);
//            if (!Remain.equals(0)){
//                System.out.println(Remain);
//                System.out.println("Remain loaded");
//                System.out.println("TC7 Pass");
//            } else {
//                System.out.println("Remain Not loaded");
//                System.out.println("TC7 Fail");
//            }
//        } else {
//            System.out.println("Dropdown is empty");
//            System.out.println("TC6  Fail");
//        }
//        } catch (Exception e) {
//        System.out.println("Error: " + e.getMessage());
//        }
//    }

    public static void LeavesType() {
        try {
            WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(15));

            // 1. Open the dropdown
            WebElement typeDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@formcontrolname='vacCode']")
            ));
            typeDropdown.click(); // Open the dropdown

            // 2. Get all dropdown options (after dropdown is open)
            List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.xpath("//mat-option//span[@class='mat-option-text']")
            ));

            // 3. Check if dropdown has data
            if (!options.isEmpty()) {
                System.out.println("Dropdown contains data:");
                for (WebElement option : options) {
                    System.out.println(option.getText());
                }

                System.out.println("TC6 Pass");
                System.out.println("---------------------------------------------------------------------------");

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
                    System.out.println("Remain value: " + remainValue); // Print actual value
                    System.out.println("TC7 Pass");
                    System.out.println("---------------------------------------------------------------------------");
                } else {
                    System.out.println("Remain Not loaded");
                    System.out.println("TC7 Fail");
                    System.out.println("---------------------------------------------------------------------------");
                }
            } else {
                System.out.println("Dropdown is empty");
                System.out.println("TC6 Fail");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("---------------------------------------------------------------------------");
        }
    }
    public static void uploadFile() {
        String fileName = "ESS Issues.xlsx"; // Ensure correct filename + extension
        String absolutePath = "D:\\Omar Afifi\\SelfService\\" + fileName;

        // 1. Verify file exists
        if (!new File(absolutePath).exists()) {
            throw new RuntimeException("File not found: " + absolutePath);
        }

        // 2. Locate the file input element
        WebElement fileInput = driver1.findElement(By.xpath("//*[@formcontrolname='attachedFile']"));

        // 3. Upload the file
        fileInput.sendKeys(absolutePath);

        // 4. Verify upload success by checking if input is now "ng-dirty"
        WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.attributeContains(
                    By.xpath("//*[@formcontrolname='attachedFile']"),
                    "class",
                   // "ng-pristine"
                    "ng-dirty"  // Checks if "ng-dirty" exists in class attribute
            ));
            System.out.println("✅ File uploaded: " + fileName);
            System.out.println("TC7 Pass");
            System.out.println("---------------------------------------------------------------------------");
        } catch (TimeoutException e) {
            System.err.println("❌ Upload failed");
            System.out.println("TC7 Fail");
            System.out.println("---------------------------------------------------------------------------");
            throw e;
        }
    }

    public static void FromBalance() {
        WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(20));
        WebElement FromBalance = driver1.findElement(By.xpath("\"/html/body/div[1]/app-layout/div/div/div/div/div/app-content/div/app-leave-request/div/div/div[2]/div/form/div[2]/div[2]/div/mat-form-field/div/div[1]/div[3]"));
        boolean isDisabled = FromBalance.getAttribute("disabled") != null;

        try { List<WebElement> fromBalanceField = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.xpath("/html/body/div[1]/app-layout/div/div/div/div/div/app-content/div/app-leave-request/div/div/div[2]/div/form/div[2]/div[2]/div/mat-form-field/div/div[1]/div[3]")));

            if (!fromBalanceField.isEmpty()) {
                System.out.println("Dropdown contains data:");

                for (WebElement option : fromBalanceField) {
                    System.out.println(option.getText());
                }}

                    System.out.println("TC8 Pass");
            System.out.println("---------------------------------------------------------------------------");

        } catch (TimeoutException e) {
            System.out.println("❌ Timeout: Element not found or condition not met.");
            System.out.println("TC8 Fail");
            System.out.println("---------------------------------------------------------------------------");
            e.printStackTrace();
        }
        if (isDisabled) {
            System.out.println("Dropdown is disabled");
            System.out.println("TC9 Pass");
            System.out.println("---------------------------------------------------------------------------");
        } else {
            System.out.println("Dropdown is enabled");
            System.out.println("TC9 Fail");
            System.out.println("---------------------------------------------------------------------------");
        }
    }
    public static void Cancel(){

    }
}




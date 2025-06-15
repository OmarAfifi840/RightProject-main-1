package LeaveRequest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigReader;

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
        WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(240));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"kt_app_header_wrapper\"]/app-navbar/div[5]/div")));


        Actions actions = new Actions(driver1);
        WebElement newRequest = driver1.findElement(By.xpath("//*[@id=\"kt_app_header_menu\"]/div[2]/span/span[1]"));
        actions.moveToElement(newRequest).perform();

        WebElement timeManagement = driver1.findElement(By.xpath("//*[@id=\"kt_app_header_menu\"]/div[2]/div/div[3]/span/span[2]"));
        actions.moveToElement(timeManagement).perform();

        WebElement leaves = driver1.findElement(By.xpath("//*[@id=\"kt_app_header_menu\"]/div[2]/div/div[3]/div/div[1]/a/span[2]"));
        actions.moveToElement(leaves).click().perform();
        Thread.sleep(50000);
    }

    public static void EmployeeCode() throws InterruptedException {
        WebElement EmpCode = driver1.findElement(By.xpath("/html/body/div[1]/app-layout/div/div/div/div/div/app-content/div/app-leave-request/div/div/div[2]/div/form/div[1]/div[1]/div/app-ss-employee-select/div/mat-form-field/div/div[1]/div[3]/mat-select/div/div[1]/span/span"));
        String Field = EmpCode.getText();  // changed from getAttribute("value")
        Thread.sleep(50000);

        if (Field != null && !Field.isEmpty()) {
            System.out.println("Field is loaded with data: " + Field);
            System.out.println("TC1 Pass");
        } else {
            System.out.println("Field is empty");
            System.out.println("TC1 Fail");
        }
        // Add in case User is a manager
    }

    public static void Department() throws InterruptedException {
        WebElement Dep = driver1.findElement(By.id("mat-input-0"));
        String Field = Dep.getText();
        Thread.sleep(50000);
        if (Field != null && !Field.isEmpty()){
            System.out.println("Field is loaded with data: " + Dep);
            System.out.println("TC3 Pass");
        }
        else {
            System.out.println("Field is empty");
            System.out.println("TC3  Fail");
        }
        if (!Dep.isEnabled()){
            System.out.println("Department Is Unclickable");
            System.out.println("TC3 Pass");
        }
        else {
            System.out.println("Department Is Clickable");
            System.out.println("TC3  Fail");
        }
    }
    public static void HiringDate() throws InterruptedException {
        WebElement HiringDate =driver1.findElement(By.id("mat-input-1"));
        String Field = HiringDate.getText();
        Thread.sleep(50000);
        if (Field != null && !Field.isEmpty()){
            System.out.println("Field is loaded with data: " + HiringDate);
            System.out.println("TC4 Pass");
        }
        else {
            System.out.println("Field is empty");
            System.out.println("TC4  Fail");
        }
        if (!HiringDate.isEnabled()){
            System.out.println("Hiring Date Is Unclickable");
            System.out.println("TC4 Pass");
        }
        else {
            System.out.println("Hiring Date Is Clickable");
            System.out.println("TC4  Fail");
        }

    }
    public static void CostCenter() throws InterruptedException {
        WebElement Costcenter =driver1.findElement(By.id("mat-input-2"));
        String Field = Costcenter.getText();
        Thread.sleep(50000);
        if (Field != null && !Field.isEmpty()){
            System.out.println("Field is loaded with data: " + Costcenter);
            System.out.println("TC5 Pass");
        }
        else {
            System.out.println("Field is empty");
            System.out.println("TC5  Fail");
        }
        if (!Costcenter.isEnabled()){
            System.out.println("Cost Center Is Unclickable");
            System.out.println("TC5 Pass");
        }
        else {
            System.out.println("Cost Center Is Clickable");
            System.out.println("TC5  Fail");
        }

    }
    public static void LeavesType() {
        try {
        WebDriverWait wait = new WebDriverWait(driver1,Duration.ofSeconds(15));
        WebElement Type = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@formcontrolname='vacCode']")));
        List<WebElement> options = Type.findElements(By.xpath("//*[@formcontrolname='vacCode']"));
        WebElement Remain = driver1.findElement(By.id("mat-input-5"));
        WebElement AnnualLeave = driver1.findElement(By.xpath("//*[@id=\"mat-option-16\"]/span"));
            Thread.sleep(50000);
        if (!options.isEmpty()) {
            System.out.println("Dropdown contains data");
            System.out.println("TC6 Pass");
            Type.click();
            AnnualLeave.click();
            Thread.sleep(50000);
            if (!Remain.equals(0)){
                System.out.println("Remain loaded");
                System.out.println("TC7 Pass");
            } else {
                System.out.println("Remain Not loaded");
                System.out.println("TC7 Fail");
            }
        } else {
            System.out.println("Dropdown is empty");
            System.out.println("TC6  Fail");
        }
        } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
        }
    }
}



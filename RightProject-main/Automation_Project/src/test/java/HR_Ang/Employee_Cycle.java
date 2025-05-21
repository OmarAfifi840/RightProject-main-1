package HR_Ang;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import utilities.ConfigReader;

import java.time.Duration;

public class Employee_Cycle {
    public static WebDriver driver;
    public static Actions actions;

    public static void startBrowser() {
        String browser = ConfigReader.get("browser");
        String url = ConfigReader.get("url");
        long implicitWait = Long.parseLong(ConfigReader.get("implicitWait"));

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            throw new RuntimeException("Browser not supported");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.manage().window().maximize();
        driver.get(url);
        actions = new Actions(driver);
    }
@Test
    public void PerformCycle1() throws InterruptedException {
        startBrowser();
        loginHR();
        Create_New_Employee();

}
    private void loginHR() throws InterruptedException {
        String userName = ConfigReader.get("USERNAME");
        String password = ConfigReader.get("PASSWORD");

        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#kt_login_signin_form > div.fv-row.mb-8 > input")).sendKeys(userName);
        driver.findElement(By.cssSelector("#kt_login_signin_form > div:nth-child(3) > input")).sendKeys(password);
        driver.findElement(By.id("kt_sign_in_submit")).click();
    }
private void Create_New_Employee() throws InterruptedException {
    String UserName = ConfigReader.get("USERNAME");
Thread.sleep(15000);
    driver.findElement(By.xpath("//*[@id=\"#kt_app_sidebar_menu\"]/div[1]/span/span[2]")).click();
    WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
    WebElement Employee = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"#kt_app_sidebar_menu\"]/div[1]/div/div[2]/a/span[2]")));
    Employee.click();
    WebElement Check = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mat-tab-label-2-0\"]/div")));
    driver.findElement(By.name("FirstEngName")).sendKeys("Omar");
    driver.findElement(By.name("SecondEngName")).sendKeys("Hussien");
    driver.findElement(By.name("LastEngName")).sendKeys("Afifi");
    driver.findElement(By.name("ArFirstName")).sendKeys("عمر");
    driver.findElement(By.name("ArSecondName")).sendKeys("حسين");
    driver.findElement(By.name("ArLastName")).sendKeys("عفيفي");
    driver.findElement(By.name("ArLastName")).sendKeys("Address....");
    driver.findElement(By.xpath("//*[@formcontrolname='stateID']")).click();


    // Apply for all Navigators



//    driver.findElement(By.id("mat-input-9")).sendKeys("Address....");
//    driver.findElement(By.xpath("//*[@id=\"mat-select-76\"]/div/div[2]")).click();
//    driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div/mat-option[1]/span]")).click();
//    driver.findElement(By.xpath("//*[@id=\"mat-select-6\"]/div/div[2]/div")).click();
//    driver.findElement(By.xpath("//*[@id=\"mat-option-36\"]/span")).click();
//    driver.findElement(By.id("IDNo")).sendKeys("30006248800499");
//   // WebElement CheckBirthDate = wait.until(ExpectedConditions.attributeContains())
//   // WebElement CheckBirthPlace = wait.until(ExpectedConditions.attributeContains())
//   // WebElement CheckGender = wait.until(ExpectedConditions.attributeContains())
//    driver.findElement(By.name("FirstEngName"));
//    driver.findElement(By.xpath("//*[@id=\"mat-select-10\"]/div/div[2]/div")).click();
//    driver.findElement(By.xpath("//*[@id=\"mat-option-38\"]/span")).click();
//    driver.findElement(By.xpath("//*[@id=\"mat-select-12\"]/div/div[2]/div")).click();
//    driver.findElement(By.xpath("//*[@id=\"mat-option-40\"]/span")).click();
//    driver.findElement(By.xpath("//*[@id=\"mat-select-14\"]/div/div[2]/div")).click();
//    driver.findElement(By.xpath("//*[@id=\"mat-option-44\"]/span")).click();
//    driver.findElement(By.xpath("//*[@id=\"mat-select-16\"]/div/div[2]")).click();
//    driver.findElement(By.xpath("//*[@id=\"mat-option-51\"]/span")).click();
//    driver.findElement(By.id("mat-input-13")).sendKeys("01146225274");
//    driver.findElement(By.id("mat-input-14")).sendKeys("01146225274");
//    driver.findElement(By.id("mat-input-15")).sendKeys("omarafifi107@gmail.com");
//    driver.findElement(By.xpath("//*[@id=\"mat-select-20\"]/div/div[2]")).click();
//    driver.findElement(By.xpath("//*[@id=\"mat-option-1\"]/span")).click();
    }


}

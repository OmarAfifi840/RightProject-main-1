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

    static By UserName = By.xpath("//*[@formcontrolname='email']");
    static By Password = By.xpath("//*[@formcontrolname='password']");
    static By LoginButton = By.id("kt_sign_in_submit");

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

    public void loginHR() throws InterruptedException {
        String userName = ConfigReader.get("USERNAME");
        String password = ConfigReader.get("PASSWORD");
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
    public void Create_New_Employee() throws InterruptedException {
    String UserName = ConfigReader.get("USERNAME");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));


    WebElement Basic = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//span//span[@class = 'menu-title'and normalize-space()='Basic']")));
    driver.findElement(By.xpath("//div//span//span[@class = 'menu-title'and normalize-space()='Basic']")).click();
    Basic.click();
    WebElement Employee = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//a//span[@class = 'menu-title'and normalize-space()='Employee']")));
    Employee.click();

    driver.findElement(By.name("FirstEngName")).sendKeys("Omar");
    driver.findElement(By.name("SecondEngName")).sendKeys("Hussien");
    driver.findElement(By.name("LastEngName")).sendKeys("Afifi");
    driver.findElement(By.name("ArFirstName")).sendKeys("عمر");
    driver.findElement(By.name("ArSecondName")).sendKeys("حسين");
    driver.findElement(By.name("ArLastName")).sendKeys("عفيفي");
    driver.findElement(By.xpath("//*[@formcontrolname='stateID']")).click();
    driver.findElement(By.xpath("//mat-option//span[@class='mat-option-text' and normalize-space()='Cairo']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='cityID']")).click();
    driver.findElement(By.xpath("//mat-option//span[@class='mat-option-text' and normalize-space()='15 May']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='townID']")).click();
    driver.findElement(By.xpath("//mat-option//span[@class='mat-option-text' and normalize-space()='TEST']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='nationalityCode']")).click();
    driver.findElement(By.xpath("//mat-option//span[@class='mat-option-text' and normalize-space()='Egyptian']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='employeeNationalID']")).sendKeys("30006248800499");
    driver.findElement(By.xpath("//*[@formcontrolname='religionCode']")).click();
    driver.findElement(By.xpath("//mat-option//span[@class='mat-option-text' and normalize-space()='مسلم']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='maritalCode']")).click();
    driver.findElement(By.xpath("//mat-option//span[@class='mat-option-text' and normalize-space()='single']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='militaryCode']")).click();
    driver.findElement(By.xpath("//mat-option//span[@class='mat-option-text' and normalize-space()='اعفاء نهائى']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='registerCode']")).click();
    driver.findElement(By.xpath("//mat-option//span[@class='mat-option-text' and normalize-space()='Permanent']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='empGrpCode']")).click();
    driver.findElement(By.xpath("//mat-option//span[@class='mat-option-text' and normalize-space()='Test']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='empTel']")).sendKeys("01146225274");
    driver.findElement(By.xpath("//*[@formcontrolname='empMob']")).sendKeys("01146225274");
    driver.findElement(By.xpath("//*[@formcontrolname='mailType']")).click();
    driver.findElement(By.xpath("//mat-option//span[@class='mat-option-text' and normalize-space()='SelfMail']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='empEmail']")).sendKeys("Omarafifi107@gmail.com");
    driver.findElement(By.xpath("//*[@formcontrolname='nameEmerg']")).sendKeys("Omar");
    driver.findElement(By.xpath("//*[@formcontrolname='phNo_EmgMob']")).sendKeys("Test");
    driver.findElement(By.xpath("//*[@formcontrolname='emailEmerg']")).sendKeys("Test");



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

package HR_Ang;

import org.openqa.selenium.*;
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

    public void loginHR(){
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
    public void Create_New_Employee() throws InterruptedException {
    String UserName = ConfigReader.get("userName");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));


    WebElement Basic = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//span//span[@class = 'menu-title'and normalize-space()='Basic']")));
    driver.findElement(By.xpath("//div//span//span[@class = 'menu-title'and normalize-space()='Basic']")).click();
    Basic.click();
    WebElement Employee = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//a//span[@class = 'menu-title'and normalize-space()='Employee']")));
    Employee.click();

        driver.findElement(By.name("empCode")).sendKeys("252");
        driver.findElement(By.name("FirstEngName")).sendKeys("Omar");
        Thread.sleep(1000);
    driver.findElement(By.name("SecondEngName")).sendKeys("Hussien");
        Thread.sleep(1000);
    driver.findElement(By.name("LastEngName")).sendKeys("Afifi");
        Thread.sleep(1000);
    driver.findElement(By.name("ArFirstName")).sendKeys("عمر");
        Thread.sleep(1000);
    driver.findElement(By.name("ArSecondName")).sendKeys("حسين");
        Thread.sleep(1000);
    driver.findElement(By.name("ArLastName")).sendKeys("عفيفي");
        Thread.sleep(1000);

        driver.findElement(By.name("FirstEngName")).sendKeys("Omar");
        Thread.sleep(1000);
        driver.findElement(By.name("SecondEngName")).sendKeys("Hussien");
        Thread.sleep(1000);

    driver.findElement(By.xpath("//*[@formcontrolname='stateID']")).click();
    driver.findElement(By.xpath("//mat-option//span[@class='mat-option-text' and normalize-space()='Cairo']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='cityID']")).click();
    driver.findElement(By.xpath("//mat-option//span[@class='mat-option-text' and normalize-space()='15 May']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='townID']")).click();
    driver.findElement(By.xpath("//mat-option//span[@class='mat-option-text' and normalize-space()='Test']")).click();
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
    actions.sendKeys(Keys.PAGE_UP).perform();
    WebElement hrData = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='tab' and .//text()[normalize-space()='HR Data']]")));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", hrData);
    Thread.sleep(500); // optional short delay
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", hrData);
    driver.findElement(By.xpath("//mat-expansion-panel-header[@id='mat-expansion-panel-header-0']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='positionSerial']")).click();
    driver.findElement(By.xpath("//mat-option//span[@class='mat-option-text' and normalize-space()='بائع']")).click();
    driver.findElement(By.xpath("//*[@id=\"cdk-accordion-child-0\"]/div/div[1]/div[2]/mat-form-field/div/div[1]/div[4]/button/span[1]")).click();
    driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-unit-dialouge/mat-dialog-content/div/mat-tree/mat-nested-tree-node/div[1]/button/span[1]/mat-icon")).click();
    driver.findElement(By.xpath("//*[@id=\"mat-checkbox-17\"]/label")).click();
    driver.findElement(By.xpath("//mat-dialog-actions//button")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='compCode']")).click();
    driver.findElement(By.xpath("//span[@class = 'mat-option-text' and normalize-space()='El Othaim']")).click();
    Thread.sleep(500); // optional short delay
    driver.findElement(By.xpath("//*[@formcontrolname='reportTo']")).click();
    driver.findElement(By.xpath("//span[@class = 'mat-option-text' and normalize-space()='200103']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='locationID']")).click();
    driver.findElement(By.xpath("//span[@class = 'mat-option-text' and normalize-space()='test']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='appraiselpositionserial']")).click();
    driver.findElement(By.xpath("//span[@class = 'mat-option-text' and normalize-space()='مدير فرع']")).click();
    driver.findElement(By.xpath("//mat-expansion-panel-header[@id='mat-expansion-panel-header-1']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='empJobStDat']")).sendKeys("10/31/2025");
    driver.findElement(By.xpath("//*[@formcontrolname='notes']")).sendKeys("Test");
    driver.findElement(By.xpath("//*[@formcontrolname='saluteCode']")).click();
    driver.findElement(By.xpath("//span[@class = 'mat-option-text' and normalize-space()='Mr']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='docGrpCode']")).click();
    driver.findElement(By.xpath("//span[@class = 'mat-option-text' and normalize-space()='مجموعة السائقين']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='perAprCode']")).click();
    driver.findElement(By.xpath("//span[@class = 'mat-option-text' and normalize-space()='MEG Staff']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='areaCode']")).click();
    driver.findElement(By.xpath("//span[@class = 'mat-option-text' and normalize-space()='ddd']")).click();
    driver.findElement(By.xpath("//mat-expansion-panel-header[@id='mat-expansion-panel-header-2']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='paymentCode']")).click();
    driver.findElement(By.xpath("//span[@class = 'mat-option-text' and normalize-space()='Cash']")).click();
    actions.sendKeys(Keys.PAGE_UP).perform();
    WebElement IntegrationOptions  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='tab' and .//text()[normalize-space()='Integration Options']]")));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", IntegrationOptions);
    Thread.sleep(500); // optional short delay
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", IntegrationOptions);
    driver.findElement(By.xpath("//mat-expansion-panel-header[@id='mat-expansion-panel-header-3']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='empCodeTimeAtt']")).sendKeys("1444");
    driver.findElement(By.id("mat-radio-3")).click();
    //driver.findElement(By.xpath("//input[@tabindex='0' and @value='1']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='weekEndCode']")).click();
    driver.findElement(By.xpath("//span[@class = 'mat-option-text' and normalize-space()='Friday & Saturday']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='weekendMaxDays']")).clear();
    driver.findElement(By.xpath("//*[@formcontrolname='weekendMaxDays']")).sendKeys("5");
    driver.findElement(By.xpath("//*[@formcontrolname='isWFH']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='allowRequestMission']")).click();
    driver.findElement(By.xpath("//mat-expansion-panel-header[@id='mat-expansion-panel-header-5']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='paymentPeriodCode']")).click();
    driver.findElement(By.xpath("//span[@class = 'mat-option-text' and normalize-space()='Default']")).click();
    driver.findElement(By.xpath("//mat-expansion-panel-header[@id='mat-expansion-panel-header-7']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='empLvlSecurityGrpID']")).click();
    driver.findElement(By.xpath("//span[@class = 'mat-option-text' and normalize-space()='test']")).click();
    actions.sendKeys(Keys.PAGE_UP).perform();
    WebElement Users  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='tab' and .//text()[normalize-space()='supervisor_account']]")));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Users);
    Thread.sleep(500); // optional short delay
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", Users);
    driver.findElement(By.xpath("//mat-expansion-panel-header[@id='mat-expansion-panel-header-9']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='userID']")).sendKeys("252");
    driver.findElement(By.xpath("//*[@formcontrolname='userName']")).clear();
    driver.findElement(By.xpath("//*[@formcontrolname='userName']")).sendKeys("252");
    driver.findElement(By.xpath("//*[@formcontrolname='password']")).clear();
    driver.findElement(By.xpath("//*[@formcontrolname='password']")).sendKeys("1234");
    driver.findElement(By.xpath("//input[@name='confirmPassword']")).sendKeys("1234");
    driver.findElement(By.xpath("//*[@formcontrolname='selfService']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='enabled']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='groupCode']")).click();
    driver.findElement(By.xpath("//span[@class = 'mat-option-text' and normalize-space()='ESS']")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(500);
        actions.sendKeys(Keys.PAGE_UP).perform();
    WebElement Multibanks  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='tab' and .//text()[normalize-space()='Multi Banks']]")));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Multibanks);
    Thread.sleep(500); // optional short delay
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", Multibanks);
    driver.findElement(By.xpath("//button[normalize-space()='Add New Record']")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@formcontrolname='bankCode']")).click();
    driver.findElement(By.xpath("//span[@class = 'mat-option-text' and normalize-space()='ALEXBANK']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='idBankNO']")).sendKeys("12345678912");
    driver.findElement(By.xpath("//*[@formcontrolname='empAcNo']")).sendKeys("98765432198");
    driver.findElement(By.xpath("//*[@formcontrolname='branchCode']")).click();
    driver.findElement(By.xpath("//span[@class = 'mat-option-text' and normalize-space()='Test']")).click();
    driver.findElement(By.xpath("//*[@formcontrolname='per_Val']")).clear();
    driver.findElement(By.xpath("//*[@formcontrolname='per_Val']")).sendKeys("50000");
    driver.findElement(By.xpath("//*[@formcontrolname='ipan']")).sendKeys("1234");
    driver.findElement(By.xpath("//*[@formcontrolname='currencyCode']")).click();
    driver.findElement(By.xpath("//span[@class = 'mat-option-text' and normalize-space()='جنيه مصري']")).click();
    driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/mat-dialog-container/app-multi-bank-dialog/div/div/div[3]/button[2]/span")).click();

    WebElement PertsonalData  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='tab' and .//text()[normalize-space()='Personal Data']]")));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", PertsonalData);
    Thread.sleep(500); // optional short delay
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", PertsonalData);
    Thread.sleep(1000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();









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

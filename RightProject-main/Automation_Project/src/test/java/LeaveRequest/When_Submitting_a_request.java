package LeaveRequest;

import Parallel.Employees_V3.AllActionsEmployee1_V3;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigReader;

import java.time.Duration;
import java.util.List;

public class When_Submitting_a_request {

    private static final Logger logger = LogManager.getLogger(AllActionsEmployee1_V3.class);
    public static WebDriver driver1;
    public static Actions actions;
    private static void Infologger (String Message) {
        //Infologger(Message);
        logger.info(Message);  //Changed from Error To Info
    }
    public static void LeaveType(){
        driver1.findElement(By.xpath("//*[@formcontrolname='vacCode']")).click();
        driver1.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div/mat-option[1]/span")).click();
    }

    public static void Dates(){
        driver1.findElement(By.xpath("//*[@formcontrolname='dateFrom']")).sendKeys(ConfigReader.get("FromDateEmployeeLeave"));
        driver1.findElement(By.xpath("//*[@formcontrolname='dateFrom']")).clear();
        driver1.findElement(By.xpath("//*[@formcontrolname='dateFrom']")).sendKeys(ConfigReader.get("FromDateEmployeeLeave"));
        driver1.findElement(By.xpath("//*[@formcontrolname='dateTo']")).sendKeys(ConfigReader.get("ToDateEmployeeLeave"));
        driver1.findElement(By.xpath("//*[@formcontrolname='dateTo']")).clear();;
        driver1.findElement(By.xpath("//*[@formcontrolname='dateTo']")).sendKeys(ConfigReader.get("ToDateEmployeeLeave"));

    }
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
//                Infologger("TC6 Pass");
//                Infologger("---------------------------------------------------------------------------");
                WebElement annualLeave = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//mat-option//span[contains(text(),'Annual Leave')]")
                ));
                annualLeave.click();
                WebElement remainField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.id("mat-input-5")
                ));
                Thread.sleep(10000);
                String remainValue = remainField.getAttribute("value");

                WebElement NoOfDays = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[@formcontrolname='totalDays']")
                ));

                wait.until(d -> !NoOfDays.getText().isEmpty());
                String NumOfDays = NoOfDays.getAttribute("//*[@formcontrolname='totalDays']");

                if (!remainValue.equals(0)) {
//                    Infologger("Remain value: " + remainValue); // Print actual value
//                    Infologger("TC7 Pass");
//                    Infologger("---------------------------------------------------------------------------");
                } else {
//                    Infologger("Remain Not loaded");
//                    Infologger("TC7 Fail");
//                    Infologger("---------------------------------------------------------------------------");
                }
            } else {
                //Infologger("Dropdown is empty");
                //Infologger("TC6 Fail");
            }
        } catch (Exception e) {
            //Infologger("Error: " + e.getMessage());
            //Infologger("---------------------------------------------------------------------------");
        }
    }

}

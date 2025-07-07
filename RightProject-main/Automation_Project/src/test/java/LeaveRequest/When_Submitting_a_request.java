//package LeaveRequest;
//
//import Parallel.Employees_V3.AllActionsEmployee1_V3;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.openqa.selenium.By;
//import org.openqa.selenium.TimeoutException;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import utilities.ConfigReader;
//
//import java.time.Duration;
//import java.util.List;
//
//public class When_Submitting_a_request {
//
//    private static final Logger logger = LogManager.getLogger(When_Submitting_a_request.class);
//    public static WebDriver driver2;
//    public static Actions actions;
//
//    private static void swalerrorMessage(String requestName) {
//        String Employee1 = ConfigReader.get("userName1");
//        WebDriverWait wait = new WebDriverWait(driver2, Duration.ofSeconds(30));
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("swal2-loading")));
//        WebElement swal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-html-container")));
//
//        logger.error("Request: " + requestName + " | Swal Message: " + swal.getText() + " / UserName: " + Employee1);
//        clickSwalOkIfExists();
//    }
//    public static void clickSwalOkIfExists() {
//        //  String Employee1 = ConfigReader.get("userName1");
//
//        try {
//            SendPath("swal2-actions", "OK button clicked.");
//        } catch (TimeoutException e1) {}
////            Infologger("First OK button not found, trying second XPath... / UserName :" + Employee1);}
//    }
//    private static  void SendPath(String Path,String Message){
//        WebDriverWait wait = new WebDriverWait(driver2, Duration.ofSeconds(3));
//        WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(Path)));
//        okButton.click();
//        //System.out.println(Message);
//        logger.info(Message);
//    }
//
//    private static void Infologger(String Message) {
//        //Infologger(Message);
//        logger.info(Message);  //Changed from Error To Info
//    }
//
//    public static void LeaveType() {
//        driver2.findElement(By.xpath("//*[@formcontrolname='vacCode']")).click();
//        driver2.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div/mat-option[1]/span")).click();
//    }
//
//    public static void Dates() {
//        driver2.findElement(By.xpath("//*[@formcontrolname='dateFrom']")).sendKeys(ConfigReader.get("FromDateEmployeeLeave"));
//        driver2.findElement(By.xpath("//*[@formcontrolname='dateFrom']")).clear();
//        driver2.findElement(By.xpath("//*[@formcontrolname='dateFrom']")).sendKeys(ConfigReader.get("FromDateEmployeeLeave"));
//        driver2.findElement(By.xpath("//*[@formcontrolname='dateTo']")).sendKeys(ConfigReader.get("ToDateEmployeeLeave"));
//        driver2.findElement(By.xpath("//*[@formcontrolname='dateTo']")).clear();
//        driver2.findElement(By.xpath("//*[@formcontrolname='dateTo']")).sendKeys(ConfigReader.get("ToDateEmployeeLeave"));
//    }
//
//    public static void LeavesType() {
//        try {
//            WebDriverWait wait = new WebDriverWait(driver2, Duration.ofSeconds(15));
//
//            WebElement typeDropdown = wait.until(ExpectedConditions.elementToBeClickable(
//                    By.xpath("//*[@formcontrolname='vacCode']")
//            ));
//            typeDropdown.click();
//            List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
//                    By.xpath("//mat-option//span[@class='mat-option-text']")
//            ));
//            if (!options.isEmpty()) {
//                Infologger("Dropdown contains data:");
//                for (WebElement option : options) {
//                    Infologger(option.getText());
//                }
//                WebElement annualLeave = wait.until(ExpectedConditions.elementToBeClickable(
//                        By.xpath("//mat-option//span[contains(text(),'Annual Leave')]")
//                ));
//                annualLeave.click();
//
//                WebElement remainField = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                        By.id("mat-input-5")
//                ));
//                String remainValue = remainField.getAttribute("value");
//
//                WebElement NoOfDays = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                        By.id("mat-input-6")
//                ));
//                String NumOfDays = NoOfDays.getAttribute("value");
//
//                try {
//                    // Parse the strings to numbers (using double for decimal numbers, use Integer.parseInt for whole numbers)
//                    double remainNum = Double.parseDouble(remainValue.trim());
//                    double daysNum = Double.parseDouble(NumOfDays.trim());
//
//                    if (remainNum < daysNum) {
//                        System.out.println("Remain is less than Number of days");
//                        WebElement Send = driver2.findElement(By.xpath("//*[@id=\"kt_app_content_container\"]/app-leave-request/div/div/div[3]/button/span[1]"));
//                        Send.click();
//                        Thread.sleep(10000);
//                        swalerrorMessage("Leave");
//                    }
//                } catch (NumberFormatException e) {
//                    System.out.println("Error parsing numbers: " + e.getMessage());
//                    }
//                }
//            } catch (Exception e) {
//        }
//    }
//}
//

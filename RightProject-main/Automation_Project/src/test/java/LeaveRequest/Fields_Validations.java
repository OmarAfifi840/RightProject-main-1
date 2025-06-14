package LeaveRequest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.time.Duration;

public class Fields_Validations {
    public static WebDriver driver1;

    public static void EmployeeCode() throws InterruptedException {
        WebElement EmpCode = driver1.findElement(By.xpath("/html/body/div[1]/app-layout/div/div/div/div/div/app-content/div/app-leave-request/div/div/div[2]/div/form/div[1]/div[1]/div/app-ss-employee-select/div/mat-form-field/div/div[1]/div[3]/mat-select/div/div[1]/span/span"));
        String Field = EmpCode.getAttribute("value");
        Thread.sleep(10000);
        if (!Field.isEmpty()){
            System.out.println("Field is loaded with data: " + EmpCode);
            System.out.println("TC1 Pass");
        }
        else {
            System.out.println("Field is empty");
            System.out.println("TC1  Fail");
        }
// Add in case User is a manager
    }

    public static void Department() throws InterruptedException {
        WebElement Dep = driver1.findElement(By.id("mat-input-0"));
        String Field = Dep.getText();
        Thread.sleep(10000);
        if (!Field.isEmpty()){
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
    public static void HiringDate() {
        WebElement HiringDate =driver1.findElement(By.id("mat-input-1"));
        String Field = HiringDate.getText();
        if (!Field.isEmpty()){
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
    public static void CostCenter() {
        WebElement Costcenter =driver1.findElement(By.id("mat-input-2"));
        String Field = Costcenter.getText();
        if (!Field.isEmpty()){
            System.out.println("Field is loaded with data: " + Costcenter);
            System.out.println("TC5 Pass");
        }
        else {
            System.out.println("Field is empty");
            System.out.println("TC5  Fail");
        }
        if (!Costcenter.isEnabled()){
            System.out.println("Hiring Date Is Unclickable");
            System.out.println("TC5 Pass");
        }
        else {
            System.out.println("Hiring Date Is Clickable");
            System.out.println("TC5  Fail");
        }

    }
    public static void LeavesType(String[] args) {
        try {
        WebDriverWait wait = new WebDriverWait(driver1,Duration.ofSeconds(15));
        WebElement Type = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@formcontrolname='vacCode']")));
        List<WebElement> options = Type.findElements(By.xpath("//*[@formcontrolname='vacCode']"));
        WebElement Remain = driver1.findElement(By.id("mat-input-14"));
        if (!options.isEmpty()) {
            System.out.println("Dropdown contains data");
            System.out.println("TC6 Pass");

            // Code for choosing the leave type to load the remain and add thread time out after choosing the leave type

            if (!Remain.equals(0)){
                System.out.println("Remain loaded");
                System.out.println("TC7 Pass");
            } else {
                System.out.println("Remain Not loaded");
                System.out.println("TC7 Fail");
            }
            // add checking remaining use this  (mat-input-14)
        } else {
            System.out.println("Dropdown is empty");
            System.out.println("TC6  Fail");
        }
        } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
        }
    }
}



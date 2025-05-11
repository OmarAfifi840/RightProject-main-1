package Three_Browsers_In_Order;

import One_Browser_In_Order.AllActions;
import org.testng.annotations.Test;

public class PermissionRequest {

    @Test

    public void Permission () throws InterruptedException {
        System.out.println("Test Case 3 (Perission Request) is running");
        AllActions2.startBrowser();
        AllActions2.performLogin();
        AllActions2.Permission();
        AllActions2.PermissionDetails();
    }
}

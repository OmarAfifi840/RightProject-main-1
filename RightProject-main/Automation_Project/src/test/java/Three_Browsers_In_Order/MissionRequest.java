package Three_Browsers_In_Order;

import One_Browser_In_Order.AllActions;
import org.testng.annotations.Test;

public class MissionRequest {


    @Test

    public void Mission() throws InterruptedException {
        System.out.println("Test Case 2 (Mission Request) is running");
        AllActions2.startBrowser();
        AllActions2.performLogin();
        AllActions2.Mission();
        AllActions2.MissionDetails();
    }
}

package Three_Browsers_In_Order;

import org.testng.annotations.Test;

public class LeavesRequest {

    @Test
    public static void Leave () throws InterruptedException {
        System.out.println("Test Case 1 (Leaves Request) is running");
        AllActions2.startBrowser();
        AllActions2.performLogin();
        AllActions2.Leave();
        AllActions2.LeavesDetails();
    }
}

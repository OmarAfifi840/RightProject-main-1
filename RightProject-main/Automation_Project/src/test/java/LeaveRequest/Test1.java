package LeaveRequest;

import Three_Browsers_In_Order.LeavesRequest;
import org.testng.annotations.Test;

public class Test1  {

    @Test
    public void Leave1 () throws InterruptedException {
        Fields_Validations.startBrowser();
        Fields_Validations.performLogin();
        Fields_Validations.submitLeaveRequest();
        Fields_Validations.EmployeeCode();
        Fields_Validations.Department();
        Fields_Validations.HiringDate();
//        //  Fields_Validations.CostCenter();
        Fields_Validations.LeavesType();
        Fields_Validations.uploadFile();
        Fields_Validations.FromBalance();
    }
}

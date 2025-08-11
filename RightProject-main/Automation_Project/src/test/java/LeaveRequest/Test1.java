package LeaveRequest;

import org.testng.annotations.Test;

public class Test1 {

    @Test
    public void Leave1() throws InterruptedException {
        Fields_Validations.startBrowser();
        Fields_Validations.performLogin();
        Fields_Validations.submitLeaveRequest();
        Fields_Validations.EmployeeCode();
        //Fields_Validations.Department();
        Fields_Validations.HiringDate();
        //  Fields_Validations.CostCenter();
        Fields_Validations.LeavesType();
        Fields_Validations.uploadFile();
        Fields_Validations.FromBalance();
        //Fields_Validations.Cancel();
        //-------------------------//
        //Case 1 in When Submitting a request (Happy Scenario)
        //LeavesRequest.Leave();
        //Case 2 in When Submitting a request
        //Fields_Validations.submitLeaveRequest();
        Fields_Validations.LeaveType();
        Fields_Validations.Dates();
        Fields_Validations.RemainNoOfDays();
        // Object transactionTest = Database.TransactionTest;
    }
}

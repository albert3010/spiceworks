package system_design_problems.splitwise;

import java.util.HashMap;
import java.util.List;

public class Helper {

    public static double getPerPersonBill(Bill bill){
        int usersInvolved = bill.usersInvolved.size();
        double totalPaidAmount = bill.totalPaidAmount;
        double perPerson = totalPaidAmount/usersInvolved;
        return perPerson;
    }
    public static HashMap<Integer, List<MoneyReceivers>> getAllPersonsBalance(double perPerson, Bill bill){
        int usersInvolved = bill.usersInvolved.size();
        double totalPaidAmount = bill.totalPaidAmount;

        return null;
    }
}

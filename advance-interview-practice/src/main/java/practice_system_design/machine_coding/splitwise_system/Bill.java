package practice_system_design.machine_coding.splitwise_system;

import java.util.List;

public class Bill {
    int id;
    List<UserToBill> userToBills;
    double amount;

    public Bill(List<UserToBill> userToBills, double amount) {
        this.id = Constants.getBillId();
        this.userToBills = userToBills;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public List<UserToBill> getUserToBills() {
        return userToBills;
    }
}

package practice_system_design.machine_coding.splitwise_system;

import java.util.List;

public class Bill {
    int id;
    List<UserToBill> userToBills;
    double amount;

    public Bill(int id, List<UserToBill> userToBills, double amount) {
        this.id = id;
        this.userToBills = userToBills;
        this.amount = amount;
    }
}

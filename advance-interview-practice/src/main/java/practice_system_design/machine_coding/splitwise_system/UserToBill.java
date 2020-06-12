package practice_system_design.machine_coding.splitwise_system;

public class UserToBill {
    int userId;
    double amountSpent;
    double amountPaid;

    public UserToBill(int userId, double amountSpent, double amountPaid) {
        this.userId = userId;
        this.amountSpent = amountSpent;
        this.amountPaid = amountPaid;
    }
}

package practice_system_design.machine_coding.splitwise_system;

public class GiveAndTake {
    int userId;
    double amount;

    public GiveAndTake(int userId, double amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public int getUserId() {
        return userId;
    }

    public double getAmount() {
        return amount;
    }
}

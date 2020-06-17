package system_design_problems.splitwise;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;

public class Bill {
    int id;
    int groupId;
    List<Integer> usersInvolved;
    HashMap<Integer, Double> usersPaid;
    double totalPaidAmount;

    public Bill(int id, int groupId, List<Integer> usersInvolved, HashMap<Integer, Double> usersPaid, double totalPaidAmount) {
        this.id = id;
        this.groupId = groupId;
        this.usersInvolved = usersInvolved;
        this.usersPaid = usersPaid;
        this.totalPaidAmount = totalPaidAmount;
    }

    public int getId() {
        return id;
    }
}

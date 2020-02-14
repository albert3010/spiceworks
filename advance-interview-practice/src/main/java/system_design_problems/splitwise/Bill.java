package system_design_problems.splitwise;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
public class Bill {
    int id;
    int groupId;
    List<Integer> usersInvolved;
    HashMap<Integer, Double> usersPaid;
    double totalPaidAmount;
}

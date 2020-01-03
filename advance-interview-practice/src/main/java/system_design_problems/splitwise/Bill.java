package system_design_problems.splitwise;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Bill {
    int id;
    int groupId;
    List<Integer> usersInvolved;
    int paidByUserId;
    int paidAmount;

}

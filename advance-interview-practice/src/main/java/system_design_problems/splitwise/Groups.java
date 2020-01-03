package system_design_problems.splitwise;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;

@Getter
public class Groups {
    private int id;
    private int groupId;
    private String name;
    private List<Integer> userIds;
    private HashMap<Long, List<BillPaid>> userBalanceMap;

    Groups(String name, List<Integer> userIds) {
        this.name = name;
        this.userIds = userIds;
        groupId = Constants.getGroupId();
        userBalanceMap = new HashMap<>();
    }

    public void addUserToGroup(int userId) {
        this.userIds.add(userId);
    }

    public String getGroupName() {
        return this.name;
    }
}

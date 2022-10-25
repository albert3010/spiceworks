package system_design_problems.splitwise;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
public class Groups {
    private int groupId;
    private String name;
    private List<Integer> userIds;
    private HashMap<Integer, List<MoneyReceivers>> usersToPay;

    Groups(String name, List<Integer> userIds) {
        this.name = name;
        this.userIds = userIds;
        groupId = Constants.getGroupId();
        usersToPay = new HashMap<>();
    }

    public void addUserToGroup(int userId) {
        this.userIds.add(userId);
    }

    public String getGroupName() {
        return this.name;
    }

    public void updateUsersToPay(HashMap<Integer, List<MoneyReceivers>> usersToPayObj) {
        for(Integer userId: usersToPayObj.keySet()){
            List<MoneyReceivers> oldReceivers = usersToPay.getOrDefault(userId, new ArrayList<>());
            List<MoneyReceivers> newReceivers = usersToPayObj.get(userId);
            oldReceivers.addAll(newReceivers);
        }
    }
}

package system_design_problems.splitwise;

import java.util.ArrayList;
import java.util.List;

public class Users {
    int id;
    String name;
    private List<Integer> groupIds;
    private double currentBalance;

    Users(String name) {
        this.id = Constants.getUserId();
        this.name = name;
        this.groupIds = new ArrayList<>();
        this.currentBalance = 0;
    }

    Users(String name, List<Integer> groupIds) {
        this.id = Constants.getUserId();
        this.name = name;
        this.groupIds = groupIds;
        this.currentBalance = 0;
    }

    public void addToGroupId(int groupId) {
        this.groupIds.add(groupId);
    }

    public void updateCurrentBalance(double amount) {
        this.currentBalance += amount;
    }
    public int getId(){
        return this.id;
    }
}

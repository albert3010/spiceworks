package practice_lld.top25.lld.splitwise.services;

import practice_lld.top25.lld.splitwise.entity.Expense;
import practice_lld.top25.lld.splitwise.entity.Group;
import practice_lld.top25.lld.splitwise.entity.User;

import java.util.*;


public class GroupService {
    private final Map<Integer, Group> groupMap;
    private Map<User, List<Group>> userGroups;

    public GroupService() {
        this.groupMap = new HashMap<>();
        this.userGroups = new HashMap<>();
    }

    public Group createGroup(String groupName, Set<User> users, String description) {
        Group group = new Group(groupName, users, description);
        groupMap.put(group.getGroupId(), group);
        return group;
    }

    public Group getGroup(int groupId) {
        return groupMap.get(groupId);
    }

    public void addUserToGroup(int groupId, User user) {
        userGroups.putIfAbsent(user, new ArrayList<>());

        groupMap.get(groupId).addUser(user);
        userGroups.get(user).add(groupMap.get(groupId));
    }

    public void addExpenses(int groupId, Expense expense) throws Exception {
        Group group = groupMap.get(groupId);
        if (group != null) {
            group.addExpense(expense);
            Map<User, Double> userSplits = expense.executeSplit();
            for (Map.Entry<User, Double> entry : userSplits.entrySet()) {
                double amount = entry.getValue();
                group.updateUserBalance(entry.getKey(), amount);
            }
        }
    }
}

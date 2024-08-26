package practice_lld.top25.lld.splitwise.entity;

import lombok.Getter;
import practice_lld.top25.lld.splitwise.Constants;

import java.util.*;

@Getter
public class Group {
    int groupId;
    String groupName;
    String description;
    Set<User> users;
    private List<Expense> expenses;
    private Map<Integer, Double> userNetBalance;
    private Map<Integer, Map<Integer, Double>> balanceSheet;

    public Group(String groupName, Set<User> users, String description) {
        this.groupId = Constants.getGroupId();
        this.groupName = groupName;
        this.users = users;
        this.description = description;
        this.expenses = new ArrayList<>();
        this.userNetBalance = new HashMap<>();
        this.balanceSheet = new HashMap<>();
    }

    public void addUser(User user){
        users.add(user);
        userNetBalance.putIfAbsent(user.getUserId(), 0.0);
    }

    public void addExpense(Expense expense){
        expenses.add(expense);
    }

    public void updateUserBalance(User user, double amount) throws Exception {
        if(!users.contains(user)){
            throw new Exception("User not in the group");
        }
        int userId = user.getUserId();
        userNetBalance.put(userId, userNetBalance.get(userId) + amount);
    }

    private void updateBalance(int payerId, int receiverId, double amount) {
        balanceSheet.putIfAbsent(payerId, new HashMap<>());
        balanceSheet.putIfAbsent(receiverId, new HashMap<>());

        balanceSheet.get(receiverId).put(payerId, balanceSheet.get(receiverId).getOrDefault(payerId, 0.0) + amount);
        balanceSheet.get(payerId).put(receiverId, balanceSheet.get(payerId).getOrDefault(receiverId, 0.0) - amount);
    }

    public void updateTransaction(Transaction transaction){
        int payerId = transaction.getPayer().getUserId();
        int receiverId = transaction.getReceiver().getUserId();
        double amount = transaction.getAmount();
        updateBalance(payerId, receiverId, amount);
    }

}

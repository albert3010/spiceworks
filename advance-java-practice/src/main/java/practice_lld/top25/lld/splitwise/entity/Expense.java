package practice_lld.top25.lld.splitwise.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import practice_lld.top25.lld.splitwise.split.Split;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
public class Expense {
    int expenseId;
    String expenseName;
    List<User> users;
    int groupId;
    Double totalAmount;
    Split splitStrategy;
    Map<User, Double> usersPaid;

    public void addPayment(User user, double amount){
        usersPaid.put(user, amount);
    }

    public  Map<User, Double> executeSplit() throws Exception {
        return splitStrategy.split(totalAmount, usersPaid);
    }
}

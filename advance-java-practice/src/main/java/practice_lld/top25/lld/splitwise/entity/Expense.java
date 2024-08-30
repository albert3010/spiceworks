package practice_lld.top25.lld.splitwise.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import practice_lld.top25.lld.splitwise.split.Split;
import practice_lld.top25.lld.splitwise.split.SplitType;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Value
public class Expense {
    int expenseId;
    int groupId;
    String expenseName;

    // ExpenseDetails
    List<User> users;
    Double totalAmount;
    SplitType splitType;
    Split splitStrategy;
    Map<User, Double> usersPaid;

    public void addPayment(User user, double amount){
        usersPaid.put(user, amount);
    }

    public  Map<User, Double> executeSplit() throws Exception {
        return splitStrategy.split(totalAmount, usersPaid);
    }
}

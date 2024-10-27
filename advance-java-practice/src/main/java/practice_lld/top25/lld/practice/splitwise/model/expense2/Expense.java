package practice_lld.top25.lld.practice.splitwise.model.expense2;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.joda.time.DateTime;

import java.util.Map;

@AllArgsConstructor
@Getter
public abstract class Expense {
    Integer expenseId;
    String description;
    Map<Integer, Double> usersPaid;
    Double totalAmount;
    DateTime dateTime;

    public Expense(Integer expenseId, String description, Map<Integer, Double> usersPaid, Double totalAmount) {
        this.expenseId = expenseId;
        this.description = description;
        this.usersPaid = usersPaid;
        this.totalAmount = totalAmount;
        this.dateTime = DateTime.now();
    }

    public abstract Map<Integer, Double> getOweAmount() throws Exception;

    public abstract boolean validate();

}

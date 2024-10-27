package practice_lld.top25.lld.practice.splitwise.model.expense;

import lombok.Getter;
import practice_lld.top25.lld.practice.splitwise.Constants.Constants;

@Getter
public class Expense {
    private Integer expenseId;
    private Integer paidBy;
    private String description;
    private Split split;

    public Expense(Integer paidBy, String description, Split split) {
        this.expenseId = Constants.getExpenseId();
        this.paidBy = paidBy;
        this.description = description;
        this.split = split;
    }
}

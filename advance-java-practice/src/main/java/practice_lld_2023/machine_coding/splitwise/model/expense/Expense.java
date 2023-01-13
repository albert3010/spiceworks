package practice_lld_2023.machine_coding.splitwise.model.expense;

import lombok.Getter;
import practice_lld_2023.machine_coding.splitwise.Constants.Constants;

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

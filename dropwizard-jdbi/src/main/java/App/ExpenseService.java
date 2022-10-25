package App;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class ExpenseService {

    public Expense createExpense(int id, Integer userId, List<Split> splits,
                                 Double amount, ExpenseType expenseType) {
        switch (expenseType) {
            case EQUAL:
                int people = splits.size();
                double equal = amount / people;
                for (Split split : splits) {
                    split.setAmount(equal);
                }
                return new EqualExpense(id, userId, splits, amount);
            case EXACT:
                return new ExactExpense(id, userId, splits, amount);

            case PERCENT:
                for (Split split : splits) {
                    PercentSplit percentSplit = (PercentSplit) (split);
                    split.setAmount((amount * percentSplit.getPercent()) / 100);
                }
                return new PercentExpense(id, userId, splits, amount);
            default:
                return null;
        }
    }
}

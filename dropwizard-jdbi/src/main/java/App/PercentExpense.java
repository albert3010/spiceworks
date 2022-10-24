package App;

import java.util.List;

public class ExactExpense extends Expense {


    public ExactExpense(int id, Integer userId, List<Split> splits, Double amount) {
        super(id, userId, splits, amount);
    }

    @Override
    public boolean validate() {
        List<Split> splits = this.getSplits();
        Double totalAmount = 0.0;
        for (Split split : splits) {
            if (!(split instanceof ExactSplit)) {
                return false;
            }
            totalAmount+=split.getAmount();
        }
        if(totalAmount!=this.getAmount()) return false;

        return true;
    }
}

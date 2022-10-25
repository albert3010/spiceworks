package App;

import java.util.List;

public class PercentExpense extends Expense {


    public PercentExpense(int id, Integer userId, List<Split> splits, Double amount) {
        super(id, userId, splits, amount);
    }

    @Override
    public boolean validate() {
        List<Split> splits = this.getSplits();
        Integer percent = 0;

        for (Split split : splits) {
            if (!(split instanceof PercentSplit)) {
                return false;
            }
            PercentSplit percentSplit = (PercentSplit) split;
            percent += percentSplit.getPercent();
        }
        if (percent != 100) return false;

        return true;
    }
}

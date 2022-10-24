package App;

import java.util.List;

public class EqualExpense extends Expense {


    public EqualExpense(int id, Integer userId, List<Split> splits, Double amount) {
        super(id, userId, splits, amount);
    }

    @Override
    public boolean validate() {
        List<Split> splits = this.getSplits();
        int total = splits.size();
        if (total == 0) return true;

        for (Split split : splits) {
            if (!(split instanceof EqualSplit)) {
                return false;
            }
        }
        for (int personI = 1; personI < total; personI++) {
            if (!(splits.get(personI) == splits.get(0))) {
                return false;
            }
        }
        return true;
    }
}

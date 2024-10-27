package practice_lld.top25.lld.practice.splitwise.model.expense2;

import practice_lld.top25.lld.practice.splitwise.Constants.SplitType;
import practice_lld.top25.lld.splitwise.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PercentExpense extends Expense {
    private Map<Integer, Double> percentageShare;

    public PercentExpense(Map<Integer, Double> percentageShare, Integer expenseId, String description,
                          Map<Integer, Double> usersPaid, Double totalAmount) {
        super(expenseId, description, usersPaid, totalAmount);
        this.percentageShare = percentageShare;
    }


    @Override
    public Map<Integer, Double> getOweAmount() throws Exception {
        if (validate()) {
            throw new Exception("Invalid percentages sum");
        }

        Map<Integer, Double> oweMap = new HashMap<>();

        for (Integer member : percentageShare.keySet()) {
            double expectedPaid = totalAmount * percentageShare.getOrDefault(member, 0.0) / 100.0;
            double actualPaid = usersPaid.getOrDefault(member, 0.0);
            double balance = actualPaid - expectedPaid;

            oweMap.put(member, balance);
        }
        return oweMap;
    }

    @Override
    public boolean validate() {
        Double total = 0.0;
        for (Double val : percentageShare.values()) {
            total += val;
        }
        return total == 100;
    }
}

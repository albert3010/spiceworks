package practice_lld.top25.lld.practice.splitwise.model.expense2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EqualExpense extends Expense {
    private List<Integer> usersParticipated;
    public EqualExpense(List<Integer> users, Integer expenseId, String description,
                        Map<Integer, Double> usersPaid, Double totalAmount) {

        super(expenseId, description, usersPaid, totalAmount);
        this.usersParticipated = users;
    }

    @Override
    public Map<Integer, Double> getOweAmount() throws Exception {
        if (Boolean.FALSE == validate()) {
            throw new Exception("Validation failed");
        }
        Map<Integer, Double> oweMap = new HashMap<>();
        Double expectedPaid = getTotalAmount() / usersParticipated.size();
        for (Integer userId : usersParticipated) {

            double actualPaid = usersPaid.getOrDefault(userId, 0.0); // 100
            double balance = actualPaid - expectedPaid; // 100-150 = -50
            oweMap.put(userId, balance);
        }
        return oweMap;
    }

    @Override
    public boolean validate() {
        return true;
    }
}

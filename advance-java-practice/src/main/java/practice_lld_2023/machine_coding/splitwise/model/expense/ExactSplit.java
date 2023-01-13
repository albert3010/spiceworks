package practice_lld_2023.machine_coding.splitwise.model.expense;

import practice_lld_2023.machine_coding.splitwise.Constants.SplitType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExactSplit extends Split {
    private List<Double> shares;

    public ExactSplit(List<Integer> users,
                      Double totalAmount, List<Double> shares) {
        super(SplitType.EXACT, users, totalAmount);
        this.shares = shares;
    }

    @Override
    public Map<Integer, Double> getOweAmount() throws Exception {
        if (Boolean.FALSE == validate()) {
            throw new Exception("Validation failed");
        }
        List<Integer> users = getUsers();
        Map<Integer, Double> oweMap = new HashMap<>();
        int index = 0;
        for (Integer userId : users) {
            oweMap.put(userId, shares.get(index++));
        }
        return oweMap;
    }

    @Override
    public boolean validate() {
        Double total = 0.0;
        for (Double val : shares) {
            total += val;
        }

        return total == getTotalAmount();
    }
}

package practice_lld_2023.machine_coding.splitwise.model.expense;

import practice_lld_2023.machine_coding.splitwise.Constants.SplitType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PercentSplit extends Split {
    private List<Double> shares;

    public PercentSplit(List<Integer> users,
                        Double totalAmount, List<Double> shares) {
        super(SplitType.PERCENT, users, totalAmount);
        this.shares = shares;
    }

    @Override
    public Map<Integer, Double> getOweAmount() throws Exception {
        if (Boolean.FALSE == validate()) {
            throw new Exception("Validation failed");
        }
        List<Integer> users = getUsers();
        Map<Integer, Double> oweMap = new HashMap<>();
        Double totalAmount = getTotalAmount();
        int index = 0;
        for (Integer userId : users) {
            oweMap.put(userId, (totalAmount * shares.get(index++)) / 100.0);
        }
        return oweMap;
    }

    @Override
    public boolean validate() {
        Double total = 0.0;
        for (Double val : shares) {
            total += val;
        }

        return total == 100;
    }
}

package practice_lld_2023.machine_coding.splitwise.model.expense;

import practice_lld_2023.machine_coding.splitwise.Constants.SplitType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EqualSplit extends Split {

    public EqualSplit(List<Integer> users,
                      Double totalAmount) {
        super(SplitType.EQUAL, users, totalAmount);
    }

    @Override
    public Map<Integer, Double> getOweAmount() throws Exception {
        if (Boolean.FALSE == validate()) {
            throw new Exception("Validation failed");
        }
        List<Integer> users = getUsers();
        Map<Integer, Double> oweMap = new HashMap<>();
        Double equalAmount = getTotalAmount() / users.size();
        for (Integer userId : users) {
            oweMap.put(userId, equalAmount);
        }
        return oweMap;
    }

    @Override
    public boolean validate() {
        return true;
    }
}

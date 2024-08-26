package practice_lld.top25.lld.splitwise.split;

import practice_lld.top25.lld.splitwise.entity.User;

import java.util.HashMap;
import java.util.Map;

public class PercentageSplit implements Split{
    Map<User, Double> percentages;

    @Override
    public Map<User, Double> split(Double amount, Map<User, Double> usersPaid) throws Exception {
        if(validate()){
            throw new Exception("Invalid percentages sum");
        }

        Map<User, Double> splitMap = new HashMap<>();

        for (User member : percentages.keySet()) {
            double expectedPaid = amount * percentages.getOrDefault(member, 0.0) / 100.0;
            double actualPaid = usersPaid.getOrDefault(member, 0.0);
            double balance = actualPaid - expectedPaid;

            splitMap.put(member, balance);
        }
        return splitMap;
    }

    @Override
    public boolean validate() {
        return percentages.values().stream().mapToDouble(a -> a).sum() == 100;
    }
}

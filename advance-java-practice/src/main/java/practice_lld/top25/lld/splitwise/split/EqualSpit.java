package practice_lld.top25.lld.splitwise.split;

import lombok.NoArgsConstructor;
import practice_lld.top25.lld.splitwise.entity.User;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class EqualSpit implements Split{

    @Override
    public Map<User, Double> split(Double totalAmount, Map<User, Double> usersPaid) {
        Map<User, Double> splitMap = new HashMap<>();
        int usersParticipated = usersPaid.size();

        Double expectedPaid = totalAmount/usersParticipated;
        for (User member : usersPaid.keySet()) {
            double actualPaid = usersPaid.getOrDefault(member, 0.0);
            double balance = actualPaid - expectedPaid;
            splitMap.put(member, balance);
        }
        return splitMap;
    }

    @Override
    public boolean validate() {
        return true;
    }
}

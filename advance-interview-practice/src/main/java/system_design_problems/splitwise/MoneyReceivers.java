package system_design_problems.splitwise;

import lombok.AllArgsConstructor;
import org.joda.time.DateTime;

@AllArgsConstructor
public class MoneyReceivers {
    int receivedFrom;
    int receivedto;
    double amount;
    DateTime date;
}

package system_design_problems.splitwise;

import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class MoneyReceivers {
    int receivedFrom;
    int receivedTo;
    double amount;
    Date date;
}

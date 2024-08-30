package practice_lld.top25.lld.splitwise.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor
@Getter
@Value
public class Transaction {
    User payer;
    User receiver;
    Double amount;
}

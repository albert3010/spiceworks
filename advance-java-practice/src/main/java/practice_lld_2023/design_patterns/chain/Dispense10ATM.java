package practice_lld_2023.design_patterns.chain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class Dispense10ATM implements DispenseChain {
    private static int noteVal = 10;
    private DispenseChain chain;

    public void setNextChain(DispenseChain dispenseChain) {
        chain = dispenseChain;
    }

    public void dispense(Currency currency) {
        int amount = currency.getAmount();
        if(amount ==0) return;
        if (amount >= noteVal) {
            int notes = amount / noteVal;
            int remaining = amount % noteVal;
            System.out.println("Dispense 10 notes total : " + notes);
            if(Objects.nonNull(chain)){
                chain.dispense(new Currency(remaining));
            }
        }else {
            chain.dispense(new Currency(amount));
        }
    }
}

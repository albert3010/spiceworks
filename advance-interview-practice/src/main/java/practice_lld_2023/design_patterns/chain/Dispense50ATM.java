package practice_lld_2023.design_patterns.chain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Dispense50ATM implements DispenseChain {

    private DispenseChain chain;

    public void setNextChain(DispenseChain dispenseChain) {
        chain = dispenseChain;

    }

    public void dispense(Currency currency) {
        int amount = currency.getAmount();
        if(amount ==0) return;
        if (amount >= 50) {
            int notes = amount / 50;
            int remaining = amount % 50;
            System.out.println("Dispense 50 notes total : " + notes);
            chain.dispense(new Currency(remaining));
        }else {
            chain.dispense(new Currency(amount));
        }
    }
}

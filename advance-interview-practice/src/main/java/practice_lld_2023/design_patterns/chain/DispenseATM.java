package practice_lld_2023.design_patterns.chain;

public class DispenseATM {
    private DispenseChain c1;

    public DispenseATM() {
        c1 = new Dispense50ATM();
        DispenseChain c2 = new Dispense20ATM();
        DispenseChain  c3 = new Dispense10ATM();
        c1.setNextChain(c2);
        c2.setNextChain(c3);
    }

    public void dispenseMoney(Currency currency){
         c1.dispense(currency);
    }

    public static void main(String[] args) {
        DispenseATM atmDispenser = new DispenseATM();
        atmDispenser.dispenseMoney(new Currency(130));
    }
}

package practice_lld_2023.design_patterns.chain;

public interface DispenseChain {

    void setNextChain(DispenseChain dispenseChain);

    void dispense(Currency currency);
}

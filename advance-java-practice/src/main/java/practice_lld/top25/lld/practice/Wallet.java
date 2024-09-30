package practice_lld.top25.lld.practice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Wallet {
    String name;
    int balance;

    public Wallet(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    void update(int val) throws WalletException {
        if(val<0) throw new WalletException(WalletError.INVALID_AMOUNT);
        if(balance<val){
            throw new WalletException(WalletError.LOW_AMOUNT);
        }
        balance = val;
    }
}

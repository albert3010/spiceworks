package practice_lld.top25.lld.practice;

public class WalletException extends Exception {
    private WalletError walletError;

    public WalletException(WalletError walletError) {
        super(walletError.getMessage());
        this.walletError = walletError;
    }

    public WalletError getWalletError() {
        return walletError;
    }
}

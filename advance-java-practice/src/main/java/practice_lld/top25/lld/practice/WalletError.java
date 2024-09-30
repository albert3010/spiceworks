package practice_lld.top25.lld.practice;

public enum WalletError {
    INVALID_AMOUNT("Invalid amount entered"),
    LOW_AMOUNT("Insufficient balance");

    private String message;

    WalletError(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

package practice_lld.top25.lld.practice.splitwise.exception;


public enum ExpenseError {

    SHARE_NOT_CORRECT("share not correct"),
    EXPENSE_INCORRECT("expense not correct"),
    GROUP_NOT_EXIST("group not found");

    private final String message;
    ExpenseError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

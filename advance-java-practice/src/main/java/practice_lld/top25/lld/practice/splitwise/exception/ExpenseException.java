package practice_lld.top25.lld.practice.splitwise.exception;


import lombok.Getter;

@Getter
public class ExpenseException extends Exception{

    private ExpenseError expenseError;

    public ExpenseException(ExpenseError expenseError) {
        super(expenseError.getMessage());
        this.expenseError = expenseError;
    }
}

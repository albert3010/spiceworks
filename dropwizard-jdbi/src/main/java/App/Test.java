package App;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
        User user1 = new User(1,"user1", "", 123);
        User user2 = new User(2,"user2", "", 123);
        User user3 = new User(3,"user3", "", 123);
        User user4 = new User(4,"user4", "", 123);

        Double total = 100.0;

        ExpenseService expenseService = new ExpenseService();
        ExpenseManager expenseManager = new ExpenseManager(expenseService);

        List<Split> splits = new ArrayList<>();

        splits.add(new Split(1));
        splits.add(new Split(2));
        splits.add(new Split(3));
        splits.add(new Split(4));
        expenseManager.addExpense(11, 1, splits, total, ExpenseType.EQUAL);

        expenseManager.printExpense(1);
        expenseManager.printExpense();

    }
}

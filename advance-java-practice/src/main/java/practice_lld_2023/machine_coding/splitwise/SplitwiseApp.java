package practice_lld_2023.machine_coding.splitwise;

import practice_lld_2023.machine_coding.splitwise.dao.ExpenseDao;
import practice_lld_2023.machine_coding.splitwise.dao.UserDao;
import practice_lld_2023.machine_coding.splitwise.dao.impl.ExpenseDaoImpl;
import practice_lld_2023.machine_coding.splitwise.dao.impl.UserDaoImpl;
import practice_lld_2023.machine_coding.splitwise.model.expense.EqualSplit;
import practice_lld_2023.machine_coding.splitwise.model.expense.Expense;
import practice_lld_2023.machine_coding.splitwise.model.user.User;
import practice_lld_2023.machine_coding.splitwise.service.SplitwiseService;

import java.util.Arrays;

public class SplitwiseApp {

    public static void main(String[] args) throws Exception {
        ExpenseDao expenseDao = new ExpenseDaoImpl();
        UserDao userDao = new UserDaoImpl();

        SplitwiseService splitwiseService = new SplitwiseService(expenseDao, userDao);

        int g1 = splitwiseService.addGroup("G1");
        int g2 = splitwiseService.addGroup("G2");
        int u1 = splitwiseService.addUser(new User("u1", "email1", 12));
        int u2 = splitwiseService.addUser(new User("u2", "email1", 123));
        int u3 = splitwiseService.addUser(new User("u3", "email1", 123));

        splitwiseService.addUserToGroup(g1, u1);
        splitwiseService.addUserToGroup(g1, u2);
        splitwiseService.addUserToGroup(g1, u3);

        splitwiseService.addUserToGroup(g2, u1);
        splitwiseService.addUserToGroup(g2, u2);
        splitwiseService.addUserToGroup(g2, u3);

        Expense expense1 = new Expense(u1, "cafe bill 1", new EqualSplit(Arrays.asList(u1, u2, u3), 600.0));
        Expense expense2 = new Expense(u2, "cafe bill 2", new EqualSplit(Arrays.asList(u1, u2, u3), 900.0));
        Expense expense3 = new Expense(u2, "cafe bill 3", new EqualSplit(Arrays.asList(u1, u2, u3), 300.0));

        splitwiseService.addExpenseToGroup(g1, expense1);
        splitwiseService.addExpenseToGroup(g1, expense3);
        splitwiseService.addExpenseToGroup(g2, expense2);

        splitwiseService.showGroupBalance(g1);
        splitwiseService.showGroupBalance(g2);
        splitwiseService.showBalances(u1, u2);

        Expense expense4 = new Expense(u2, "cafe bill 4", new EqualSplit(Arrays.asList(u1, u2, u3), 300.0));

        splitwiseService.addExpense(expense4);
        splitwiseService.showBalances(u1, u2);

        splitwiseService.getAllExpense(g1);

    }
}

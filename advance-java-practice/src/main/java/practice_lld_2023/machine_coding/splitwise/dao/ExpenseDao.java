package practice_lld_2023.machine_coding.splitwise.dao;

import practice_lld_2023.machine_coding.splitwise.model.expense.Expense;

import java.util.List;
import java.util.Map;

public interface ExpenseDao {

    void addExpenseToGroup(Integer groupId, Expense expense) throws Exception;

    Map<Integer, Map<Integer, Double>> getBalanceSheet(Integer groupId);

    Map<Integer, Map<Integer, Map<Integer, Double>>> getBalanceSheet();

    List<Expense> getAllTransactions(Integer groupId);

}

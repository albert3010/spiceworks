package practice_lld_2023.lld_2022.splitwise.dao.impl;

import practice_lld_2023.lld_2022.splitwise.dao.ExpenseDao;
import practice_lld_2023.lld_2022.splitwise.model.expense.Expense;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseDaoImpl implements ExpenseDao {

    private Map<Integer, List<Expense>> groupExpenseLog;

    private Map<Integer, Map<Integer, Map<Integer, Double>>> groupBalanceSheet;

    public ExpenseDaoImpl() {
        this.groupExpenseLog = new HashMap<>();
        this.groupBalanceSheet = new HashMap<>();
    }

    public synchronized void addExpenseToGroup(Integer groupId, Expense expense) throws Exception {
        groupExpenseLog.putIfAbsent(groupId, new ArrayList<>());
        groupExpenseLog.get(groupId).add(expense);
        Integer paidBy = expense.getPaidBy();
        groupBalanceSheet.putIfAbsent(groupId, new HashMap<>());
        Map<Integer, Map<Integer, Double>> groupBalance = groupBalanceSheet.get(groupId);

        groupBalance.putIfAbsent(paidBy, new HashMap<>());

        Map<Integer, Double> balanceForPaidTo = groupBalance.get(paidBy);

        Map<Integer, Double> oweAmount = expense.getSplit().getOweAmount();

        oweAmount.forEach((paidTo, amount)
                -> balanceForPaidTo.put(paidTo, balanceForPaidTo.getOrDefault(paidTo, 0.0) + amount));

        oweAmount.forEach((paidTo, amount) -> {
            groupBalance.putIfAbsent(paidTo, new HashMap<>());
            Map<Integer, Double> balanceForPaidBy = groupBalance.get(paidTo);
            balanceForPaidBy.put(paidBy, balanceForPaidBy.getOrDefault(paidBy, 0.0) - amount);
        });
    }

    public Map<Integer, Map<Integer, Double>> getBalanceSheet(Integer groupId) {
        return groupBalanceSheet.get(groupId);
    }

    public Map<Integer, Map<Integer, Map<Integer, Double>>> getBalanceSheet() {
        return groupBalanceSheet;
    }

    public List<Expense> getAllTransactions(Integer groupId){
        return groupExpenseLog.get(groupId);
    }
}

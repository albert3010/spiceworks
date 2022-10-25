package App;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {
    private Map<Integer, Map<Integer, Double>> expenseMap;
    private Map<Integer, User> userMap;
    private List<Expense> expenseLog;
    private ExpenseService expenseService;

    public ExpenseManager(ExpenseService expenseService) {
        this.expenseMap = new HashMap<>();
        this.userMap = new HashMap<>();
        this.expenseLog = new ArrayList<>();
        this.expenseService = expenseService;
    }

    public boolean addExpense(int id, Integer paidBy, List<Split> splits,
                              Double amount, ExpenseType expenseType) throws Exception {
        Expense expense = expenseService.createExpense(id, paidBy, splits, amount, expenseType);
        if (!expense.validate()) {
            throw new Exception("Validation Failed");
        }
        expenseLog.add(expense);
        List<Split> splitList = expense.getSplits();
        expenseMap.putIfAbsent(paidBy, new HashMap<>());
        for (Split split : splitList) {
            int paidToId = split.getUserId();
            Map<Integer, Double> paidToMap = expenseMap.get(paidBy);
            paidToMap.put(paidToId,
                    paidToMap.getOrDefault(paidToId, 0.0) + split.getAmount());

            expenseMap.putIfAbsent(paidToId, new HashMap<>());
            Map<Integer, Double> paidByMap = expenseMap.get(paidToId);
            paidByMap.put(paidBy, paidByMap.getOrDefault(paidBy, 0.0) - split.getAmount());
        }
        return true;
    }

    void printExpense(int userId) {
        Map<Integer, Double> paidToMap = expenseMap.get(userId);
        double total = 0;
        for (Integer paidToId : paidToMap.keySet()) {
            Double amount = paidToMap.get(paidToId);
            System.out.println(userId + " owes " + paidToId + " : " + amount);
            total += paidToMap.get(paidToId);
        }
        System.out.println("Total amount: " + total);

    }

    void printExpense() {
        for (Integer paidBy : expenseMap.keySet()) {
            Map<Integer, Double> paidToMap = expenseMap.get(paidBy);
            for (Integer paidToId : paidToMap.keySet()) {
                System.out.println(paidBy + " owes " + paidToId + " : " + paidToMap.get(paidToId));
            }
        }
    }


}

package practice_lld_2023.lld_2022.splitwise.service;

import practice_lld_2023.lld_2022.splitwise.dao.ExpenseDao;
import practice_lld_2023.lld_2022.splitwise.dao.UserDao;
import practice_lld_2023.lld_2022.splitwise.model.expense.Expense;
import practice_lld_2023.lld_2022.splitwise.model.user.Group;
import practice_lld_2023.lld_2022.splitwise.model.user.User;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SplitwiseService {
    ExpenseDao expenseDao;
    UserDao userDao;

    public SplitwiseService(ExpenseDao expenseDao, UserDao userDao) {
        this.expenseDao = expenseDao;
        this.userDao = userDao;
    }

    public void addExpenseToGroup(Integer groupId, Expense expense) throws Exception {

        expenseDao.addExpenseToGroup(groupId, expense);
    }

    public void addExpense(Expense expense) throws Exception {

        expenseDao.addExpenseToGroup(0, expense);
    }

    public Integer addUser(User user) throws Exception {
        userDao.addUser(user);
        return user.getUserId();
    }

    public synchronized void addUserToGroup(Integer groupId, Integer userId) throws Exception {
        userDao.addUserToGroup(groupId, userId);
    }

    public Integer addGroup(String name) {
        Group group = new Group(name);
        userDao.addGroup(group);
        return group.getGroupId();
    }

    public List<Expense> getAllExpense(Integer groupId) {
        return expenseDao.getAllTransactions(groupId);
    }

    public void showGroupBalance(Integer groupId) {
        System.out.println("----- Group : " + groupId + " ------");
        Map<Integer, Map<Integer, Double>> groupBalances = expenseDao.getBalanceSheet(groupId);
        if (Objects.nonNull(groupBalances)) {
            for (Integer userId : groupBalances.keySet()) {
                Map<Integer, Double> paidsTpMap = groupBalances.get(userId);
                paidsTpMap.forEach((paidTo, amount) -> {
                    System.out.println("------- group " + groupId + "--------------");
                    display(userId, paidTo, amount);
                });
            }
        }

    }

    public Double computeBalance(Integer groupId, Integer userId1, Integer userId2) {
        Map<Integer, Map<Integer, Double>> groupBalances = expenseDao.getBalanceSheet(groupId);
        Map<Integer, Double> paidsTo = groupBalances.get(userId1);
        Double amount = 0.0;
        if (Objects.nonNull(paidsTo)) {
            amount = paidsTo.getOrDefault(userId2, 0.0);
        }
        System.out.println("------- group " + groupId + "--------------");
        display(userId1, userId2, amount);
        return amount;
    }


    public void showBalances(Integer userId1, Integer userId2) {
        System.out.println("---------------------");
        Double totalAmount = 0.0;
        for (Integer groupId : expenseDao.getBalanceSheet().keySet()) {
            totalAmount += computeBalance(groupId, userId1, userId2);
        }
        System.out.println("------- Total All group--------------");
        display(userId1, userId2, totalAmount);
    }

    private void display(Integer userId1, Integer userId2, Double amount) {
        if (userId1 == userId2) return;
        if (amount >= 0) {
            System.out.println("User " + userId1 + " gets from user " + userId2 + " Amount : " + amount);
        } else {
            System.out.println("User " + userId1 + " owes to user " + userId2 + " Amount : " + -amount);
        }
    }
}

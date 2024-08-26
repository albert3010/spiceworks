package practice_lld.top25.lld.splitwise.settle;

import practice_lld.top25.lld.splitwise.entity.Group;
import practice_lld.top25.lld.splitwise.entity.Transaction;

import java.util.List;
import java.util.Map;

public abstract class GroupSettlement {

    public final void settleGroup(Group group){
            Map<Integer, Double> netBalances = calculateNetBalances(group);
            List<Transaction> transactions = settleDebts(netBalances);
            applyTransactions(group, transactions);
    }

    abstract Map<Integer, Double> calculateNetBalances(Group group);

    abstract List<Transaction> settleDebts(Map<Integer, Double> netBalances);

    abstract void applyTransactions(Group group, List<Transaction> transactions);
}

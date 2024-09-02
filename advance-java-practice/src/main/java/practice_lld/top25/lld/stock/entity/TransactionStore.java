package practice_lld.top25.lld.stock.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TransactionStore {
    Map<Integer, List<Transaction>> transactions;

    public TransactionStore(Map<Integer, List<Transaction>> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(int userId, Transaction t){
        transactions.computeIfAbsent(userId, u -> new ArrayList<>())
                .add(t);
    }
}

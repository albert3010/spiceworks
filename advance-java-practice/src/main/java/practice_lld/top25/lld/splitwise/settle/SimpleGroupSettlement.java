package practice_lld.top25.lld.splitwise.settle;

import practice_lld.top25.lld.splitwise.entity.Group;
import practice_lld.top25.lld.splitwise.entity.Transaction;
import practice_lld.top25.lld.splitwise.services.GroupService;

import java.util.List;
import java.util.Map;

public class SimpleGroupSettlement extends GroupSettlement{
    GroupService groupService;

    public SimpleGroupSettlement(GroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    Map<Integer, Double> calculateNetBalances(Group group) {
        return group.getUserNetBalance();
    }

    @Override
    List<Transaction> settleDebts(Map<Integer, Double> netBalances) {
        return null;
    }

    protected void applyTransactions(Group group, List<Transaction> transactions){
        for(Transaction t : transactions){
            group.updateTransaction(t);
        }
    }
}

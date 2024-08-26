package practice_lld.top25.lld.splitwise.services;

import practice_lld.top25.lld.splitwise.entity.Expense;
import practice_lld.top25.lld.splitwise.entity.Group;
import practice_lld.top25.lld.splitwise.settle.GroupSettlement;

public class SplitwiseService {
    private final GroupService groupService;
    private final GroupSettlement groupSettlement;

    public SplitwiseService(GroupService groupService, GroupSettlement groupSettlement) {
        this.groupService = groupService;
        this.groupSettlement = groupSettlement;
    }

    public void recordExpense(int groupId, Expense expense) throws Exception {
        groupService.addExpenses(groupId, expense);
    }

    void settleUpGroup(int groupId){
        Group group = groupService.getGroup(groupId);
        groupSettlement.settleGroup(group);
    }

}

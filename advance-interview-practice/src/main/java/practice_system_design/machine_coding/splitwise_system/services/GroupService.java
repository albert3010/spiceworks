package practice_system_design.machine_coding.splitwise_system.services;

import practice_system_design.machine_coding.splitwise_system.*;

import java.util.HashMap;
import java.util.List;

public class GroupService {
    HashMap<Integer, User> users = new HashMap<>();

    HashMap<Integer, UserGroup> groupMap = new HashMap<>();
    HashMap<Integer, Bill> billRecord = new HashMap<>();
    HashMap<Integer, HashMap<Integer, HashMap<Integer, Double>>> groupBalanceMap = new HashMap<>();

    public void addGroup(UserGroup userGroup) {
        groupMap.put(userGroup.getId(), userGroup);
        for (User user : userGroup.getUsers()) {
            users.put(user.getUserId(), user);
        }
    }

    public void addUserToGroup(User user, int groupId) {
        users.put(user.getUserId(), user);
        UserGroup userGroup = groupMap.get(groupId);
        if (userGroup != null) {
            userGroup.addUser(user);
        } else {
            System.out.println("Group doesn't exist");
        }
    }

    public void addBill(Bill bill, int groupId) {
        billRecord.put(bill.getId(), bill);
        List<GiveAndTake> giveAndTakes = SettleUpService.getIndividualBill(bill);
        List<PersonAToB> personAToBList = SettleUpService.getPersonsAmount(giveAndTakes);
        updateGroupBalance(personAToBList, groupId);
    }

    private void updateGroupBalance(List<PersonAToB> personAToBList, int groupId) {
        HashMap<Integer, HashMap<Integer, Double>> personIdToAmounts = groupBalanceMap.getOrDefault(groupId, new HashMap<>());
        for (PersonAToB personAToB : personAToBList) {
            double amount = personAToB.getAmount();

            HashMap<Integer, Double> personIdToAmount = personIdToAmounts
                    .getOrDefault(personAToB.getPersonAId(), new HashMap<>());

            Double currentAmount = personIdToAmount.getOrDefault(personAToB.getPersonBId(), 0.0);
            personIdToAmount.put(personAToB.getPersonBId(), currentAmount + amount);

            personIdToAmounts.put(personAToB.getPersonAId(), personIdToAmount);
        }
        groupBalanceMap.put(groupId, personIdToAmounts);
    }

    public void showPersonView(int userId, int groupId) {
        System.out.println("--------- Person " + users.get(userId).getName() + " view ------");
        HashMap<Integer, HashMap<Integer, Double>> personToGiveMap = groupBalanceMap.get(groupId);
        Double amount = 0.0;
        if (personToGiveMap != null) {

            for (Integer userAId : personToGiveMap.keySet()) {

                HashMap<Integer, Double> personView = personToGiveMap.get(userAId);
                if (personView == null) {
                    break;
                }
                for (Integer userToGiveId : personView.keySet()) {
                    if (userAId == userId) {
                        double pay = personView.get(userToGiveId);
                        amount -= pay;
                        System.out.println("Person " + users.get(userAId).getName() + " pay " + pay);
                    }
                    if (userToGiveId == userId) {
                        double pay = personView.get(userToGiveId);
                        amount += pay;
                        System.out.println("Person " + users.get(userAId).getName() + " gets " + pay);
                    }
                }
            }
            if (amount > 0) {
                System.out.println("Person ---" + users.get(userId).getName() + " gets total " + amount);
            }
            if (amount < 0) {
                System.out.println("Person ---" + users.get(userId).getName() + " pay total " + -amount);
            }

            if (amount == 0) {
                System.out.println("Person ---" + users.get(userId).getName() + " pay or gets zero");
            }
        }
        System.out.println();
        System.out.println();
    }

    public void groupBalanceView(int groupId) {
        HashMap<Integer, HashMap<Integer, Double>> personToGiveMap = groupBalanceMap.get(groupId);
        UserGroup userGroup = groupMap.get(groupId);
        System.out.println("Group view G" + groupId + " \n");
        if (personToGiveMap != null) {
            for (User user : userGroup.getUsers()) {
                showPersonView(user.getUserId(), groupId);
            }
        }
    }
}

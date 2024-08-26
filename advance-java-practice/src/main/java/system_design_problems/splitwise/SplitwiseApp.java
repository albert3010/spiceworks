package system_design_problems.splitwise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SplitwiseApp {
    static List<Users> usersList = new ArrayList<>();
    static List<Groups> groupsList = new ArrayList<>();
    static HashMap<Integer, Groups> groupsMap = new HashMap<>();
    static HashMap<Integer, Bill> billsGroupMap = new HashMap<>();

    public static void main(String[] args) throws Exception {


        Users user1 = new Users(UUID.randomUUID().toString());
        Users user2 = new Users(UUID.randomUUID().toString());
        Users user3 = new Users(UUID.randomUUID().toString());
        Users user4 = new Users(UUID.randomUUID().toString());
        Users user5 = new Users(UUID.randomUUID().toString());

        usersList.add(user1);
        usersList.add(user2);
        usersList.add(user3);
        usersList.add(user4);
        usersList.add(user5);
        List<Integer> group1Users = new ArrayList<>();
//        group1Users.add((long) user1.getId());
//        group1Users.add((long) user2.getId());
//        group1Users.add((long) user3.getId());
//
//        Groups group1 = new Groups("Group1", group1Users);
//        groupsList.add(group1);
//
//        addUser(user1.getId(), group1.getId());
//        addUser(user2.getId(), group1.getId());
//        addUser(user3.getId(), group1.getId());
//        addUser(user4.getId(), group1.getId());
//
//        printUserList();
//        printGroupList();
//
//        Bill bill1 = new Bill(1, 1l, group1Users, 1l, 100.0);
//        addBill(bill1);
//
//        printUserList();
//        printGroupList();


    }

    public static void addUser(Integer userId, Integer groupId) throws Exception {
        Groups group = groupsMap.get(groupId);
        group.addUserToGroup(userId);
    }
    public static void addBill(Bill bill){
    // add bill to group
        int groupId = bill.groupId;
        Groups group = groupsMap.get(groupId);
        billsGroupMap.put(groupId, bill);
        double perPerson = Helper.getPerPersonBill(bill);

        HashMap<Integer, List<MoneyReceivers>> allPersonsBalance = Helper.getAllPersonsBalance(perPerson, bill);


    }

    public static void deleteBill(int billId){

    }
    public static void viewUserDetailsForAllGroups(int userId, int groupId){

    }
    public static void viewGroupDetails(int userId, int groupId){

    }

}

package system_design_problems.splitwise;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SplitwiseApp {
    static List<Users> usersList = new ArrayList<>();
    static List<Groups> groupsList = new ArrayList<>();

//    public static void main(String[] args) throws Exception {
//
//
//        Users user1 = new Users(UUID.randomUUID().toString());
//        Users user2 = new Users(UUID.randomUUID().toString());
//        Users user3 = new Users(UUID.randomUUID().toString());
//        Users user4 = new Users(UUID.randomUUID().toString());
//        Users user5 = new Users(UUID.randomUUID().toString());
//
//        usersList.add(user1);
//        usersList.add(user2);
//        usersList.add(user3);
//        usersList.add(user4);
//        usersList.add(user5);
//        List<Integer> group1Users = new ArrayList<>();
////        group1Users.add((long) user1.getId());
////        group1Users.add((long) user2.getId());
////        group1Users.add((long) user3.getId());
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
//
//
//    }

    private static void printGroupList() {

        System.out.println("************** GROUP LIST ********************");
        groupsList.forEach(groups -> System.out.println(groups));
    }

    private static void printUserList() {

        System.out.println("************** USER LIST ********************");
        usersList.forEach(users -> System.out.println(users));
    }

    public static void addUser(Integer userId, Integer groupId) throws Exception {

    }
}

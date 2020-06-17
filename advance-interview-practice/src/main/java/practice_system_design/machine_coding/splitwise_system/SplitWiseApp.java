package practice_system_design.machine_coding.splitwise_system;

import com.google.common.collect.Lists;
import practice_system_design.machine_coding.splitwise_system.services.GroupService;

import java.util.ArrayList;
import java.util.List;

public class SplitWiseApp {

    public static void main(String[] args) {

        User user1 = new User("Mudit");
        User user2 = new User("Sourav");
        User user3 = new User("Souvik");

        GroupService groupService = new GroupService();
        List<User> usersG1 = new ArrayList<>();
        usersG1.add(user1);
        usersG1.add(user2);
        usersG1.add(user3);
        UserGroup userGroup1 = new UserGroup(usersG1);

        List<UserToBill> userToBills = new ArrayList<>();
        userToBills.add(new UserToBill(user1.getUserId(), 100.0, 300.0));
        userToBills.add(new UserToBill(user2.getUserId(), 100.0, 0.0));
        userToBills.add(new UserToBill(user3.getUserId(), 100.0, 0.0));

        Bill bill1 = new Bill(userToBills, 300.0);

        groupService.addGroup(userGroup1);
        groupService.addBill(bill1, userGroup1.getId());

        groupService.showPersonView(user1.getUserId(), userGroup1.getId());
        groupService.showPersonView(user2.getUserId(), userGroup1.getId());
        groupService.showPersonView(user3.getUserId(), userGroup1.getId());

        groupService.groupBalanceView(userGroup1.getId());


    }
}

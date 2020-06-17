package practice_system_design.machine_coding.splitwise_system.services;

import practice_system_design.machine_coding.splitwise_system.User;

import java.util.HashMap;

public class UserService {
    HashMap<Integer, User> users = new HashMap<>();

    public void registerUser(User user) {
        users.put(user.getUserId(), user);

    }

}

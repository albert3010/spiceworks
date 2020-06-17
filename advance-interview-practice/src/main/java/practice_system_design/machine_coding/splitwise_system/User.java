package practice_system_design.machine_coding.splitwise_system;

import practice_system_design.machine_coding.food_ordering_system.Constants;

public class User {
    int userId;
    String name;

    User(String name) {
        this.userId = Constants.getUserId();
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}

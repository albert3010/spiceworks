package practice_system_design.machine_coding.splitwise_system;

import java.util.List;

public class UserGroup {
    int id;
    List<User> users;

    UserGroup(List<User> users) {
        this.id = Constants.getBillId();
        this.users = users;
    }

    List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        if (!checkIfUserIsPresent(user)) {
            users.add(user);
        }
    }

    public void removeUser(User user) {
        if (checkIfUserIsPresent(user)) {
            users.remove(user);
        }
    }

    private boolean checkIfUserIsPresent(User user) {
        return users.stream().anyMatch(u -> u.userId == user.userId);
    }
}

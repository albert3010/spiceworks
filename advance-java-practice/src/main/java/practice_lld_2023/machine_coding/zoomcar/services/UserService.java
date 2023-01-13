package practice_lld_2023.machine_coding.zoomcar.services;

import practice_lld_2023.machine_coding.zoomcar.entity.User;

import java.util.*;
import java.util.stream.Collectors;

public class UserService {

    Map<Integer, User> usersMap = new HashMap<>();

    public List<User> getUsers() {
        return usersMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    public String addUser(User user) {
        usersMap.put(user.getId(), user);
        return "User added : " + user.getName();
    }

    public User getUser(int userId) {
        User user = usersMap.get(userId);
        Objects.nonNull(user);
        return user;
    }

    public void makeAdmin(int userId) {
        User user = usersMap.get(userId);
        Objects.nonNull(user);
        user.setAdmin(true);
        usersMap.put(userId, user);
    }


}

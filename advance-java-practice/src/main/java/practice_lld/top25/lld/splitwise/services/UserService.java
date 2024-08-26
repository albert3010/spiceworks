package practice_lld.top25.lld.splitwise.services;

import practice_lld.top25.lld.splitwise.entity.User;

import java.util.Map;

public class UserService {
    private Map<Integer, User> userMap;

    public User addUser(String name){
        User user  = new User(name, "", "");
        userMap.put(user.getUserId(), user);
        return user;
    }

    public User getUser(int userId){
        return userMap.get(userId);
    }
}

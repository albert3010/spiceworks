package practice_lld_2023.lld_2022.splitwise.dao.impl;

import practice_lld_2023.lld_2022.splitwise.dao.UserDao;
import practice_lld_2023.lld_2022.splitwise.model.user.Group;
import practice_lld_2023.lld_2022.splitwise.model.user.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {

    private Map<Integer, Group> groupsMap;
    private Map<Integer, User> usersMap;

    public UserDaoImpl() {
        this.groupsMap = new HashMap<>();
        this.usersMap = new HashMap<>();
    }

    public void addUser(User user) throws Exception {
        if (usersMap.get(user.getUserId()) != null) {
            throw new Exception("User id exists");
        }
        usersMap.put(user.getUserId(), user);
    }

    public synchronized void addUserToGroup(Integer groupId, Integer userId) throws Exception {
        if (usersMap.get(userId) != null) {
            if (groupsMap.get(groupId) != null) {
                groupsMap.get(groupId).getUsers().add(usersMap.get(userId));
            } else {
                throw new Exception("Group doesn't exist");
            }
        } else {
            throw new Exception("User doesn't exist");
        }
    }

    public List<User> getUsers(Integer groupId) {
        return groupsMap.get(groupId).getUsers();
    }

    public void addGroup(Group group) {
        groupsMap.putIfAbsent(group.getGroupId(), group);
    }

}

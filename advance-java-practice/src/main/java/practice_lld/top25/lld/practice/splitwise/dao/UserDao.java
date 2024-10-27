package practice_lld.top25.lld.practice.splitwise.dao;

import practice_lld.top25.lld.practice.splitwise.model.user.Group;
import practice_lld.top25.lld.practice.splitwise.model.user.User;

import java.util.List;

public interface UserDao {

    void addUser(User user) throws Exception;

    void addUserToGroup(Integer groupId, Integer userId) throws Exception;

    void addGroup(Group group);

    List<User> getUsers(Integer groupId);

}

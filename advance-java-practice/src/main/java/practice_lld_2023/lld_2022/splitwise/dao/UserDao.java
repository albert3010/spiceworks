package practice_lld_2023.lld_2022.splitwise.dao;

import practice_lld_2023.lld_2022.splitwise.model.user.Group;
import practice_lld_2023.lld_2022.splitwise.model.user.User;

import java.util.List;

public interface UserDao {

    void addUser(User user) throws Exception;

    void addUserToGroup(Integer groupId, Integer userId) throws Exception;

    void addGroup(Group group);

    List<User> getUsers(Integer groupId);

}

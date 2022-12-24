package practice_lld_2023.lld_2022.splitwise.model.user;


import lombok.Getter;
import practice_lld_2023.lld_2022.splitwise.Constants.Constants;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Group {
    private Integer groupId;
    String name;
    List<User> users;

    public Group(String name, List<User> users) {
        this.groupId = Constants.getGroupId();
        this.name = name;
        this.users = users;
    }

    public Group(String name) {
        this.groupId = Constants.getGroupId();
        this.name = name;
        this.users = new ArrayList<>();
    }
}

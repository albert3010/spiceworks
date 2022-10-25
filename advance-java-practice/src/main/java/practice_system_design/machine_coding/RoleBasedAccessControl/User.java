package practice_system_design.machine_coding.RoleBasedAccessControl;

import java.util.HashSet;
import java.util.Set;

public class User {
    int userId;
    String name;
    Set<Role> roles;

    public User(String name) {
        this.userId = Constants.getUserId();
        this.name = name;
        this.roles = new HashSet<>();
    }

    public int getUserId() {
        return userId;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }

    public Set<Role> getRoles() {
        return roles;
    }
}

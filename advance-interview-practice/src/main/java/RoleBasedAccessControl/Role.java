package RoleBasedAccessControl;

import java.util.HashSet;
import java.util.Set;

// Roles that user and resource will have
public class Role {
    int roleId;
    String roleName;
    Set<Action> actions;

    public Role(String roleName) {
        this.roleId = Constants.getRoleId();
        this.roleName = roleName;
        this.actions = new HashSet<>();
    }

    public void addRight(Action right) {
        actions.add(right);
    }

    public void removeRight(Action right) {
        actions.remove(right);
    }

    public Set<Action> getActions() {
        return actions;
    }

    public int getRoleId() {
        return roleId;
    }

}

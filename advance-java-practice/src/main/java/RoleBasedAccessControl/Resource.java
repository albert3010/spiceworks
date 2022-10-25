package RoleBasedAccessControl;

import java.util.HashSet;
import java.util.Set;

// This is resource file for user to access any resource based on roles

public class Resource {
    int resourceId;
    Set<Role> roles;

    public Resource(Set<Role> roles) {
        this.resourceId = Constants.getResourceId();
        this.roles = roles;
    }

    public Resource() {
        this.resourceId = Constants.getResourceId();
        this.roles = new HashSet<>();
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }

    public int getResourceId() {
        return resourceId;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}

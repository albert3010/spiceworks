package RoleBasedAccessControl.services;

import RoleBasedAccessControl.Action;
import RoleBasedAccessControl.Resource;
import RoleBasedAccessControl.Role;
import RoleBasedAccessControl.User;

import java.util.HashMap;
import java.util.Set;

// Manages roles of user and resource

public class SystemAccessManager {

    HashMap<Integer, Role> roleMap = new HashMap<>();
    HashMap<Integer, User> userMap = new HashMap<>();
    HashMap<Integer, Resource> resourceMap = new HashMap<>();

    public void addRole(Role role) {
        roleMap.putIfAbsent(role.getRoleId(), role);
    }

    public void addUser(User user) {
        userMap.putIfAbsent(user.getUserId(), user);
    }

    public void addRoleToUser(int userId, Role role) {
        userMap.get(userId).addRole(role);
    }

    public void removeRoleToUser(int userId, Role role) {
        userMap.get(userId).removeRole(role);
    }

    public void addActionToSystem(Action action, int systemId) {
        roleMap.get(systemId).getActions().add(action);
    }

    public void addResourceToSystem(Resource resource) {
        resourceMap.putIfAbsent(resource.getResourceId(), resource);
    }

    public void removeActionToSystem(Action action, int systemId) {
        roleMap.get(systemId).getActions().remove(action);
    }

    public boolean canAccessToResources(int userId, Action action, int resourceId) {
        Set<Role> resourceRoles = resourceMap.get(resourceId).getRoles();
        Set<Role> userRoles = userMap.get(userId).getRoles();

        for (Role resourceRole : resourceRoles) {
            for (Role userRole : userRoles) {
                if (userRole.getActions().contains(action) && resourceRole.getActions().contains(action)) {
                    return true;
                }
            }
        }
        return false;
    }
}

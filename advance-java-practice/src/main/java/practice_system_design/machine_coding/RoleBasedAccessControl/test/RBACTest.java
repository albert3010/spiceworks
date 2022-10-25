package practice_system_design.machine_coding.RoleBasedAccessControl.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import practice_system_design.machine_coding.RoleBasedAccessControl.Action;
import practice_system_design.machine_coding.RoleBasedAccessControl.Resource;
import practice_system_design.machine_coding.RoleBasedAccessControl.Role;
import practice_system_design.machine_coding.RoleBasedAccessControl.User;
import practice_system_design.machine_coding.RoleBasedAccessControl.services.SystemAccessManager;


public class RBACTest {
    static SystemAccessManager systemAccessManager;

    static Resource resource1;
    static Resource resource2;
    static Resource resource3;
    static Role adminRole;

    @BeforeClass
    public static void setupSystem() {
        systemAccessManager = new SystemAccessManager();

        User user1 = new User("om");
        User user2 = new User("harry");
        User user3 = new User("Neha");

        adminRole = new Role("admin");
        adminRole.addRight(Action.READ);
        adminRole.addRight(Action.WRITE);
        adminRole.addRight(Action.DELETE);

        Role empRole = new Role("emp");
        empRole.addRight(Action.READ);
        empRole.addRight(Action.WRITE);

        Role readRole = new Role("read");
        readRole.addRight(Action.READ);

        Role writeRole = new Role("write");
        writeRole.addRight(Action.WRITE);

        user1.addRole(empRole);
        user2.addRole(adminRole);
        user3.addRole(readRole);

        systemAccessManager.addUser(user1);
        systemAccessManager.addUser(user2);
        systemAccessManager.addUser(user3);

        systemAccessManager.addRole(adminRole);
        systemAccessManager.addRole(readRole);
        systemAccessManager.addRole(writeRole);
        systemAccessManager.addRole(empRole);

        resource1 = new Resource();
        resource1.addRole(adminRole);

        resource2 = new Resource();
        resource2.addRole(writeRole);

        resource3 = new Resource();
        resource3.addRole(readRole);

        systemAccessManager.addResourceToSystem(resource1);
        systemAccessManager.addResourceToSystem(resource2);
        systemAccessManager.addResourceToSystem(resource3);
    }
    @Test
    public void userAccessFail_WRITE() {
        Assert.assertEquals(systemAccessManager
                        .canAccessToResources(2, Action.WRITE, resource3.getResourceId()),
                false);
    }

    @Test
    public void userAccessSuccess_READ() {
        Assert.assertEquals(systemAccessManager
                .canAccessToResources(1, Action.READ, resource1.getResourceId()),
                true);
    }
    @Test
    public void userAccessFail_DELETE() {
        Assert.assertEquals(systemAccessManager
                .canAccessToResources(1, Action.DELETE, resource1.getResourceId()),
                false);
    }

    @Test
    public void userAccessSuccess_DELETE_After_adding_admin_role() {
        systemAccessManager.addRoleToUser(1,adminRole);
        Assert.assertEquals(systemAccessManager
                .canAccessToResources(1, Action.DELETE, resource1.getResourceId()),
                true);
    }

}

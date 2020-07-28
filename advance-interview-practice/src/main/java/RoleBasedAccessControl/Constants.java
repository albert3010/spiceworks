package RoleBasedAccessControl;

import java.util.concurrent.atomic.AtomicInteger;

public class Constants {

    private static final AtomicInteger counterUserId = new AtomicInteger(0);

    private static final AtomicInteger counterRoleId = new AtomicInteger(0);
    private static final AtomicInteger counterResourceId = new AtomicInteger(0);

    public static Integer getUserId() {
        return counterUserId.incrementAndGet();
    }
    public static Integer getResourceId() {
        return counterResourceId.incrementAndGet();
    }

    public static Integer getRoleId() {
        return counterRoleId.incrementAndGet();
    }

}

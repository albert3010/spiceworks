package system_design_problems.splitwise;

import java.util.concurrent.atomic.AtomicInteger;

public class Constants {

    private static final AtomicInteger counterUserId = new AtomicInteger(0);

    public static Integer getUserId() {
        return counterUserId.incrementAndGet();
    }

    private static final AtomicInteger counterGroupId = new AtomicInteger(0);

    public static Integer getGroupId() {
        return counterGroupId.incrementAndGet();
    }

}

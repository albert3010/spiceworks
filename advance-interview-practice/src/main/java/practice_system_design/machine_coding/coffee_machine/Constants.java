package practice_system_design.machine_coding.splitwise_system;

import java.util.concurrent.atomic.AtomicInteger;

public class Constants {

    private static final AtomicInteger counterUserId = new AtomicInteger(0);
    private static final AtomicInteger groupId = new AtomicInteger(0);
    private static final AtomicInteger billId = new AtomicInteger(0);

    public static Integer getUserId() {
        return counterUserId.incrementAndGet();
    }

    public static Integer getGroupId() {
        return groupId.incrementAndGet();
    }

    public static Integer getBillId() {
        return billId.incrementAndGet();
    }

}

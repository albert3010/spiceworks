package practice_lld.top25.lld.splitwise;

import java.util.concurrent.atomic.AtomicInteger;

public class Constants {

    static AtomicInteger userId = new AtomicInteger(0);
    static AtomicInteger groupId = new AtomicInteger(0);

    public static int getUserId() {
        return userId.incrementAndGet();
    }
    public static int getGroupId(){
        return groupId.incrementAndGet();
    }
}

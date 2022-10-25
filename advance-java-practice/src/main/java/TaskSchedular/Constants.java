package TaskSchedular;

import java.util.concurrent.atomic.AtomicInteger;

public class Constants {

    static AtomicInteger taskId =  new AtomicInteger(0);

    public static int getTaskId() {
        return taskId.incrementAndGet();
    }
}

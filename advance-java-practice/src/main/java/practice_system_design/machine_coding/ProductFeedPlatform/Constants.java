package practice_system_design.machine_coding.ProductFeedPlatform;

import java.util.concurrent.atomic.AtomicInteger;

public class Constants {

    private static final AtomicInteger counterUserId = new AtomicInteger(0);
    private static final AtomicInteger sellerId = new AtomicInteger(0);
    private static final AtomicInteger postId = new AtomicInteger(0);
    private static final AtomicInteger productId = new AtomicInteger(0);

    public static Integer getUserId() {
        return counterUserId.incrementAndGet();
    }

    public static Integer getSellerId() {
        return sellerId.incrementAndGet();
    }

    public static Integer getPostId() {
        return postId.incrementAndGet();
    }

    public static Integer getProductId() {
        return productId.incrementAndGet();
    }
}

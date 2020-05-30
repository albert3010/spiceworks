package practice_new.machine_coding.food_ordering_system;

import java.util.concurrent.atomic.AtomicInteger;

public class Constants {

    private static final AtomicInteger counterUserId = new AtomicInteger(0);
    private static final AtomicInteger menuId = new AtomicInteger(0);
    private static final AtomicInteger restaurantId = new AtomicInteger(0);
    private static final AtomicInteger orderId = new AtomicInteger(0);

    public static Integer getUserId() {
        return counterUserId.incrementAndGet();
    }

    public static Integer getOrderId() {
        return orderId.incrementAndGet();
    }

    public static Integer getRestaurantId() {
        return restaurantId.incrementAndGet();
    }

    public static Integer getMenuId() {
        return menuId.incrementAndGet();
    }
}

package entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

@NoArgsConstructor
public class IdCounters {

    static AtomicInteger orderId = new AtomicInteger(0);
    static AtomicInteger buyerId = new AtomicInteger(0);
    static AtomicInteger productId = new AtomicInteger(0);
    static AtomicInteger addressId = new AtomicInteger(0);

    public static int getOrderId() {
        return orderId.incrementAndGet();
    }

    public static int getBuyerId() {
        return buyerId.incrementAndGet();
    }

    public static int getProductId() {
        return productId.incrementAndGet();
    }

    public static int getAddressId() {
        return addressId.incrementAndGet();
    }
}

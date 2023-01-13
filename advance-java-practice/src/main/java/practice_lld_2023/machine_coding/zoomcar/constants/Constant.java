package practice_lld_2023.machine_coding.zoomcar.constants;

import java.util.concurrent.atomic.AtomicInteger;

public class Constant {
    private static final AtomicInteger userId = new AtomicInteger(0);
    private static final AtomicInteger vehicleId = new AtomicInteger(0);
    private static final AtomicInteger vehicleBookingId = new AtomicInteger(0);
    private static final AtomicInteger invoiceId = new AtomicInteger(0);
    private static final AtomicInteger pickupLocationId = new AtomicInteger(0);
    private static final AtomicInteger inventoryId = new AtomicInteger(0);

    public static Integer getUserId() {
        return userId.incrementAndGet();
    }

    public static Integer getVehicleId() {
        return vehicleId.incrementAndGet();
    }

    public static Integer getVehicleBookingId() {
        return vehicleBookingId.incrementAndGet();
    }
    public static Integer getInvoiceId() {
        return invoiceId.incrementAndGet();
    }

    public static Integer getPickupLocationId() {
        return pickupLocationId.incrementAndGet();
    }
    public static Integer getInventoryId() {
        return inventoryId.incrementAndGet();
    }
}

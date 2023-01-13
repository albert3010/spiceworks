package practice_lld_2023.machine_coding.zoomcar.entity;

import lombok.Data;

import java.util.Calendar;
import java.util.Date;

@Data
public class VehicleBooking {

    int id;
    int inventoryId;
    int userId;
    int pickupLocationId;
    Date bookingStartDate;
    Date bookingEndDate;
    boolean isCanceled;
    Date bookedAt;
    Date updatedAt;

    public VehicleBooking(int inventoryId, int userId, int pickupLocationId, Date bookingStartDate, Date bookingEndDate, boolean isCanceled) {
        this.inventoryId = inventoryId;
        this.userId = userId;
        this.pickupLocationId = pickupLocationId;
        this.bookingStartDate = bookingStartDate;
        this.bookingEndDate = bookingEndDate;
        this.isCanceled = isCanceled;
        this.bookedAt = Calendar.getInstance().getTime();
        this.updatedAt = Calendar.getInstance().getTime();
    }
}

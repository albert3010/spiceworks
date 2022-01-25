package lld_2022.zoomcar.entity;

import lld_2022.zoomcar.constants.Constant;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;

@Data
public class Inventory {

    int id;
    int vehicleId;
    int pickupLocationId;
    boolean isReady;
    Date createdAt;
    Date updatedAt;

    public Inventory(int vehicleId, int pickupLocationId, boolean isReady) {
        id = Constant.getInventoryId();
        this.vehicleId = vehicleId;
        this.pickupLocationId = pickupLocationId;
        this.isReady = isReady;
        this.createdAt = Calendar.getInstance().getTime();
        this.updatedAt = Calendar.getInstance().getTime();

    }
}

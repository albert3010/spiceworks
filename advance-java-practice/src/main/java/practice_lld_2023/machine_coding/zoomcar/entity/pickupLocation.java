package practice_lld_2023.machine_coding.zoomcar.entity;

import practice_lld_2023.machine_coding.zoomcar.constants.Constant;
import lombok.Data;

@Data
public class pickupLocation {
    int id;
    String locationName;
    int lat;
    int log;

    public pickupLocation(String locationName, int lat, int log) {
        id = Constant.getPickupLocationId();
        this.locationName = locationName;
        this.lat = lat;
        this.log = log;
    }
}

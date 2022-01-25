package lld_2022.zoomcar.entity;

import lld_2022.zoomcar.constants.Constant;
import lld_2022.zoomcar.constants.VehicleType;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;

@Data
public class Vehicle {

    int id;
    VehicleType vehicleType;
    int ownerId;
    String plateNo;
    String scanId;
    Date createdAt;

    public Vehicle(VehicleType vehicleType, int ownerId, String plateNo, String scanId) {
        this.id = Constant.getVehicleId();
        this.vehicleType = vehicleType;
        this.ownerId = ownerId;
        this.plateNo = plateNo;
        this.scanId = scanId;
        this.createdAt = Calendar.getInstance().getTime();
    }
}

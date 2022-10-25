package practice_system_design.machine_coding.parkingLotApp.Vehicles;

import practice_system_design.machine_coding.parkingLotApp.Vehicle;
import practice_system_design.machine_coding.parkingLotApp.VehicleType;

public class Motor extends Vehicle {

    String name;
    VehicleType vehicleType;

    public Motor(VehicleType type, String name) {
        super(type, name);
        this.vehicleType = type;
    }
}

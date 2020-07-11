package practice_system_design.machine_coding.parkingLotApp.Vehicles;

import practice_system_design.machine_coding.parkingLotApp.Vehicle;
import practice_system_design.machine_coding.parkingLotApp.VehicleType;

public class SUV extends Vehicle {
    String name;
    VehicleType vehicleType;
    public SUV(VehicleType type, String name) {
        super(type, name);
        this.vehicleType = type;
    }
}

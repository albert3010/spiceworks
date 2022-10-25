package practice_system_design.machine_coding.parkingLotApp;

import org.junit.Test;
import practice_system_design.machine_coding.parkingLotApp.Vehicles.Car;
import practice_system_design.machine_coding.parkingLotApp.Vehicles.Motor;
import practice_system_design.machine_coding.parkingLotApp.Vehicles.SUV;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotApp {

    @Test
    public void testParking() {

        Vehicle vehicle1 = new Car(VehicleType.MEDIUM, "car1");
        Vehicle vehicle2 = new Motor(VehicleType.SMALL, "motor1");
        Vehicle vehicle3 = new SUV(VehicleType.LARGE, "SUV1");
        Vehicle vehicle4 = new SUV(VehicleType.LARGE, "SUV2");

        Lot lot1 = new Lot(3, 100);
        Lot lot2 = new Lot(3, 100);

        List<Lot> lots = new ArrayList<>();
        lots.add(lot1);
        lots.add(lot2);
        ParkingLot parkingLot = new ParkingLot(lots);

        parkingLot.parkVehicle(vehicle1);
        parkingLot.parkVehicle(vehicle2);

        parkingLot.unParkVehicle(vehicle2);
        List<Spot> spots = parkingLot.getVehicleHistory(vehicle2.id);
        print(spots);
        parkingLot.parkVehicle(vehicle2);
        parkingLot.parkVehicle(vehicle3);


    }

    public void print(List<Spot> spots) {
        for (Spot spot : spots) {
            System.out.println(spot.toString());
        }
    }

}

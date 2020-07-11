package practice_system_design.machine_coding.parkingLotApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParkingLot {
    List<Lot> lots;
    HashMap<Integer, Lot> vehicleLot = new HashMap<>();
    HashMap<Integer, List<Spot>> vehicleHistory = new HashMap<>();

    public ParkingLot(List<Lot> lots) {
        this.lots = lots;
    }

    public void addNewLot(Lot lot) {
        lots.add(lot);
    }

    public boolean parkVehicle(Vehicle vehicle) {
        if(vehicleLot.get(vehicle.id)!=null){
            System.out.println("Already Park");
            return false;
        }
        Lot lot = checkFreeLot(vehicle);
        if (lot != null) {
            Spot spot = lot.isSpotFree(vehicle);
            lot.parkToSpot(vehicle, spot);
            vehicleLot.put(vehicle.id, lot);
            List<Spot> spots = vehicleHistory.getOrDefault(vehicle.id, new ArrayList<>());
            spots.add(spot);
            vehicleHistory.put(vehicle.id, spots);
            return true;
        } else {
            System.out.println("No Space");
        }
        return false;
    }

    public Lot checkFreeLot(Vehicle vehicle) {
        for (Lot lot : lots) {
            Spot spot = lot.isSpotFree(vehicle);
            if (spot != null) {
                return lot;
            }
        }
        return null;
    }

    public boolean unParkVehicle(Vehicle vehicle) {
        Lot lot = vehicleLot.get(vehicle.id);
        if (lot != null) {
            lot.upParkToSpot(vehicle);
            List<Spot> spots = vehicleHistory.get(vehicle.id);
            Spot spotLast = spots.get(spots.size() - 1);
            double amount = lot.getAmountToBePaid(vehicle);
            spotLast.setAmountPaid(amount);
            vehicleHistory.put(vehicle.id, spots);
            return true;
        } else {
            System.out.println("No Vehicle Present");
        }
        return false;
    }

    public List<Spot> getVehicleHistory(int vehicleId) {
        if (vehicleHistory.get(vehicleId) != null) {
            return vehicleHistory.get(vehicleId);
        } else {
            System.out.println("No History So Far");
        }
        return null;
    }

}

package practice_system_design.machine_coding.parkingLotApp;


import java.util.HashMap;

public class Lot {

    boolean[][] spot;
    HashMap<String, Integer> vehicleMap = new HashMap<>();
    HashMap<Integer, Spot> vehicleSpotMap = new HashMap<>();

    public Lot(int row, int size) {
        this.spot = new boolean[row][size];
        vehicleMap.put(VehicleType.SMALL.toString(), 0);
        vehicleMap.put(VehicleType.MEDIUM.toString(), 1);
        vehicleMap.put(VehicleType.LARGE.toString(), 2);
    }

    public Spot isSpotFree(Vehicle vehicle) {
        int no = vehicleMap.get(vehicle.vehicleType.toString());
        return vehicle.isSpotFree(no, spot);
    }

    public void parkToSpot(Vehicle vehicle, Spot spot_) {
        int no = vehicleMap.get(vehicle.vehicleType.toString());
        for (int i = spot_.start; i <= spot_.end; i++) {
            spot[no][i] = true;
        }
        vehicleSpotMap.put(vehicle.id, spot_);
    }


    public void upParkToSpot(Vehicle vehicle) {
        if (vehicleSpotMap.get(vehicle.id) == null) {
            return;
        }
        int no = vehicleMap.get(vehicle.vehicleType.toString());
        Spot spot_ = vehicleSpotMap.get(vehicle.id);

        for (int i = spot_.start; i <= spot_.end; i++) {
            spot[no][i] = false;
        }
    }

    double getAmountToBePaid(Vehicle vehicle) {
        Spot spot_ = vehicleSpotMap.get(vehicle.id);
        spot_.setExitTime();
        int duration = spot_.getTotalTimeInMin();
        int rate = getPrice(vehicle);

        double totalAmount = rate * duration;
        vehicleSpotMap.remove(vehicle.id);
        return totalAmount;
    }

    int getPrice(Vehicle vehicle) {
        switch (vehicle.vehicleType) {
            case LARGE:
                return Rate.LARGE.val;
            case MEDIUM:
                return Rate.MEDIUM.val;
            case SMALL:
                return Rate.SMALL.val;
        }
        return Rate.SMALL.val;
    }

}

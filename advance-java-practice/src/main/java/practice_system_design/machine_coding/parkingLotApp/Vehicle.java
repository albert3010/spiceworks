package practice_system_design.machine_coding.parkingLotApp;

public abstract class Vehicle {
    VehicleType vehicleType;
    int id;

    public Vehicle(VehicleType vehicleType, String name) {
        this.vehicleType = vehicleType;
        this.id = Constants.getCarId();
    }
    public void parkVehicle(){

    }
    public Spot isSpotFree(int no, boolean[][] spot) {
        int size = vehicleType.val;
        for (int i = 0; i < 100; i++) {
            boolean flag = true;
            for (int j = i; j < size; j++) {
                if (spot[no][j]) {
                    flag = false;
                }
            }
            if (flag) {
                return new Spot(i, i + size);
            }
        }
        return null;
    }
//    boolean checkForSmall(){
//
//    }
//    boolean checkForMedium(){
//
//    }
//    boolean checkForLarge(){
//
//    }


}

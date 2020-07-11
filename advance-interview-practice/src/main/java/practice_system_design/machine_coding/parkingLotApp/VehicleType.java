package practice_system_design.machine_coding.parkingLotApp;

public enum VehicleType {
    LARGE(40),
    MEDIUM(20),
    SMALL(10);
    public int val;

    VehicleType(int val) {
        this.val = val;
    }
}

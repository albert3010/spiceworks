package practice_system_design.machine_coding.parkingLotApp;

public enum Rate {
    LARGE(40),
    MEDIUM(20),
    SMALL(10);
    int val;

    Rate(int val) {
        this.val = val;
    }
}

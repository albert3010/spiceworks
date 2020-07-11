package practice_system_design.machine_coding.parkingLotApp;

import java.util.concurrent.atomic.AtomicInteger;

public class Constants {

    private static final AtomicInteger carId = new AtomicInteger(0);


    public static Integer getCarId() {
        return carId.incrementAndGet();
    }

}

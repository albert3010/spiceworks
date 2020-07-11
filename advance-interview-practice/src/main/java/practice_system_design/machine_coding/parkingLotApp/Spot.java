package practice_system_design.machine_coding.parkingLotApp;

import org.joda.time.DateTime;
import org.joda.time.Minutes;

public class Spot {
    int start;
    int end;
    DateTime entryTime;
    DateTime exitTime;
    double amountPaid;

    public Spot(int start, int end) {
        this.start = start;
        this.end = end;
        this.entryTime = new DateTime();
    }

    public void setExitTime() {
        this.exitTime = new DateTime().plusMinutes(2);
    }

    public int getTotalTimeInMin() {
        return Minutes.minutesBetween( entryTime, exitTime).getMinutes();
    }

    public void setAmountPaid(double amount) {
        this.amountPaid = amount;
    }

    @Override
    public String toString() {
        return "Spot{" +
                "start=" + start +
                ", end=" + end +
                ", entryTime=" + entryTime +
                ", exitTime=" + exitTime +
                ", amountPaid=" + amountPaid +
                '}';
    }
}

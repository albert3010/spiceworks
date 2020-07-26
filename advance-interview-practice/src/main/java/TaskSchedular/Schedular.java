package TaskSchedular;

import org.joda.time.DateTime;

public interface Schedular {

    public void schedule(Task task, DateTime time);
//    public void scheduleAtFixedInterval(Task task, int interval, IntervalType type);
}

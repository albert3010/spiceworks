package TaskSchedular;

import org.joda.time.DateTime;

public class Task implements Runnable {
    int id;
    String name;
    DateTime scheduleTime;
    int interval;
    DateTime lastExecuted;

    public Task(String name) {
        this.id = Constants.getTaskId();
        this.name = name;
    }


    public void setInterval(int interval) {

        this.interval = interval;
    }

    public void setScheduleTime(DateTime scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public void setLastExecuted(DateTime lastExecuted) {
        this.lastExecuted = lastExecuted;
    }

    @Override
    public void run() {
        System.out.println("Task Running " + id);
        try {
            Thread.sleep(1);
            System.out.println("Task Finished " + id);
            if (interval > 0) {
                NotifyService.addToIntervalTask(id);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

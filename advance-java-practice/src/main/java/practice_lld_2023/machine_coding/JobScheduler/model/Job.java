package practice_lld_2023.machine_coding.JobScheduler.model;

import lombok.Getter;
import practice_lld_2023.machine_coding.JobScheduler.TaskType;
import practice_lld_2023.machine_coding.JobScheduler.service.SchedulerService;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Getter
public class Job implements Runnable, Comparable<Job> {

    private int jobId;
    private Runnable task;
    private Date startTime;
    private long reschedulePeriod;
    private TaskType taskType;
    private TimeUnit timeUnit;

    public Job(int jobId, Runnable task, Date startTime, TimeUnit timeUnit) {
        this(jobId, task, startTime, -1, TaskType.ONCE, timeUnit);
    }

    public Job(int jobId, Runnable task, Date startTime, long reschedulePeriod, TaskType taskType, TimeUnit timeUnit) {
        this.jobId = jobId;
        this.task = task;
        this.startTime = startTime;
        this.reschedulePeriod = reschedulePeriod;
        this.taskType = taskType;
        this.timeUnit = timeUnit;
    }

    @Override
    public void run() {

        if (taskType.equals(TaskType.FIXED_RATE)) {
            SchedulerService.getInstance().scheduleTaskFixedRate(task, reschedulePeriod, reschedulePeriod, timeUnit);
        }
        System.out.println("Running task id : " + jobId);
        task.run();
        System.out.println("Competed task id : " + jobId);
        if (taskType.equals(TaskType.FIXED_DELAY)) {
            SchedulerService.getInstance().scheduleTaskFixedDelay(task, reschedulePeriod, reschedulePeriod, timeUnit);
        }
    }

    @Override
    public int compareTo(Job job) {
        return (int) (this.startTime.getTime() - job.startTime.getTime());
    }
}

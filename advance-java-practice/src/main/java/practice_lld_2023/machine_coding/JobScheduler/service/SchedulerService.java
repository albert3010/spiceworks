package practice_lld_2023.machine_coding.JobScheduler.service;

import practice_lld_2023.machine_coding.JobScheduler.TaskType;
import practice_lld_2023.machine_coding.JobScheduler.helper.JobExecutor;
import practice_lld_2023.machine_coding.JobScheduler.model.Job;

import java.util.Calendar;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SchedulerService implements IScheduler {

    private static final SchedulerService INSTANCE = new SchedulerService(Runtime.getRuntime().availableProcessors() - 1);
    private final PriorityQueue<Job> priorityQueue;
    private final ReentrantLock lock;
    private final Condition entryAdded;
    private final AtomicInteger jobId;

    public SchedulerService(int threadCount) {
        this.priorityQueue = new PriorityQueue<>();
        this.lock = new ReentrantLock();
        this.entryAdded = this.lock.newCondition();
        this.jobId = new AtomicInteger(1);

        Thread executor = new Thread(new JobExecutor(priorityQueue, lock, entryAdded, threadCount));
        executor.start();
    }

    public static SchedulerService getInstance() {
        return INSTANCE;
    }

    private void addToQueue(Job job) {
        lock.lock();
        try {
            priorityQueue.add(job);
            entryAdded.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void schedule(Runnable task, long delay, TimeUnit timeUnit) {

        Date startTime = new Date(Calendar.getInstance().getTime().getTime() + timeUnit.toMillis(delay));
        addToQueue(new Job(jobId.getAndIncrement(), task, startTime, timeUnit));

    }

    @Override
    public void scheduleTaskFixedRate(Runnable task, long delay, long reschedulePeriod, TimeUnit timeUnit) {
        Date startTime = new Date(Calendar.getInstance().getTime().getTime() + timeUnit.toMillis(delay));
        addToQueue(new Job(jobId.getAndIncrement(), task, startTime, reschedulePeriod, TaskType.FIXED_RATE, timeUnit));
    }

    @Override
    public void scheduleTaskFixedDelay(Runnable task, long delay, long reschedulePeriod, TimeUnit timeUnit) {
        Date startTime = new Date(Calendar.getInstance().getTime().getTime() + timeUnit.toMillis(delay));
        addToQueue(new Job(jobId.getAndIncrement(), task, startTime, reschedulePeriod, TaskType.FIXED_DELAY, timeUnit));
    }
}

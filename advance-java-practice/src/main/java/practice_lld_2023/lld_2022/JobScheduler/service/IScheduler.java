package practice_lld_2023.lld_2022.JobScheduler.service;

import java.util.concurrent.TimeUnit;

public interface IScheduler {

    void schedule(Runnable task, long delay, TimeUnit timeUnit);

    void scheduleTaskFixedRate(Runnable task, long delay, long reschedulePeriod, TimeUnit timeUnit);

    void scheduleTaskFixedDelay(Runnable task, long delay, long reschedulePeriod, TimeUnit timeUnit);
}

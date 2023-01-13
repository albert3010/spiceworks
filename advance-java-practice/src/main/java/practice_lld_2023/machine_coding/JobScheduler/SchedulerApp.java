package practice_lld_2023.machine_coding.JobScheduler;

import practice_lld_2023.machine_coding.JobScheduler.service.SchedulerService;

import java.util.concurrent.TimeUnit;

public class SchedulerApp {

    public static void main(String[] args) {

        Runnable task1 = () -> {
            System.out.println("Completed task 1:once");
        };
        Runnable task2 = () -> {

            try {
                Thread.sleep(14000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("running: fixed rate");
        };
        Runnable task3 = () -> {

            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("running: fixed delay");
        };

        SchedulerService schedulerService = SchedulerService.getInstance();

        schedulerService.schedule(task1, 5, TimeUnit.SECONDS);
        schedulerService.scheduleTaskFixedRate(task2, 7, 4, TimeUnit.SECONDS);
        schedulerService.scheduleTaskFixedDelay(task3, 5, 2, TimeUnit.SECONDS);

    }
}

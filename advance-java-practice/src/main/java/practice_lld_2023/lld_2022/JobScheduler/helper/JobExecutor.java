package practice_lld_2023.lld_2022.JobScheduler.helper;

import practice_lld_2023.lld_2022.JobScheduler.model.Job;

import java.util.Calendar;
import java.util.PriorityQueue;
import java.util.concurrent.Executor;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class JobExecutor implements Runnable {

    private final PriorityQueue<Job> priorityQueue;
    private final ReentrantLock lock;
    private final Condition entryAdded;
    private Executor jobExecutor;

    public JobExecutor(PriorityQueue<Job> priorityQueue, ReentrantLock lock, Condition entryAdded, int threadCount) {
        this.priorityQueue = priorityQueue;
        this.lock = lock;
        this.entryAdded = entryAdded;
        this.jobExecutor = Executors.newFixedThreadPool(threadCount);
    }


    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                if (!priorityQueue.isEmpty()) {
                    Job job = priorityQueue.peek();
                    Long currentTime = Calendar.getInstance().getTime().getTime();
                    if (currentTime.compareTo(job.getStartTime().getTime()) >= 0) {
                        priorityQueue.poll();
                        jobExecutor.execute(job);
                    }
                } else {
                    entryAdded.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}

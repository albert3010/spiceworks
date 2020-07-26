package TaskSchedular;

import leetcode.contests.ContestAA.Contests1;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskSchedular implements Schedular {

    static PriorityQueue<Task> tasks;
    static HashMap<Integer, Task> intervalTasks = new HashMap<Integer, Task>();

    ExecutorService executorService;

    public TaskSchedular() {
        this.tasks = new PriorityQueue<>();
        this.executorService = Executors.newFixedThreadPool(10);
    }

    public static PriorityQueue<Task> getTasks() {
        return tasks;
    }

    public void schedule(Task task, DateTime time) {
        task.setScheduleTime(time);
        tasks.add(task);
        submitTask();
    }

    static HashMap<Integer, Task> getIntervalTask(){
        return  intervalTasks;
    }

    void submitTask(){
        while (!tasks.isEmpty()){
            Task task = tasks.poll();
            executorService.submit(task);
        }
    }

}

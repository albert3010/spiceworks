package TaskSchedular;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class NotifyService {

    static Queue<Task> IntervalTasks = new LinkedList<Task>();

    static void addToIntervalTask(int taskId) {
        HashMap<Integer, Task> integerTaskHashMap = TaskSchedular.getIntervalTask();
        Task task = integerTaskHashMap.get(taskId);
//
        Task newTask = new Task(task.name);
//        ctime + interval
        TaskSchedular.getTasks().add(newTask);
        IntervalTasks.add(task);
    }

}

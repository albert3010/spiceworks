package TaskSchedular;

import org.joda.time.DateTime;

public class App {

    public static void main(String[] args) {
        Task task1 = new Task("task1");
        Task task2 = new Task("task1");

        TaskSchedular taskSchedular = new TaskSchedular();

        taskSchedular.schedule(task1, new DateTime().plusSeconds(3));
        taskSchedular.schedule(task2, new DateTime().plusSeconds(5));

//        taskSchedular.submitTask();

    }



}

package practice.concurrency.pool;



import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by omprakash.yadav on 16/02/16.
 */
public class SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);//newFixedThreadPool(5);

        //running 10 jobs
        for (int i = 0; i < 10; i++) {
            Runnable worker = new WorkerThread("Worker no = "+i);
           // worker.run();
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }

}

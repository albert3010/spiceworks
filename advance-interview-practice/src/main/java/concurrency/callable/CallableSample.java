package concurrency.callable;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by omprakash.yadav on 17/02/16.
 */

public class CallableSample {

    public static void main(String[] args) throws Exception{

        ExecutorService service = Executors.newFixedThreadPool(4);

        Set<Future<Integer>> set= new HashSet<Future<Integer>>() ;

        Callable<Integer> callable = new WordLenghtCallable("google..");
        Future<Integer> future = service.submit(callable);
        set.add(future);
        set.add(future);
        int sum = future.get();
        System.out.println("sum = "+sum);
    }

}

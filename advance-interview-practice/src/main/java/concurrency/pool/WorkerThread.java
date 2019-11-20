package concurrency.pool;

/**
 * Created by omprakash.yadav on 16/02/16.
 */
public class WorkerThread implements Runnable {

    private String command;

    public  WorkerThread(String s){
        this.command = s ;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" Start. Command = "+command);
        processCommand();
        System.out.println(Thread.currentThread().getName()+" End.");
    }

    private void processCommand(){
        try {
            Thread.sleep(4000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return this.command;
    }
}

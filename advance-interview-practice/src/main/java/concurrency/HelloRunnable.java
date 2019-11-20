package concurrency;

/**
 * Created by omprakash.yadav on 16/02/16.
 */
public class HelloRunnable implements MyThread {

    public void run(){
        System.out.println("Hello from thread..");
    }
    public void myRun(){
        System.out.println("hello my run");
    }
    public void myRun1(){
        System.out.println("hello my run");
    }

    public static void main(String[] args) {
        HelloRunnable helloRunnable = new HelloRunnable();

        (new Thread(helloRunnable)).start();

        helloRunnable.myRun1();

    }

}

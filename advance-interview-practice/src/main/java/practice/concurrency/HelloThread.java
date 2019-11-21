package practice.concurrency;


import java.lang.Thread;

/**
 * Created by omprakash.yadav on 16/02/16.
 */
public class HelloThread extends Thread {

    public void run(){
        System.out.println("Hello from thread..");
    }

    public static void main(String[] args) {
        (new Thread(new HelloThread())).start();
    }
}

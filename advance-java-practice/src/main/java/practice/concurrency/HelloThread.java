package practice.concurrency;


import lombok.SneakyThrows;

import java.lang.Thread;

/**
 * Created by omprakash.yadav on 16/02/16.
 */
public class HelloThread implements Runnable {

    @SneakyThrows
    public void run() {
        int c = 0;
        while (true) {
            System.out.println("Hello from thread.. test 1" + c++);
            Thread.sleep(4000);
        }

    }

    public static void main(String[] args) {
        new HelloThread().run();
    }
}

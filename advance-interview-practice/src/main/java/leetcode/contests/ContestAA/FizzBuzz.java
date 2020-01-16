package leetcode.contests.ContestAA;

import org.junit.Test;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class FizzBuzz {
    private int n;
    private Semaphore num = new Semaphore(1);
    private Semaphore fizz = new Semaphore(0);
    private Semaphore buzz = new Semaphore(0);
    private Semaphore fizzbuzz = new Semaphore(0);

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        fizz.acquire();
        printFizz.run();
        num.release();
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        buzz.release();
        printBuzz.run();
        num.release();

    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        fizzbuzz.acquire();
        printFizzBuzz.run();
        num.release();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {

        for (int i = 1; i <=n; i++) {
            num.acquire();

            if(i%3==0 && i%5==0){
                fizzbuzz.release();
            }
            else if(i%3==0){
                fizz.release();
            }
            else if(i%5==0){
                buzz.release();
            } else {
                printNumber.accept(i);
                num.release();
            }
        }
    }
}

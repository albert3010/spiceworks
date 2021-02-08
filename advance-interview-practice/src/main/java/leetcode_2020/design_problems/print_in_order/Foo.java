package leetcode_2020.design_problems.print_in_order;

class Foo {

    private int turn = 1;
    public Foo() {

    }

    public synchronized void first(Runnable printFirst) throws InterruptedException {
        while(turn != 1) wait();

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        turn = 2;
        notifyAll();
    }

    public synchronized void second(Runnable printSecond) throws InterruptedException {
        while(turn != 2) wait();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        turn = 3;
        notifyAll();
    }

    public synchronized void third(Runnable printThird) throws InterruptedException {
        while(turn != 3) wait();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
        turn = 1;
        notifyAll();
    }
}

package leetcode_2020.contests.ContetsAE;

public class Task implements Runnable{

    String line;

    Task(String line){
        this.line = line;
    }

    @Override
    public void run() {
        System.out.println(line + "\n");
    }
}

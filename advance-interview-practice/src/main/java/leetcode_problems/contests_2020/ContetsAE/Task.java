package leetcode_problems.contests_2020.ContetsAE;

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

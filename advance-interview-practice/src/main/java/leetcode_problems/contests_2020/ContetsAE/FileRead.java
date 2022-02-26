package leetcode_problems.contests_2020.ContetsAE;

import org.joda.time.DateTime;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FileRead {

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);

        File file = new File("/Users/oyadav/codebase/personal/spiceworks/advance-interview-practice/src/main/java/leetcode/contests/ContetsAE/Contests10.java");    //creates a new file instance

        FileReader fr = new FileReader(file);   //reads the file
        BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
        StringBuffer sb = new StringBuffer();    //constructs a string buffer with no characters
        String line;
        System.out.println();
        DateTime time_start = new DateTime();
        System.out.println("Start time " + time_start);
        while ((line = br.readLine()) != null) {
//            sb.append(line);
            Task taskRead = new Task(line);
            executorService.submit(taskRead);
//            sb.append("\n");     //line feed
        }

        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            DateTime time_end = new DateTime();
            System.out.println("Start end " + time_end);

            long diffTime = time_end.getMillis() - time_start.getMillis();
            System.out.println("Diff time -- " + diffTime);
            fr.close();
        } catch (InterruptedException e) {

        }
        //closes the stream and release the resources
//        System.out.println("Contents of File: ");
//        System.out.println(sb.toString());

    }


}

package concurrency;

/**
 * Created by omprakash.yadav on 16/02/16.
 */
public class SleepMsg {
    public static void main(String[] args) throws InterruptedException {

        String importantInfo[]= { "sample1", "sample2", "sample3", "sample4" };

        for (int i = 0; i < importantInfo.length; i++) {

            try {
                //sleep 4 sec
                Thread.sleep(4000);
            }catch (Exception e){
                System.out.println("error");
                return;
            }
            System.out.println( importantInfo[i]);
        }
    }
}

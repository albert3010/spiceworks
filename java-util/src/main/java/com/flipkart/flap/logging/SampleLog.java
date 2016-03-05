package com.flipkart.flap.logging;
import emoji.Emoji;
/**
 * Created by omprakash.yadav on 21/02/16.
 */
public class SampleLog {


    public static void main(String[] args) throws Exception{
        Logger logger = new Logger(SampleLog.class);

        int i =0;
        int j=5;
        try{
            System.out.println("this is sample");
            String s = logger.info("Starting server on localhost with port 80");
            System.out.println(s);
            new Exception();

        }catch (Exception e){

            logger.error("Class not initialised!!!");
        }

    }
}

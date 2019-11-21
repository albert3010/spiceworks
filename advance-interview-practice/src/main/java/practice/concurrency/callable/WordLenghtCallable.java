package practice.concurrency.callable;

import java.util.concurrent.Callable;

/**
 * Created by omprakash.yadav on 17/02/16.
 */
public  class WordLenghtCallable implements Callable {
    private String word;

    public  WordLenghtCallable(String s){
        this.word = s;
    }
    public Integer call(){
        return word.length();
    }

}
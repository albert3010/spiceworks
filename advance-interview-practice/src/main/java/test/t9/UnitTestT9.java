package test.t9;

import java.util.Arrays;
import java.util.List;

public class UnitTestT9 {

    public static void main(String[] args) {
        T9Dictionary t9Dictionary = new T9Dictionary();
        List<String> words = Arrays.asList("car","bar", "adp", "bad" , "ab", "depart", "golf","gold","gnkf", "dag","bbb", "ccc");

        t9Dictionary.buildTrie(words);
//        List<String> wordList  = t9Dictionary.getListOfWords(4653);
        List<String> wordList  = t9Dictionary.getListOfWords(227);
//        List<String> wordList  = t9Dictionary.getListOfWords(222);
        System.out.println(wordList);

    }
}

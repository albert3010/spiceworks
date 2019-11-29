package leetcode.contests;

import org.junit.Test;

import java.util.*;

public class Contests10 {


    @Test
    public void ContestsSolution() {

        String[] words = {"leetcode"};
        char[] letters = {'l', 'e', 't', 'c', 'o', 'd'};
        int[] score = {0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0};

//        synonyms.add(Lists.newArrayList("happy", "joy"));
//        int a[][] = {{0, 0, 1, 1, 0, 1, 0, 0, 1, 0}, {1, 1, 0, 1, 1, 0, 1, 1, 1, 0}, {1, 0, 1, 1, 1, 0, 0, 1, 1, 0}, {0, 1, 1, 0, 0, 0, 0, 1, 0, 1}, {0, 0, 0, 0, 0, 0, 1, 1, 1, 0}, {0, 1, 0, 1, 0, 1, 0, 1, 1, 1}, {1, 0, 1, 0, 1, 1, 0, 0, 0, 1}, {1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 1, 0, 1, 0, 1}, {1, 1, 1, 0, 1, 1, 0, 1, 1, 0}};
//        int[] nums = {3, 6, 5, 1, 8};
//["leetcode"]
//["l","e","t","c","o","d"]
//[0,0,1,1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0]
        System.out.println(maxScoreWords(words, letters, score));

    }


    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        HashMap<Integer, Integer> lmap = new HashMap<>();
        int n = letters.length;

        for (int i = 0; i < n; i++) {
            int key = letters[i] - 'a';
            lmap.put(key, lmap.getOrDefault(key, 0) + 1);
        }
        int result = 0;

        maxScoreWordsBacktracking(words, 0, lmap, score, result);

        return lmap.get(26);
    }

    public void maxScoreWordsBacktracking(String[] words, int i, HashMap<Integer, Integer> lmap, int[] score, int result) {
        if (i == words.length) {
            lmap.put(26, Math.max(lmap.getOrDefault(26, 0), result));
            return;
        }

        if (checkWordInLmap(words[i], lmap)) {
            updateLmap(words[i], lmap);
            maxScoreWordsBacktracking(words, i + 1, lmap, score, result + getScore(words[i], score));
            revertLmap(words[i], lmap);
        }
        maxScoreWordsBacktracking(words, i + 1, lmap, score, result);
    }

    public void updateLmap(String word, HashMap<Integer, Integer> lmap) {
        for (Character c : word.toCharArray()) {
            int key = c - 'a';
            lmap.put(key, lmap.get(key) - 1);
        }
    }

    public void revertLmap(String word, HashMap<Integer, Integer> lmap) {
        for (Character c : word.toCharArray()) {
            int key = c - 'a';
            lmap.put(key, lmap.get(key) + 1);
        }
    }

    public boolean checkWordInLmap(String word, HashMap<Integer, Integer> lmap) {
        HashMap<Integer, Integer> mcopy = new HashMap<>();

        for (Character c : word.toCharArray()) {
            int k = c - 'a';
            mcopy.put(k, mcopy.getOrDefault(k, 0) + 1);
        }
        for (Character c : word.toCharArray()) {
            int k = c - 'a';
            if (lmap.get(k)== null || mcopy.get(k) > lmap.get(k)) {
                return false;
            }
        }

        return true;
    }

    public int getScore(String word, int[] score) {
        int sum = 0;
        for (Character c : word.toCharArray()) {
            sum += score[c - 'a'];
        }
        return sum;
    }

}

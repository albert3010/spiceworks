package practice.generic;

/**
 * Created by OMPRAKASH on 9/25/2016.
 */
public class TrieNode {

    TrieNode[] arr;
    boolean isEnd;
    int frq;
    // Initialize your data structure here.
    public TrieNode() {
        this.arr = new TrieNode[26];
        this.frq=0;
    }

}

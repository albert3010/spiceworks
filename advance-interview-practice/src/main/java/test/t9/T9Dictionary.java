package test.t9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Node {
    char c;
    Node[] child;
    boolean isWorld = false;
    List<String> words;

    public Node(char c) {
        this.c = c;
        this.child = new Node[10];
        this.words = new ArrayList<>();
    }
}

public class T9Dictionary {

    private Node root;
    private String[] codes;
    private Map<Character, Integer> map;

    public T9Dictionary() {
        this.root = new Node('*');
        this.codes = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        this.map = new HashMap<>();
        updateKeyMap();
    }

    void updateKeyMap() {
        int l = codes.length;
        for (int i = 0; i < l; i++) {
            int t = codes[i].length();
            for (int j = 0; j < t; j++) {
                map.put(codes[i].charAt(j), i);
            }
        }
    }

    void buildTrie(List<String> words) {

        for (String word : words) {
            String number = getNum(word);
            updateTrie(root, number,word, 0);
        }
    }

    void updateTrie(Node node, String number, String word, int i) {
        if (i == word.length()) {
            node.isWorld = true;
            node.words.add(word);
            return;
        }
        int j = number.charAt(i) - '0';
        Node nd = node.child[j];
        if (nd == null) {
            node.child[j] = new Node(number.charAt(i));
        }
        updateTrie(node.child[j], number, word,i + 1);
    }

    String getNum(String word) {
        StringBuilder sb = new StringBuilder();
        for (char c : word.toCharArray()) {
            sb.append(map.get(c));
        }
        return sb.toString();
    }

    List<String> getListOfWords(int num) {
        String number = String.valueOf(num);
        return searchAndGetInTrie(root, number, 0);
    }

    private List<String> searchAndGetInTrie(Node node, String number, int i) {
        if (i == number.length()) {
            return node.words;
        }
        int j = number.charAt(i) - '0';
        Node nd = node.child[j];
        if (nd == null) {
            return new ArrayList<>();
        }
        return searchAndGetInTrie(nd, number, i + 1);
    }


}


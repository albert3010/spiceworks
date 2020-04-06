package leetcode.contests.ContetAD;

import org.junit.Test;

import java.util.*;

public class Contests15 {

    @Test
    public void ContestsSolution() {
        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] words = {"oath", "pea", "eat", "rain"};

        System.out.println(findWords(board, words));
    }

    class TrieNode {
        Character c;
        boolean isWord;
        TrieNode child[];

        TrieNode() {
            child = new TrieNode[26];
            isWord = false;
        }

        TrieNode(Character c) {
            this.c = c;
            child = new TrieNode[26];
            isWord = false;
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        int n = board.length;
        int m = board[0].length;
        TrieNode root = new TrieNode();
        int l = words.length;
        for (int i = 0; i < l; i++) {
            addWorldToTrie(root, words[i], 0);
        }
        Set<String> set = new LinkedHashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                boolean v[][] = new boolean[n][m];
                addWorldToTrieDFS("", root, board, i, j, v, n, m, set);
            }
        }
        List<String> ans = new ArrayList<>();

        for (int i = 0; i < l; i++) {
            if (set.contains(words[i])) {
                ans.add(words[i]);
            }
        }
        return ans;
    }

    public boolean searchWordInTrie(String s, TrieNode root, int index) {
        if (s.length() == index) return true;
        char c = s.charAt(index++);
        if (root.child[c - 'a'] == null) {
            return false;
        }
        return searchWordInTrie(s, root.child[c - 'a'], index);
    }

    public boolean isWordInTrie(String s, TrieNode root, int index) {
        if (s.length() == index) return false;

        char c = s.charAt(index);
        if (root.child[c - 'a'] == null) {
            return false;
        }
        if (index + 1 == s.length()) {
            if (root.child[c - 'a'].isWord)
                return true;
        }

        return isWordInTrie(s, root.child[c - 'a'], index + 1);
    }

    public void addWorldToTrieDFS(String s, TrieNode root, char[][] board, int i, int j, boolean v[][], int n, int m, Set<String> set) {
        if (i < 0 || j < 0 || i >= n || j >= m || v[i][j] || !searchWordInTrie(s, root, 0)) {
            if (isWordInTrie(s, root, 0)) {
                set.add(s);
            }
            return;
        }
        if (isWordInTrie(s, root, 0)) {
            set.add(s);
        }
        int a[] = {1, 0, -1, 0, 1};
        v[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int ii = i + a[k];
            int jj = j + a[k + 1];
            addWorldToTrieDFS(s + board[i][j], root, board, ii, jj, v, n, m, set);
        }
        v[i][j] = false;
    }

    public void addWorldToTrie(TrieNode root, String s, int index) {
        if (index == s.length()) return;
        char c = s.charAt(index);
        if (root.child[c - 'a'] == null) {
            root.child[c - 'a'] = new TrieNode(c);
        }
        if (index + 1 == s.length()) {
            root.child[c - 'a'].isWord = true;
        }
        addWorldToTrie(root.child[c - 'a'], s, index + 1);
    }

}

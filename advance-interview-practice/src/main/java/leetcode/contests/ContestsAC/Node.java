package leetcode.contests.ContestsAC;

import leetcode.contests.ContetAD.Contests15;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class Node {


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

    @Test
    public void test(){
        MagicDictionary md = new MagicDictionary();
        String[] ws = {"leetcode","hello"};
//        String[] ws = {"ab"};
//        ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
        md.buildDict(ws);
        System.out.println(md.search("leetcode"));
    }

    class MagicDictionary {

        TrieNode root;

        public MagicDictionary() {
            root = new TrieNode();
        }


        public void buildDict(String[] dict) {
            int n = dict.length;
            for (int i = 0; i < n; i++) {
                addWorldToTrie(root, dict[i], 0);
            }
        }


        public boolean search(String word) {
            return isWordInTrie(word, root, 0, false);
        }

        public boolean isWordInTrie(String s, TrieNode root, int index, boolean change) {
            if (root == null) {
                return false;
            }
            if(s.length() == index){
                return change && root.isWord;
            }
            char c = s.charAt(index);



            boolean check = false;
            if (change) {
                if(root.child[c - 'a']==null || c!= root.child[c - 'a'].c)
                    return false;
                return isWordInTrie(s, root.child[c - 'a'], index + 1, true);
            }

            for (int i = 0; i < 26; i++) {
                int k = c - 'a';
                if (k != i ) {
                    check = check || isWordInTrie(s, root.child[i], index + 1, true);
                } else {
                    check = check || isWordInTrie(s, root.child[i], index + 1, false);
                }
            }
            return check;
        }

        public void addWorldToTrie(TrieNode root, String s, int index) {
            if (index == s.length()) return;
            char c = s.charAt(index);
            if (root.child[c - 'a'] == null) {
                root.child[c - 'a'] = new TrieNode(c);
            }
            if (index == s.length()-1) {
                root.child[c - 'a'].isWord = true;
            }
            addWorldToTrie(root.child[c - 'a'], s, index + 1);
        }
    }
}

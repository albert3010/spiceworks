package practice.generic;

import java.util.Stack;

/**
 * Created by OMPRAKASH on 9/25/2016.
 */
public class Trie {

    private TrieNode root;
   static Stack<Character> s = new Stack<>();
    public Trie() {
        root = new TrieNode();
    }

    public static void main(String[] args) {

        Trie trie = new Trie();

        trie.insert("cat");
        trie.insert("cattle");
        trie.insert("sole");
        trie.insert("solve");
        trie.insert("sell");

        trie.insert("hello");
        trie.insert("hack");
        trie.insert("cat");

        System.out.println(trie.getFrequency("cat"));

   if(trie.startsWith("cp")){
       System.out.println("found");
   }else{
       System.out.println("not found");
   }

   trie.autoSuggest("s");
       // trie.printTrie(trie.root, s);
    }
    private void autoSuggest(String prifix){
        TrieNode t = searchNode(prifix);
        System.out.println(prifix+"-->");
        if(t==null){
            return;

        }else{

        }
     printTrie(t, s);
    }

    private int getFrequency(String word){

        TrieNode t = searchNode(word);

        if(t==null){
            return 0;

        }else{
            if(t.isEnd){
                return t.frq;
            }
        }
        return 0;
    }
    private void printTrie(TrieNode t, Stack<Character> s) {

        for (int i = 0; i < 26; i++) {
            if (t.arr[i] != null) {
                // char[] c = Character.toChars('a'+i);

                char ca = (char) ((int) 'a' + i);
                Character c = ca;
                s.push(c);
                if (t.arr[i].isEnd) {
                    int n = s.size();

                    for (int j = 0; j < n; j++) {
                        System.out.print(s.get(j));
                    }
                    System.out.println();

                }
                printTrie(t.arr[i], s);
                s.pop();
            }

        }
    }
    private void insert(String word) {

        TrieNode t = root;

        for (int i = 0; i < word.length(); i++) {

            char c = word.charAt(i);
            int index = c - 'a';

            if (t.arr[index] != null) {
                t = t.arr[index];

            } else {
                TrieNode tmp = new TrieNode();
                t.arr[index] = tmp;
                t = tmp;
            }



        }
        t.isEnd = true;
        t.frq = t.frq+1;
    }

    private boolean search(String word) {
        TrieNode t = searchNode(word);

        if (t == null) {
            return true;
        } else {
            if (t.isEnd)
                return true;
        }
        return false;

    }

    public boolean startsWith(String prefix) {
        TrieNode p = searchNode(prefix);
        if(p==null){
            return false;
        }else{
            return true;
        }
    }


    private TrieNode searchNode(String word){
      TrieNode t = root;

      for (int i = 0; i < word.length(); i++) {

          char c = word.charAt(i);
          int index = c - 'a';

          if (t.arr[index] != null) {
              t = t.arr[index];

          } else {
              return null;
          }
      }
      if (t == root)
          return null;

      return t;
  }

}

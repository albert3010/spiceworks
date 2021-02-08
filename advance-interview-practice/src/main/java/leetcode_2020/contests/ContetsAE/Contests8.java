package leetcode_2020.contests.ContetsAE;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Contests8 {

    @Test
    public void ContestsSolution() {
        List<String> ls = new ArrayList<>();
        ls.add("a1 9 2 3 1");
        ls.add("g1 act car");
        ls.add("zo4 4 7");
        ls.add("ab1 off key dog");
        ls.add("b8 act zoo");
        ls.add("a8 off key dog");
        System.out.println(reorderLines(6, ls));
    }

    class Node implements Comparable<Node> {
        String key, val;

        Node(String k, String v) {
            this.key = k;
            this.val = v;
        }

        @Override
        public int compareTo(Node p) {
            if (this.val.equals(p.val)) {
                return this.key.compareTo(p.key);
            }
            // sorting in increasing order
            return this.val.compareTo(p.val);
        }
    }

    public List<String> reorderLines(int logFilesSize, List<String> logLines) {
        List<Node> wordList = new ArrayList<>();
        List<String> numList = new ArrayList<>();

        IntStream.range(0, logFilesSize).forEach(i -> {
            String[] words = logLines.get(i).split(" ");
            if (words[1].charAt(0) >= '0' && words[1].charAt(0) <= '9') {
                numList.add(logLines.get(i));
            } else {
                int l = words[0].length();
                wordList.add(new Node(words[0], logLines.get(i).substring(l)));
            }
        });
        Collections.sort(wordList);
        int n = wordList.size();
        List<String> ans = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            Node node = wordList.get(i);
            ans.add(node.key + node.val);
        });
        ans.addAll(numList);
        return ans;
    }
}

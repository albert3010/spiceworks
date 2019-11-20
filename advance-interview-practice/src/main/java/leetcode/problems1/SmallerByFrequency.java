package leetcode.problems1;

import com.google.common.collect.Lists;
import org.junit.Test;


public class SmallerByFrequency {
    //        List<List<Integer>> books = Lists.newArrayList();
//        books.add(Lists.newArrayList(1,1));
//        int arr[][] = { {2,7,9},{3,6,1},{7,4,2} };


    private class node {

        node(int max, int sum) {
            this.max = max;
            this.sum = sum;
        }

        int max;
        int sum;
    }

    @Test
    public void numSmallerByFrequencyTest() {
        String queries[] = {"bbb", "cc"};
        String words[] = {"a", "aa", "aaa", "aaaa"};
        System.out.println(numSmallerByFrequency(queries, words));

    }

    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int m = 11;
        int frq[] = new int[m];
        int dp[] = new int[m];

        int n = words.length;

        for (int i = 0; i < n; i++) {
            int f = countFrequencyOfSmallestChar(words[i]);
            frq[f]++;
        }

        dp[0] = frq[0];
        for (int i = 1; i < m; i++) {
            dp[i] = dp[i - 1] + frq[i];
        }

        n = queries.length;
        int result[] = new int[n];
        for (int i = 0; i < n; i++) {
            int f = countFrequencyOfSmallestChar(queries[i]);
            result[i] = dp[m - 1] - dp[f];
        }

        return result;
    }

    private int countFrequencyOfSmallestChar(String word) {

        int l = word.length();
        int f = 0;
        if (l == 0) return f;
        char sm = word.charAt(l - 1);

        while (l > 0) {
            char c = word.charAt(l - 1);

            if (c == sm) {
                f++;
            }
            if (c < sm) {
                f = 1;
                sm = c;
            }
            l--;
        }
        return f;
    }
}
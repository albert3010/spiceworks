package leetcode.contests;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class ContestB13 {

    @Test
    public void ContestsSolution() {

        List<List<String>> synonyms = Lists.newArrayList();
        synonyms.add(Lists.newArrayList("happy", "joy"));
        synonyms.add(Lists.newArrayList("sad", "sorrow"));
        synonyms.add(Lists.newArrayList("joy", "cheerful"));

//        System.out.println(generateSentences(synonyms, "I am happy today but was sad yesterday"));
        System.out.println(numberOfWays(2));
    }

    boolean isBSTHelper(Node node, Integer min, Integer max) {

        if (node == null) {
            return true;
        }
        if (node.data < min || node.data > max) {
            return false;
        }

        boolean leftSubTreeCheck = isBSTHelper(node.left, min, node.data);
        boolean rightSubTreeCheck = isBSTHelper(node.right, node.data, max);

        return leftSubTreeCheck && rightSubTreeCheck;
    }

    int isBST(Node root) {
        boolean b = isBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);

        if (b == true) {
            return 1;
        }
        return 0;
    }

    public int numberOfWays(int num_people) {

        Integer mod = 1000000007;

        long dp[] = new long[num_people + 1];

        if (num_people <= 4) {
            return num_people / 2;
        }
        dp[0] = 0;
        dp[2] = 1;
        dp[4] = 2;

        for (int k = 6; k <= num_people; k += 2) {
            int n = k - 2;
            for (int i = 2, j = n - 2; i <= j; i += 2, j -= 2) {
                if (i != j) {
                    dp[k] += (dp[i] * dp[j] * 2) % mod;
                } else {
                    dp[k] += (dp[i] * dp[j]) % mod;
                }
            }
            dp[k] = (dp[k] + dp[n] * 2) % mod;
        }
        return (int) dp[num_people] % mod;
    }

    public List<String> generateSentences(List<List<String>> synonyms, String text) {

        List<TreeSet<String>> hset = new ArrayList<>();

        int n = synonyms.size();
        String[] texts = text.split(" ");

        for (int i = 0; i < n; i++) {

            int k = hset.size();
            boolean flag = false;
            List<String> curSyn = synonyms.get(i);
            for (int j = k - 1; j >= 0; j--) {

                TreeSet<String> set = hset.get(j);

                if (set.contains(curSyn.get(0)) || set.contains(curSyn.get(1))) {
                    flag = true;
                    set.add(curSyn.get(0));
                    set.add(curSyn.get(1));
                }
            }
            if (!flag) {
                TreeSet<String> set = new TreeSet<>();
                set.add(curSyn.get(0));
                set.add(curSyn.get(1));
                hset.add(set);
            }
        }

        List<String> ans = new ArrayList<>();

        generateSentencesHelper(texts, 0, texts.length, hset, "", ans);

        return ans;
    }

    public void generateSentencesHelper(String[] texts, int pos, int l, List<TreeSet<String>> hset, String s, List<String> ans) {

        if (pos == l) {
            ans.add(s);
            return;
        }
        String cur = texts[pos];
        int k = hset.size();
        boolean flag = true;
        String sp = "";
        if (pos != 0) {
            sp = " ";
        }
        for (int i = 0; i < k; i++) {
            if (hset.get(i).contains(cur)) {
                flag = false;
                for (String st : hset.get(i)) {
                    generateSentencesHelper(texts, pos + 1, l, hset, s + sp + st, ans);
                }
            }
        }
        if (flag) {
            generateSentencesHelper(texts, pos + 1, l, hset, s + sp + texts[pos], ans);
        }
    }

}
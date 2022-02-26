package leetcode_contest_2022.group1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

public class ContestB1 {


    public static void main(String[] args) {

    }










    public void minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int l = nums1.length;
        long sum = 0;

        for (int i = 0; i < l; i++) {
            sum += Math.abs(nums1[i] - nums2[i]);
        }
//        if (sum == 0) return 0;

    }

    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        int l = logs.length;
        int[] ans = new int[k];
        HashMap<Integer, Set<Integer>> map = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();
        HashMap<Integer, Integer> map3 = new HashMap<>();

        for (int i = 0; i < l; i++) {
            Set<Integer> set = map.getOrDefault(logs[i][0], new HashSet<>());
            set.add(logs[i][1]);
            map.put(logs[i][0], set);
        }
        for (Integer key : map.keySet()) {
            Set<Integer> set = map.getOrDefault(key, new HashSet<>());
            map3.put(key, set.size());
        }
        for (Integer key : map.keySet()) {
            int times = map3.getOrDefault(key, 0);
            int userCount = map2.getOrDefault(times, 0);
            map2.put(times, userCount + 1);
        }
        for (int i = 1; i <= k; i++) {
            int times = map2.getOrDefault(i, 0);
            ans[i - 1] = times;
        }
        return ans;
    }

    public String truncateSentence(String s, int k) {
        String[] words = s.split(" ");
        StringBuilder res = new StringBuilder("");
        IntStream.range(0, k).forEach(i -> {
            res.append(words[i]);
            res.append(" ");
        });
        String ss = "";
        if (res.length() > 0)
            ss = res.substring(0, res.length() - 1);
        return ss;
    }


    public int countNicePairs(int[] nums) {
        int mod = 1000000000 + 7;
        int l = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < l; i++) {
            int val = nums[i] - reverse(nums[i]);
            int count = map.getOrDefault(val, 0);
            ans = (ans + count) % mod;
            map.put(val, count + 1);
        }
        return ans;
    }

    static int reverse(int n) {

        int num = 0;
        while (n > 0) {
            int t = n % 10;
            num = num * 10 + t;
            n = n / 10;
        }
        return num;
    }

    public static boolean areSentencesSimilar(String sentence1, String sentence2) {
        if (sentence1.equals(sentence2)) return true;
        String[] sentences = sentence1.split(" ");
        if (sentence1.length() < sentence2.length()) {
            sentences = sentence2.split(" ");
        }
        StringBuilder res = new StringBuilder("");
        int l = sentences.length;
        for (int i = 0; i < l; i++) {
            for (int j = i; j < l; j++) {
                for (int k = 0; k < l; k++) {
                    if (k < i || k > j) {
                        res.append(sentences[k]);
                        res.append(" ");
                    }
                }
                if (res.length() > 0) {
                    String ss = res.substring(0, res.length() - 1);
                    if (ss.equals(sentence1) || ss.equals(sentence2)) {
                        return true;
                    }
                }
                res = new StringBuilder();
            }
        }
        return false;
    }

    public boolean squareIsWhite(String coordinates) {
        int x = (coordinates.charAt(0) - 'a') + 1;
        int y = (coordinates.charAt(1) - 'a');
        System.out.println(x + y);
        if ((x + y) % 2 == 0) {
            return false;
        }
        return true;
    }

}

package leetcode_problems.problems.solutions;


import org.junit.Test;

import java.util.*;

public class Solutions8 {

    @Test
    public void Tests() {
        int a[] = {3, 1, 5, 8};
        int b = "bcd".compareTo("abc"); // b=1
//        System.out.println(maxCoins(a));
        System.out.println(get45Palindrom(4));
    }

    String get45Palindrom(int n) {
        int last = 0;
        int k = 2;

        int l = 1;
        while (k < n) {
            l++;
            last = k;
            k = k + k * 2;
        }
        int rank = n - last;

        String s = palindromHelper(l, rank);

        int x = s.length() - 1;

        while (x >= 0) {
            s = s + s.charAt(x--);
        }
        return s;
    }

    String palindromHelper(int l, int rank) {
        if (l == 0) return "";
        int pow = (int) Math.pow(2, l);

        int mid = pow / 2;

        if (mid < rank) {
            return "5" + palindromHelper(l - 1, rank - mid);
        }
        return "4" + palindromHelper(l - 1, rank);
    }

    public List<String> topKFrequent(String[] words, int k) {

        Map<String, Integer> map = new HashMap<>();
        for (String s : words) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        PriorityQueue<String> heap = new PriorityQueue<>(
            (w1, w2) -> map.get(w1).equals(map.get(w2)) ? w2.compareTo(w1) : map.get(w1) - map.get(w2));

        for (String word : map.keySet()) {
            heap.add(word);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        List<String> ans = new ArrayList<>();

        while (!heap.isEmpty()) {
            ans.add(heap.poll());
        }
        Collections.reverse(ans);
        return ans;

    }

    public int maxCoins(int[] nums) {
//        312. Burst Balloons

        int n = nums.length;
        int dp[][] = new int[n + 2][n + 2];
        if (n == 0) return 0;
        dp[0][0] = 1;
        dp[n + 1][n + 1] = 1;

        for (int gap = 0; gap < n; gap++) {

            for (int st = 1, en = gap + 1; en < n + 1; st++, en++) {

                for (int k = st; k <= en; k++) {
                    int left = 1;
                    int right = 1;
                    if (st > 1) {
                        left = nums[st - 2];
                    }
                    if (en < n) {
                        right = nums[en];
                    }


                    dp[st][en] = Math.max(dp[st][k - 1] + left * nums[k - 1] * right + dp[k + 1][en], dp[st][en]);
                }
            }
        }
        return dp[1][n];
    }
}
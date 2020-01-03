package leetcode.contests.ContestsAC;

import org.junit.Test;

import java.util.*;

public class Contests11 {

    class Node {
        String s;
        int fq;

        Node(String s, int fq) {
            this.s = s;
            this.fq = fq;
        }

        public void incFq() {
            this.fq++;
        }
    }

    @Test
    public void ContestsSolution() {
//  aaaabbbac 3
        String[] products = {"bags", "baggage", "banner", "box", "cloths"};
//        synonyms.add(Lists.newArrayList("happy", "joy"));
//        int a[][] = {{0, 0, 1, 1, 0, 1, 0, 0, 1, 0}, {1, 1, 0, 1, 1, 0, 1, 1, 1, 0}, {1, 0, 1, 1, 1, 0, 0, 1, 1, 0}, {0, 1, 1, 0, 0, 0, 0, 1, 0, 1}, {0, 0, 0, 0, 0, 0, 1, 1, 1, 0}, {0, 1, 0, 1, 0, 1, 0, 1, 1, 1}, {1, 0, 1, 0, 1, 1, 0, 0, 0, 1}, {1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 1, 0, 1, 0, 1}, {1, 1, 1, 0, 1, 1, 0, 1, 1, 0}};
        int[][] nums = {{0, 2}, {4, 6}, {8, 10}, {1, 9}, {1, 5}, {5, 9}};

//        System.out.println(videoStitching(nums, 10));
//        System.out.println(numSquares(4));
        List<String> s = new ArrayList<>();
        s.add("g");
        s.add("e");
        s.add("e");
        s.add("g");
//        s.add("k");
//        s.add("s");
//        s.add("f");
//        s.add("o");
//        s.add("r");
//        s.add("g");
//        s.add("e");
//        s.add("e");
//        s.add("k");
//        s.add("s");
//       geeksforgeeks
        System.out.println(srinkString(s, 2));
    }

    public List<String> srinkString(List<String> s, int len) {

        int l = s.size();
        Stack<Node> st = new Stack<>();

        for (int i = 0; i < l; i++) {
            String c = s.get(i);
            if (st.isEmpty()) {
                st.add(new Node(c, 1));
            } else {
                Node n = st.peek();
                if (n.s == c) {
                    n.incFq();
                } else {
                    if (n.fq >= len) {
                        st.pop();
                    }
                    if (!st.isEmpty()) {
                        Node nn = st.peek();
                        if (nn.s == c) {
                            nn.incFq();
                        } else {
                            st.add(new Node(c, 1));
                        }
                    } else {
                        st.add(new Node(c, 1));
                    }
                }
            }
        }
        if (!st.isEmpty()) {
            Node nn = st.peek();
            if (nn.fq >= len) {
                st.pop();
            }
        }
        int t = st.size();
        List<String> ss = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            Node nn = st.get(i);
            int nt = nn.fq;
            while (nt-- > 0) {
                ss.add(nn.s);
            }
        }

        return ss;
    }

    public int numSquares(int n) {

        int dp[] = new int[n + 1];
        for (int k = 1; k <= n; k++) {
            dp[k] = Integer.MAX_VALUE;

            for (int sq = 1; sq * sq <= k; sq++) {

                dp[k] = Math.min(dp[k - sq * sq] + 1, dp[k]);
            }

        }
        return dp[n];

    }

    public int minCostClimbingStairs(int[] cost) {

        int n = cost.length;
        if (n <= 1) return 0;

        int[] dp = new int[n + 1];

        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i]);
            dp[i] = Math.min(dp[i - 2] + cost[i - 2], dp[i]);
        }
        return dp[n];

    }

    public int videoStitching(int[][] clips, int T) {

        Arrays.sort(clips, (a, b) -> a[0] - b[0]);

        int i = 0;
        int maxreach = 0;
        int bound = 0;
        int ans = 0;
        int n = clips.length;

        while (i < n && maxreach < T) {
            int s = clips[i][0];
            int e = clips[i][1];
            if (s == 0) {
                // find initial max,
                bound = Math.max(bound, e);
                maxreach = Math.max(maxreach, e);
                ans = 1;
                i++;
                continue;
            } else if (s <= bound) {
                // so till bound we will not increase ans untill new bound value comes
                maxreach = Math.max(maxreach, e);
                if (maxreach >= T) {
                    ans++;
                    break;
                }
            } else {
                if (s > maxreach) return -1;
                bound = maxreach;
                maxreach = Math.max(maxreach, e);
                ans++;
            }
            i++;
        }
        if (maxreach < T) return -1;
        return ans;
    }

}

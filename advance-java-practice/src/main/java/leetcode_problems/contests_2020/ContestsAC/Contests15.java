package leetcode_problems.contests_2020.ContestsAC;

import org.junit.Test;

import java.util.HashMap;

public class Contests15 {
    TreeNode parentNode = null;
    TreeNode xNode = null;

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    @Test
    public void ContestsSolution() {

        String[] products = {"bags", "baggage", "banner", "box", "cloths"};
//        synonyms.add(Lists.newArrayList("happy", "joy"));
//        int a[][] = {{0, 0, 1, 1, 0, 1, 0, 0, 1, 0}, {1, 1, 0, 1, 1, 0, 1, 1, 1, 0}, {1, 0, 1, 1, 1, 0, 0, 1, 1, 0}, {0, 1, 1, 0, 0, 0, 0, 1, 0, 1}, {0, 0, 0, 0, 0, 0, 1, 1, 1, 0}, {0, 1, 0, 1, 0, 1, 0, 1, 1, 1}, {1, 0, 1, 0, 1, 1, 0, 0, 0, 1}, {1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 1, 0, 1, 0, 1}, {1, 1, 1, 0, 1, 1, 0, 1, 1, 0}};
//        int[] nums = {3, 6, 5, 1, 8};
//        {{0,0},{1,1},{0,1},{0,2},{1,0},{2,0}}
//        System.out.println(palindromePartition("affsdf", 2));
//        System.out.println(getMinForPalindrome("q"));
        System.out.println(numWays(4,2));
    }

    public int numWays(int steps, int arrLen) {
        int mod = 1000000000 + 7;
        long dp[] = new long[arrLen+1];

        if (steps == 0) return 0;
        dp[0] = 1;
        dp[1] = 1;
        // cant go more than half to come back at 0 index
        int mid = (steps/2)+1;
        steps--;
        while (steps > 0) {
            long prev = dp[0];
            for (int i = 0; i < mid && i<arrLen; i++) {
                long pp = dp[i];
                dp[i] = (dp[i]+dp[i+1])%mod;
                if(i!=0){
                    dp[i] = (dp[i]+prev)%mod;
                }
                prev = pp;
            }
            steps--;
        }
        return (int)dp[0];
    }

    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {

        HashMap<Integer, Integer> map = new HashMap<>();
        updateNodeCount(root, map, null, x);
        int a = 0;
        int b = 0;
        int c = 0;

        if (parentNode != null) {
            a = n - map.get(xNode.val);
        }
        if (xNode.left != null) {
            b = map.get(xNode.left.val);
        }
        if (xNode.right != null) {
            b = map.get(xNode.right.val);
        }

        int max = Math.max(a, Math.max(b, c));

        if (2 * max > n) {
            return true;
        }
        return false;

    }

    int updateNodeCount(TreeNode root, HashMap<Integer, Integer> map, TreeNode parent, int x) {
        if (root == null) {
            return 0;
        }
        if (x == root.val) {
            parentNode = parent;
            xNode = root;
        }
        int l = updateNodeCount(root.left, map, root, x);
        int r = updateNodeCount(root.right, map, root, x);
        int v = l + r + 1;
        map.put(root.val, v);
        return v;
    }

    public int palindromePartition(String s, int k) {

        int n = s.length();
        if (n == k) return 0;

        int dp[][] = new int[n + 1][k + 1];
        int pp[][] = new int[n + 1][n + 1];

        updateMinForPalindrome(s, pp);

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = -1;
            }
        }
        dp[1][1] = 0;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                for (int t = 1; t <= k; t++) {
                    if (t == 1) {
                        dp[i][t] = pp[1][i];
                        continue;
                    }
                    if (dp[j][t - 1] >= 0 && j >= t - 1) {
                        if (dp[i][t] != -1) {
                            dp[i][t] = Math.min(dp[j][t - 1] + pp[j + 1][i], dp[i][t]);
                        } else {
                            dp[i][t] = dp[j][t - 1] + pp[j + 1][i];
                        }
                    }
                }
            }
        }
        return dp[n][k];
    }

    void updateMinForPalindrome(String s, int dp[][]) {
        int n = s.length();
        for (int k = 1; k <= n; k++) {
            for (int st = 1, e = k; e <= n; st++, e++) {
                if (k == 1) continue;
                if (k == 2) {
                    if (s.charAt(st - 1) != s.charAt(e - 1)) {
                        dp[st][e] = 1;
                    }
                    continue;
                }
                if (s.charAt(st - 1) == s.charAt(e - 1)) {
                    dp[st][e] = dp[st + 1][e - 1];
                } else {
                    dp[st][e] = dp[st + 1][e - 1] + 1;
                }
            }
        }
    }
}

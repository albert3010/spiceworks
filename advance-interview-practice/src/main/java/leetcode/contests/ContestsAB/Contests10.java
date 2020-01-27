package leetcode.contests.ContestsAB;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

public class Contests10 {


    @Test
    public void ContestsSolution() {
        int ede[][] = {{0,1,2},{0,4,8},{1,2,3},{1,4,2},{2,3,1},{3,4,1}};
        System.out.println(findTheCity(5, ede, 2));
    }

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        long dp[][] = new long[n][n];
        for (long[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        // base condition
        int m = edges.length;
        for (int i = 0; i < m; i++) {
            dp[edges[i][0]][edges[i][1]] = edges[i][2];
            dp[edges[i][1]][edges[i][0]] = edges[i][2];
        }
        floydHelper(dp, n);
        int city = Integer.MAX_VALUE;
        int count = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int citys = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (dp[i][j] <= distanceThreshold) {
                    citys++;
                }
            }
            if (citys <= count) {
                city = i;
                count = citys;
            }
        }
        return city;
    }

    public void floydHelper(long dp[][], int n) {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
    }

    public int removePalindromeSub(String s) {
        int n = s.length();
        if (n <= 1) return n;
        int l = 0;
        int r = n - 1;
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else {
                return 2;
            }
        }
        return 1;
    }
}

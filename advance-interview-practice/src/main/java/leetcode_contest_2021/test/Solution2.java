package leetcode_contest_2021.test;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {

    public static void main(String[] args) {
        List<String> grid = new ArrayList<>();
        grid.add("..##");
        grid.add("#.##");
        grid.add("#...");
        System.out.println(reachTheEnd(grid, 5));
    }

    public static String reachTheEnd(List<String> grid, int maxTime) {

        int n = grid.size();
        int m = grid.get(0).length();
        int dp[][] = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                if (!(i==0 || j==0) && grid.get(i - 1).charAt(j - 1) == '.') {

                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]);

                    if (dp[i][j] != Integer.MAX_VALUE) {
                        dp[i][j]++;
                    }
                    if (i == 1 && j == 1) dp[i][j] = 0;
                }
            }
        }
        if (dp[n][m] != Integer.MAX_VALUE && dp[n][m] <= maxTime) {
            return "Yes";
        }
        return "No";
    }
}

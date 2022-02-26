package leetcode_contest_2022.group1;

import java.util.ArrayList;
import java.util.List;

public class SolutionsA2 {

    public static void main(String[] args) {
//        System.out.println(findTheWinner(5, 2));
        int a[] = {0,2,1,0,3,0};
        System.out.println(minSideJumps(a));

    }

    public static int minSideJumps(int[] obstacles) {
        int n = obstacles.length;
        int dp[][] = new int[n][3];
        dp[0][0] = 1;
        dp[0][2] =1;
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0];
            dp[i][1] =dp[i - 1][1];
            dp[i][2] = dp[i - 1][2];
            if (obstacles[i] != 0) {
                dp[i][obstacles[i] - 1] = Integer.MAX_VALUE;
            }
            int min = Math.min(Math.min(dp[i][0], dp[i][1]), dp[i][2]);

            if (dp[i][0] != min) {
                dp[i][0] = min+1;
            }
            if (dp[i - 1][1] != min) {
                dp[i][1] = min+1;
            }
            if (dp[i - 1][2] != min) {
                dp[i][2] = min+1;
            }
            if (obstacles[i] != 0) {
                dp[i][obstacles[i] - 1] = Integer.MAX_VALUE;
            }

        }
        return Math.min(Math.min(dp[n - 1][0], dp[n - 1][1]), dp[n - 1][2]);
    }

    public static int findTheWinner(int n, int k) {
        List<Integer> arr = new ArrayList<>();
        if (n == 1) return 1;
        for (int i = 1; i <= n; i++) {
            arr.add(i);
        }
        int st = 0;
        while (arr.size() > 1) {
            int m = arr.size();
            int end = (st + k - 1) % m;
            arr.remove(end);
            st = end % (arr.size());
        }
        return arr.get(0);
    }

    public int arraySign(int[] nums) {
        int negCount = 0;

        int l = nums.length;
        for (int i = 0; i < l; i++) {
            if (nums[i] < 0) {
                negCount++;
            }
            if (nums[i] == 0) {
                return 0;
            }
        }
        if (negCount % 2 == 0) {
            return 1;
        }
        return -1;
    }
}

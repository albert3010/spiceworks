package leetcode_2020.contests.ContetAD;

import org.junit.Test;

public class Contests2 {

    @Test
    public void ContestsSolution() {
        int a[] = {1, 2, 3, 1, 1};
//        System.out.println(minDifficulty(a, 6));
//        System.out.println(shipWithinDays(a, 4));
    }

    public int shipWithinDays(int[] weights, int D) {
        int n = weights.length;
        int maxWeight = 500 * 50000;
        int minWeight = 1;

        int ansMinW = maxWeight;

        while (minWeight < maxWeight) {
            int midW = (minWeight + maxWeight) / 2;
            int daysRemaining = getDaysRemaining(weights, midW, D);

            if (daysRemaining >= 0) {
                ansMinW = Math.min(ansMinW, midW);
            }
            if (minWeight + 1 == maxWeight) {
                daysRemaining = getDaysRemaining(weights, maxWeight, D);
                if (daysRemaining >= 0) {
                    ansMinW = Math.min(ansMinW, maxWeight);
                }
            }
            if (daysRemaining >= 0) {
                maxWeight = midW - 1;
            } else {
                minWeight = midW + 1;
            }
        }
        return ansMinW;
    }

    public int getDaysRemaining(int[] weights, int midW, int D) {
        int n = weights.length;
        int days = 0;
        int wd = weights[0];
        if (weights[0] > midW) return -1;
        for (int i = 1; i < n; i++) {
            if (weights[i] > midW) return -1;
            wd += weights[i];
            if (wd > midW) {
                days++;
                wd = weights[i];
            }
        }
        if (wd <= midW) {
            days++;
        }
        return D - days;
    }

    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        int dp[][] = new int[n][d + 1];
        dp[0][1] = jobDifficulty[0];

        for (int day = 1; day <= d; day++) {
            for (int i = 0; i < n; i++) {
                if (day == 1 && i == 0) continue;
                dp[i][day] = Integer.MAX_VALUE;
                if (i + 1 < day) {
                    continue;
                }
                if (day == 1) {
                    dp[i][day] = Math.max(dp[i - 1][day], jobDifficulty[i]);
                }
                int max = 0;
                for (int k = i; k >= day - 1; k--) {
                    max = Math.max(max, jobDifficulty[k]);
                    dp[i][day] = Math.min(dp[i][day], dp[k - 1][day - 1] + max);
                }
            }
        }
        if (dp[n - 1][d] == Integer.MAX_VALUE) return -1;
        return dp[n - 1][d];
    }

}

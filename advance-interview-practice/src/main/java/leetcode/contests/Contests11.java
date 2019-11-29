package leetcode.contests;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class Contests11 {


    @Test
    public void ContestsSolution() {

        String[] products = {"bags", "baggage", "banner", "box", "cloths"};
//        synonyms.add(Lists.newArrayList("happy", "joy"));
//        int a[][] = {{0, 0, 1, 1, 0, 1, 0, 0, 1, 0}, {1, 1, 0, 1, 1, 0, 1, 1, 1, 0}, {1, 0, 1, 1, 1, 0, 0, 1, 1, 0}, {0, 1, 1, 0, 0, 0, 0, 1, 0, 1}, {0, 0, 0, 0, 0, 0, 1, 1, 1, 0}, {0, 1, 0, 1, 0, 1, 0, 1, 1, 1}, {1, 0, 1, 0, 1, 1, 0, 0, 0, 1}, {1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 1, 0, 1, 0, 1}, {1, 1, 1, 0, 1, 1, 0, 1, 1, 0}};
        int[][] nums = {{0, 2}, {4, 6}, {8, 10}, {1, 9}, {1, 5}, {5, 9}};

        System.out.println(videoStitching(nums, 10));


    }


    public int minCostClimbingStairs(int[] cost) {

        int n = cost.length;
        if(n<=1) return 0;

        int [] dp = new int[n+1];

        dp[0] = 0;
        dp[1] = 0;

        for(int i=2; i<=n; i++){
            dp[i] = Integer.MAX_VALUE;
            dp[i] = Math.min(dp[i-1]+cost[i-1], dp[i]);
            dp[i] = Math.min(dp[i-2]+cost[i-2], dp[i]);
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
                ans = 1;i++;
                continue;
            } else if (s <=bound) {
                // so till bound we will not increase ans untill new bound value comes
                maxreach = Math.max(maxreach, e);
                if (maxreach >= T) {
                    ans++;
                    break;
                }
            } else {
                if(s > maxreach) return -1;
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

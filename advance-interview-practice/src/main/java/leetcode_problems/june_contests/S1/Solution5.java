package leetcode_problems.june_contests.S1;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution5 {


    @Test
    public void function() {
//0,1,3, 5,6,8
        int[] ct = {3, 6, 9, 1, 12, 1, 3, 14, 12, 14, 15, 55, 22, 33, 44};
//        int[] ct = {100};
        System.out.println(maximumGap(ct));
    }

    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;
        int min = Arrays.stream(nums).min().getAsInt();
        int max = Arrays.stream(nums).max().getAsInt();

        int range = ((max - min) / n) + 1;

        int[][] cache = new int[n + 1][2];
        IntStream.rangeClosed(0, n).forEach(i -> {
            cache[i][0] = max + 1;
            cache[i][1] = -1;
        });
        Arrays.stream(nums).forEach(e -> {
            int x = (e - min) / range;
            cache[x][0] = Math.min(cache[x][0], e);
            cache[x][1] = Math.max(cache[x][1], e);
        });
        int ans = 0;
        if (cache[0][1] != -1 && cache[0][0] != max + 1) {
            ans = Math.max(ans, cache[0][1] - cache[0][0]);
        }

        for (int i = 1; i <= n; i++) {
            if (cache[i][1] != -1 && cache[i][0] != max + 1) {
                ans = Math.max(ans, cache[i][1] - cache[i][0]);
            }
            if (cache[i][1] != -1 && cache[i - 1][0] != max + 1) {
                ans = Math.max(ans, cache[i][0] - cache[i - 1][0]);
            }
        }
        return ans;
    }

    public int hIndex(int[] citations) {
        int n = citations.length;
        if (n <= 1) return n;
        int l = 0;
        int h = n;
        int hIndex = -1;
        while (l <= h) {
            int hx = (h - l) / 2 + l;
            int right = (int) Arrays.stream(citations).filter(e -> e >= hx).count();
            int left = (int) Arrays.stream(citations).filter(e -> e <= hx).count();

            if (right == hx) {
                hIndex = Math.max(hIndex, hx);
                l = hx + 1;
            }
            if (right < hx) {
                h = hx - 1;
            } else {
                int x = n - hx;
                if (left >= x) {
                    l = hx + 1;
                    hIndex = Math.max(hIndex, hx);
                } else {
                    l = hx + 1;
                }

            }
        }
        return hIndex;
    }

    int checkIndex(int[] citations, int h) {
        return h - (int) Arrays.stream(citations).filter(e -> e <= h).count();
    }
}


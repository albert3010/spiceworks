package leetcode_contest_2021;

import org.junit.Test;

import java.util.stream.IntStream;

public class Contest_feb {

    @Test
    public void ContestsSolution() {

    }
    public int minOperations(String s) {
        char[] c = {'1', '0'};
        int ans1 = 0;
        int ans2 = 0;
        int l = s.length();
        for (int i = 0; i < l; i++) {
            if (s.charAt(i) != c[i % 2]) {
                ans1 += 1;
            }
            if (s.charAt(i) != c[(i + 1) % 2]) {
                ans2 += 1;
            }
        }
        return Math.min(ans1, ans2);
    }


    public int countHomogenous(String s) {
        int l = s.length();
        int i = 0;
        double ans = 0;
        int mod = 1000000000 + 7;
        while (i < l) {
            double sameCount = 1;
            while (i + 1 < l && s.charAt(i) == s.charAt(i + 1)) {
                i++;
                sameCount++;
            }
            i++;
            ans += ((sameCount * (sameCount + 1)) / 2) % mod;

        }
        return (int) ans;
    }

    public int minimumSize(int[] nums, int maxOperations) {
        int n = nums.length;
        int min = 1;
        int max = IntStream.range(0, n).map(i -> nums[i]).max().getAsInt();
        int ans = max;
        while (min <= max) {
            int mid = (max + min) / 2;
            if (isValid(mid, nums, maxOperations, n)) {
                max = mid - 1;
                ans = Math.min(ans, mid);
            } else {
                min = mid + 1;
            }
        }
        return ans;
    }

    private boolean isValid(int val, int[] nums, int maxOperations, int l) {
        for (int i = 0; i < l; i++) {
            if (nums[i] > val) {
                double up = Math.ceil(nums[i] / (val + 0.0)) - 1;
                if (maxOperations >= up) {
                    maxOperations -= up;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}

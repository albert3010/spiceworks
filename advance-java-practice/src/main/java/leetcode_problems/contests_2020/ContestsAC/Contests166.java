package leetcode_problems.contests_2020.ContestsAC;

import org.junit.Test;

public class Contests166 {


    @Test
    public int smallestDivisor(int[] nums, int threshold) {
        int n = nums.length;
        int max = 0;
        int min = 1;

        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
        }

        while (min < max) {

            int mid = (min + max) / 2;
            if (min + 1 == max || min == max) {
                long amin = getSum(nums, min);
                if (amin <= threshold) {
                    return min;
                }else {
                    return max;
                }

            }
            long newSum = getSum(nums, mid);

            if (newSum > threshold) {
                min = mid + 1;
            } else {
                max = mid;
            }

        }


        return min;
    }

    long getSum(int[] nums, double x) {
        long sum = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            sum += Math.ceil(nums[i] / x);
        }
        return sum;

    }

    public int subtractProductAndSum(int n) {
        if (n == 0) return n;
        int sum = 0;
        int prd = 1;
        int r, t;

        while (n > 0) {
            r = n % 10;

            sum += r;
            prd *= r;
            n = n / 10;

        }
        return prd - sum;

    }

}
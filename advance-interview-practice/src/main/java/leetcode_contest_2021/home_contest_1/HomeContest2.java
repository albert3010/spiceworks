package leetcode_contest_2021.home_contest_1;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

public class HomeContest2 {
    int diffG;
    boolean[] snapshot;

    @Test
    public void test() {
        String[] a = {"3", "6", "7", "10"};
        int[] aa = {1, 2, 3};
//        System.out.println(kthLargestNumber(a, 4));
        System.out.println(minSessions(aa, 3));
    }

    public int minSessions(int[] tasks, int sessionTime) {
        int n = tasks.length;

        boolean[] visitG = new boolean[n];
        int minSession = 0;
        while (true) {
            boolean[] visit = new boolean[n];
            snapshot = new boolean[n];
            int sum = 0;
            diffG = sessionTime;

            getClosestTasks(0, tasks, sessionTime, sum, visitG, visit, n);

            for (int i = 0; i < n; i++) {
                if (snapshot[i]) {
                    visitG[i] = true;
                }
            }
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                if (!visitG[i]) {
                    flag = false;
                    break;
                }
            }
            minSession++;
            if (flag) {
                break;
            }
        }
        return minSession;
    }

    private void getClosestTasks(int i, int[] tasks, int sessionTime, int sum,
                                 boolean[] visitG, boolean[] visit, int n) {
        if (sum > sessionTime || diffG == 0) {
            return;
        } else {
            int diff = sessionTime - sum;
            if (diff < diffG) {
                diffG = diff;
                for (int j = 0; j < n; j++) {
                    snapshot[j] = visit[j];
                }
            }
        }
        if (i == n) {
            return;
        }

        if (!visitG[i]) {
            if (!visit[i]) {
                System.out.println("dd");
                visit[i] = true;
                getClosestTasks(i + 1, tasks, sessionTime, sum + tasks[i], visitG, visit, n);
                getClosestTasks(i + 1, tasks, sessionTime, sum + tasks[i], visitG, visit, n);
                visit[i] = false;
            } else {
                System.out.println("dd2");
                getClosestTasks(i + 1, tasks, sessionTime, sum, visitG, visit, n);
            }
        } else {
            System.out.println("dd3");
            getClosestTasks(i + 1, tasks, sessionTime, sum, visitG, visit, n);
        }
    }


    public class Comparator_ implements Comparator<String> {
        @Override
        public int compare(String m1, String m2) {
            if (m1.length() == m2.length()) {
                return m1.compareToIgnoreCase(m2);
            } else {
                return m1.length() - m2.length();
            }
        }
    }

    public String kthLargestNumber(String[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums, new Comparator_());
        return nums[n - k];

    }

    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;
        if (n <= 1) return 0;
        Arrays.sort(nums);
        System.out.println(nums[0]);
        int max = nums[0];
        int min = nums[k - 1];
        int ans = max - min;
        for (int i = k; i < n; i++) {
            ans = Math.min(ans, nums[i - k] - nums[i]);
        }
        return ans;
    }


}

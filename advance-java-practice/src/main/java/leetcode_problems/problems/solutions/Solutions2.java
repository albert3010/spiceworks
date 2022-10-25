package leetcode_problems.problems.solutions;


import org.junit.Test;

import java.util.*;

public class Solutions2 {

    class Job {
        int start, end, profit;

        Job(int s, int e, int p) {
            this.start = s;
            this.end = e;
            this.profit = p;
        }
    }

    @Test
    public void Tests() {

        String folder[] = {"/a/b/c", "/a/b/ca", "/a/b/c/x", "/a/b/d"};
//        System.out.println(removeSubfolders(folder));
        System.out.println(balancedString("QQQQ"));
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = profit.length;
        Job[] jobs = new Job[n];

        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }
        Arrays.sort(jobs, (a, b) -> (a.start - b.end));

        int dp[] = new int[n];
        dp[n - 1] = jobs[n-1].profit;
        for (int i = n - 2; i >= 0; i--) {
            int j = i + 1;
            while (j < n && (jobs[j].start < jobs[i].end)) {
                j++;
            }
            if (j != n) {
                dp[i] = Math.max(dp[j] + jobs[i].profit, dp[i+1]);
            } else {
                dp[i] = Math.max(jobs[i].profit, dp[i+1]);
            }

        }
        return dp[0];

    }

    public int balancedString(String s) {
        int[] arr = new int[26];
        int[] brr = new int[26];
        int[] crr = new int[26];
        int n = s.length();
        int res = Integer.MAX_VALUE;
        int ave = (n) / 4;

        for (char ch : s.toCharArray()) {
            arr[ch - 'A']++;
            // brr stores extra char count
            brr[ch - 'A'] = ave >= arr[ch - 'A'] ? 0 : arr[ch - 'A'] - ave;
        }

        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            while (j < n && !(chaeckIfAllExtrasFound(brr, crr))) {
                crr[s.charAt(j++) - 'A']++;
            }
            if (chaeckIfAllExtrasFound(brr, crr)) {
                res = Math.min(res, j - i);
            }
            crr[s.charAt(i) - 'A']--;
        }

        return res;
    }

    boolean chaeckIfAllExtrasFound(int[] arr, int[] crr) {
        return crr['Q' - 'A'] >= arr['Q' - 'A'] &&
            crr['W' - 'A'] >= arr['W' - 'A'] &&
            crr['E' - 'A'] >= arr['E' - 'A'] &&
            crr['R' - 'A'] >= arr['R' - 'A'];
    }

    public List<String> removeSubfolders(String[] folder) {

        List<String> folders = Arrays.asList(folder);
        Collections.sort(folders);
        int l = folders.size();
        List<String> res = new LinkedList<>();

        String s = folders.get(0);
        res.add(s);

        for (int i = 1; i < l; i++) {
            int n = s.length();
            String ff = folders.get(i);
            int index = ff.indexOf(s);
            if (index != 0 || (index == 0 && ff.charAt(n) != '/')) {
                s = ff;
                res.add(s);
            }
        }


        return res;
    }
}
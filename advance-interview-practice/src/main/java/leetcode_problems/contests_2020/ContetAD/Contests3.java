package leetcode_problems.contests_2020.ContetAD;

import org.junit.Test;

import java.util.*;

public class Contests3 {


    @Test
    public void ContestsSolution() {
//        System.out.println(numberOfSteps(8));
        int a[] = {0, 11, 2, 5, 3, 0};
//        System.out.println(numOfSubarrays(a, 4, 1));
//        System.out.println(angleClock(3, 15));
//        System.out.println(minJumps(a));
//        System.out.println(checkIfExist(a));
        System.out.println(minSteps("leetcode_problems", "practice"));
    }


    public int minSteps(String s, String t) {
        int sCout[] = new int[26];
        int tCout[] = new int[26];

        int l = s.length();
        for (int i = 0; i < l; i++) {
            sCout[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < l; i++) {
            tCout[t.charAt(i) - 'a']++;
        }
        int ans = 0;
        for (int i = 0; i < 26; i++) {
            int coutDiff = sCout[i] - tCout[i];
            if (coutDiff > 0) {
                ans += coutDiff;
            }
        }
        return ans;
    }

    public boolean checkIfExist(int[] arr) {
        int n = arr.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0 && map.get(0) != null) return true;
            map.put(arr[i], arr[i]);
            if (arr[i] != 0 && arr[i] % 2 == 0 && map.get(arr[i] / 2) != null) {
                return true;
            }
            if (arr[i] != 0 && map.get(arr[i] * 2) != null) {
                return true;
            }
        }
        return false;
    }

    public int minJumps(int[] arr) {
        Queue<Integer> queue = new LinkedList<>();
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int n = arr.length;

        boolean v[] = new boolean[n];

        for (int i = 0; i < n; i++) {
            int val = arr[i];

            List<Integer> list = map.getOrDefault(val, new ArrayList<>());
            list.add(i);
            map.put(val, list);
        }
        queue.add(0);
        int level = 0;
        while (!queue.isEmpty()) {
            int l = queue.size();
            while (l > 0) {
                Integer index = queue.poll();
                v[index] = true;
                if (index - 1 >= 0 && !v[index - 1]) {
                    queue.add(index - 1);
                }
                if (index + 1 < n && !v[index + 1]) {
                    queue.add(index + 1);
                }
                List<Integer> list = map.get(arr[index]);
                if (list != null) {
                    for (Integer x : list) {
                        if (!v[x]) {
                            queue.add(x);
                        }
                    }
                }
                map.put(arr[index], null);
                l--;
            }
            if (v[n - 1]) {
                return level;
            }
            level++;
        }
        return n - 1;
    }

    public double angleClock(int hour, int minutes) {
        double totalHr = hour + (minutes / 60.0);
        int totalMins = minutes;

        int md = totalMins * 6;
        double hd = totalHr * 30;
        double ans = Math.abs(md - hd);
        if (360 - ans < ans) {
            return 360 - ans;
        }
        return ans;
    }

    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int prevIndex = -1;
        int val = 0;
        int subArraySum = 0;
        int l = arr.length;
        int ans = 0;
        for (int i = 0; i < l; i++) {
            subArraySum += arr[i];
            if (i >= k - 1) {
                if (prevIndex >= 0) {
                    subArraySum -= arr[prevIndex];
                }
                prevIndex++;
                if (subArraySum / k >= threshold) {
                    ans++;
                }
            }
        }
        return ans;

    }

    public int numberOfSteps(int num) {
        int steps = 0;
        if (num == 0) return steps;

        while (num > 0) {
            if (num % 2 == 0) {
                num = num / 2;
            } else {
                num -= 1;
            }
            steps++;

        }
        return steps;
    }
}

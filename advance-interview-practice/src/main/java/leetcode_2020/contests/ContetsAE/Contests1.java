package leetcode_2020.contests.ContetsAE;

import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;

public class Contests1 {

    @Test
    public void ContestsSolution() {
        System.out.println(largestMerge("abcabc", "abdcaba"));
    }

    public String largestMerge(String word1, String word2) {
        int l1 = word1.length();
        int l2 = word2.length();

        int i = 0;
        int j = 0;
        StringBuilder ans = new StringBuilder();
        while (i < l1 && j < l2) {
            if (word1.charAt(i) > word2.charAt(j)) {
                ans.append(word1.charAt(i++));
            }
            if (word1.charAt(i) < word2.charAt(j)) {
                ans.append(word2.charAt(j++));
            } else {
                int t1 = i;
                int t2 = j;
                while (t1 < l1 && t2 < l2 && word1.charAt(t1) == word2.charAt(t2)) {
                    t1++;
                    t2++;
                }

                if (t1 < l1 && t2 < l2) {
                    if (word1.charAt(t1) > word2.charAt(t2)) {
                        ans.append(word1.charAt(i++));
                    }
                    if (word1.charAt(t1) < word2.charAt(t2)) {
                        ans.append(word2.charAt(j++));
                    }
                } else if (t2 < l2) {
                    ans.append(word2.charAt(j++));
                } else {
                    ans.append(word1.charAt(i++));
                }

            }
        }
        while (i < l1) {
            ans.append(word1.charAt(i++));
        }
        while (j < l2) {
            ans.append(word2.charAt(j++));
        }
        return ans.toString();
    }

    public int maxAbsoluteSum(int[] nums) {

        int n = nums.length;

        int max = 0;
        int min = 0;
        int ans = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];

            ans = Math.max(max, Math.abs(sum - max));
            ans = Math.max(max, Math.abs(sum - min));

            max = Math.max(max, sum);
            min = Math.min(max, sum);

        }
        return ans;
    }

    public int minimumLength(String s) {
        int l = s.length();
        int st = 0;
        int en = l - 1;
        while (st < en) {
            if (s.charAt(st) == s.charAt(en)) {
                int t = st;
                while (t < l) {
                    if (s.charAt(st) == s.charAt(t)) {
                        t++;
                    }
                }
                st = t;
                t = en;
                while (t >= 0 && t > st) {
                    if (s.charAt(en) == s.charAt(t)) {
                        t--;
                    }
                }
                en = t;
            } else {
                break;
            }

        }
        System.out.println(st);
        System.out.println(en);
        if (st == en) return 0;
        return en - st + 1;
    }

    public int countBalls(int lowLimit, int highLimit) {
        HashMap<Integer, Integer> map = new HashMap<>();
        return IntStream.range(lowLimit, highLimit + 1)
                .map(this::getDigitSum)
                .map(e -> {
                    map.putIfAbsent(e, 0);
                    map.put(e, map.get(e) + 1);
                    return map.get(e);
                }).max().getAsInt();
    }


    int getDigitSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    public int[] restoreArray(int[][] adjacentPairs) {
        int n = adjacentPairs.length;
        int[] ans = new int[n + 1];
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.putIfAbsent(adjacentPairs[i][0], new ArrayList<>());
            map.putIfAbsent(adjacentPairs[i][1], new ArrayList<>());
            List<Integer> l1 = map.get(adjacentPairs[i][0]);
            l1.add(adjacentPairs[i][1]);

            map.put(adjacentPairs[i][0], l1);
            List<Integer> l2 = map.get(adjacentPairs[i][1]);
            l2.add(adjacentPairs[i][0]);

            map.put(adjacentPairs[i][1], l2);

        }
        int start = map.entrySet().stream().filter(e -> e.getValue().size() == 1).findFirst().get().getKey();
        System.out.println(start);
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i <= n; i++) {
            System.out.println(i);
            if (i != 0) {
                int a = map.get(start).get(0);
                int b = a;
                if (map.get(start).size() == 2) {
                    b = map.get(start).get(1);
                }

                if (!set.contains(a)) {
                    start = a;
                } else {
                    start = b;
                }
            }
            set.add(start);
            ans[i] = start;
        }
        return ans;
    }

    class TaskNode implements Comparable<TaskNode> {
        char task;
        int rem;
        int nextTime;

        TaskNode(char task, int rem, int nextTime) {
            this.task = task;
            this.rem = rem;
            this.nextTime = nextTime;
        }

        public void reduceRemCount() {
            this.rem--;
        }

        public TaskNode updateRemCount() {
            this.rem++;
            return this;
        }

        public void updateNextTime(int time) {
            this.nextTime = time;
        }

        public int compareTo(TaskNode p) {
            return p.rem - this.rem;
        }
    }

    public int leastInterval(char[] tasks, int n) {
        if (n == 0) return tasks.length;

        Deque<TaskNode> coolingList = new LinkedList<>();
        PriorityQueue<TaskNode> taskQ = new PriorityQueue<>();
        HashMap<Character, TaskNode> mapCount = new HashMap<>();
        int l = tasks.length;
        for (int i = 0; i < l; i++) {
            mapCount.putIfAbsent(tasks[i], new TaskNode(tasks[i], 0, 0));
            mapCount.put(tasks[i], mapCount.get(tasks[i]).updateRemCount());
        }
        for (TaskNode nd : mapCount.values()) {
            taskQ.add(nd);
        }
        int timeCounter = 0;

        while (!(taskQ.isEmpty() && coolingList.isEmpty())) {

            if (!coolingList.isEmpty() && coolingList.getFirst().nextTime == timeCounter) {
                taskQ.add(coolingList.pollFirst());
            }
            if (!taskQ.isEmpty()) {
                TaskNode nd = taskQ.poll();
                nd.reduceRemCount();
                nd.updateNextTime(timeCounter + n + 1);
                if (nd.rem != 0) {
                    coolingList.add(nd);
                }
            }

            timeCounter++;
        }
        return timeCounter;

    }

    public int findMaxLength(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int ans = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                sum--;
            } else {
                sum++;
            }
            if (map.get(sum) == null) {
                map.put(sum, i);
            } else {
                int lastIndex = map.get(sum);
                ans = Math.max(ans, i - lastIndex);
            }
        }
        return ans;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        int n = strs.length;
        HashMap<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String hash = getHash(strs[i]);
            List<String> aa = map.getOrDefault(hash, new ArrayList<>());
            aa.add(strs[i]);
            map.put(hash, aa);
        }
        return new ArrayList(map.values());
    }

    public String getHash(String ss) {
        int l = ss.length();
        int a[] = new int[26];
        for (int k = 0; k < l; k++) {
            a[ss.charAt(k) - 'a']++;
        }
        String hash = "";
        for (int j = 0; j < 26; j++) {
            String t = Integer.toString(a[j]);
            hash += "#" + t;
        }
        return hash;
    }

    public String solve(int n, int k) {
        int dp[][] = new int[n][2];
        dp[0][0] = 1;
        dp[0][1] = 1;
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
            dp[i][1] = dp[i - 1][0];
        }
        return helper("", k, 0, n - 1, dp);
    }

    String helper(String ans, int k, int v, int n, int dp[][]) {
        if (k == 0 || n < 0) return ans;
        if (v == 0 && k > dp[n][0]) {
            return helper(ans + 'b', k - dp[n][0], 1, n - 1, dp);
        }
        return helper(ans + 'a', k, 0, n - 1, dp);
    }

    public String longestDiverseString(int a, int b, int c) {
        int arr[] = {a, b, c};
        StringBuilder ans = new StringBuilder();
        int last = -1;
        int lastmax = -1;
        while (arr[0] + arr[1] + arr[2] > 0) {
            int index = getMaxIndex(arr, last);
            if (index == -1) break;

            if ((arr[index] >= 2 && last == -1) || (arr[index] >= 2 && (last != -1 && arr[last] <= arr[index]))) {
                char cc = (char) ('a' + index);
                ans.append(cc);
                ans.append(cc);
                arr[index] -= 2;
            } else {
                char cc = (char) ('a' + index);
                ans.append(cc);
                arr[index]--;
            }
            last = index;


        }
        return ans.toString();
    }

    public int getMaxIndex(int arr[], int ex) {
        if (ex == -1) {
            int v = Math.max(arr[0], Math.max(arr[1], arr[2]));
            if (v == arr[0]) return 0;
            if (v == arr[1]) return 1;
            if (v == arr[2]) return 2;
        }
        if (ex == 0) {
            if (Math.max(arr[1], arr[2]) == 0) return -1;

            if (arr[1] > arr[2]) {
                return 1;
            }
            return 2;
        }
        if (ex == 1) {
            if (Math.max(arr[0], arr[2]) == 0) return -1;
            if (arr[0] > arr[2]) {
                return 0;
            }
            return 2;
        }
        if (Math.max(arr[0], arr[1]) == 0) return -1;

        if (arr[0] > arr[1]) {
            return 0;
        }
        return 1;
    }

    public int numSteps(String s) {
        s = "0" + s;
        int steps = 0;
        StringBuilder str = new StringBuilder();
        str.append(s);
        while (true) {
            int n = str.length();
            steps++;
            if (str.charAt(n - 1) == '0') {
                str.deleteCharAt(n - 1);
            } else {
                for (int i = n - 1; i >= 0; i--) {
                    if (str.charAt(i) == '1') {
                        str.setCharAt(i, '0');
                    } else {
                        str.setCharAt(i, '1');
                        break;
                    }
                }

            }

            s = str.toString();
            if (s.equals("01") || s.equals("1")) {
                return steps;
            }
        }
    }

    public List<Integer> minSubsequence(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        int n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            sum += nums[i];
        }
        int s2 = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            s2 += nums[i];
            int s1 = sum - s2;
            if (s2 > s1) {
                for (int j = n - 1; j >= i; j--) {
                    ans.add(nums[j]);
                }
                break;
            }
        }
        return ans;
    }

    public boolean checkOverlap(int radius, int x_center, int y_center, int x1, int y1, int x2, int y2) {
        int xmin = Math.min(x1, x2);
        int xmax = Math.max(x1, x2);

        int ymin = Math.min(y1, y2);
        int ymax = Math.max(y1, y2);

        int xcleft = x_center - radius;
        int xcright = x_center + radius;

        int ycdown = y_center - radius;
        int yctop = y_center + radius;

        if ((xmin <= xcright && xmax >= xcleft) && y_center <= ymax && y_center >= ymin)
            return true;
        if ((ymin <= yctop && ymax >= ycdown) && x_center <= xmax && x_center >= xmin)
            return true;
        int a[] = {x1, x2};
        int b[] = {y1, y2};
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                int dd = (x_center - a[i]) * (x_center - a[i]) + (y_center - b[j]) * (y_center - b[j]);
                if (dd <= radius * radius) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean canConstruct(String s, int k) {

        int a[] = new int[26];
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int x = s.charAt(i) - 'a';
            a[x]++;
        }
        int twoPairs = 0;
        int uniqe = 0;
        for (int i = 0; i < 26; i++) {
            int t = a[i];
            twoPairs += t / 2;
            uniqe += t % 2;
        }
        if (uniqe > k) return false;
        if (twoPairs == k) return true;

        if (twoPairs + uniqe == k) return true;
        if (twoPairs * 2 + uniqe >= k) return true;

        return false;
    }

    public int countLargestGroup(int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = -1;
        for (int i = 1; i <= n; i++) {
            int s = getSumOfDigit(i);
            int x = map.getOrDefault(s, 0) + 1;
            map.put(s, x);
            max = Math.max(x, max);
        }
        int count = 0;
        for (Integer i : map.keySet()) {
            if (map.get(i) == max) {
                count++;
            }
        }
        return count;
    }

    int getSumOfDigit(int n) {
        int s = 0;

        while (n > 0) {
            s += n % 10;
            n = n / 10;
        }
        return s;
    }


}

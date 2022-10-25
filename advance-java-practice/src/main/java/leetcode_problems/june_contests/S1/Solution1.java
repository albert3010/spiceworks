package leetcode_problems.june_contests.S1;

import org.junit.Test;

import java.util.*;

public class Solution1 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    @Test
    public void function() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);

//        int[] a = {1, 2, 3, 4, 8, 16, 9, 12, 15, 24, 48, 45, 90, 180, 1800};
        int[] a = {3, 1, 1, 1, 5, 1, 2, 1};
//        System.out.println(largestDivisibleSubset(a));
//        System.out.println(minSumOfLengths(a, 3));
        List<String> wordDict = new ArrayList<>();
        wordDict.add("sand");
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("and");
        wordDict.add("dog");
//        System.out.println(wordBreak("catsanddog", wordDict));
//        System.out.println(validIPAddress("2001:db8:85a3:0:0:8A2E:0370:7334"));
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(8);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        System.out.println(countPairs(root, 3));

    }
    public int countPairs(TreeNode root, int distance) {

        int [] ans =new int [1];
        countPairshelper(root, distance, 0, ans);
        return ans[0];

    }

    HashMap<Integer, Integer> countPairshelper(TreeNode node, int distance, int currDis, int[] ans) {
        if (node == null) return new HashMap<>();

        HashMap<Integer, Integer> curr = new HashMap<>();
        if (node.left == null && node.right == null) {
            curr.put(currDis, 1);
            return curr;
        }
        HashMap<Integer, Integer> left = countPairshelper(node.left, distance, currDis + 1, ans);
        HashMap<Integer, Integer> right = countPairshelper(node.right, distance, currDis + 1, ans);



        left.forEach((k1, v1) ->
                right.forEach((k2, v2) -> {
                            if (k1 + k2- 2*currDis <= distance) {
                                ans[0] += v1*v2;
                            }
                        }
                ));
        left.forEach((k1, v1) -> {
            curr.putIfAbsent(k1, 0);
            curr.put(k1, curr.get(k1) + v1);
        });
        right.forEach((k2, v2) -> {
            curr.putIfAbsent(k2, 0);
            curr.put(k2, curr.get(k2) + v2);
        });


        return curr;
    }

    public String validIPAddress(String IP) {
        IP = IP.toLowerCase();
        int l = IP.length();
        if (l == 0) return "Neither";
        if (IP.charAt(l - 1) == '.' || IP.charAt(l - 1) == ':') return "Neither";

        boolean ipv4 = IP.contains(".");
        boolean ipv6 = IP.contains(":");
        if ((ipv4 && ipv6) || (!ipv4 && !ipv6)) return "Neither";

        String[] ipv4Group = IP.split("\\.");
        String[] ipv6Group = IP.split("\\:");

        if (ipv4) {
            int l4 = ipv4Group.length;
            if (l4 != 4) return "Neither";
            for (int i = 0; i < l4; i++) {
                if (!validateIp4Group(ipv4Group[i])) {
                    return "Neither";
                }
            }
            return "IPv4";
        }
        if (ipv6) {
            int l6 = ipv6Group.length;
            if (l6 != 8) return "Neither";
            for (int i = 0; i < l6; i++) {
                if (!validateIp6Group(ipv6Group[i])) {
                    return "Neither";
                }
            }
            return "IPv6";
        }


        return "Neither";
    }

    boolean validateIp4Group(String ipv4) {

        int l = ipv4.length();
        if ((l > 1 && ipv4.charAt(0) == '0') || l > 3 || ipv4.equals("")) {
            return false;
        }
        for (int i = 0; i < l; i++) {
            if (!(ipv4.charAt(i) >= '0' && ipv4.charAt(i) <= '9')) {
                return false;
            }
        }
        Integer seg = Integer.parseInt(ipv4);
        if (seg >= 0 && seg <= 255) {
            return true;
        }
        return false;
    }

    boolean validateIp6Group(String ipv6) {
        int l = ipv6.length();
        if (l > 4 || ipv6.equals("")) {
            return false;
        }
        for (int i = 0; i < l; i++) {
            boolean num = ipv6.charAt(i) >= '0' && ipv6.charAt(i) <= '9';
            boolean charC = ipv6.charAt(i) >= 'a' && ipv6.charAt(i) <= 'f';
            if (!(num || charC)) return false;
        }
        return true;
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new LinkedHashSet<>();
        wordDict.stream().forEach(e -> wordDictSet.add(e));

        int l = s.length();
        boolean[][] dp = new boolean[l][l];
        List<String>[] ans = new ArrayList[l];

        String[][] dpval = new String[l][l];
        for (int i = 0; i < l; i++) {
            for (int j = i; j < l; j++) {
                String ss = s.substring(i, j + 1);
                if (wordDictSet.contains(ss)) {
                    dp[i][j] = true;
                    dpval[i][j] = ss;
                }
            }
        }

        return generateSentence(s, 0, dp, ans, dpval);
    }

    List<String> generateSentence(String s, int start, boolean[][] dp, List<String>[] ans, String[][] dpval) {
        int l = s.length();

        if (start == l) {
            return new ArrayList<>();
        }
        if (ans[start] != null) {
            return ans[start];
        }
        List<String> allAns = new ArrayList<>();
        for (int i = start; i < l; i++) {
            if (dp[start][i]) {

                List<String> ll = generateSentence(s, i + 1, dp, ans, dpval);
                String ss = " " + dpval[start][i];
                if (start == 0) {
                    ss = dpval[start][i];
                }
                List<String> lla = new ArrayList<>();
                if (ll.size() == 0 && i == s.length() - 1) {
                    lla.add(ss);
                }
                for (String item : ll) {
                    lla.add(ss + item);
                }
                allAns.addAll(lla);
            }
        }
        ans[start] = allAns;
        return allAns;
    }

    public int minSumOfLengths(int[] arr, int target) {
        int l = arr.length;
        int[] left = preProcess(arr, target, false);
        int[] right = preProcess(arr, target, true);

        int ans = Integer.MAX_VALUE;

        for (int i = 1; i < l - 1; i++) {
            if (left[i] != Integer.MAX_VALUE && right[l - i - 2] != Integer.MAX_VALUE) {
                ans = Math.min(left[i] + right[l - i - 2], ans);
            }
        }
        if (ans != Integer.MAX_VALUE) return ans;
        return -1;
    }

    int[] preProcess(int[] arr, int target, boolean reverse) {

        int l = arr.length;
        if (reverse) {
            int s = 0;
            int e = l - 1;
            while (s < e) {
                int t = arr[s];
                arr[s] = arr[e];
                arr[e] = t;
                s++;
                e--;
            }
        }
        int start = 0;
        int end = -1;
        int sum = 0;
        int[] left = new int[l];
        int ans = Integer.MAX_VALUE;
        while (end < l) {

            if (sum == target) {
                ans = Math.min(end - start + 1, ans);
                left[end] = ans;
                sum -= arr[start];
                start++;
                end++;
                if (end == l) break;
                sum += arr[end];
            } else if (sum < target) {
                end++;
                if (end == l) break;
                sum += arr[end];
            } else {
                sum -= arr[start];
                start++;
            }
            left[end] = ans;
        }
        return left;
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {

        int l = nums.length;
        if (l == 0) return new ArrayList<>();

        HashMap<Integer, List<Integer>> subsetMap = new HashMap<>();
        Arrays.sort(nums);
        int maxLen = 0;
        int maxLenVal = nums[0];
        for (int i = 0; i < l; i++) {
            int val = nums[i];
            int max = 0;
            int an = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0) {
                    List<Integer> subset = subsetMap.getOrDefault(nums[j], new ArrayList<>());
                    if (max < subset.size()) {
                        max = subset.size();
                        val = nums[j];
                    }
                }
            }
            List<Integer> subset = subsetMap.getOrDefault(val, new ArrayList<>());
            List<Integer> ls = new ArrayList<>();
            ls.addAll(subset);
            ls.add(nums[i]);

            if (maxLen <= ls.size()) {
                maxLen = ls.size();
                maxLenVal = nums[i];
            }
            subsetMap.put(nums[i], ls);
        }
        return subsetMap.get(maxLenVal);
    }

    public int minDays(int[] bloomDay, int m, int k) {
        int l = bloomDay.length;

        int min = bloomDay[0];
        int max = bloomDay[0];
        for (int i = 0; i < l; i++) {
            min = Math.min(min, bloomDay[i]);
            max = Math.max(max, bloomDay[i]);
        }
        int ans = Integer.MAX_VALUE;

        while (min <= max) {

            int day = (max - min) / 2 + min;
            int maxBt = calculate(day, bloomDay, k);

            if (maxBt >= m) {
                ans = Math.min(ans, day);
                max = day - 1;
            } else {
                min = day + 1;
            }
            if (min + 1 == max) {
                int a = calculate(min, bloomDay, k);
                int b = calculate(max, bloomDay, k);
                if (a >= m) {
                    ans = Math.min(ans, min);
                }
                if (b >= m) {
                    ans = Math.min(ans, max);
                }
                break;
            }
        }
        if (ans != Integer.MAX_VALUE) return ans;
        return -1;
    }

    int calculate(int day, int[] bloomDay, int k) {

        int[] a = bloomDay.clone();
        int l = bloomDay.length;
        for (int i = 0; i < l; i++) {
            if (bloomDay[i] <= day) {
                a[i] = 0;
            }
        }
        int maxBt = 0;
        int count = 0;
        for (int i = 0; i < l; i++) {
            if (a[i] == 0) {
                count++;
            } else {
                count = 0;
            }
            if (count == k) {
                maxBt++;
                count = 0;
            }
        }
        return maxBt;
    }


    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        HashMap<Integer, Integer> mapv = new HashMap<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int l = arr.length;
        for (int i = 0; i < l; i++) {
            int c = mapv.getOrDefault(arr[i], 0) + 1;
            mapv.put(arr[i], c);
        }

        for (Integer key : mapv.keySet()) {
            if (mapv.get(key) > 0) {
                queue.add(mapv.get(key));
            }
        }
        while (!queue.isEmpty() && k > 0) {
            if (k > 0) {
                int count = queue.poll();
                k = k - count;
                if (k < 0) {
                    queue.add(-k);
                }
            }
        }
        return queue.size();
    }

}


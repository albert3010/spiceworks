package leetcode_contest_2022.group1;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

public class Contest_feb {

    @Test
    public void ContestsSolution() {
//
        int[] a = {33, 13, 15, 22, 29, 26, 3, 7};
        int n[] = {3, 10};
        int m[] = {2, 5};
//        System.out.println(maximumScore(a, m));
//        System.out.println(closestCost(n, m, 9));
//        System.out.println(longestDiffSubarray(a, 4));

//        char[][] mat = {
//                {'F', 'F', 'F'},
//                {'.', 'F', '.'},
//                {'.', 'F', 'F'},
//                {'#', 'F', '.'},
//                {'F', 'F', 'F'},
//                {'.', '.', '.'},
//                {'.', '.', '#'},
//                {'.', 'F', '.'}
//        };
//        System.out.println(figureUnderGravity(mat));
        System.out.println(splitString("20000000000000000001"));
        String s = "011010090807060";
        s = s.replaceFirst("^0+(?!$)", "");
//        System.out.println(s);
    }

    public boolean splitString(String s) {
        s = s.replaceFirst("^0+(?!$)", "");
        int l = s.length();
        int st = 0;
        long nextNum = -2;
        int split =0;
        return dfsHelperCheck(s, st, nextNum, split, l);
    }

    public boolean dfsHelperCheck(String s, int st, long nextNum, int split, int l) {
        if (st == l){
            if (split>1)
            return true;
            return false;
        }
        boolean f = false;
        for (int i = st + 1; i <=l; i++) {

            String tmp = s.substring(st, i);
            String s1 = tmp.replaceFirst("^0+(?!$)", "");
            if (s1.length()>11){
                break;
            }
            long num = Long.parseLong(tmp);
            if (nextNum == -2) {
                if (num != 0){
                     f = f  || dfsHelperCheck(s, i, num - 1, split+1, l);
                }
            } else {
                if (num == nextNum) {
                     f = f || dfsHelperCheck(s, i, num - 1, split+1, l);
                }
            }

        }
        return f;
    }


    public int getMinDistance(int[] nums, int target, int start) {
        int l = nums.length;
        int min = l + 1;
        for (int i = 0; i < l; i++) {
            if (target == nums[i]) {
                if (Math.abs(i - start) < min) {
                    min = Math.abs(i - start);
                }
            }
        }
        return min;
    }

    char[][] figureUnderGravity(char[][] matrix) {

        int n = matrix.length;
        int m = matrix[0].length;

        int minFall = n - 1;

        for (int i = 0; i < m; i++) {
//            start index
            int s = -1;
//            end index
            int e = -1;
            for (int j = 0; j < n; j++) {
                if (matrix[j][i] == 'F') {
                    s = j;
                    e = j;
                    if (j == n - 1) minFall = 0;
                }
                if (matrix[j][i] == '#') {
                    if (s != -1)
                        minFall = Math.min(minFall, e - s);
                    s = -1;
                    e = -1;
                }
                if (matrix[j][i] == '.') {
                    if (s != -1) {
                        e = j;
                        if (j == n - 1) {
                            minFall = Math.min(minFall, e - s);
                        }
                    }

                }
            }
        }
        System.out.println(minFall);
        char ans[][] = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 'F') {
                    ans[i + minFall][j] = 'F';
                } else if (matrix[i][j] == '#') {
                    ans[i][j] = '#';
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!(ans[i][j] == 'F' || ans[i][j] == '#')) {
                    ans[i][j] = '.';
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(ans[i][j] + "  ");
            }
            System.out.println();
        }
        return ans;
    }


    int[] longestDiffSubarray(int[] numbers, int diff) {

        int n = numbers.length;
        int pre = 0;
        int curr = 0;
        int ans[] = new int[2];
        ans[0] = 0;
        ans[1] = 0;
        for (int i = 0; i < n; i++) {
            if (i != n - 1) {
                if (-diff <= numbers[i] - numbers[i + 1] && numbers[i] - numbers[i + 1] <= diff) {
                    curr = i + 1;
                    if (ans[1] - ans[0] < curr - pre) {
                        ans[1] = curr;
                        ans[0] = pre;
                    }
                } else {
                    pre = i + 1;
                    curr = i + 1;
                }
            }

        }
        return ans;
    }

    public int minOperations(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int l1 = nums1.length;
        int l2 = nums2.length;
        int min = Math.min(l1, l2);
        int max = min * 6;
        return 1;

    }


    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        int n = baseCosts.length;
        long[] ans = new long[2];
        ans[0] = Math.abs(baseCosts[0] - target);
        ans[1] = baseCosts[0];
        for (int i = 0; i < n; i++) {
            long baseCost = baseCosts[i];
            allCombination(0, toppingCosts, baseCost, target, ans);
        }
        return (int) ans[1];
    }

    private void allCombination(int i, int[] toppingCosts, long baseCost, int target, long[] ans) {
        long diff = Math.abs(baseCost - target);
        if (ans[0] == diff && ans[1] > baseCost) {
            ans[1] = baseCost;
        } else if (ans[0] > diff) {
            ans[0] = diff;
            ans[1] = baseCost;
        }
        if (i == toppingCosts.length) return;
        allCombination(i + 1, toppingCosts, baseCost + 2 * toppingCosts[i], target, ans);
        allCombination(i + 1, toppingCosts, baseCost + toppingCosts[i], target, ans);
        allCombination(i + 1, toppingCosts, baseCost, target, ans);
    }


    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int check = 0;
        if (ruleKey.equals("color")) {
            check = 1;
        } else if (ruleKey.equals("name")) {
            check = 2;
        }
        int finalCheck = check;
        return (int) items.stream().filter(e -> e.get(finalCheck).equals(ruleValue)).count();
    }

    public int maximumScore(int[] nums, int[] multipliers) {

        int n = nums.length;
        int m = multipliers.length;
        HashMap<Integer, HashMap<Integer, Integer>> mapVal = new HashMap<>();

        return dfsHelper(0, n - 1, 0, nums, multipliers, mapVal);
    }

    private int dfsHelper(int i, int j, int k, int[] nums, int[] multipliers, HashMap<Integer, HashMap<Integer, Integer>> mapVal) {
        if (mapVal.containsKey(i) && mapVal.get(i).containsKey(j)) {
            return mapVal.get(i).get(j);
        }
        if (k == multipliers.length) {
            return 0;
        }

        int valLeft = dfsHelper(i + 1, j, k + 1, nums, multipliers, mapVal) + (multipliers[k] * nums[i]);
        int valRight = dfsHelper(i, j - 1, k + 1, nums, multipliers, mapVal) + (multipliers[k] * nums[j]);
        mapVal.putIfAbsent(i, new HashMap<>());
        mapVal.get(i).put(j, Math.max(valLeft, valRight));
        return Math.max(valLeft, valRight);
    }


    public int[] minOperations1(String boxes) {
        int l = boxes.length();
        int ans[] = new int[l];
        for (int i = 0; i < l; i++) {
            ans[i] = getMoves(i, l - 1, boxes);
        }
        return ans;
    }

    private int getMoves(int currentI, int l, String boxes) {
        int count = 0;
        while (l >= 0) {
            if (boxes.charAt(l) == '1') {
                count += Math.abs(currentI - l);
            }
            l--;
        }
        return count;
    }

    public String mergeAlternately(String word1, String word2) {
        int l1 = word1.length();
        int l2 = word2.length();
        int i = 0;
        int j = 0;
        StringBuilder stringBuilder = new StringBuilder("");
        while (i < l1 || j < l2) {
            if (i < l1) {
                stringBuilder.append(word1.charAt(i++));
            }
            if (j < l2) {
                stringBuilder.append(word2.charAt(j++));
            }
        }
        return stringBuilder.toString();
    }

    public boolean canChoose(int[][] groups, int[] nums) {
        int m = groups.length;

        int l = nums.length;
        int[] dup = new int[l];
        int t = l - 1;
        for (int i = 0; i < l; i++) {
            dup[i] = nums[t--];
        }

        int last = 0;
        for (int i = m - 1; i >= 0; i--) {
            int n = groups[i].length;
            boolean found = false;
            for (int j = last; j < l; j++) {
                boolean f = true;
                int count = 0;
                int k = j;
                found = false;
                int tt = n - 1;
                for (; k < l && count < n && tt >= 0; k++) {
                    if (dup[k] != groups[i][tt--]) {
                        f = false;

                        break;
                    } else count++;
                }
                if (f && count == n) {
                    last = k;
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
    }

    public String longestNiceSubstring(String s) {
        int l = s.length();
        String ans = "";
        for (int i = 0; i < l; i++) {
            for (int j = i + 1; j <= l; j++) {
                String sub = s.substring(i, j);
                if (checkNice(sub)) {
                    if (sub.length() > ans.length()) {
                        ans = sub;
                    }
                }

            }
        }
        return ans;
    }

    private boolean checkNice(String sub) {
        boolean[] small = new boolean[26];
        boolean[] big = new boolean[26];

        int l = sub.length();
        for (int i = 0; i < l; i++) {
            char c = sub.charAt(i);
            if (c >= 'a' && c <= 'z') {
                small[c - 'a'] = true;
            } else {
                big[c - 'A'] = true;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (small[i] != big[i]) {
                return false;
            }
        }
        return true;
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

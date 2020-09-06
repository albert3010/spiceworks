package leetcode.contests.ContestAA;

import org.junit.Test;

import java.util.*;

public class Contests1 {

    public class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    @Test
    public void ContestsSolution() {
//        "atmtxzjkz"
//"tvbtjhvjd"
//35
//        System.out.println(removeInvalidParentheses("()())()"));
//        System.out.println(canConvertString("atmtxzjkz","tvbtjhvjd",35));
//        System.out.println(minInsertions("(()))"));
        int[] arr = {1, 2, 1, 2, 1, 1, 1, 3};
//        System.out.println(containsPattern(arr, 2,2));
        System.out.println(numWays("100100010100110"));

    }

    public int minCost(String s, int[] cost) {
        int l = s.length();
        int i = 0;
        int ans = 0;
        while (i < l) {
            int count = 0;
            int max = 0;
            while ((i - 1 < 0) || (i < l && s.charAt(i - 1) == s.charAt(i))) {
                count += cost[i];
                max = Math.max(cost[i], max);
                i++;
            }
            ans += count - max;
        }
        return ans;
    }

    public int numTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        HashMap<Double, Double> map1 = new HashMap<>();
        HashMap<Double, Double> map2 = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Double x = (double) nums1[i] * (double) nums1[j];
                map1.put(x, map1.getOrDefault(x, 0.0) + 1);
            }
        }
        double ans = 0;
        for (int i = 0; i < m; i++) {
            ans += map1.getOrDefault((double) nums2[i] * (double) nums2[i], 0.0);
            for (int j = i + 1; j < m; j++) {
                double x = (double) nums2[i] * (double) nums2[j];
                map2.put(x, map2.getOrDefault(x, 0.0) + 1.0);
            }
        }
        for (int i = 0; i < n; i++) {
            ans += map2.getOrDefault((double) nums1[i] * (double) nums1[i], 0.0);
        }
        return (int) ans;
    }

    public String modifyString(String s) {
        int l = s.length();
        s = "a" + s + "b";
        StringBuilder ans = new StringBuilder();

        for (int i = 1; i < l; i++) {
            if (s.charAt(i) == '?') {
                if (s.charAt(i - 1) != 'a' && s.charAt(i + 1) != 'a') {
                    ans.append('a');
                } else if (s.charAt(i - 1) != 'b' && s.charAt(i + 1) != 'b') {
                    ans.append('b');
                } else if (s.charAt(i - 1) != 'c' && s.charAt(i + 1) != 'c') {
                    ans.append('c');
                }
            } else {
                ans.append(s.charAt(i));
            }
        }
        ans.substring(1);
        return ans.toString();
    }

    public int numWays(String s) {
        double l = s.length();
        int oneCount = 0;
        int mod = 1000000000 + 7;
        for (int i = 0; i < l; i++) {
            if (s.charAt(i) == '1') {
                oneCount++;
            }
        }
        if (oneCount % 3 != 0) return 0;
        int val = oneCount / 3;
        int i = 0;
        int count = 0;
        double[] t = new double[6];
        int x = 0;
        while (i < l) {
            if (s.charAt(i) == '1') {
                count++;
                if (count == 1) {
                    t[x++] = i;
                }
            }

            if (count == val) {
                t[x++] = i;
                count = 0;
            }
            i++;
        }
        double ans = (t[2] - t[1]) * (t[4] - t[3]) % mod;
        return (int) ans;

    }

    public boolean containsPattern(int[] arr, int m, int k) {
        int n = arr.length;
        int l = m * k;
        for (int i = 0; i < n; i++) {
            for (int j = i + l - 1, t = i; j < n; t++, j++) {
                int a = t;
                int b = j;
                StringBuilder pat = new StringBuilder();
                int x = 0;
                int xx = 0;
                while (a <= b) {
                    pat.append(arr[a]);
                    pat.append("#");
                    a++;
                    if (x < m) {
                        x++;
                        xx = pat.length();
                    }
                }
                String check = pat.substring(xx);
                int ii = pat.indexOf(check);
                if (ii == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canConvertString(String s, String t, int k) {
        int n = s.length();
        int m = t.length();
        if (n != m) return false;

        Set<Integer> set = new HashSet<>();
        int[] countUsed = new int[26];

        for (int i = 0; i < n; i++) {
            int a = s.charAt(i) - 'a';
            int b = t.charAt(i) - 'a';
            int val = 0;
            if (a == b) {
                val = 0;
            } else if (a <= b) {
                val = b - a;
            } else {
                val = 26 - (a - b);
            }
            if (val > 0) {
                if ((val + countUsed[val] * 26) <= k) {
                    countUsed[val]++;
                } else {
                    return false;
                }
            }
        }
        StringBuilder ss = new StringBuilder("");
        ss.append(1);

        int max = Integer.MAX_VALUE;
        return true;

    }


    public int minInsertions(String s) {

        int n = s.length();
        Stack<Character> st = new Stack();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                st.push('(');
            } else {
                st.push(')');
                if (st.size() >= 3) {
                    char c1 = st.pop();
                    char c2 = st.pop();
                    char c3 = st.pop();
                    if (!(c1 == ')' && c2 == ')' && c3 == '(')) {
                        st.push(c3);
                        st.push(c2);
                        st.push(c1);
                    }
                }

            }
        }
        int ans = 0;
        while (!st.isEmpty()) {
            char c = st.pop();
            if (c == '(') {
                ans += 2;
            } else {
                if (st.size() == 0) {
                    ans += 2;
                } else {
                    char c2 = st.pop();
                    if (c2 == '(') {
                        ans++;
                    } else {
                        if (st.size() == 0) {
                            ans++;
                        } else {
                            char c3 = st.pop();
                            if (c3 == ')') {
                                st.push(')');
                            }
                        }
                    }

                }

            }


        }
        return ans;
    }

    public int minMeetingRooms(List<Interval> intervals) {
        int t = intervals.stream().mapToInt(e -> e.end).max().getAsInt();
        int T[] = new int[t + 2];
        intervals.forEach(e -> {
            T[e.start]++;
            T[e.end]--;
        });
        int max = 0;
        int ans = 0;
        for (int i = 0; i <= t; i++) {
            ans = T[i] + ans;
            max = Math.max(ans, max);
        }
        return max;
    }

    public int findSpecialInteger(int[] arr) {
        int n = arr.length;

        int preVal = arr[0];
        int preFq = 1;
        int currVal = arr[0];
        int currFq = 1;

        for (int i = 1; i < n; i++) {
            if (arr[i] != arr[i - 1]) {
                currVal = arr[i];
                currFq = 1;
            }
            if (arr[i] == arr[i - 1]) {
                currFq++;
            }
            if (currFq > preFq) {
                preFq = currFq;
                preVal = currVal;
            }
        }
        return preVal;
    }


    public List<String> removeInvalidParentheses(String s) {
        Set<String> results = new HashSet<>();
        backtrackingHelper(s, 0, "", 0, 0, results);
        List<String> aList = new ArrayList<>();
        aList.addAll(results);
        return aList;
    }

    public void backtrackingHelper(String s, int index, String ans, int leftCount, int rightCount, Set<String> results) {
        if (leftCount < rightCount) {
            return;
        }
        if (index == s.length()) {
            if (leftCount == rightCount) {
                if (results.size() == 0) {
                    results.add(ans);
                } else {
                    int l = 0;
                    for (String ss : results) {
                        l = ss.length();
                        break;
                    }
                    if (ans.length() > l) {
                        results.clear();
                    }
                    if (ans.length() >= l) {
                        results.add(ans);
                    }
                }
            }
            return;
        }

        char c = s.charAt(index);
        int l = 0, r = 0;
        if (c == '(') {
            l = 1;
        } else if (c == ')') {
            r = 1;
        }
        if (l == 1 || r == 1) {
            backtrackingHelper(s, index + 1, ans, leftCount, rightCount, results);
        }
        backtrackingHelper(s, index + 1, ans + c, leftCount + l, rightCount + r, results);
    }

}

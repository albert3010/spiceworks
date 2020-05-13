package leetcode.contests.ContetsAE;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Contests5 {

    @Test
    public void ContestsSolution() {
//        System.out.println(numberOfArrays("2020", 30));
//        System.out.println(reformat("6"));

//        ["David","3","Ceviche"]
//        List<List<String>> orders = new ArrayList<>();
//        List<String> it = new ArrayList<>();
//        it.add("David");
//        it.add("3");
//        it.add("Ceviche");
//        orders.add(it);
//        System.out.println(displayTable(orders));
//        System.out.println(minNumberOfFrogs("croackroakcroakcroak"));
        int a[] = {4, 5, 6, 7, 0, 1, 2};
//        System.out.println(search(a, 5));
        System.out.println(rangeBitwiseAnd(5, 7));
    }
    public int rangeBitwiseAnd(int m, int n) {
        int min = m;
        int max = n;
        int v =0;
        int x =0;
        int ans =0;
        while (min>0){

            int t = min&1;
            int num = 1<<x;
            if(t==1){
                v = v | 1<<(x);
            }
            if( t>0 && num+v<=max){
                ans= ans | num;
            }
            x++;
            min = min<<1;
        }
        return ans;
    }

    public int search(int[] nums, int target) {
        int n = nums.length;
        int s = 0;
        int e = n - 1;
        while (s <= e) {
            int mid = (s + e) / 2;
            if (nums[mid] == target) return mid;

            if (s + 1 == e) {
                if (nums[s] == target) return s;
                if (nums[e] == target) return e;
                return -1;
            }

            if (nums[mid] < nums[s] && nums[mid] < nums[e]) {
                if (nums[s] < nums[e]) {
                    if (isBetween(nums[mid], nums[s], target)) {
                        e = mid - 1;
                    }
                } else {
                    if (!isBetween(nums[mid], nums[e], target)) {
                        e = mid - 1;
                    }
                }
            } else if (nums[mid] > nums[s] && nums[mid] > nums[e]) {
                if (nums[s] < nums[e]) {
                    if (!isBetween(nums[e], nums[mid], target)) {
                        e = mid - 1;
                    }
                } else {
                    if (isBetween(nums[s], nums[mid], target)) {
                        e = mid - 1;
                    }
                }
            } else {
                if (target < nums[mid]) {
                    e = mid - 1;
                }
            }
            s = mid+1;
        }
        return -1;
    }

    public boolean isBetween(int a, int b, int v) {
        if (a <= v && v <= b) return true;
        return false;
    }

    public int minNumberOfFrogs(String croakOfFrogs) {
        int cc[] = new int[26];
        int l = croakOfFrogs.length();
        for (int i = 0; i < l; i++) {
            char c = croakOfFrogs.charAt(i);
            cc[c - 'a']++;
        }

        char v[] = {'c', 'r', 'o', 'a', 'k'};
        for (int i = 1; i < 5; i++) {
            if (cc[v[i] - 'a'] != cc[v[i - 1] - 'a']) return -1;
        }

        HashMap<Character, Integer> order = new HashMap<>();
        order.put('c', 0);
        order.put('r', 1);
        order.put('o', 2);
        order.put('a', 3);
        order.put('k', 4);
        boolean dp[][] = new boolean[20001][6];

        int count[] = new int[26];
        int lock = 0;
        int release = 0;
        for (int i = 0; i < l; i++) {
            char c = croakOfFrogs.charAt(i);
            count[c - 'a']++;
            int t = count[c - 'a'];
            if (c == 'c') {
                dp[t][0] = true;
                dp[t][1] = true;
                if (release >= lock) {
                    lock++;
                }
                release++;
            } else {
                if (!dp[t][order.get(c)]) return -1;
                dp[t][order.get(c) + 1] = true;
                if (c == 'k') {
                    release--;
                }
            }

        }
        return lock;
    }

    public List<List<String>> displayTable(List<List<String>> orders) {
        int n = orders.size();
        HashMap<String, HashMap<String, Integer>> map = new HashMap<>();
        Set<String> items = new HashSet<>();
        Set<String> table = new HashSet<>();
        for (int i = 0; i < n; i++) {
            List<String> list = orders.get(i);
            items.add(list.get(2));
            table.add(list.get(1));
            HashMap<String, Integer> mp = map.getOrDefault(list.get(1), new HashMap<String, Integer>());
            mp.put(list.get(2), mp.getOrDefault(list.get(2), 0) + 1);
            map.put(list.get(1), mp);
        }
        List<String> ss = items.stream().collect(Collectors.toList());

        List<String> tb = table.stream().collect(Collectors.toList());
        Collections.sort(ss);
        Collections.sort(tb, (a, b) -> Integer.parseInt(a) - Integer.parseInt(b));
        List<List<String>> ans = new ArrayList<>();
        int ll = ss.size();
        List<String> tmp = new ArrayList<>();
        tmp.add("Table");
        for (int i = 0; i < ll; i++) {
            tmp.add(ss.get(i));
        }
        ans.add(tmp);
        ll = tb.size();
        for (int j = 0; j < ll; j++) {
            tmp = new ArrayList<>();
            tmp.add(tb.get(j));
            int x = ss.size();
            for (int i = 0; i < x; i++) {
                tmp.add(map.get(tb.get(i)).get(ss.get(i)).toString());
            }
            ans.add(tmp);
        }

        return ans;
    }

    public String reformat(String s) {
        int l = s.length();
        String ss = "";
        String n = "";
        for (int i = 0; i < l; i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                n += c;
            } else {
                ss += c;
            }
        }
        String ans = "";
        int i = 0;
        int m = Math.min(ss.length(), n.length());
        int max = Math.max(ss.length(), n.length());
        if (max - m > 1) return ans;
        while (i < m) {
            ans += ss.charAt(i);
            ans += n.charAt(i);
            i++;
        }
        if (max - m == 0) return ans;
        if (max == 1) return ss + n;
        if (ss.length() > n.length()) {
            ans += ss.charAt(i);
        } else {
            char c = s.charAt(0);
            ans = ans.substring(1);
            ans += c;
            ans += n.charAt(i);
        }
        return ans;
    }

    public int numberOfArrays(String s, int k) {
        int n = s.length();
        int dp[] = new int[n + 1];
        int mod = 1000000000 + 7;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i, l = 0; j < n && l < 9; j++, l++) {
                String ss = s.substring(i, j + 1);
                if (ss.length() == 10 && ss.charAt(0) >= '2') break;
                int val = Integer.parseInt(ss);
                if (ss.charAt(0) != '0' && val != 0 && val <= k) {
                    if (i == n - 1) {
                        dp[i] = 1;
                    } else {
                        if (dp[j + 1] != 0)
                            dp[i] = (dp[i] + dp[j + 1]) % mod;
                        if (j == n - 1) {
                            dp[i] = dp[i] + 1;
                        }
                    }

                }
            }

        }
        return dp[0];
    }

    public int minStartValue(int[] nums) {
        int n = nums.length;
        int sum = 0;
        int min = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            min = Math.min(sum, min);
        }
        return 1 - min;
    }

    public int findMinFibonacciNumbers(int k) {
        List<Integer> fb = new ArrayList<>();
        fb.add(1);
        fb.add(1);
        int i = 2;
        while (true) {
            int val = fb.get(i - 1) + fb.get(i - 2);
            if (val <= k) {
                fb.add(val);
                i++;
            } else {
                break;
            }
        }
        int count = 0;
        int l = fb.size() - 1;
        while (l >= 0) {
            count += k / fb.get(l);
            k = k % fb.get(l);
            l--;
        }
        return count;
    }


    public String getHappyString(int n, int k) {
        int count[] = new int[1];
        String ss[] = new String[1];
        ss[0] = "";
        helper("", 0, n, k, count, ss);
        return ss[0];
    }

    public void helper(String s, int i, int n, int k, int count[], String ss[]) {
        if (s.length() > n) {
            return;
        }
        if (s.length() == n) {
            count[0]++;
            if (count[0] == k) {
                ss[0] = s;
            }
        }

        for (int j = 0; j < 3; j++) {
            char c = (char) ('a' + j);
            if (i == 0) {
                helper(s + c, i + 1, n, k, count, ss);
            } else {
                int l = s.length();
                if (s.charAt(l - 1) != c) {
                    helper(s + c, i + 1, n, k, count, ss);
                }
            }
        }

    }

}

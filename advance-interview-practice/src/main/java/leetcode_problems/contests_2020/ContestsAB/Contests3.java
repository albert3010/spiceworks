package leetcode_problems.contests_2020.ContestsAB;

import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;


public class Contests3 {

    static class Node implements Comparable<Node> {
        int a, b;

        public Node(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Node o) {
            return Math.abs(o.a - o.b) - Math.abs(this.a - this.b);

        }
    }

    @Test
    public void ContestsSolution() {
//        int[][] costs = {{10, 20}, {400, 50}, {30, 200}, {40, 30}, {40, 100}, {30, 20}};
        int[][] costs = {{259, 770}, {448, 54}, {926, 667}, {184, 139}, {840, 118}, {577, 469}};
//        System.out.println(twoCitySchedCost(costs));

        int[] weights = {1, 2, 3, 1, 1};
        int D = 5 - 1;
//        System.out.println(shipWithinDays(weights, D));
//        System.out.println(maximum69Number(9669));
//        System.out.println(printVertically("TO BE OR NOT TO BE"));
//cbaebabacd
        System.out.println(findAnagrams("cbaebabacdaabcbcbacbacbcacbacbbcaaccbbccxaaaacbbca", "abc"));

    }

    class Node1 implements Comparable<Node1> {
        int index;
        String val;

        Node1(int index, String val) {
            this.index = index;
            this.val = val;
        }

        @Override
        public int compareTo(Node1 o) {
            if (this.val.length() == o.val.length()) {
                return this.index - o.index;
            }
            return this.val.length() - o.val.length();
        }

    }

    public List<Integer> findAnagrams(String s, String p) {
        int l = s.length();
        int n = p.length();
        int pp[] = new int[26];
        int ss[] = new int[26];
        IntStream.range(0, n).forEach(i -> pp[p.charAt(i) - 'a']++);
        List<Integer> ans = new ArrayList<>();
        int prevIn = 0;
        for (int i = 0; i < l; i++) {
            ss[s.charAt(i) - 'a']++;
            while (compareArray(ss, pp) == -1) {
                ss[s.charAt(prevIn) - 'a']--;
                prevIn++;
            }

            if (compareArray(ss, pp) == 0) {
                ans.add(prevIn);
                ss[s.charAt(prevIn) - 'a']--;
                prevIn++;
            }

        }
        return ans;
    }

    public int compareArray(int a[], int[] b) {
        int fl = 0;
        for (int i = 0; i < 26; i++) {
            if (a[i] > b[i]) {
                return -1;
            }
            if (a[i] != b[i]) {
                fl = 1;
            }
        }
        return fl;
    }

    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        HashMap<String, Integer> map = new HashMap<>();
        int l = favoriteCompanies.size();
        int in = 1;
        List<Set<String>> tmp = new ArrayList<>();
        for (List<String> ls : favoriteCompanies) {
            Set<String> set = new HashSet<>();
            for (String s : ls) {
                if (map.get(s) == null) {
                    map.put(s, in);
                    set.add(Integer.toString(in));
                    in++;
                } else {
                    set.add(Integer.toString(map.get(s)));
                }
            }
            tmp.add(set);
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < l; i++) {
            boolean flag = true;
            for (int j = 0; j < l; j++) {
                if (i == j) continue;
                Set<String> a = tmp.get(i);
                Set<String> b = tmp.get(j);
                int tt = 0;
                for (String s : a) {
                    if (b.contains(s)) {
                        tt++;
                    }
                }
                if (tt == a.size()) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ans.add(i);
            }
        }
        return ans;
    }

    public String arrangeWords(String text) {

        String[] texts = text.split(" ");

        int l = texts.length;

        List<Node1> ls = new ArrayList<>();

        for (int i = 0; i < l; i++) {
            ls.add(new Node1(i, texts[i]));
        }
        Collections.sort(ls);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < l; i++) {
            String s = ls.get(i).val;
            if (i == 0) {
                char c = (char) (s.charAt(0) - 32);
                result.append(c);
                s = s.substring(1);

            }
            result.append(s);
            if (i != l) result.append(" ");
        }

        return result.toString();

    }

    public List<String> printVertically(String s) {
        String[] words = s.split(" ");
        int n = words.length;

        List<String> ans = new ArrayList<>();
        int index = 0;
        while (true) {
            String tmp = "";
            int lastIndex = -1;
            int count = 0;
            for (int i = 0; i < n; i++) {
                int l = words[i].length();
                if (index + 1 <= l) {
                    tmp += words[i].charAt(index);
                    lastIndex = i;
                    count = 1;
                } else {
                    tmp += " ";
                }
            }
            if (count == 0) {
                break;
            }
            if (lastIndex != -1) {
                ans.add(tmp.substring(0, lastIndex + 1));
            }
            index++;
        }
        return ans;

    }

    public int maximum69Number(int num) {
        int t = 1;
        int n = num;
        int count = 0;
        while (n > 0) {
            int d = n % 10;
            if (d == 6) {
                count++;
            }
            n = n / 10;
        }
        n = 0;
        int x = 0;
        while (num > 0) {
            int d = num % 10;
            if (d == 6) {
                x++;
            }
            if (x == count) {
                d = 9;
            }
            n = n + t * d;
            t = t * 10;
            num = num / 10;
        }
        return n;
    }

    public int shipWithinDays(int[] weights, int D) {
        int n = weights.length;
        int sum[] = new int[n];
        sum[0] = weights[0];

        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + weights[i];
        }
        int dp[][] = new int[n][D + 1];

        for (int i = 0; i < n; i++) {
            for (int d = 1; d <= D; d++) {
                dp[i][d] = Integer.MAX_VALUE;
                if (i == 0 || d == 1) {
                    dp[i][d] = sum[i];
                    continue;
                }
                for (int k = 0; k < i; k++) {
                    dp[i][d] = Math.min(dp[i][d], Math.max(dp[k][d - 1], sum[i] - sum[k]));
                }
            }
        }
        return dp[n - 1][D];
    }

    public int twoCitySchedCost(int[][] costs) {

        int n = costs.length;
        List<Node> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arr.add(new Node(costs[i][0], costs[i][1]));
        }
        Collections.sort(arr);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (i < n / 2) {
                ans += arr.get(i).a;
            } else {
                ans += arr.get(i).b;
            }
        }
        return ans;
    }
}

package leetcode_problems.contests_2020.ContetsAE;

import org.junit.Test;

import java.util.*;

public class Contests9 {
    static int dp1[];

    String result = "";

    @Test
    public void ContestsSolution() {
//        updatePrimes();
//        System.out.println(primeDigitSums(6652));
        int xc = -1;
        String ss = "_" + xc;
        StringBuilder c = new StringBuilder(ss);
        List<Integer> arr = Arrays.asList(4, 8, 10, 13, 14, 17, 20, 25);
        SortedMap<Integer, Integer> map = new TreeMap<>();
        map.put(4, 1);
        map.put(2, 1);
        map.put(4, 2);
        map.put(1, 1);
        map.put(7, 1);
        map.put(10, 1);
        for (Integer k : map.keySet()) {
            System.out.println(k);
        }
        Collections.binarySearch(arr, 9);

//        System.out.println(findLongestWord("bab", Arrays.asList("ab", "ba")));
    }

    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        Arrays.sort(tasks);
        Arrays.sort(workers);

        int st = 0;
        int end = Math.min(tasks.length, workers.length)-1;
        int ans =0;
        while(st<=end){
            int mid = (st+end)/2;
            if(isPossible(tasks, workers, pills, strength, mid)){
                st = mid+1;
                ans = Math.max(ans, mid+1);
            }else{
                end = mid-1;
            }
        }
        return ans;
    }

    boolean isPossible(int[] tasks, int[] workers, int pills, int strength, int mid) {
        int n = workers.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int i = n - 1, count = 0; count <= mid && i >= 0; i--, count++) {
            map.merge(workers[i], 1, (a, b) -> a + b);
        }
        int done = n - 1;
        for (int i = mid; i >= 0; i--) {
            int val = tasks[i];

                Integer kv = map.ceilingKey(val);
                if (kv != null) {
                    int key = kv;
                    int vl = map.get(key);
                    map.put(key, vl - 1);
                    if (vl - 1 == 0) {
                        map.remove(key);
                    }
                } else {
                    if(pills>0){
                        int newStrg = val-strength;
                        Integer newKv = map.ceilingKey(newStrg);
                        if(newKv!=null){
                            int vl = map.get(newKv);
                            map.put(newKv, vl - 1);
                            if (vl - 1 == 0) {
                                map.remove(newKv);
                            }
                            pills--;
                        }else {
                            return false;
                        }
                    } else {
                        return false;
                    }
            }
        }
        return true;
    }

    public String kthLargestNumber(String[] nums, int k) {
        int l = nums.length;
        Arrays.sort(nums, (a, b) -> {

            if (a.length() == b.length()) {
                return a.compareTo(b);
            }
            return a.length() - b.length();
        });
        return nums[l - k];
    }

    public int solveArray(int[] balloon) {

        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < balloon.length; i++) {
            if (map.containsKey(balloon[i])) {
                map.put(balloon[i], map.getOrDefault(balloon[i], 0) - 1);
                if (map.get(balloon[i]) == 0) {
                    map.remove(balloon[i]);
                }
                map.put(balloon[i] - 1, map.getOrDefault(balloon[i] - 1, 0) + 1);
            } else {
                ans++;
                map.put(balloon[i] - 1, map.getOrDefault(balloon[i] - 1, 0) + 1);
            }
        }
        return ans;
    }

    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        int[] sum = new int[n];
        sum[0] = stones[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + stones[i];
        }

        int[][][] dp = new int[n + 1][n + 1][k + 1];
        for (int i = 0; i < n; i++) {
            dp[i][i][1] = stones[i];
        }

        for (int gap = 1; gap < n; gap++) {
            for (int s = 0; s <= n - gap; s++) {
                int end = s + gap;
                for (int kk = 2; kk <= k; kk++) {
                    int min = Integer.MAX_VALUE;
                    min = Math.min(min, stones[s] + dp[s + 1][end][kk - 1]);
                    min = Math.min(min, dp[s][end - 1][kk - 1] + stones[end]);
                    for (int t = s + 1; t < end; t++) {

                        for (int i = 1; i < kk; i++) {
                            min = Math.min(min, dp[s][t - 1][i] + stones[t] + dp[t + 1][end][kk - 1 - i]);
                        }
                        dp[s][end][kk] = min;
                    }

                }

            }

        }
        return dp[n - 1][n - 1][k];
    }

    public int openLock(String[] deadends, String target) {

        int l = deadends.length;


        return 0;
    }

    public static Set<Integer> getPrimes() {
        //        2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43
        return new HashSet<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43));

    }

    class Node {
        int index;
        boolean isWord = false;
        Node[] chidrens = new Node[26];

        Node(int index) {
            this.index = index;
        }
    }

    public String findLongestWord(String s, List<String> dictionary) {

        int l = dictionary.size();
        int max = 0;
        int index = -1;
        int[] dp = new int[l];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < l; j++) {
                if (s.charAt(i) == dictionary.get(j).charAt(dp[j])) {
                    dp[j]++;
                    if (max < dp[j]) {
                        max = dp[j];
                        index = j;
                    }
                    if (max == dp[j]) {
                        if (index == -1) {
                            index = j;
                        } else {
                            if (dictionary.get(j).compareTo(dictionary.get(index)) < 0) {
                                index = j;
                            }
                        }
                    }
                }
            }
        }
        if (index == -1) return "";

        return dictionary.get(index);
    }

    public String findLongestWord2(String s, List<String> dictionary) {

        Node root = new Node(-1);

        for (String ss : dictionary) {
            buildTrie(root, ss, 0);
        }
        Stack<Integer> ans = new Stack<>();

        searchLongest(s, root, 0, ans, false);

        return result;
    }

    void searchLongest(String s, Node root, int i, Stack<Integer> ans, boolean isWord) {

        if (i == s.length()) {

            if (ans.size() >= result.length() && isWord) {
                Stack<Integer> res = (Stack<Integer>) ans.clone();

                StringBuilder anss = new StringBuilder();
                while (!res.isEmpty()) {
                    char c = (char) ((int) 'a' + res.pop());
                    anss.append(c);
                }
                String rev = anss.reverse().toString();
                if (ans.size() > result.length()) {
                    result = rev;
                } else {
                    if (rev.compareTo(result) < 0) {
                        result = rev;
                    }
                }

            }
            return;
        }

        int index = s.charAt(i) - 'a';

        if (root.chidrens[index] != null) {
            ans.push(index);
            searchLongest(s, root.chidrens[index], i + 1, ans, root.chidrens[index].isWord);
            ans.pop();
        }
        searchLongest(s, root, i + 1, ans, isWord);

    }

    void buildTrie(Node root, String s, int i) {

        if (s.length() == i) return;

        int index = s.charAt(i++) - 'a';
        if (root.chidrens[index] == null) {
            root.chidrens[index] = new Node(index);
            if (s.length() == i) {
                root.chidrens[index].isWord = true;
            }
        }

        buildTrie(root.chidrens[index], s, i);
    }


    public List<String> alertNames(String[] keyName, String[] keyTime) {

        HashMap<String, List<Integer>> map = new HashMap<>();
        int n = keyName.length;
        List<String> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            List<Integer> list = map.getOrDefault(keyName[i], new ArrayList<>());
            list.add(getValueOfTime(keyTime[i]));
            map.put(keyName[i], list);
        }

        for (String key : map.keySet()) {
            List<Integer> list = map.get(key);
            List<Integer> list2 = new ArrayList<>(list);
            list2.addAll(list);

            if (solve(list2)) {
                ans.add(key);
            }
        }
        Collections.sort(ans);
        return ans;
    }

    public boolean solve(List<Integer> times) {
        Deque<Integer> deque = new LinkedList<>();
        int n = times.size();

        for (int i = 0; i < n; i++) {
            deque.add(times.get(i));
            while (!deque.isEmpty()) {
                int fr = deque.getFirst();
                int ls = deque.getLast();
                if (getTimeDiff(fr, ls) > 60) {
                    deque.pollFirst();
                } else {
                    if (deque.size() > 2) {
                        return true;
                    } else return false;
                }
            }
            if (deque.size() > 2) {
                return true;
            }

        }
        return false;
    }

    int getTimeDiff(int val1, int val2) {
        if (val2 == 0) {
            return 2400 + val2 - val1;
        }
        return val2 - val1;
    }

    int getValueOfTime(String time) {
        String hr = time.substring(0, 2);
        String min = time.substring(3, 5);
        int val = Integer.valueOf(hr) * 100 + Integer.valueOf(min);
        return val;

    }


    public static List<List<Integer>> get3DigitPrimes(Set<Integer> primes) {
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k <= 9; k++) {
                    int sum = i + j + k;
                    if (primes.contains(sum)) {
                        lists.add(Arrays.asList(i, j, k));
                    }
                }
            }
        }
        return lists;
    }

    public static List<List<Integer>> updateTo4DigitPrimes(List<List<Integer>> list, Set<Integer> primes) {
        List<List<Integer>> lists = new ArrayList<>();
        list.forEach(e -> {
            int sum = e.stream().mapToInt(i -> i).sum();
            for (int i = 0; i <= 9; i++) {
                int v = e.get(0);
                if (primes.contains(sum + i) && primes.contains(sum + i - v)) {
                    List<Integer> ls = new ArrayList<>();
                    e.forEach(k -> ls.add(k));
                    ls.add(i);
                    lists.add(ls);
                }
            }
        });
        return lists;
    }

    public static List<List<Integer>> updateTo5DigitPrimes(List<List<Integer>> list, Set<Integer> primes) {
        List<List<Integer>> lists = new ArrayList<>();
        list.stream().forEach(e -> {
            int sum = e.stream().mapToInt(i -> i).sum();
            for (int i = 0; i <= 9; i++) {
                int v = e.get(0);
                int v1 = e.get(1);

                if (primes.contains(sum + i) && primes.contains(sum + i - v) && primes.contains(sum + i - v - v1)) {
                    List<Integer> ls = new ArrayList<>();
                    e.forEach(k -> ls.add(k));
                    ls.add(i);
                    lists.add(ls);
                }
            }
        });
        return lists;
    }

    public static String getString(List<Integer> ls) {
        StringBuilder s = new StringBuilder();
        ls.forEach(i -> {
            s.append(i.toString());
        });
        return s.toString();
    }

    public static List<List<Integer>> get5DigitPrimesHash(List<List<Integer>> list) {
        List<List<Integer>> lists = new ArrayList<>();
        list.forEach(e -> {
            String key = getString(e);
            String sr = "1" + key.substring(1);
            String sl = "1" + key.substring(0, 4);

            lists.add(Arrays.asList(Integer.parseInt(sr), Integer.parseInt(sl)));
        });
        return lists;
    }

    public static List<List<Integer>> removeZero(List<List<Integer>> list) {
        List<List<Integer>> lists = new ArrayList<>();
        list.forEach(e -> {
            if (e.get(0) != 0) {
                lists.add(e);
            }
        });
        return lists;
    }

    public static void updatePrimeHash(List<List<Integer>> list, int[] primeHash, HashMap<Integer, Integer> valToIndex) {
        int n = list.size();
        for (int i = 0; i < n; i++) {
            List<Integer> e = list.get(i);
            String s = "1" + getString(e).substring(1);
            primeHash[valToIndex.get(Integer.parseInt(s))]++;
        }
    }

    public static HashMap<Integer, Integer> updatePrimeHash4(List<List<Integer>> list) {
        int n = list.size();
        int ct = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            List<Integer> e = list.get(i);
            String s = "1" + getString(e);
            set.add(Integer.parseInt(s));
        }
        HashMap<Integer, Integer> pmapVtoI = new HashMap<>();
        for (Integer a : set) {
            pmapVtoI.put(a, ct);
            ct++;
        }
        return pmapVtoI;
    }

    static void updatePrimes() {
        int n = 200001;
        int mod = 1000000007;
        int dp[] = new int[n];
        int[] primeHash = new int[416];
        Set<Integer> primes = getPrimes();
        List<List<Integer>> lists3 = get3DigitPrimes(primes);
        List<List<Integer>> lists4 = updateTo4DigitPrimes(lists3, primes);
        List<List<Integer>> lists5 = updateTo5DigitPrimes(lists4, primes);
        List<List<Integer>> primeSet = get5DigitPrimesHash(lists5);
        HashMap<Integer, Integer> valToIndex = updatePrimeHash4(lists4);
        lists5 = removeZero(lists5);
        dp[1] = 9;
        dp[2] = 90;
        dp[3] = removeZero(lists3).size();
        dp[4] = removeZero(lists4).size();
        dp[5] = lists5.size();
        updatePrimeHash(lists5, primeHash, valToIndex);
        int lk = primeSet.size();
        for (int i = 6; i < n; i++) {
            int[] primeHash2 = new int[416];
            long sum = 0;
            for (int k = 0; k < lk; k++) {
                // a b c d e

                Integer sr = primeSet.get(k).get(0);
                Integer sl = primeSet.get(k).get(1);
                int cnt = primeHash[valToIndex.get(sl)];
                sum = (sum + cnt) % mod;
                primeHash2[valToIndex.get(sr)] = (primeHash2[valToIndex.get(sr)] + cnt) % mod;
            }
            primeHash = primeHash2;
            dp[i] = (int) (sum % mod);
        }
        dp1 = dp;
    }

    static int primeDigitSums(int n) {
        return dp1[n];
    }
}

package leetcode_problems.june_contests.S1;

import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;

public class Solution5 {
    int minSumGloble;
    Stack<Integer> st = new Stack<>();
    @Test
    public void function() {
//0,1,3, 5,6,8
        int[] cookies = {8,15,10,20,8};
//        int[] ct = {100};
//        System.out.println(maximumGap(ct));
//        System.out.println(solve(6, new int[]{1, 2, 4, 4, 1, 2}));
//        System.out.println(SetBitCount2(6, "101111", 3));
        System.out.println(distributeCookies(cookies, 1));

    }
    public int distributeCookies(int[] cookies, int k) {
        int n = cookies.length;
        for(int i=0; i< n ;i++){
            minSumGloble+=cookies[i];
        }

        boolean [] taken = new boolean [n];

        solve(0, cookies, taken, k, 0);
        return minSumGloble;

    }
    void solve(int j, int [] cookies, boolean [] taken, int k, int tsum){
        int n = cookies.length;
        if(k==1){
            int sum =0;
            for(int i=0; i< n ;i++){
                if(taken[i]){
                    sum+=cookies[i];
                }
            }
           Stack<Integer> sts = new Stack<>();
           while (!st.isEmpty()){
               sts.push(st.peek());
               sum = Math.max(sum, st.pop());
           }
           while (!sts.isEmpty()){
               st.push(sts.pop());
           }
            minSumGloble = Math.min(minSumGloble, sum);
            return;
        }
        if(j==n){
            st.push(tsum);
            solve(0, cookies, taken, k-1, 0);
            st.pop();
            return;
        }
        if(!taken[j]){
            taken[j] = true;
            solve(j+1, cookies, taken, k, tsum+cookies[j]);
            taken[j] = false;
        }
        solve(j+1, cookies, taken, k, tsum);

    }



    int solve1(int a[]) {
        int n = a.length;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(a[i]);
        }
        int diff1 = getDiff(list);
        Collections.reverse(list);
        int diff2 = getDiff(list);
        return Math.max(diff1, diff2);
    }
    int getDiff(List<Integer> list){
        int n = list.size();
        int mid = n / 2;
        int r = mid;
        if (n % 2 != 0) {
            r++;
        }
        int sum1=list.get(0);

        if(n==1) return sum1;
        int sum2=list.get(n-1);

        for (int i = 1; i < mid; i++) {
            if (list.get(i - 1) % 2 == 0 && list.get(i + 1) % 2 == 0) {
                int sum = (list.get(i - 1) + list.get(i + 1)) / 2;
                if (sum > list.get(i)) {
                    list.set(i, sum);
                }
            }
            sum1+=list.get(i);
        }
        for (int i = r; i < n - 1; i++) {
            if (list.get(i - 1) % 2 == 0 && list.get(i + 1) % 2 == 0) {
                int sum = (list.get(i - 1) + list.get(i + 1)) / 2;
                if (sum < list.get(i)) {
                    list.set(i, sum);
                }
            }
            sum2+=list.get(i);
        }
        int diff = Math.abs(sum1-sum2);

        return diff;
    }

    public static String getShiftedString(String s, int leftShifts, int rightShifts) {
        int sh = rightShifts - leftShifts;
        if (sh == 0) return s;
        int n = s.length();
        if (rightShifts > leftShifts) {
            sh = sh % n;
            sh = n - sh;
        } else {
            sh = (-sh) % n;
        }
        return s.substring(sh) + s.substring(0, sh);

    }

    public static int findLargestSquareSize(List<List<Integer>> samples) {
        // Write your code here
        int n = samples.size();
        int[][] dp = new int[n + 1][n + 1];
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (samples.get(i - 1).get(j - 1) == 1) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
                ans = Math.max(dp[i][j], ans);

            }
        }
        return ans;
    }

    public static List<Integer> awardTopKHotels(String positiveKeywords, String negativeKeywords, List<Integer> hotelIds, List<String> reviews, int k) {
        Set<String> psSet = getSet(positiveKeywords);
        Set<String> ngSet = getSet(negativeKeywords);

        Map<Integer, Integer> map = new HashMap<>();
        int n = hotelIds.size();
        for (int i = 0; i < n; i++) {
            int score = getScore(reviews.get(i), psSet, ngSet);

            map.put(hotelIds.get(i), map.getOrDefault(hotelIds.get(i), 0) + score);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));

        for (Integer key : map.keySet()) {
            pq.add(key);
        }
        List<Integer> ans = new ArrayList<>();
        while (k > 0 && !pq.isEmpty()) {
            int id = pq.poll();
            ans.add(id);
            k--;
        }
        return ans;

    }

    static int getScore(String s, Set<String> psSet, Set<String> ngSet) {
        int score = 0;
        for (String ss : s.split(" ")) {
            ss = ss.replaceAll("[^A-Za-z]+", "").toLowerCase();

            if (psSet.contains(ss)) {
                score += 3;
            }
            if (ngSet.contains(ss)) {
                score -= 1;
            }
        }
        return score;
    }

    static Set<String> getSet(String s) {

        Set<String> psSet = new HashSet<>();
        for (String ss : s.split(" ")) {
            psSet.add(ss.toLowerCase());
        }
        return psSet;
    }

    static long SetBitCount(int N, String s, int X) {
        long ans = 0;
        for (int i = 0; i < N; i++) {
            int count = 0;
            for (int j = i; j < N; j++) {
                if (s.charAt(j) == '1') {
                    count++;
                }
                if (count >= X) {
                    ans++;
                }
            }
        }
        return ans;
    }

    static long SetBitCount2(int N, String s, int X) {
        long ans = 0;
        int st = 0;
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (s.charAt(i) == '1') {
                count++;
            }
            if (count == X) {
                while (st <= i && count == X) {
                    ans += N - i;
                    if (s.charAt(st) == '1') {
                        count--;
                    }
                    st++;
                }
            }
        }
        return ans;
    }


    public String solve(int N, int[] A) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);
        }
        for (Integer key : map.keySet()) {
            if (map.get(key) % 2 != 0) {
                return "NO";
            }
        }
        return "YES";
    }

    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;
        int min = Arrays.stream(nums).min().getAsInt();
        int max = Arrays.stream(nums).max().getAsInt();

        int range = ((max - min) / n) + 1;

        int[][] cache = new int[n + 1][2];
        IntStream.rangeClosed(0, n).forEach(i -> {
            cache[i][0] = max + 1;
            cache[i][1] = -1;
        });
        Arrays.stream(nums).forEach(e -> {
            int x = (e - min) / range;
            cache[x][0] = Math.min(cache[x][0], e);
            cache[x][1] = Math.max(cache[x][1], e);
        });
        int ans = 0;
        if (cache[0][1] != -1 && cache[0][0] != max + 1) {
            ans = Math.max(ans, cache[0][1] - cache[0][0]);
        }

        for (int i = 1; i <= n; i++) {
            if (cache[i][1] != -1 && cache[i][0] != max + 1) {
                ans = Math.max(ans, cache[i][1] - cache[i][0]);
            }
            if (cache[i][1] != -1 && cache[i - 1][0] != max + 1) {
                ans = Math.max(ans, cache[i][0] - cache[i - 1][0]);
            }
        }
        return ans;
    }

    public int hIndex(int[] citations) {
        int n = citations.length;
        if (n <= 1) return n;
        int l = 0;
        int h = n;
        int hIndex = -1;
        while (l <= h) {
            int hx = (h - l) / 2 + l;
            int right = (int) Arrays.stream(citations).filter(e -> e >= hx).count();
            int left = (int) Arrays.stream(citations).filter(e -> e <= hx).count();

            if (right == hx) {
                hIndex = Math.max(hIndex, hx);
                l = hx + 1;
            }
            if (right < hx) {
                h = hx - 1;
            } else {
                int x = n - hx;
                if (left >= x) {
                    l = hx + 1;
                    hIndex = Math.max(hIndex, hx);
                } else {
                    l = hx + 1;
                }

            }
        }
        return hIndex;
    }

    int checkIndex(int[] citations, int h) {
        return h - (int) Arrays.stream(citations).filter(e -> e <= h).count();
    }
}


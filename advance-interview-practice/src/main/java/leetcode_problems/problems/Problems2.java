package leetcode_problems.problems;

import org.junit.Test;

import java.util.*;

public class Problems2 {

    @Test
    public void dietPlanPerformanceTest() {

        int arr[] = {3, 8, -10, 23, 19, -4, -14, 27};
        int mat[][] = {{1, 2, 3, 4, 5}, {2, 4, 5, 8, 10}, {2, 5, 7, 9, 11}, {2, 3, 5, 7, 9}};
//        int calories[] = {1, 2, 3, 4, 5};
        String words[] = {"cat", "bt", "hat", "tree"};
        String chars = "leetcode_problems";
//        System.out.println(dietPlanPerformance(calories, 1, 3, 3));
//        System.out.println(lastSubstring(chars));
//        System.out.println(numRollsToTarget(2, 6, 7));
//        System.out.println(maxNumberOfBalloons("loonbalxballpon"));
//        System.out.println(maxNumberOfApples(arr));
//        System.out.println(smallestCommonElement(mat));
//        System.out.println(minimumAbsDifference(arr));
//        System.out.println(nthUglyNumber(1000000000, 2, 217983653, 336916467));
        int [] a = {2,1,3,5,4,6,7};
//        getWinner(a, 2);
        Solution solution = new Solution();
//        int[] nums1 = {2,4,5,8,10};
        int[] nums1 = {2,4};
//        int[] nums2 = {4,6,8,9};
        int[] nums2 = {4,6};
        solution.maxSum(nums1, nums2);
    }
    class Solution {
        Map<String, Integer> map = new HashMap<>();
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        public int maxSum(int[] nums1, int[] nums2) {

            int l1= nums1.length;
            int l2= nums2.length;

            for(int i=0; i< l1 ;i++){
                map1.put(nums1[i], i);
            }

            for(int i=0; i< l2 ;i++){
                map2.put(nums2[i], i);
            }
            int turn =0;

            int v1 = dfs(0, 0, 0, nums1, nums2);
            int v2 = dfs(1, 0, 0, nums1, nums2);
            return Math.max(v1,v2);

        }
        int dfs(int turn, int i, int j, int [] num1, int [] num2){

            if(i==num1.length && turn==0) return 0;
            if(j==num2.length && turn==1) return 0;

            int max =0;
            String key = turn+"#"+i+"#" +j;
            if(map.get(key)!=null){
                return map.get(key);
            }
            if(turn==0){
                int val = num1[i];
                int v2 = num1[i] + dfs(0, i+1, j, num1, num2);
                int v1=0;
                if(map2.get(val)!=null){
                    v1 = num1[i]+ num2[map2.get(val)]+ dfs(1, i+1, map2.get(val)+1, num1, num2);
                }
                max = Math.max(v1, v2);
            }else{
                int val = num2[j];
                int v2 = num2[j] + dfs(1, i, j+1, num1, num2);
                int v1=0;
                if(map1.get(val)!=null){
                    v1 = num1[map1.get(val)]+ num2[j]+ dfs(0, map1.get(val)+1, j+1, num1, num2);
                }
                max = Math.max(v1, v2);
            }

            map.put(key, max);

            return max;
        }
    }

    public int getWinner(int[] arr, int k) {
        Deque<Integer> queue = new LinkedList<>();

        int l = arr.length;

        int count = 0;
        for (int i = 0; i <l; i++) {
            queue.add(arr[i]);
        }
        int last = -1;
        while (count < k && count < l) {
            int v1 = queue.pollFirst();
            int v2 = queue.pollFirst();
            int max = Math.max(v1, v2);
            int min = Math.min(v1, v2);

            if (last == -1) {
                last = max;
                count++;
            } else if (last == max) {
                count++;
            } else {
                last = max;
                count = 1;
            }
            queue.addFirst(max);
            queue.addLast(min);
            if(count==k) return last;
        }
        return last;
    }

    public int nthUglyNumber(int n, int a, int b, int c) {

        int ans = 0;
        int start = Math.min(a, Math.min(b, c));
        int counter = 1;
        while (true) {
            start++;
            if (start % a == 0 || start % b == 0 || start % c == 0) {
                counter++;
            }
            if (counter == n) {
                return start;
            }

        }

    }

    public List<List<Integer>> minimumAbsDifference(int[] arr) {

        Arrays.sort(arr);
        int l = arr.length;
        int min = arr[1] - arr[0];
        for (int i = 1; i < l - 1; i++) {

            if (arr[i + 1] - arr[i] < min) {
                min = arr[i + 1] - arr[i];
            }
        }
        List<List<Integer>> llist = new ArrayList<>();

        for (int i = 0; i < l - 1; i++) {

            if (arr[i + 1] - arr[i] == min) {
                llist.add(Arrays.asList(arr[i], arr[i + 1]));
            }
        }

        return llist;
    }


    public int smallestCommonElement(int[][] mat) {

        int ans = -1;
        int row = mat.length;
        int col = mat[0].length;

        for (int i = 0; i < col; i++) {

            int isAns = mat[0][i];
            int r = 1;
            for (int j = r; r < row; r++) {

                int found = Arrays.binarySearch(mat[r], isAns);

                if (found < 0) {
                    break;
                }
            }
            if (r == row) {
                ans = isAns;
                break;
            }
        }

        return ans;
    }

    public int maxNumberOfApples(int[] arr) {
        int max = 5000;
        int count = 0;
        int sum = 0;
        Arrays.sort(arr);
        int l = arr.length;
        for (int i = 0; i < l; i++) {
            sum += arr[i];
            if (sum <= max) {
                count++;
            } else {
                break;
            }

        }

        return count;
    }

    public int maxNumberOfBalloons(String text) {
        int[] map = new int[26];

        int l = text.length();

        for (int i = 0; i < l; i++) {
            map[text.charAt(i) - 'a']++;
        }
        String s = "balloon";
        int n = s.length();
        boolean flag = true;
        int count = 0;
        while (flag) {

            for (int i = 0; i < n; i++) {
                if (map[s.charAt(i) - 'a'] > 0) {
                    map[s.charAt(i) - 'a']--;
                } else {
                    flag = false;
                    break;
                }

            }
            if (flag) {
                count++;
            }

        }
        return count;
    }

    public String lastSubstring(String s) {
        char[] chars = s.toCharArray();

        int i = 0, j = 1, offset = 0;
        int l = s.length();
        while (i + offset < l && j + offset < l) {
            if (chars[i] > chars[j]) {
                j++;
                offset = 0;
            } else if (chars[i] == chars[j]) {
                if (j + offset == l || chars[i + offset] > chars[j + offset]) {
                    j++;
                    offset = 0;
                } else if (chars[i + offset] < chars[j + offset]) {
                    i = j;
                    j++;
                    offset = 0;
                } else if (chars[i + offset] == chars[j + offset]) {
                    offset++;
                }
            } else if (chars[i] < chars[j]) {
                i = j;
                offset = 0;
                j++;
            }

        }
        return s.substring(i);
    }

    public int countCharacters(String[] words, String chars) {

        int map[] = new int[256];
        int mapCopy[];

        int l = chars.length() - 1;
        while (l >= 0) {
            map[chars.charAt(l--)]++;
        }
        l = words.length;
        int sum = 0;

        for (int i = 0; i < l; i++) {
            mapCopy = map.clone();
            if (checkIfFormed(words[i], mapCopy)) {
                sum += words[i].length();
            }

        }
        return sum;

    }

    private boolean checkIfFormed(String word, int map[]) {
        int l = word.length() - 1;
        while (l >= 0) {
            if (map[word.charAt(l)] <= 0) {
                return false;
            }
            map[word.charAt(l)]--;
            l--;

        }
        return true;

    }

    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {

        int n = calories.length, curSum = 0, points = 0;

        for (int i = 0; i < n; i++) {

            curSum += calories[i];
            if (i + 1 < k) {
                // skip till k
                continue;
            }
            if (i + 1 > k) {
                //reduce form front
                curSum -= calories[i - k];
            }
            if (curSum < lower) {
                points--;
            }
            if (curSum > upper) {
                points++;
            }
        }
        return points;

    }

    public int numRollsToTarget(int d, int f, int target) {
        int mod = 1000000000 + 7;
        long dp[][] = new long[31][1001];

        for (int i = 1; i <= f; i++) {
            dp[1][i] = 1;
        }

        for (int n = 2; n <= d; n++) {
            for (int s = 1; s <= target; s++) {
                for (int i = 1; i <= f && s > i; i++) {
                    dp[n][s] = (dp[n][s] + dp[n - 1][s - i]) % mod;
                }
            }
        }
        return (int) (dp[d][target] % mod);
    }
}

package leetcode.problems;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problems2 {

    @Test
    public void dietPlanPerformanceTest() {

        int arr[] = {3,8,-10,23,19,-4,-14,27};
        int mat[][] = {{1, 2, 3, 4, 5}, {2, 4, 5, 8, 10}, {2, 5, 7, 9, 11}, {2, 3, 5, 7, 9}};
//        int calories[] = {1, 2, 3, 4, 5};
        String words[] = {"cat", "bt", "hat", "tree"};
        String chars = "leetcode";
//        System.out.println(dietPlanPerformance(calories, 1, 3, 3));
//        System.out.println(lastSubstring(chars));
//        System.out.println(numRollsToTarget(2, 6, 7));
//        System.out.println(maxNumberOfBalloons("loonbalxballpon"));
//        System.out.println(maxNumberOfApples(arr));
//        System.out.println(smallestCommonElement(mat));
//        System.out.println(minimumAbsDifference(arr));
        System.out.println(nthUglyNumber(1000000000,2,217983653,336916467));

    }
    public int nthUglyNumber(int n, int a, int b, int c) {

        int ans = 0;
        int start = Math.min(a,Math.min(b,c));
        int counter =1;
        while(true){
            start++;
            if(start%a==0 || start%b==0 || start%c==0){
                counter++;
            }
            if(counter==n){
                return start;
            }

        }

    }

    public List<List<Integer>> minimumAbsDifference(int[] arr) {

        Arrays.sort(arr);
        int l = arr.length;
        int min = arr[1]-arr[0];
        for (int i = 1; i < l-1; i++) {

            if(arr[i+1]-arr[i]< min){
                min = arr[i+1]-arr[i];
            }
        }
        List<List<Integer>>  llist = new ArrayList<>();

        for (int i = 0; i < l-1; i++) {

            if(arr[i+1]-arr[i]==min){
                llist.add(Arrays.asList(arr[i],arr[i+1]));
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
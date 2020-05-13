package leetcode.contests.ContetsAE;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Contests3 {

    @Test
    public void ContestsSolution() {
        String s[] = {"leetcoder", "leetcode", "od", "hamlet", "am"};
//        System.out.println(stringMatching(s));
        int a[] = {44, 46, 22, 68, 45, 66, 43, 9, 37, 30, 50, 67, 32, 47, 44, 11, 15, 4, 11, 6, 20, 64, 54, 54, 61, 63, 23, 43, 3, 12, 51, 61, 16, 57, 14, 12, 55, 17, 18, 25, 19, 28, 45, 56, 29, 39, 52, 8, 1, 21, 17, 21, 23, 70, 51, 61, 21, 52, 25, 28};
//        System.out.println(productExceptSelf(a));
//        System.out.println(longestArithSeqLength(a));
        List<List<Integer>> aa = new ArrayList<>();
        aa.add(Arrays.asList(20,17,13,14));
        aa.add(Arrays.asList(12,6));
        aa.add(Arrays.asList(3,4));
        int ab[] = {1,1,1,0};
        System.out.println(canJump(ab));
//        System.out.println(findDiagonalOrder(aa));
    }

    //    public int nthUglyNumber(int n) {
//
//    }
    public boolean canJump(int[] nums) {
        int n = nums.length;
        // if(n>0 && nums[0]==0)  return false;

        int maxReach = nums[0];
        int max = maxReach;
        for(int i=0; i<n ;i++){
            if(i>max) break;
            if(i>maxReach){
                maxReach = max;
            } else{
                max = Math.max(max, i+nums[i]);
            }
        }
        if(maxReach>= n-1 || max>=n-1) return true;
        return false;
    }

    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int n = nums.size();

        List<Integer>  ans = new ArrayList<>();
        int max =0;
        for(int i=0; i<n ;i++){
            int t = nums.get(i).size();
            max = Math.max(t, max);

            int j =0;
            int k =i;
            while(k>=0){
                if(j<nums.get(k).size()){
                    ans.add(nums.get(k).get(j));
                }
                j++;
                k--;
            }
        }
        int m = nums.get(n-1).size();
        int x =n-1;
        for(int t= 1 ; t< max && x>=0 ;){

            int j =t;
            if( x>=0 && j>=nums.get(x).size()){
                x--;
                t++;
                continue;

            }
            int k = x;
            while(k>=0){
                if( j<nums.get(k).size()){
                    ans.add(nums.get(k).get(j));
                } else{
                    break;
                }
                j++;
                k--;
            }

            t++;

        }

        n = ans.size();
        int [] answ = new int [n];
        for(int i =0; i< n ;i++){
            answ[i] = ans.get(i);
        }
        return answ;

    }
    class Solution {
        public int findDuplicate(int[] nums) {
            int n = nums.length;
            int e = n - 1;
            int s = 1;

            while (s < e) {
                int mid = (s + e) / 2;
                int count = 0;
                for (int i = 0; i < n; i++) {
                    if (nums[i] <= mid) {
                        count++;
                    }
                }
                if (s + 1 == e) {
                    if (count > mid) {
                        return s;
                    } else {
                        return e;
                    }
                }
                if (count > mid) {
                    e = mid;
                } else {
                    s = mid;
                }
            }
            return s;
        }
    }

    public int longestArithSeqLength(int[] A) {
        List<Integer>  ans = new ArrayList<>();

        int n = A.length;
        int dp[][] = new int[n][20001];
        int max = 0;
        for (int i = 1; i < n; i++) {
            for (int k = 0; k < i; k++) {
                int diff = A[i] - A[k] + 10000;
                dp[i][diff] = dp[k][diff] + 1;
                max = Math.max(dp[i][diff], max);
            }
        }
        return max + 1;
    }

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int s = 0;
        int e = n - 1;
        int ans[] = new int[n];
        int left = 1;
        int right = 1;
        while (s != n) {

            if (ans[s] == 0) {
                ans[s] = 1;
            }
            if (ans[e] == 0) {
                ans[e] = 1;
            }

            if (ans[s] > 1) {
                ans[s] = left * ans[s];
            } else {
                ans[s] = left;
            }

            left = left * nums[s++];
            if (ans[e] > 1) {
                ans[e] = right * ans[e];
            } else {
                ans[e] = right;
            }
            right = right * nums[e--];
        }
        return ans;
    }

    public String stringShift(String s, int[][] shift) {

        int n = shift.length;

        for (int i = 0; i < n; i++) {
            if (shift[i][0] == 0) {
                int m = shift[i][1];
                String s1 = s.substring(0, m);
                String s2 = s.substring(m, s.length());
                s = s2 + s1;
            } else {
                int m = shift[i][1];
                int l = s.length();
                String s1 = s.substring(0, m);
                String s2 = s.substring(m, s.length());
                s = s2 + s1;
            }

        }
        return s;
    }

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> y - x);
        int n = stones.length;
        for (int i = 0; i < n; i++) {
            queue.add(stones[i]);
        }
        int a = 0;
        int b = 0;
        while (!queue.isEmpty()) {
            a = queue.poll();
            b = 0;
            if (!queue.isEmpty()) {
                b = queue.poll();
            }
            if (b != 0 && a - b > 0) {
                queue.add(a - b);
            }
        }
        return a - b;
    }

    public int numOfWays(int n) {
        int dp[][] = new int[n][2];
        dp[0][0] = 6;
        dp[0][1] = 6;
        for (int i = 1; i < n; i++) {
            int val = dp[i - 1][0] + dp[i - 1][1];
            dp[i][0] = 2 * val;
            dp[i][1] = dp[i][0] + dp[i - 1][1];
        }
        return dp[n - 1][0] + dp[n - 1][1];

    }

    public int[] processQueries(int[] queries, int m) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= m; i++) {
            list.add(i);
        }
        int n = queries.length;
        int ans[] = new int[n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (queries[i] == list.get(j)) {
                    ans[k++] = j;
                    int v = list.get(j);
                    list.remove(j);
                    list.add(0, v);
                    break;
                }
            }
        }
        return ans;

    }

    public List<String> stringMatching(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        int n = words.length;
        List<String> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (words[i].length() != words[j].length() && words[j].indexOf(words[i]) != -1) {
                    ans.add(words[i]);
                    break;
                }
            }

        }
        return ans;
    }

}

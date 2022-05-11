package leetcode_problems.Q1;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Solution1 {

    @Test
    public void test() {
        String[] words = {"cb", "dbc", "bcd", "ca"};
//        String[] words = {"cdefg"};
//        mergeAndGet("abc", "bcd");
//        System.out.println(shortestSuperstring(words));
        int[] a1 = {3};
        int[] a2 = {1,2,4};
        findMedianSortedArrays(a1, a2);
    }
    
    class Solution {
        public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
            Boolean [] visited = new Boolean [1<<maxChoosableInteger];
            int val = (maxChoosableInteger*(maxChoosableInteger+1))/2;
            if(desiredTotal>val) return false;
            return dfs(0, 0, maxChoosableInteger, desiredTotal, visited);

        }
        Boolean dfs(int sum, int state, int n, int desiredTotal,  Boolean [] visited){

            if(visited[state]!=null){
                return visited[state];
            }
            for(int i=0;i< n ;i++){
                int curr = 1<<i;
                if((state & curr) == 0){
                    if(sum+i+1 >= desiredTotal || !dfs(sum+i+1, state|curr, n, desiredTotal, visited)){
                        visited[state] = true;
                        return true;
                    }
                }
            }
            return visited[state] = false;
        }
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int k1 = (m + n) / 2;
        int k2 = (m + n - 1) / 2;
        List<Integer> l = new ArrayList<>();
        Deque<String> deque = new LinkedList<>();
        Set<String> a = new HashSet<>();
        


//        int v1 = findKthInSortedArrays(nums1, nums2, k1, 0, m - 1, 0, n - 1);
        int k =1;
//        int v2 = findKthInSortedArrays(nums1, nums2, k, 0, m-1, 0 , n-1);
//        System.out.println(v2);
        for (int i=0; i<m+n ;i++){
            int v2 = findKthInSortedArrays(nums1, nums2, i, 0, m-1, 0 , n-1);

            System.out.println(v2);
        }

        // return (v1+v2)/2.0;
        return 0.0;

    }

    int findKthInSortedArrays(int[] nums1, int[] nums2, int k, int s1, int e1, int s2, int e2) {
        int l1 = (e1 - s1 + 1 - 1) / 2;
        int l2 = (e2 - s2 + 1 - 1) / 2;

        int mid1 = s1 + l1;
        int mid2 = s2 + l2;
        if(s1>e1) return nums2[s2+k];
        if(s2>e2) return nums1[s1+k];
        if (k == 0) {

            if (nums1[s1] < nums2[s2]) {
                return nums1[s1];
            }
            return nums2[s2];
        }

        if (l1 + l2 + 2 == k + 1) {
            if (nums1[mid1] <=nums2[mid2]) {
                return findKthInSortedArrays(nums1, nums2, k+1-l1-1-1, mid1+1, e1, s2, mid2);
            }
            return findKthInSortedArrays(nums1, nums2, k+1-l2-1-1, s1, mid1, mid2+1, e2);
        }
        if (l1 + l2 + 2 <k + 1) {
            if (nums1[mid1] <=nums2[mid2]) {
                return findKthInSortedArrays(nums1, nums2, k+1-l1-1-1, mid1+1, e1, s2, e2);
            } else {
                return findKthInSortedArrays(nums1, nums2, k+1-l2-1-1, s1, e1, mid2+1, e2);
            }

        } else {
            if (nums1[mid1] < nums2[mid2]) {
                return findKthInSortedArrays(nums1, nums2, k, s1, e1, s2, mid2-1);
            }else {
                return findKthInSortedArrays(nums1, nums2, k, s1, mid1-1, s2, e2);
            }
        }
    }

    public boolean increasingTriplet(int[] nums) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        int l = nums.length;
        int max = 1;
        for (int i = 0; i < l; i++) {
            Integer ceilKey = treeMap.ceilingKey(nums[i]);
            if (ceilKey != null) {
                treeMap.remove(ceilKey);
            }
            treeMap.put(nums[i], 1);
            max = Math.max(max, treeMap.size());
        }
        if (max >= 3)
            return true;

        return false;

    }

    public long minimumTime(int[] time, int totalTrips) {

        int l = time.length;
        int st = 1;
        int end = totalTrips;
        int ans = totalTrips;
        while (st <= end) {
            int mid = (st + end) / 2;
            if (isPossible(time, mid, totalTrips)) {
                ans = Math.min(mid, ans);
                end = mid - 1;
            } else {
                st = mid + 1;
            }
        }
        return ans;
    }

    private boolean isPossible(int[] time, int midTime, int totalTrips) {

        int t = time.length;
        int count = 0;
        for (int i = 0; i < t; i++) {
            count += midTime / time[i];
        }
        if (count >= totalTrips) return true;
        return false;
    }

    public String shortestSuperstring(String[] words) {

        int l = words.length;
        int state = 0;
        for (int i = 0; i < l; i++) {
            state = state | 1 << i;
        }
        Map<String, String> map = new HashMap<>();

        return solveTPS(words, state, "", map, l);

    }

    String solveTPS(String[] words, int state, String startWord, Map<String, String> map, int l) {

        String key = startWord + "|" + state;
        if (state == 0) return startWord;

        if (map.get(key) != null) {
            return map.get(key);
        }
        String minLenWord = "";

        for (int i = 0; i < l; i++) {
            if ((state >> i & 1) == 1) {
                int takenState = state & ~(1 << i);
                String result = solveTPS(words, takenState, words[i], map, l);

                String tmp = mergeAndGet(startWord, result);

                if (minLenWord.length() == 0) {
                    minLenWord = tmp;
                }
                if (minLenWord.length() > tmp.length()) {
                    minLenWord = tmp;
                }

            }
        }
        map.put(key, minLenWord);
        return minLenWord;
    }

    String mergeAndGet(String word, String result) {

        int l = word.length();
        int t = result.length();
        if (result.contains(word)) return result;
        if (word.contains(result)) return word;
        String ans = "";
        int found = l;
        for (int k = 0; k < l; k++) {
            int i = k;
            int j = 0;
            while (i < l && j < t) {
                if (word.charAt(i) == result.charAt(j)) {
                    i++;
                    j++;
                } else break;
            }
            if (i == l) {
                found = k;
                break;
            }
        }
        return word.substring(0, found) + result;
    }
}

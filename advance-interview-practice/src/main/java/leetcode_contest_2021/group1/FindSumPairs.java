package leetcode_contest_2021.group1;

import java.util.HashMap;

public class FindSumPairs {
    int[] nums1;
    int[] nums2;
    HashMap<Integer, Integer> map1 = new HashMap<>();
    HashMap<Integer, Integer> map2 = new HashMap<>();

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        int l1 = nums1.length;
        int l2 = nums2.length;
        for (int i = 0; i < l1; i++) {
            map1.put(nums1[i], map1.getOrDefault(nums1[i], 0) + 1);
        }
        for (int i = 0; i < l2; i++) {
            map2.put(nums2[i], map2.getOrDefault(nums1[i], 0) + 1);
        }
    }

    public void add(int index, int val) {
        int newVal = this.nums2[index] + val;
        map2.put(nums2[index], map2.get(nums2[index]) - 1);
        if (map2.get(nums2[index])==0){
            map2.put(nums2[index], null);
        }
        this.nums2[index] = newVal;
        map2.put(nums2[index], map2.getOrDefault(nums1[index], 0) + 1);
    }

    public int count(int tot) {
        int ans = 0;
        for (Integer key : map1.keySet()) {
            if (map1.get(key) != null && map1.get(key) != 0) {
                int rem = tot - key;
                if (map2.get(rem) != null && map2.get(rem) != 0) {
                    ans += map2.get(rem) * map1.get(key);
                }
            }
        }
        return ans;
    }
}

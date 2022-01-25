package leetcode_contest_2021.home_contest_1;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HomeContest6 {

    @Test
    public void test() {
        int aa[] = {1, 3, 4, 2, 6, 8};
        System.out.println(findOriginalArray(aa));
    }

    public int[] findOriginalArray(int[] changed) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>();
        int l = changed.length;
        for (int i = 0; i < l; i++) {
            int val = changed[i];
            queue.add(val);
            int v = map.getOrDefault(val, 0);
            map.put(changed[i], v + 1);
        }
        int[] ans = new int[0];
        if (l % 2 != 0) return ans;

        int ansW[] = new int[l / 2];
        int k = 0;
        while (!queue.isEmpty()) {
            int val = queue.poll();
            int dv = val * 2;
            if (map.get(val) > 0) {
                if (map.get(dv) != null && map.get(dv) > 0) {
                    ansW[k++] = val;
                    int x = map.get(dv);
                    map.put(dv, x - 1);
                    int y = map.get(val);
                    map.put(val, y - 1);
                } else {
                    return ans;
                }
            }
        }
        return ansW;
    }

    public int countKDifference(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    ans++;
                }
            }
        }
        return ans;
    }
}

package leetcode_2020.problems1;

import java.util.HashMap;
import java.util.Map;

public class MajorityChecker {
    int arr[];
    int fqSum[];


    public void majorityCheckerTest() {

        int calories[] = {6, 13, 8, 7, 10, 1, 12, 11};
//        int calories[] = {1, 2, 3, 4, 5};
        String words[] = {"cat", "bt", "hat", "tree"};
        String chars = "leetcode_2020";
//        System.out.println(dietPlanPerformance(calories, 1, 3, 3));
    }

    public MajorityChecker(int[] arr) {
        this.arr = arr;
        int l = arr.length;
        this.fqSum = new int[l];

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < l; i++) {
            int newVal = map.getOrDefault(arr[i], 0) + 1;
            map.put(arr[i], newVal);
            fqSum[i] = newVal;
        }
    }

    public int query(int left, int right, int threshold) {

        int i = 0;
        int m=0;

        for (int l = left; l <= right; l++) {
            if (i == 0) {
                m = arr[l];
                i = 1;
            } else if (arr[l] == m) {
                i++;
            } else {
                i--;
            }
        }

        while (arr[left] != m) {
            left++;
        }
        while (arr[right] != m) {
            right--;
        }

        return fqSum[right] - fqSum[left] + 1 >= threshold ? m : -1;
    }
}
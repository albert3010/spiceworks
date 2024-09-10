package practice_lld.top25.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ContestsSep {

    public static void main(String[] args) {
        System.out.println("test");
        int [] s = {2,6,13,13};
        System.out.println(maxPossibleScore(s, 5));
    }

    public String convertDateToBinary(String date) {
        String ans = "";
        String[] dates = date.split("-");
        int i = 0;
        for (String d : dates) {
            int v = Integer.valueOf(d);
            ans += Integer.toBinaryString(v);
            if (i++ < 2) ans += "-";
        }
        return ans;
    }

    public static int maxPossibleScore(int[] start, int d) {
        Arrays.sort(start);
//        int minDiff = 1;
//        int maxDiff = 1000000001;
        int n = start.length;
        int minDiff = 0;
        int maxDiff = start[n-1]-start[0]+d;

        int ans =0;
        while (minDiff < maxDiff) {
            int mid = minDiff+ ((maxDiff - minDiff) / 2);
            if (isPossible(start, d, mid)) {
                minDiff = mid;
                ans = mid;
            }else {
                maxDiff = mid-1;
            }
        }
        return ans;
    }

    private static boolean isPossible(int[] start, int d, int diff) {
        int n = start.length;
        int st = start[0];
        for (int i = 1; i < n; i++) {
            int next = st + diff;
            if (start[i] <= next && next <= start[i] + d) {
                st = next;
            } else {
                return false;
            }
        }
        return true;
    }

    public long findMaximumScore(List<Integer> nums) {
        Stack<Integer> st = new Stack<>();
        int n = nums.size();
        int ans = 0;
        for (int i = 1; i < n; i++) {
            int v = nums.get(i);
            int j = st.peek();
            int v2 = nums.get(st.peek());
            if (v2 < v) {
                st.push(i);
                ans += (i - j) * v2;
            } else {
                if (i == n - 1) {
                    ans += (i - j) * v2;
                }
            }
        }
        return ans;
    }
}

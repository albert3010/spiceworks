package leetcode_contest_2021.home_contest_1;

import org.junit.Test;

import java.util.*;

public class HomeContest4 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    @Test
    public void test() {

//       int [] preorder = {1,2,4,5,3,6,7};
//        int [] postorder = {4,5,2,6,7,3,1};
        int[] preorder = {2, 1};
        int[] postorder = {1, 2};
//        constructFromPrePost(preorder, postorder);
        System.out.println(Process(2, 3));
    }

    public int Process(int input1, int input2) {
        int l = input1;
        int m = input2;
        int ships = 1;
        if (l == 0) return 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(2);
        while (!queue.isEmpty() && l>0){
            l--;
            if (l==0) break;
            int sz = queue.size();
            while (sz>0){
                int ll = queue.poll();
                int x = (ll * ll + 1) % m;
                for (int i = 0; i < x; i++) {
                    ships++;
                    queue.add(i);
                }
                sz--;
            }
//            ships= ships % m;
        }
        return ships % m;
    }

    public int minOperations(int[][] grid, int x) {
        int n = grid.length;
        int m = grid[0].length;
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans.add(grid[i][j]);
            }
        }
        Collections.sort(ans);
        int low = ans.get(0);
        int high = ans.get(ans.size() - 1);
        double minOperation = Math.min(getOperation(low, ans, x), getOperation(high, ans, x));
        while (low < high) {
            int mid = low + (high - low) / 2;
            double val = getOperation(mid, ans, x);
            double vall = getOperation(mid - 1, ans, x);
            double valg = getOperation(mid + 1, ans, x);
            if (valg >= vall && vall <= val) {
                high = mid;
            } else {
                low = mid;
            }

            if (low + 1 == high) {
                double lo = getOperation(low, ans, x);
                double hi = getOperation(high, ans, x);
                if (lo > hi) {
                    low = high;
                }
            }
            if (low == high) break;
        }
        return 1;

    }

    private double getOperation(int val, List<Integer> ans, int x) {
        double totalCount = 0;
        for (Integer k : ans) {
            int diff = Math.abs(k - val);
            totalCount += ((diff + 0.0) / x);
        }
        return totalCount;
    }

    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {

        Set<Integer> setCommon = new HashSet<>();
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        Set<Integer> set3 = new HashSet<>();

        for (int i = 0; i < nums1.length; i++) {
            set1.add(nums1[i]);
            setCommon.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            set2.add(nums2[i]);
            setCommon.add(nums2[i]);
        }
        for (int i = 0; i < nums3.length; i++) {
            set3.add(nums3[i]);
            setCommon.add(nums3[i]);
        }
        List<Integer> ans = new ArrayList<>();
        for (Integer k : setCommon) {
            if ((set1.contains(k) && set2.contains(k)) || (set1.contains(k) && set3.contains(k))
                    || (set3.contains(k) && set2.contains(k))) {
                ans.add(k);
            }
        }
        return ans;
    }

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int n = preorder.length;

        TreeNode node = solve(0, n - 1, 0, n - 1, preorder, postorder);
        return node;
    }

    public TreeNode solve(int i, int i2, int j1, int j2, int[] preorder, int[] postorder) {
        if (i > i2 || j1 > j2) return null;
        TreeNode node = new TreeNode(preorder[i]);
        if (i == i2 || j1 == j2) return node;
        int k = -1;
        for (int j = j1; j <= j2; j++) {
            if (preorder[i + 1] == postorder[j]) {
                k = j;
            }
        }
        if (k == -1)
            return null;
        int ll = k - j1 + 1;

        node.left = solve(i + 1, i + ll, j1, k, preorder, postorder);
        node.right = solve(i + ll + 1, i2, k + 1, j2 - 1, preorder, postorder);
        return node;
    }
}

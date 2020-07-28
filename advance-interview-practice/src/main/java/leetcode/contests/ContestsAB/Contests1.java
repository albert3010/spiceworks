package leetcode.contests.ContestsAB;

import org.junit.Test;

public class Contests1 {
    public class TreeNode {
        int xorVal;
        int l;
        int r;
        TreeNode left;
        TreeNode right;

        TreeNode(int xorVal, int l, int r) {
            this.xorVal = xorVal;
            this.l = l;
            this.r = r;
        }
    }

    @Test
    public void ContestsSolution() {
//        System.out.println(freqAlphabets("12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#"));
        int arr[] = {1, 3, 4, 8};
        int queries[][] = {{0, 1}, {1, 2}, {0, 3}, {3, 3}};
//        System.out.println(xorQueries(arr, queries));
        System.out.println(minInsertions("leetcode"));
    }


    public int minInsertions(String s) {
        int n = s.length();
        int dp[][] = new int[n][n];
        // Time O(n^2)
        for (int gap = 1; gap < n; gap++) {
            for (int i = 0, j = gap; j <= n - 1; i++, j++) {
                if (i + 1 == j) { // initialize base condition l=2
                    if (s.charAt(i) != s.charAt(j)) {
                        dp[i][j] = 1;
                    }
                } else {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i + 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
                    }
                }
            }
        }
        return dp[0][n - 1];
    }

    public int[] xorQueries(int[] arr, int[][] queries) {
        int m = arr.length - 1;
        TreeNode root = constructRangeTree(0, m, arr);
        int n = queries.length;
        int ans[] = new int[n];
        for (int i = 0; i < n; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            ans[i] = xorValFromTree(root, l, r);
        }
        return ans;
    }

    public int xorValFromTree(TreeNode node, int l, int r) {
        int cl = node.l;
        int cr = node.r;
        int mid = (cl + cr) / 2;
        int val = 0;
        if (l == r && l == cl && l == cr) {
            return node.xorVal;
        }
        if (l == cl && r == cr) {
            return node.xorVal;
        }
        if (l < mid && r > mid) {
            return xorValFromTree(node.left, l, mid) ^
                xorValFromTree(node.right, mid + 1, r);

        }
        if (l <= mid && r <= mid) {
            return xorValFromTree(node.left, l, r);
        }
        if (l >= mid && r >= mid) {
            if (l == mid && r != mid) {
                return xorValFromTree(node.left, l, mid) ^ xorValFromTree(node.right, mid + 1, r);
            }
            return xorValFromTree(node.right, l, r);
        }
        return node.xorVal;
    }

    public TreeNode constructRangeTree(int l, int r, int arr[]) {
        int xorVal = getXORval(arr, l, r);
        TreeNode node = new TreeNode(xorVal, l, r);
        if (l == r) return node;
        int mid = (l + r) / 2;
        node.left = constructRangeTree(l, mid, arr);
        node.right = constructRangeTree(mid + 1, r, arr);
        return node;
    }


    public int getXORval(int[] arr, int l, int r) {
        int val = arr[l];
        for (int i = l + 1; i <= r; i++) {
            val = val ^ arr[i];
        }
        return val;
    }

    public String freqAlphabets(String s) {

        int l = s.length();
        int i = 0;
        String t = "";
        String res = "";
        while (i < l) {
            if (i + 2 < l && s.charAt(i + 2) == '#') {
                t = s.substring(i, i + 2);
                i = i + 2;
            } else {
                t = s.substring(i, i + 1);
            }
            int val = Integer.parseInt(t);
            res += (char) ('a' + val - 1);
            i++;
        }
        return res;
    }

}

package leetcode_contest_2021.home_contest_1;

import org.junit.Test;

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
        int [] preorder = {2,1};
        int [] postorder = {1,2};
        constructFromPrePost(preorder, postorder);

    }

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int n = preorder.length;

        TreeNode node = solve( 0, n - 1, 0, n - 1, preorder, postorder);
        return node;
    }

    public TreeNode solve(int i, int i2, int j1, int j2, int[] preorder, int[] postorder) {
        if (i>i2 || j1>j2) return null;
        TreeNode node = new TreeNode(preorder[i]);
        if (i==i2 || j1==j2) return node;
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
        node.right = solve( i + ll + 1, i2, k + 1, j2 - 1, preorder, postorder);
        return node;
    }
}

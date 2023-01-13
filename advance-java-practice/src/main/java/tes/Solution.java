package tes;

import java.util.HashMap;
import java.util.Map;

class Solution {
    int ans = 0;
    boolean flag = true;

    class Node {
        int zero;
        int one;

        Node(int zero, int one) {
            this.zero = zero;
            this.one = one;
        }
    }

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length;
        int n = grid1[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                flag = true;
                isSubset(i, j, grid1, grid2, m, n);
                if (flag) {
                    count++;
                }
            }
        }
        return count;

    }

    private void isSubset(int i, int j, int[][] grid1, int[][] grid2, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid2[i][j] == 0) {
            return;
        }
        if (!(grid2[i][j] == 1 && grid1[i][j] == 1)) {
            flag = false;
        }
        grid2[i][j] = 0;
        int[] dir = {1, 0, -1, 0, 1};
        for (int k = 0; k < 4; k++) {
            int x = dir[k];
            int y = dir[k + 1];
            isSubset(i + x, j + y, grid1, grid2, m, n);
        }
    }
}

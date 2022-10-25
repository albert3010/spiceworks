package leetcode_problems.prime.SolutionsB;

import org.junit.Test;

import java.util.*;

public class PrimeB5 {

    @Test
    public void function() {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        int[][] grid = {
                {1, 1, 1, 1, 0, 0, 0, 1},
                {1, 0, 0, 1, 0, 0, 0, 1},
                {0, 1, 0, 1, 1, 1, 1, 1},
                {0, 1, 0, 1, 1, 0, 1, 0},
                {1, 1, 0, 0, 1, 1, 0, 1},
                {1, 0, 0, 1, 1, 0, 0, 0},
                {0, 1, 0, 1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1, 0, 1, 0},
                {0, 1, 0, 1, 1, 0, 1, 0},
                {1, 1, 0, 1, 0, 0, 0, 1}

        };
        System.out.println(numDistinctIslands2(grid));

    }

    public int numDistinctIslands2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] v = new boolean[m][n];
        Set<String> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!v[i][j]) {
                    String ans = getHashDFS(grid, i, j, i, j, m, n, v);
                    if (ans.length() > 0) {
                        set.add(ans);
                    }
                }
            }
        }
        return set.size();
    }

    String getHashDFS(int[][] grid, int i, int j, int x, int y, int m, int n, boolean[][] v) {

        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] != 1 || v[i][j]) return "";

        int[] t = {1, 0, -1, 0, 1};
        v[i][j] = true;
        String ans = "#" + (i - x) + "#" + (j - y) + "#";

        for (int k = 0; k < 4; k++) {
            ans += getHashDFS(grid, i + t[k], j + t[k + 1], x, y, m, n, v);
        }

        return ans;
    }
}

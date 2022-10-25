package leetcode_problems.problems;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

public class Problems7 {

    @Test
    public void dietPlanPerformanceTest() {

        List<Integer> pairs = Lists.newArrayList();

        String words[] = {"cat", "bt", "hat", "tree"};
        int grid[][] = {
                {0, 0, 1, 0},
                {1, 0, 1, 1},
                {0, 1, 1, 0},
                {1, 1, 1, 1}
        };
        String s = "abcde";
//        System.out.println(optimalStrategyforaGame(a));
//        System.out.println(alphabetBoardPath("zz"));
        System.out.println(largest1BorderedSquare(grid));
    }



    public int largest1BorderedSquare(int[][] grid) {

        int row = grid.length;
        int col = grid[0].length;

        int dpCol[][] = new int[row][col];
        int dpRow[][] = new int[row][col];
        int areaMax = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    areaMax = 1;
                }
                dpCol[i][j] = grid[i][j] + ((grid[i][j] != 0 && j > 0) ? dpCol[i][j - 1] : 0);
                dpRow[i][j] = grid[i][j] + ((grid[i][j] != 0 && i > 0) ? dpRow[i - 1][j] : 0);
            }
        }

        for (int k = 0; k < row; k++) {
            for (int i = k; i < row; i++) {
                int row1 = k, row2 = i;
                for (int col1 = 0, col2 = row2 - row1; col2 < col; col1++, col2++) {
                    areaMax = Math.max(checkIfBorderedSquare1(row1, row2, col1, col2, dpRow, dpCol, grid), areaMax);
                }
            }
        }
        return areaMax;
    }

    int checkIfBorderedSquare1(int row1, int row2, int col1, int col2, int[][] dpRow, int[][] dpCol, int[][] grid) {

        int checkAll = 0;

        if (dpCol[row1][col2] + grid[row1][col1] >= col2 - col1 + 1) {
            checkAll++;
        }
        if (dpCol[row2][col2] + grid[row2][col1] >= col2 - col1 + 1) {
            checkAll++;
        }
        if (dpRow[row2][col1] + grid[row2][col1] >= row2 - row1 + 1) {
            checkAll++;
        }
        if (dpRow[row2][col2] + grid[row2][col1] >= row2 - row1 + 1) {
            checkAll++;
        }
        if (checkAll == 4) {
            return (row2 - row1 + 1) * (row2 - row1 + 1);
        }
        return 0;
    }

    public String alphabetBoardPath(String target) {

        int a[] = new int[258];
        char c = 'a';
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int index = 10 * i + j;
                a[c++] = index;
//                System.out.println(a[index]);
            }
        }
        a[c] = 50;
        int l = target.length();
        int x1 = 0, y1 = 0, x2, y2;
        String res = "";
        for (int i = 0; i < l; i++) {
            int index = a[target.charAt(i)];
            x2 = index / 10;
            y2 = index % 10;
            if (((x1 == 5 && y1 == 0) || (x2 == 5 && y2 == 0)) && !(x1 == x2 && y1 == y2)) {
                res += getPath(x1, y1, 4, 0);
                res += getPath(4, 0, x2, y2) + "!";
            } else {
                res += getPath(x1, y1, x2, y2) + "!";
            }
            x1 = x2;
            y1 = y2;
        }
        return res;

    }

    String getPath(int x1, int y1, int x2, int y2) {
        String res = "";
        int diff = 0;
        diff = x1 - x2;
        res += getPath_(diff, "Y");
        diff = y1 - y2;
        res += getPath_(diff, "X");
        return res;
    }

    String getPath_(int a, String s) {
        String r = "";
        String t = "U";

        if (s == "Y") {
            if (a < 0) {
                a = a * -1;
                t = "D";
            }
        } else {
            t = "L";
            if (a < 0) {
                a = a * -1;
                t = "R";
            }
        }
        while (a-- > 0) {
            r += t;
        }
        return r;

    }

    static int optimalStrategyforaGame(int a[]) {
        int n = a.length;
        int dp[][] = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = a[i];

            if (i < n - 1) {
                dp[i][i + 1] = Math.max(a[i], a[i + 1]);
            }
        }
        for (int gap = 3; gap <= n; gap++) {

            for (int i = 0; i <= n - gap; i++) {
                int j = i + gap - 1;

                int x = Math.min(dp[i + 2][j], dp[i + 1][j - 1]);
                int y = Math.min(dp[i + 1][j - 1], dp[i][j - 2]);

                dp[i][j] = Math.max(a[i] + x, a[j] + y);
            }


        }

        return dp[0][n - 1];
    }

}
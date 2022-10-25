package leetcode_contest_2022.test;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int n = reader.nextInt();
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.valueOf(reader.next());
            }
        }

        System.out.println(solve(arr, n));

    }

    static class NodeH {
        int left;
        int right;
        int up;
        int down;

        public NodeH() {
            this.left = 0;
            this.right = 0;
            this.up = 0;
            this.down = 0;
        }

        List<Integer> getSortedArr() {
            List<Integer> list = Arrays.asList(up, down, left, right);
            list.sort((a, b)-> b-a);
            return list;
        }
    }

    public static int solve(int[][] arr, int n) {
        NodeH[][] dp = new NodeH[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = new NodeH();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j].left = arr[i][j];
                if (j - 1 >= 0) {
                    if (arr[i][j] != 0) {
                        dp[i][j].left = dp[i][j - 1].left + 1;
                    }
                }
                int t = n - 1 - j;
                dp[i][t].right = arr[i][t];
                if (t < n - 1) {
                    if (arr[i][t] != 0) {
                        dp[i][t].right = dp[i][t + 1].right + 1;
                    }
                }
                dp[j][i].up = arr[j][i];
                if (j > 0) {
                    if (arr[j][i] != 0) {
                        dp[j][i].up = dp[j - 1][i].up + 1;
                    }
                }
                t = n - 1 - j;
                dp[t][i].down = arr[t][i];
                if (t < n - 1) {
                    if (arr[t][i] != 0) {
                        dp[t][i].down = dp[t + 1][i].down + 1;
                    }
                }
            }
        }
        int maxAns = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                List<Integer> list = dp[i][j].getSortedArr();
                maxAns = Math.max(maxAns, list.get(0) + list.get(1));
            }
        }
        return maxAns;
    }
}

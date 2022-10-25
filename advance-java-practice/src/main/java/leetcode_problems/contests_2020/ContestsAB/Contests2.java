package leetcode_problems.contests_2020.ContestsAB;

import org.junit.Test;

import java.util.*;

public class Contests2 {
    class GNode {
        int v;
        int w;

        GNode(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    @Test
    public void ContestsSolution() {
        int mat[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

//        System.out.println(matrixBlockSum(mat, 2));
//        System.out.println(distinctEchoSubstrings("yexodmnzltyexodmnzltyexodmnzltyexodmnzlt"));
//        System.out.println(distinctEchoSubstrings("abcabcabc"));

//        11
//            [[1,4],[0,3],[1,3],[3,7],[2,7],[0,1],[2,4],[3,6],[5,6],[6,7],[4,7],[0,7],[5,7]]
//        int connections[][] = {{1, 4}, {0, 3}, {1, 3}, {3, 7}, {2, 7}, {0, 1}, {2, 4}, {3, 6}, {5, 6}, {6, 7}, {4, 7}, {0, 7}, {5, 7}};
        int connections[][] = {{0, 1}, {0, 2}, {0, 3}, {1, 2}};
//        System.out.println(makeConnected(6, connections));
        int a[] = {1,0,0, 2,3,0,4,5,0,4,5,6};
        System.out.println(duplicateZeros(a));
    }

    public int[] duplicateZeros(int[] arr) {

        int n = arr.length;
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            queue.add(arr[i]);
            if(arr[i]==0){
                queue.add(arr[i]);
            }
            arr[i] = queue.poll();
        }
        return arr;
    }

    public int makeConnected(int n, int[][] connections) {
        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        int cons = connections.length;
        if (n - 1 > cons) return -1;
        int m = connections.length;
        for (int i = 0; i < m; i++) {
            graph[connections[i][0]].add(connections[i][1]);
            graph[connections[i][1]].add(connections[i][0]);
        }
        boolean v[] = new boolean[n];
        int groups = 0;
        for (int i = 0; i < n; i++) {
            if (!v[i]) {
                groups++;
                traverse(i, v, graph);
            }
        }
        return groups - 1;
    }

    public void traverse(int node, boolean[] v, List<Integer>[] graph) {
        if (v[node]) return;
        v[node] = true;
        for (Integer adj : graph[node]) {
            traverse(adj, v, graph);
        }
    }

    public int distinctEchoSubstrings(String text) {
        int n = text.length();
        int dp[][] = new int[n][n];
        Set<String> set = new LinkedHashSet<>();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                char c1 = text.charAt(i);
                char c2 = text.charAt(j);
                if (c1 == c2) {
                    if (i == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                }
                if (dp[i][j] >= j - i) {
                    set.add(text.substring(2 * i - j + 1, j + 1));
                }
            }
        }
        return set.size();
    }

    public int minFlips(int a, int b, int c) {
        int minFlip = 0;
        while (c > 0 || a > 0 || b > 0) {
            minFlip += countFlip(a % 2, b % 2, c % 2);
            a = a >> 1;
            b = b >> 1;
            c = c >> 1;
        }
        return minFlip;
    }

    public int countFlip(int a, int b, int c) {
        if (c == 0) {
            return a + b;
        } else {
            if (a + b == 0) {
                return 1;
            }
        }
        return 0;
    }

    public int distinctEchoSubstringsBr(String text) {
        int n = text.length();
        Set<String> set = new LinkedHashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j += 2) {

                String s = text.substring(i, j + 1);
                if (isConcat(s))
                    set.add(s);
            }
        }
        return set.size();
    }

    public boolean isConcat(String s) {

        int l = 0;
        int r = s.length() - 1;
        int mid = (l + r) / 2;
        if (r % 2 == 0) return false;

        while (mid <= r) {
            if (s.charAt(l) != s.charAt(mid)) {
                return false;
            }
            mid++;
            l++;
        }
        return true;
    }

    public int[] decompressRLElist(int[] nums) {

        List<Integer> ans = new ArrayList<>();
        int n = nums.length;

        for (int i = 0; i < n - 1; i += 2) {
            int a = nums[i];
            int b = nums[i + 1];
            for (int j = 0; j < a; j++) {
                ans.add(b);
            }
        }
        n = ans.size();
        int b[] = new int[n];

        for (int i = 0; i < n; i++) {
            b[i] = ans.get(i);
        }
        return b;
    }

    public int[][] matrixBlockSum(int[][] mat, int K) {
        int n = mat.length;
        int m = mat[0].length;
        int v[][] = new int[n + 1][m + 1];
        int ans[][] = new int[n][m];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                v[i][j] = v[i][j - 1] + v[i - 1][j] - v[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        int a, b, c, d;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a = Math.max(0, i - K);
                b = Math.min(i + K, n - 1);
                c = Math.max(0, j - K);
                d = Math.min(j + K, m - 1);
                ans[i][j] = v[b + 1][d + 1] - v[a][d + 1] - v[b + 1][c] + v[a][c];
            }
        }
        return ans;
    }

}

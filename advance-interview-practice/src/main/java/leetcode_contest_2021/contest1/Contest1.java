package leetcode_contest_2021.contest1;

import org.junit.Test;

import java.util.*;

public class Contest1 {

    @Test
    public void test() {
        int[] a = {2, 3, 1, 2};
//        canBeIncreasing(a);
//        System.out.println(removeOccurrences("daabcbaabcbc", "abc"));
//        int[][] grid = {{10, 1, 4, 8}, {6, 6, 3, 10}, {7, 4, 7, 10}, {1, 10, 6, 1}, {2, 1, 1, 10}, {3, 8, 9, 2}, {7, 1, 10, 10}, {7, 1, 4, 9}, {2, 2, 4, 2}, {10, 7, 5, 10}};
        int[][] grid = {
                {4, 5, 8, 9, 4, 2, 4, 7, 2, 4},
                {7, 1, 9, 6, 6, 1, 4, 5, 7, 7},
                {7, 1, 5, 1, 1, 7, 10, 1, 3, 1},
                {7, 2, 2, 5, 2, 6, 6, 4, 7, 7},
                {1, 2, 3, 8, 4, 7, 6, 9, 6, 2},
                {5, 10, 3, 4, 7, 2, 7, 5, 3, 10}
        };
//        rotateGrid(grid, 4);
//        [[4,2,4,7,2,4,7,1,7,2],[9,1,4,5,7,3,7,6,9,10],[8,6,10,1,6,3,5,7,2,7],[5,6,7,5,2,6,6,4,7,4],[4,9,1,1,2,2,3,8,4,3],[7,7,1,5,2,1,7,1,5,10]]
//   [[4,2,4,7,2,4,7,1,7,2],
//    [9,1,4,5,7,3,7,6,9,10],
//    [8,6,10,1,4,6,6,2,6,3],
//    [5,6,7,1,1,5,2,5,7,5],
//    [4,9,1,1,2,2,3,8,4,7],
//    [7,7,7,1,5,10,3,4,7,2]]
//        sumGame("5023");
        countPalindromicSubsequence("bbcbaba");
    }


    public int colorTheGrid(int m, int n) {

        int mod = 1000000000 + 7;
        long ans = 3;
        for (int i = 2; i <= n; i++) {
            ans = (ans * 2) % mod;
        }
        for (int i = 0; i < m; i++) {
            ans = (ans * ans) % mod;
        }
        return (int) (ans) % mod;
    }

    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        List<Integer> ans = new ArrayList<>();
        int l = security.length;
        int[] right = new int[l];
        int[] left = new int[l];
        int counter = 0;
        int i = 0;
        while (i < l) {
            right[i] = counter;
            if (i + 1 < l && security[i] <= security[i + 1]) {

            } else {
                counter++;
            }
            i++;
        }
        i = l - 1;
        while (i >= 0) {
            left[i] = counter;
            if (i - 1 >= 0 && security[i] <= security[i - 1]) {

            } else {
                counter++;
            }
            i--;
        }

        for (int j = 0; j < l; j++) {
            if ((j - time >= 0 && left[j - time] == left[j]) && (j + time < l && right[j + time] == right[j])) {
                ans.add(j);
            }
        }
        return ans;
    }

    public int countPalindromicSubsequence(String s) {
        int l = s.length();

        StringBuilder ss = new StringBuilder("");
        for (int i = 0; i < 26; i++) {
            ss.append(" ");

        }

        StringBuilder[] left = new StringBuilder[l];
        StringBuilder[] right = new StringBuilder[l];
        for (int i = 0; i < l; i++) {
            int j = s.charAt(i) - 'a';
            StringBuilder s1 = new StringBuilder(ss.toString());
            if (i != 0) {
                s1 = new StringBuilder(left[i - 1].toString());
            }
            s1.setCharAt(j, (char) ('a' + j));
            left[i] = s1;
        }
        for (int i = l - 1; i >= 0; i--) {
            int j = s.charAt(i) - 'a';
            StringBuilder s1 = new StringBuilder(ss.toString());
            if (i != l - 1) {
                s1 = new StringBuilder(right[i + 1].toString());
            }
            s1.setCharAt(j, (char) ('a' + j));
            right[i] = s1;
        }

        Set<String> ans = new HashSet<>();
        for (int i = 1; i < l - 1; i++) {
            StringBuilder sl = left[i - 1];
            StringBuilder sr = right[i + 1];
            for (int j = 0; j < 26; j++) {
                if (sl.charAt(j) == sr.charAt(j) && sr.charAt(j) != ' ') {
                    ans.add(sl.charAt(j) + "#" + s.charAt(i) + "#" + sl.charAt(j));
                }
            }

        }
        return ans.size();
    }

    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] arr = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            arr[i] = nums[i % n];
        }
        return arr;
    }

    public boolean sumGame(String num) {
        int l = num.length();
        int sumLeft = 0;
        int sumRight = 0;
        int leftC = 0;
        int rightC = 0;
        for (int i = 0; i <= l / 2; i++) {
            if (num.charAt(i) == '?') {
                leftC++;
            } else {
                sumLeft += num.charAt(i) - '0';
            }
        }
        for (int i = l / 2 + 1; i < l; i++) {
            if (num.charAt(i) == '?') {
                rightC++;
            } else {
                sumRight += num.charAt(i) - '0';
            }
        }
        if ((leftC + rightC) == 0 && sumLeft == sumRight) {
            return false;
        }
        if ((leftC + rightC) % 2 != 0) {
            return true;
        }

        System.out.println(sumLeft + " __ " + sumRight);
        return false;

    }

    class NodeX {
        int i;
        int j;

        public NodeX(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public int nearestExit(char[][] maze, int[] entrance) {
        int n = maze.length;
        int m = maze[0].length;
        Queue<NodeX> queue = new LinkedList<>();
        queue.add(new NodeX(entrance[0], entrance[1]));
        int[] dir = {0, 1, 0, -1, 0};
        int step = 0;
        while (!queue.isEmpty()) {
            int l = queue.size();
            while (l > 0) {
                NodeX nd = queue.poll();
                for (int i = 1; i < 5; i++) {
                    int x = nd.i + dir[i];
                    int y = nd.j + dir[i - 1];
                    if (x >= 0 && x < n && y >= 0 && y < m && maze[x][y] == '.') {
                        if (x == 0 || y == 0 || x == n - 1 || y == m - 1) {
                            return step + 1;
                        }
                        queue.add(new NodeX(x, y));
                        maze[nd.i][nd.j] = 'x';
                    }
                }
                l--;
            }
            step++;
        }
        return -1;
    }

    public int countTriples(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (i * i + j * j == k * k) {
                        count++;
                    }
                }
            }
        }
        return count;
    }


    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int lm = m;
        int ln = n;
        int tt = Math.min(m, n);
        for (int i = 0; i < tt / 2; i++) {
            List<Integer> list = getList(i, grid, lm, ln);
            List<Integer> ls = rotateList(list, k);
            updateMAtrix(grid, ls, i, lm, ln);
            lm = lm - 2;
            ln = ln - 2;
            //   [[4,2,4,7,2,4,7,1,7,2],
            //    [9,1,4,5,7,3,7,6,9,10],
            //    [8,6,10,1,4,6,6,2,6,3],
            //    [5,6,7,1,1,5,2,5,7,5],
            //    [4,9,1,1,2,2,3,8,4,7],
            //    [7,7,7,1,5,10,3,4,7,2]]
        }
        return grid;
    }

    private void updateMAtrix(int[][] grid, List<Integer> list, int i, int lm, int ln) {
        int si = i;
        int sj = i;
        int l = lm;
        int j = 0;
        while (l > 1) {
            si++;
            grid[si][sj] = list.get(j++);
            l--;
        }
        l = ln;
        while (l > 1) {
            sj++;
            grid[si][sj] = list.get(j++);
            l--;
        }
        l = lm;
        while (l > 1) {
            si--;
            grid[si][sj] = list.get(j++);
            l--;
        }
        l = ln;
        while (l > 1) {
            sj--;
            grid[si][sj] = list.get(j++);
            l--;
        }
    }

    private List<Integer> rotateList(List<Integer> list, int k) {
        int l = list.size();
        List<Integer> list2 = new ArrayList<>();
        k = k % l;

        list2 = list.subList(l - k, l);
        list2.addAll(list.subList(0, l - k));
        return list2;
    }

    private List<Integer> getList(int i, int[][] grid, int lm, int ln) {
        int si = i;
        int sj = i;
        int l = lm;
        List<Integer> list = new ArrayList<>();
        while (l > 1) {
            si++;
            int t = grid[si][sj];
            list.add(t);
            l--;
        }
        l = ln;
        while (l > 1) {
            sj++;
            int t = grid[si][sj];
            list.add(t);
            l--;
        }
        l = lm;
        while (l > 1) {
            si--;
            int t = grid[si][sj];
            list.add(t);
            l--;
        }
        l = ln;
        while (l > 1) {
            sj--;
            int t = grid[si][sj];
            list.add(t);
            l--;
        }
        return list;
    }

    public int maxProductDifference(int[] nums) {
        PriorityQueue<Integer> maxQ = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            maxQ.add(nums[i]);
            minQ.add(nums[i]);
        }
        int a = maxQ.poll();
        int b = maxQ.poll();
        int c = minQ.poll();
        int d = minQ.poll();
        return a * b - c * d;
    }

    public String removeOccurrences(String s, String part) {
        StringBuilder ss = new StringBuilder(s);
        while (true) {
            StringBuilder tmp = new StringBuilder("");
            int in = ss.indexOf(part);
            if (in != -1) {
                tmp.append(ss.subSequence(0, in));
                tmp.append(ss.subSequence(in + part.length(), ss.length()));
            } else {
                break;
            }
            ss = tmp;
        }
        return ss.toString();
    }

    public boolean canBeIncreasing(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            List<Integer> arr = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (i != j)
                    arr.add(nums[j]);
            }
            boolean f = true;
            for (int j = 1; j < arr.size(); j++) {
                if (arr.get(j) <= arr.get(j - 1)) {
                    f = false;
                    break;
                }
            }
            if (f) return true;
        }
        return false;
    }
}

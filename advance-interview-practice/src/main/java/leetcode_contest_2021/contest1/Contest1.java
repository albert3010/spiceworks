package leetcode_contest_2021.contest1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

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
        rotateGrid(grid, 4);
//        [[4,2,4,7,2,4,7,1,7,2],[9,1,4,5,7,3,7,6,9,10],[8,6,10,1,6,3,5,7,2,7],[5,6,7,5,2,6,6,4,7,4],[4,9,1,1,2,2,3,8,4,3],[7,7,1,5,2,1,7,1,5,10]]
//   [[4,2,4,7,2,4,7,1,7,2],
//    [9,1,4,5,7,3,7,6,9,10],
//    [8,6,10,1,4,6,6,2,6,3],
//    [5,6,7,1,1,5,2,5,7,5],
//    [4,9,1,1,2,2,3,8,4,7],
//    [7,7,7,1,5,10,3,4,7,2]]

    }

    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int lm = m;
        int ln = n;
        int tt = Math.min(m,n);
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

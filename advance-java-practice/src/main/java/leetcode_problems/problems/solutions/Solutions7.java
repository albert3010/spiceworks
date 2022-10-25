package leetcode_problems.problems.solutions;


import org.junit.Test;

import java.util.*;

public class Solutions7 {
    @Test
    public void Tests() {

        int a[] = {2, 2, 1, 2, 1, 2, 2, 1, 1, 2};
        String s[] = {"\\/", "/\\"};
//        System.out.println(minRemoveToMakeValid("(a(b(c)d)"));
//        System.out.println(numberOfSubarrays(a, 2));
        System.out.println(regionsBySlashes(s));

    }

    public int regionsBySlashes(String[] grid) {

        int N = grid.length;

        int mat[][] = new int[3 * N + 1][3 * N + 1];
        int ans = 0;
        fillOneInboundry(mat, N);

        for (int row = 0; row < N; row++) {

            int l = grid[row].length();
            String s = grid[row];
            for (int col = 0; col < l; col++) {
                markWithOne(mat, s.charAt(col), row, col);
            }
        }

        for (int row = 0; row <= 3 * N; row++) {
            for (int col = 0; col <= 3 * N; col++) {
                if (mat[row][col] == 0) {
                    ans++;
                    markConnectedRegionDFS(mat, row, col, ans, N);
                }
            }
        }
//        print(mat, N);
        return ans;
    }

    void markConnectedRegionDFS(int[][] mat, int row, int col, int mark, int N) {

        if (mat[row][col] > 0 || row > 3 * N || col > 3 * N || row < 0 || col < 0) {
            return;
        }
        mat[row][col] = mark;
        int d[] = {0, -1, 0, 1, 0};
        for (int k = 0; k < 4; k++) {
            markConnectedRegionDFS(mat, row + d[k], col + d[k + 1], mark, N);
        }
    }

    void markWithOne(int[][] mat, char c, int row, int col) {

        if (c == ' ') return;
        int change = 1;
        int rw = row * 3;
        int cl = col * 3;

        if (c == '/') {
            change = -1;
            cl = cl + 3;
        }
        for (int i = rw, j = cl; i <= rw + 3; i++, j += change) {
            mat[i][j] = 1;
        }
    }

    void fillOneInboundry(int[][] mat, int N) {
        for (int i = 0; i <= 3 * N; i++) {
            mat[i][0] = 1;
            mat[i][3 * N] = 1;
            mat[0][i] = 1;
            mat[3 * N][i] = 1;
        }
    }

    void print(int[][] mat, int N) {

        for (int row = 0; row <= 3 * N; row++) {
            for (int col = 0; col <= 3 * N; col++) {
                System.out.print(mat[row][col]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length;
        int st = 0, firstOdd, lastOdd;
        int oddCount = 0;
        int ans = 0;
        int curr = 0;
        Deque<Integer> deQueue = new ArrayDeque<>();

        while (curr < n) {
            if (nums[curr] % 2 == 1) {
                deQueue.add(curr);
                oddCount++;
            }
            if (oddCount == k + 1 && deQueue.size() > 0) {
                // removing to get jth index, will be add later as deQueue.addLast(l);
                int l = deQueue.removeLast();
                firstOdd = deQueue.getFirst();
                lastOdd = deQueue.getLast();
                ans += (firstOdd - st + 1) * (curr - lastOdd);

                deQueue.addLast(l);
                st = deQueue.removeFirst() + 1;
                oddCount--;
            }
            curr++;
        }
        if (oddCount == k && deQueue.size() > 0) {
            firstOdd = deQueue.getFirst();
            lastOdd = deQueue.getLast();
            ans += (firstOdd - st + 1) * (curr - lastOdd);
        }
        return ans;
    }

    public String minRemoveToMakeValid(String s) {

        Stack<Integer> stack = new Stack<>();

        int l = s.length();

        for (int i = 0; i < l; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                if (stack.size() > 0) {
                    if (s.charAt(stack.peek()) == '(') {
                        stack.pop();
                    } else {
                        stack.push(i);
                    }
                } else {
                    stack.push(i);
                }
            }

        }
        int n = stack.size();
        int a[] = new int[n];
        int t = n;
        while (n-- > 0) {
            a[n] = stack.pop();
        }
        StringBuilder ans = new StringBuilder();
        int j = 0;
        for (int i = 0; i < l; i++) {
            if (j < t && a[j] == i) {
                j++;
            } else {
                ans.append(s.charAt(i));
            }
        }
        return ans.toString();
    }

    public List<Integer> transformArray(int[] arr) {

        boolean flag = true;
        int n = arr.length;
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(arr[i]);
        }
        if (n <= 2) return a;

        while (flag) {
            int pre = a.get(0);
            flag = false;
            for (int i = 1; i < n - 1; i++) {
                int c = a.get(i);
                if (c > pre && c > a.get(i + 1)) {
                    a.set(i, c - 1);
                    flag = true;
                }
                if (c < pre && c < a.get(i + 1)) {
                    a.set(i, c + 1);
                    flag = true;
                }
                pre = c;
            }

        }
        return a;
    }
}
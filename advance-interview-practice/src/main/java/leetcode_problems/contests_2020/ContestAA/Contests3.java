package leetcode_problems.contests_2020.ContestAA;

import org.junit.Test;

import java.util.List;

public class Contests3 {


    @Test
    public void ContestsSolution() {

    }

    class Node {
        int count;
        int sum;

        Node(int count, int sum) {
            this.count = count;
            this.sum = sum;
        }
    }

    public int[] pathsWithMaxScore(List<String> board) {
        int mod = 1000000007;
        int n = board.size();
        Node mt[][] = new Node[n][n];
        mt[0][0] = new Node(1, 0);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board.get(i).charAt(j) == 'X' || (i == 0 && j == 0))
                    continue;
                Node a = checkAndGetValue(board, mt, i - 1, j, n);
                Node b = checkAndGetValue(board, mt, i, j - 1, n);
                Node c = checkAndGetValue(board, mt, i - 1, j - 1, n);
                int max = getMaxValue(a, b, c);
                int ct = getCurrentCount(a, b, c, max);
                int x = board.get(i).charAt(j) - '0';
                mt[i][j] = new Node(ct, (max + x) % mod);
            }
        }
        int a[] = new int[2];
        Node x = mt[n - 1][n - 1];
        if (x.count == 0) return a;
        a[0] = x.sum;
        a[1] = x.count;
        return a;
    }

    public int getCurrentCount(Node a, Node b, Node c, int max) {
        int ct = 0;
        int mod = 1000000007;
        if (a != null && max == a.sum) {
            ct = (ct + a.count) % mod;
        }
        if (b != null && max == b.sum) {
            ct = (ct + b.count) % mod;
        }
        if (c != null && max == c.sum) {
            ct = (ct + c.count) % mod;
        }
        return ct;
    }

    public int getMaxValue(Node a, Node b, Node c) {
        int max = 0;
        if (a != null) {
            max = Math.max(a.sum, max);
        }
        if (b != null) {
            max = Math.max(b.sum, max);
        }
        if (c != null) {
            max = Math.max(c.sum, max);
        }
        return max;
    }

    public Node checkAndGetValue(List<String> board, Node mt[][], int i, int j, int n) {
        if (i < 0 || j < 0 || board.get(i).charAt(j) == 'X') {
            return null;
        }
        return mt[i][j];

    }

}

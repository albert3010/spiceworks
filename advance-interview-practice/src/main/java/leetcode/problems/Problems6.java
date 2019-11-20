package leetcode.problems;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;

public class Problems6 {

    class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    @Test
    public void dietPlanPerformanceTest() {

        List<Integer> pairs = Lists.newArrayList();
        String words[] = {"cat", "bt", "hat", "tree"};
        String s = "RLLLLRRRLR";

        int king[] = {0, 0};
        int queens[][] = {{0, 1}, {1, 0}, {4, 0}, {0, 4}, {3, 3}, {2, 4}};
        // System.out.println(balancedStringSplit(s));
//        System.out.println(queensAttacktheKing(queens, king));
        System.out.println(kSimilarity("bccaba", "abacbc"));
    }

    public int kSimilarity(String A, String B) {
        Queue<String> q = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        if (A.equals(B)) return 0;
        q.add(A);
        int res = 0;
        int len = A.length();
        while (!q.isEmpty()) {

            int l = q.size();
            res++;
            for (int i = 0; i < l; i++) {
                String s = q.poll();
                int k = 0;
                while (k < len && s.charAt(k) == B.charAt(k)) {
                    k++;
                }
                // swap
                for (int j = k + 1; j < len; j++) {

                    if (s.charAt(j) == B.charAt(j) || s.charAt(j) != B.charAt(k)) continue;

                    String next = swap(s, k, j);
                    if (next.equals(B)) {
                        return res;
                    }

                    if (!visited.contains(next)) {
                        visited.add(next);
                        q.add(next);
                    }
                }
            }

        }
        return res;
    }

    public String swap(String s, int i, int j) {
        char[] chars = s.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }

    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {

        List<List<Integer>> results = new ArrayList<>();
        List<List<Integer>> moves = new ArrayList<>();
        moves.add(Arrays.asList(0, 1));
        moves.add(Arrays.asList(1, 1));
        moves.add(Arrays.asList(1, 0));
        moves.add(Arrays.asList(1, -1));
        moves.add(Arrays.asList(0, -1));
        moves.add(Arrays.asList(-1, -1));
        moves.add(Arrays.asList(-1, 0));
        moves.add(Arrays.asList(-1, 1));

        for (int i = 0; i < 8; i++) {
            int mv = 1;
            List<Integer> move = moves.get(i);
            int x, y;
            while (mv <= 8) {
                x = king[0] + move.get(0) * mv;
                y = king[1] + move.get(1) * mv;
                if (checkValidPath(x, y)) {
                    if (checkIfExists(x, y, queens)) {
                        results.add(Arrays.asList(x, y));
                        break;
                    }
                } else {
                    break;
                }
                mv++;

            }
        }
        return results;
    }

    boolean checkIfExists(int x, int y, int[][] queens) {
        int r = queens.length;

        for (int i = 0; i < r; i++) {
            if (x == queens[i][0] && y == queens[i][1]) {
                return true;
            }
        }
        return false;
    }

    boolean checkValidPath(int i, int j) {
        if (i >= 0 && j >= 0 && i <= 7 && j <= 7) {
            return true;
        }
        return false;
    }

    public int balancedStringSplit(String s) {

        int l = s.length();
        int RCount = 0, LCount = 0, count = 0;

        for (int i = 0; i < l; i++) {
            if (s.charAt(i) == 'R') {
                RCount++;
            } else {
                LCount++;
            }

            if (RCount != 0 && RCount == LCount) {
                count++;
                RCount = 0;
                LCount = 0;
            }
        }

        return count;
    }

}
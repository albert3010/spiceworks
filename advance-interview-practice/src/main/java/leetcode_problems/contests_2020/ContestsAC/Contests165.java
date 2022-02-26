package leetcode_problems.contests_2020.ContestsAC;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Contests165 {


    @Test
    public void ContestsSolution() {

        String[] products = {"bags", "baggage", "banner", "box", "cloths"};
//        synonyms.add(Lists.newArrayList("happy", "joy"));
        int a[][] = {{0, 0, 1, 1, 0, 1, 0, 0, 1, 0}, {1, 1, 0, 1, 1, 0, 1, 1, 1, 0}, {1, 0, 1, 1, 1, 0, 0, 1, 1, 0}, {0, 1, 1, 0, 0, 0, 0, 1, 0, 1}, {0, 0, 0, 0, 0, 0, 1, 1, 1, 0}, {0, 1, 0, 1, 0, 1, 0, 1, 1, 1}, {1, 0, 1, 0, 1, 1, 0, 0, 0, 1}, {1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 1, 0, 1, 0, 1}, {1, 1, 1, 0, 1, 1, 0, 1, 1, 0}};
//        int[] nums = {3, 6, 5, 1, 8};
//        int moves[][] = {{0,0},{2,0},{1,1},{2,1},{2,2}};
        int moves[][] = {{0, 0}, {1, 1}, {0, 1}, {0, 2}, {1, 0}, {2, 0}};

        int b[][] = {{0, 1, 1, 1}, {1, 1, 1, 1}, {0, 1, 1, 1}};
        System.out.println(countSquares(b));
//        System.out.println(tictactoe(moves));


    }

    public int countSquares(int[][] matrix) {

        int n = matrix.length;
        int m = matrix[0].length;
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {


                if (matrix[i][j] == 1) {
                    count1++;
                }
                if (i == 0 || j == 0) {
                    continue;
                }
                if (matrix[i][j] != 0) {
                    int min = Math.min(matrix[i - 1][j - 1], Math.min(matrix[i - 1][j], matrix[i][j - 1]));
                    if (min != 0) {
                        matrix[i][j] = min + 1;
                    }
                    if (matrix[i][j] > 1) {
                        count2 += matrix[i][j] - 1;
                    }
                }

            }

        }
        return count1 + count2;
    }

    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        List<Integer> ans = new ArrayList<>();

        if (tomatoSlices % 2 != 0) {
            return ans;
        }
        int x = tomatoSlices / 2;

        int d = x - cheeseSlices;
        if (d < 0) return ans;

        int b = cheeseSlices - d;

        if (b < 0) return ans;
        ans.add(d);
        ans.add(b);
        return ans;
    }

    public String tictactoe(int[][] moves) {
        int n = moves.length;
        int m = moves[0].length;

        char[][] ticTac = new char[3][3];
        char last = 'x';
        for (int i = 0; i < n; i++) {
            int a = moves[i][0];
            int b = moves[i][1];

            if (i % 2 == 0) {
                ticTac[a][b] = 'A';
                last = 'A';
            } else {
                ticTac[a][b] = 'B';
                last = 'B';
            }
        }
        if (checkWining(ticTac, last)) {
            return String.valueOf(last);
        }
        if (checkIfAllFilled(ticTac)) {
            return "Draw";
        }
        return "Pending";
    }

    boolean checkIfAllFilled(char[][] ticTac) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ticTac[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean checkWining(char[][] ticTac, char last) {
        int countA;
        int countB;
        for (int i = 0; i < 3; i++) {
            countA = 0;
            countB = 0;
            for (int j = 0; j < 3; j++) {
                if (last == ticTac[i][j]) {
                    countA++;
                }
                if (last == ticTac[j][i]) {
                    countB++;
                }
            }
            if (countA == 3 || countB == 3) return true;
        }
        countA = 0;
        countB = 0;
        for (int i = 0, j = 2, k = 0; i < 3; i++) {
            if (ticTac[i][i] == last) {
                countA++;
            }
            if (ticTac[k++][j--] == last) {
                countB++;
            }
            if (countA == 3 || countB == 3) return true;
        }
        return false;
    }
}

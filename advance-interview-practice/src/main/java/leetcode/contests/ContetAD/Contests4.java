package leetcode.contests.ContetAD;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class Contests4 {


    @Test
    public void ContestsSolution() {
        char seats [][] = {{'#','.','.','.','#'},
            {'.','#','.','#','.'},
            {'.','.','#','.','.'},
            {'.','#','.','#','.'},
            {'#','.','.','.','#'}};
        System.out.println(maxStudents(seats));
    }

    public int maxStudents(char[][] seats) {
        int n = seats.length;
        int m = seats[0].length;
        HashMap<String, Boolean> map = new HashMap<>();
        boolean v[][] = new boolean[n][m];
        int ans[] = new int[1];
        int count = 0;
        backTrackingHelper(seats, v, count, ans, map);
        return ans[0];
    }

    public void backTrackingHelper(char[][] seats, boolean v[][], int count, int ans[], HashMap<String, Boolean> map) {

        int a[] = getEmptySeat(seats, v);
        if (a == null || map.containsKey(flatMatrixToString(v))) {
            ans[0] = Math.max(ans[0], count);
            return;
        }
        int i = a[0];
        int j = a[1];
        v[i][j] = true;
        updateNeibour(v, i, j);
        String s = flatMatrixToString(v);
        map.put(s, true);
        backTrackingHelper(seats, v, count + 1, ans, map);
//        v[i][j] = false;
//        unUpdateNeibour(v, i, j);
//        backTrackingHelper(seats, v, count, ans, map);
    }

    public String flatMatrixToString(boolean v[][]) {
        int n = v.length;
        int m = v[0].length;
        String s = "";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (v[i][j]) {
                    s += "1";
                } else s += "0";
            }
        }
        return s;
    }

    public void unUpdateNeibour(boolean v[][], int i, int j) {
        int n = v.length;
        int m = v[0].length;
        int x[] = {-1, 0, 1};
        int y[] = {1, -1};
        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 2; b++) {
                int xx = i + a;
                int yy = j + b;
                if (xx >= 0 && yy >= 0 && xx < n && yy < m) {
                    v[xx][yy] = false;
                }
            }
        }
    }

    public void updateNeibour(boolean v[][], int i, int j) {
        int n = v.length;
        int m = v[0].length;
        int x[] = {-1, 0, 1};
        int y[] = {1, -1};
        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 2; b++) {
                int xx = i + a;
                int yy = j + b;
                if (xx >= 0 && yy >= 0 && xx < n && yy < m) {
                    v[xx][yy] = true;
                }
            }
        }
    }

    public int[] getEmptySeat(char[][] seats, boolean v[][]) {
        int n = seats.length;
        int m = seats[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (seats[i][j] == '.' && !v[i][j]) {
                    int a[] = {i, j};
                    return a;
                }
            }
        }
        return null;
    }

}

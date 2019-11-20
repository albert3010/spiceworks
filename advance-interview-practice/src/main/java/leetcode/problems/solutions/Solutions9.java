package leetcode.problems.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Solutions9 {
    @Test
    public void Tests() {

//        upper = 2, lower = 1, colsum = [1,1,1]
        int[] a = {2, 1, 2, 0, 1, 0, 1, 2, 0, 1};
//        System.out.println(reconstructMatrix(5, 5, a));
        System.out.println(Decoding("oppeeen", "1230"));
    }

    public String Decoding(String msg, String key) {

        int k = 0;
        String ans = "";
        int i = 0;
        int n = msg.length();

        while (i < n && k<key.length()) {

            int num = (key.charAt(k) - '1') + 1;
            ans += msg.charAt(i);
            int m = num;
            int t = i;

            while (num > 0 && t < n) {
                if (msg.charAt(i) != msg.charAt(t++)) {
                    return "-1";
                }
                num--;
            }
            if (num != 0) {
                return "-1";
            }
            i = i + m;
            k++;
        }

        if(k<key.length() ){
            return "-1";
        }

        return ans+ msg.substring(i);
    }


    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {

        int l = colsum.length;

        List<List<Integer>> tm = new ArrayList<>();
        int[][] ans = new int[2][l];

        int[] a = new int[1];

        int col = 0;
        helper(col, 0, 0, upper, lower, colsum, ans, a);

        if (a[0] == 1) {
            for (int i = 0; i < 2; ++i) {
                List<Integer> row = new ArrayList<>();
                tm.add(row);
                for (int j = 0; j < l; j++) {
                    tm.get(i).add(j, ans[i][j]);
                }

            }
            return tm;
        }
        return new ArrayList<>();
    }

    void helper(int col, int upperSum, int lowerSum, int upper, int lower, int[] colsum, int[][] ans, int a[]) {

        if (a[0] == 1 || upperSum > upper || lowerSum > lower) return;

        if (col == colsum.length) {
            if (upperSum == upper && lowerSum == lower) {
                a[0] = 1;
            }
            return;
        }
        if (a[0] != 1) {
            ans[0][col] = colsum[col];
            ans[1][col] = 0;
            helper(col + 1, upperSum + colsum[col], lowerSum, upper, lower, colsum, ans, a);
        }
        if (colsum[col] - 1 >= 0 && a[0] != 1) {
            ans[0][col] = colsum[col] - 1;
            ans[1][col] = 1;
            helper(col + 1, upperSum + colsum[col] - 1, lowerSum + 1, upper, lower, colsum, ans, a);
        }
        if (colsum[col] - 2 >= 0 && a[0] != 1) {
            ans[0][col] = colsum[col] - 2;
            ans[1][col] = 2;
            helper(col + 1, upperSum + colsum[col] - 2, lowerSum + 2, upper, lower, colsum, ans, a);
        }


    }

}
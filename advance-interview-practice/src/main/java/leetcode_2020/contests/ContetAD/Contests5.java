package leetcode_2020.contests.ContetAD;

import org.junit.Test;

public class Contests5 {


    @Test
    public void ContestsSolution() {

        int arr [] ={2,5,1,8,4,2,2,7,9,8};
        System.out.println(solveArrayMulti(20, arr,25,69));
    }

    public static int solveArrayMulti(int K, int arr[], long L, long R) {
        int ans[] = new int[201];
        int n = arr.length;

        int x = (int) (L - 1) / n;
        int x1 = (int) (L - 1) % n;
        int y = (int) (R - 1) / n;
        int y1 = (int) (R - 1) % n;
        int startL = x1;
        int endL = n - 1;
        if (x == y) {
            endL = y1;
        }
        for (int i = startL; i <= endL; i++) {
            ans[arr[i]]++;
        }
        if (x != y) {
            for (int i = 0; i <= endL; i++) {
                ans[arr[i]]++;
            }
        }
        int rp = y - x - 1;

        if (rp > 0) {
            for (int i = 0; i <= n - 1; i++) {
                ans[arr[i]]+=rp;
            }
        }
        int max = -1;
        int result = -1;
        for (int i = 1; i < 101; i++) {
            if (ans[i] >= max) {
                max = ans[i];
                result = i;
            }
        }
        return result;
    }

}

package leetcode_2020.problems.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solutions5 {
    @Test
    public void Tests() {
//        System.out.println(circularPermutation(3, 2));
//        int[] arr = {2, 2, 2, 1, 2, 2, 3};
        int[] arr = {17,6,5,18,17,4,18,8,16,8,12,1,5,14,14,6};
        // ans = 25
//        System.out.println(tilingRectangle(11, 13));
        System.out.println(minimumMoves(arr));
        System.out.println(minimumMoves1(arr));

    }

    public int minimumMoves(int[] arr) {

        int n = arr.length;
        int dp[][] = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
            if (i < n - 1) {
                if (arr[i] == arr[i + 1]) {
                    dp[i][i + 1] = 1;
                } else {
                    dp[i][i + 1] = 2;
                }
            }
        }

        for (int gap = 2; gap < n; gap++) {

            for (int st = 0, end = st + gap; end < n; st++, end++) {

                int ii = st;
                int jj = end;
                dp[st][end] = end-st+1;

                if(arr[st]==arr[end]){
                    dp[st][end] = Math.min(dp[st][end], dp[st+1][end-1]);
                }
                for (int i = ii; i < jj; i++) {
                    dp[st][end] = Math.min(dp[ii][i] + dp[i + 1][jj], dp[st][end]);
                }
            }
        }
        return dp[0][n - 1];
    }
    int minimumMoves1(int[] arr){
        int i,j;
        int n=arr.length;
        int dp[][] = new int[n][n];
        for(i=0;i<n;i++){
            for(j=0;j<n;j++){
                dp[i][j]=1000;
            }
        }

        int l = rep(0,n-1, dp, arr);

        for(i=0;i<n;i++){
            for(j=0;j<n;j++){
                System.out.print(dp[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        return l;
    }
    int rep(int st,int fi, int [][]dp, int []arr){
        if(st>=fi){return 1;}
        if(dp[st][fi]!=1000){return dp[st][fi];}
        if(arr[st]==arr[fi]){
            dp[st][fi]=rep(st+1,fi-1, dp, arr);
            return dp[st][fi];
        }
        int i;
        for(i=st;i<=fi-1;i++){
            dp[st][fi]=Math.min(rep(st,i, dp, arr)+rep(i+1,fi, dp, arr),dp[st][fi]);
        }
        return dp[st][fi];
    }
    public int tilingRectangle(int n, int m) {
        // not complete solution
        int t = Math.max(n, m);

        int dp[][] = new int[t + 1][t + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= t; i++) {
            dp[i][0] = 0;
            dp[0][i] = 0;
            dp[i][i] = 1;
        }
        for (int i = 1; i <= t; i++) {
            for (int j = 1; j <= t; j++) {
                int x = Math.min(i, j);
                for (int k = 1; k <= x; k++) {
                    int val = dp[k][k] + dp[i - k][k] + dp[i - k][j - k] + dp[k][j - k];
                    if (dp[i][j] == 0) {
                        dp[i][j] = val;
                    } else {
                        dp[i][j] = Math.min(dp[i][j], val);
                    }
                }
            }
        }
        return dp[m][n];

    }

    public List<Integer> circularPermutation(int n, int start) {

        List<Integer>[] preComputeDp = new ArrayList[n + 1];
        int foundIndex = 0;

        preComputeDp[0] = new ArrayList<>();
        preComputeDp[0].add(0);

        for (int x = 1; x <= n; x++) {
            preComputeDp[x] = new ArrayList<>();
            int add = 1 << (x - 1);
            int l = preComputeDp[x - 1].size();

            for (int i = l - 1; i >= 0; i--) {
                int num = preComputeDp[x - 1].get(i);
                if (num == start) {
                    foundIndex = l - i - 1;
                }
                preComputeDp[x].add(num);
            }
            for (int i = 0; i < l; i++) {
                int num = add + preComputeDp[x - 1].get(i);
                if (num == start) {
                    foundIndex = l + i;
                }
                preComputeDp[x].add(num);
            }
        }
        Collections.rotate(preComputeDp[n], -foundIndex);
        return preComputeDp[n];
    }
}
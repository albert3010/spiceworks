package leetcode_problems.problems.solutions;


public class Solutions {


    public static void main(String[] args) {

//        Scanner sc = new Scanner(System.in);
//        int t = sc.nextInt();
//        for (int i = 0; i < t; i++) {
//            int n = sc.nextInt();
//            int[] arr = new int[n];
//            for (int j = 0; j < n; j++) {
//                arr[j] = sc.nextInt();
//            }
//
//            System.out.println(longestIncreasingSubsequence(arr));
//        }

        //System.out.println(longestCommonSubsequence("ABACDDGGHGG", "AEADDFHRGGGG"));
        int arr[] = {86, 177, 115, 193, 135, 186, 92, 49, 21, 162, 27, 90, 59, 163, 126, 140, 26, 172, 136, 11, 168, 167, 29, 182, 130, 62, 123, 67, 135, 129, 2, 22, 58, 69, 167, 193, 56, 11, 42, 29, 173, 21, 119, 184, 137, 198, 124, 115, 170, 13, 126, 91, 180, 156, 73, 62, 170, 196, 81, 105, 125, 84, 127, 136, 105, 46, 129, 113, 57, 124, 95, 182, 145, 14, 167, 34, 164, 43, 150, 87, 8, 76, 178};
        System.out.println(longestIncreasingSubsequence(arr));
    }

    public boolean checkStraightLine(int[][] coordinates) {
        int l = coordinates.length;

        if (l <= 2) {
            return true;
        }

        int yy = coordinates[1][1] - coordinates[0][1];
        int xx = coordinates[1][0] - coordinates[0][0];

        for (int i = 2; i < l; i++) {

            int yd = coordinates[i][1] - coordinates[0][1];
            int xd = coordinates[i][0] - coordinates[0][0];

            int a = xx * yd;
            int b = yy * xd;

            if (a != b) {
                return false;
            }
        }
        return true;

    }

    public int longestCommonSubsequence(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n + 1][m + 1];
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                }
                dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], Math.max(dp[i + 1][j], dp[i][j + 1]));
                max = Math.max(dp[i + 1][j + 1], max);
            }
        }
        return max;

    }

    static int longestIncreasingSubsequence(int a[]) {
        int l = a.length;
        int dp[] = new int[l];
        if (l <= 1) {
            return l;
        }
        int max = 1;
        for (int i = 0; i < l; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {

                if (a[i] > a[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max;

    }
}
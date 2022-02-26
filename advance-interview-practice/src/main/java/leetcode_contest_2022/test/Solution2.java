package leetcode_contest_2022.test;

import java.util.*;

public class Solution2 {

    public static void main(String[] args) {
        List<String> grid = new ArrayList<>();
        grid.add("..##");
        grid.add("#.##");
        grid.add("#...");
//        System.out.println(reachTheEnd(grid, 5));
        int[] aaa = {2, 1, 3, 4, 1, 5, 8, 2, 9, 3, 5, 9, 8, 7};
        int[][] aa = {{1, 2, 2}, {2, 3, 3}, {3, 3, 1}};

//        minimumCost(aaa);
//        String[] B = {
//                "X.....>",
//                "..v..X.",
//                ".>..X..",
//                "A......"};

        String[] B = {
                "A.v",
                "..."};
//        System.out.println(solution(aaa));
        System.out.println(minCostToPaint(aa));
    }

    public boolean isRobotBounded(String instructions) {
//        GLR
        int[][] dir = new int[4][2];
        dir[0][0] = 0;
        dir[0][1] = 1;
        dir[1][0] = 1;
        dir[1][1] = 0;
        dir[2][0] = 0;
        dir[2][1] = -1;
        dir[3][0] = -1;
        dir[3][1] = 0;
        int x = 0;
        int y = 0;
        int currDir = 0;

        while (true) {
            int l = instructions.length();
            for (int i = 0; i < l; i++) {
                if (instructions.charAt(i) == 'G') {
                    x += dir[currDir][0];
                    y += dir[currDir][1];
                } else if (instructions.charAt(i) == 'L') {
                    currDir = (currDir - 1 + 4) % 4;
                } else if (instructions.charAt(i) == 'R') {
                    currDir = (currDir + 1 + 4) % 4;
                }
            }
            if(currDir==0){
                if(x == 0 && y == 0) return true;
                return false;
            }
        }
    }

    public int swimInWater(int[][] grid) {
        int lowLevel = 0;
        int highLevel = 2500;
        int ans = highLevel;
        while (lowLevel <= highLevel) {
            int mid = (lowLevel + highLevel) / 2;
            if (solveWaterBFS(grid, mid)) {
                System.out.println(mid);
                ans = Math.min(highLevel, mid);
                highLevel = mid - 1;
            } else {
                lowLevel = mid + 1;
            }
        }
        return ans;
    }

    private static boolean solveWaterBFS(int[][] grid, int level) {
        if (grid[0][0] > level) {
            return false;
        }
        int row = grid.length;
        int col = grid[0].length;
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[row][col];
        int[] dir = {1, 0, -1, 0, 1};

        queue.add(new Node(0, 0));
        visited[0][0] = true;
        while (queue.size() > 0) {
            Node nd = queue.poll();

            for (int k = 0; k < 4; k++) {
                int ii = nd.i + dir[k];
                int jj = nd.j + dir[k + 1];
                if (ii >= 0 && ii < row && jj >= 0 && jj < col && grid[ii][jj] <= level && !visited[ii][jj]) {
                    if (ii == row - 1 && jj == col - 1) {
                        return true;
                    }
                    queue.add(new Node(ii, jj));
                    visited[ii][jj] = true;
                }
            }
        }
        return false;
    }

    public long maxPoints(int[][] points) {
        int m = points.length;
        int n = points[0].length;
        long dp[][] = new long[m][n];

        for (int i = 0; i < n; i++) {
            dp[0][i] = points[0][i];
        }
        long max = Integer.MIN_VALUE;
        for (int i = 1; i < m; i++) {

            long leftMax[] = new long[n];
            long rightMax[] = new long[n];
            leftMax[0] = dp[i - 1][0];
            for (int j = 1; j < n; j++) {
                leftMax[j] = Math.max(leftMax[j - 1] - 1, dp[i - 1][j]);
            }
            rightMax[n - 1] = dp[i - 1][n - 1];
            for (int j = n - 2; j >= 0; j--) {
                rightMax[j] = Math.max(rightMax[j + 1] - 1, dp[i - 1][j]);
            }
            for (int k = 0; k < n; k++) {
                dp[i][k] = Math.max(leftMax[k], rightMax[k]) + points[i][k];
            }
        }

        for (int k = 0; k < n; k++) {
            max = Math.max(max, dp[m - 1][k]);
        }
        return max;
    }


    public int minimizeTheDifference(int[][] mat, int target) {
        int m = mat.length;
        int n = mat[0].length;
        int[] min = new int[1];
        min[0] = 4900 + 1;
        boolean[][] visited = new boolean[m + 1][min[0]];
        helper(mat, 0, 0, target, min, visited);
        return min[0];
    }

    private void helper(int[][] mat, int row, int sum, int target, int[] min, boolean[][] visited) {
        if (row == mat[0].length) {
            min[0] = Math.min(min[0], Math.abs(sum - target));
            return;
        }
        if (visited[row][sum]) {
            return;
        }

        for (int i = 0; i < mat[0].length; i++) {

            helper(mat, row + 1, sum + mat[row][i], target, min, visited);
        }
        visited[row][sum] = true;
    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        int k = 2;
        int l = nums.length;
        for (int i = 0; i < l; i++) {
            sum += nums[i];
        }
        if (sum % k != 0) return false;
        int target = sum / 2;
        boolean dp[][] = new boolean[l + 1][target + 1];

        for (int i = 0; i < l; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= l; i++) {
            for (int j = 1; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - nums[i - 1] >= 0) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[l][target];
    }

    static int minCostToPaint(int[][] block) {
        int l = block.length;
        int dp[][] = new int[l][3];
        if (l == 0) return 0;
        dp[0][0] = block[0][0];
        dp[0][1] = block[0][1];
        dp[0][2] = block[0][2];
        for (int i = 1; i < l; i++) {
            dp[i][0] = block[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = block[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = block[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
            int a = 0;
        }
        return Math.min(dp[l - 1][0], Math.min(dp[l - 1][1], dp[l - 1][2]));
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % k != 0) return false;

        boolean[] v = new boolean[nums.length];
        return dfsHelper(k, sum / k, 0, nums, 0, v);


    }

    private boolean dfsHelper(int k, int targetSum, int currentSum, int[] nums, int i, boolean[] v) {
        if (k == 1) return true;

        if (targetSum == currentSum) {
            return dfsHelper(k - 1, targetSum, 0, nums, 0, v);
        }

        for (int j = i; j < nums.length; j++) {
            if (v[i]) continue;
            v[i] = true;
            if (currentSum + nums[i] <= targetSum && dfsHelper(k, targetSum, currentSum + nums[i], nums, j + 1, v)) {
                return true;
            }
            v[i] = false;
        }
        return false;
    }

    public List<Integer> findLonely(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.putIfAbsent(nums[i], 0);
            map.put(nums[i], map.get(nums[i]) + 1);
        }
        List<Integer> ans = new ArrayList<>();
        for (Integer key : map.keySet()) {
            if (!(map.get(key) > 1 || map.get(key - 1) != null || map.get(key + 1) != null)) {
                ans.add(key);
            }
        }
        return ans;
    }

    public int[] rearrangeArray(int[] nums) {
        int l = nums.length;
        Deque<Integer> pos = new LinkedList<>();
        Deque<Integer> neg = new LinkedList<>();

        for (int i = 0; i < l; i++) {
            if (nums[i] > 0) {
                pos.add(nums[i]);
            } else {
                neg.add(nums[i]);
            }
        }
        int[] ans = new int[l];
        for (int i = 0; i < l; ) {
            ans[i++] = pos.pollFirst();
            ans[i++] = neg.pollFirst();
        }
        return ans;
    }

    public int countElements(int[] nums) {
        Arrays.sort(nums);
        int a = nums[0];
        int l = nums.length;
        int b = nums[l];
        int i = 0;
        for (; i < l; i++) {
            if (a != nums[i]) {
                break;
            }
        }
        int j = l - 1;
        for (; i >= 0; j--) {
            if (b != nums[j]) {
                break;
            }
        }
        if (i == j || i + 1 == j) {
            return 0;
        }
        return j - i + 1;
    }

    public static int solution(int[] A) {
        int[] feb = {0, 1};
        int l = A.length;
        int ans = -1;
        for (int i = 0; i < l; i++) {
            if (feb[1] == A[i]) {
                int b = feb[1];
                feb[1] = feb[0] + feb[1];
                feb[0] = b;
                ans = i;
            }
        }
        return ans;
    }

    public static int minimumCost(int[] cost) {

        List<Integer> aa = new ArrayList<>();

        int costs = 0;
        int l = cost.length;
        for (int i = 0; i < l; i++) {
            aa.add(cost[i]);
        }
        Collections.sort(aa, Collections.reverseOrder());
        for (int i = 0; i < l; ) {
            if (i + 1 < l) {
                costs += aa.get(i) + aa.get(i + 1);
            } else {
                costs += aa.get(i);
            }
            i = i + 3;
        }
        return costs;
    }

    static class Node {
        int i;
        int j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static boolean solution2(String[] B) {
        int row = B.length;
        int col = B[0].length();

        char[][] mat = new char[row][col];
        int ii = 0;
        int jj = 0;
        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < B[i].length(); j++) {
                mat[i][j] = B[i].charAt(j);
                if (mat[i][j] == 'A') {
                    ii = i;
                    jj = j;
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                if (mat[i][j] == '>') {
                    for (int k = j + 1; k < col; k++) {
                        if (mat[i][k] == '.' || mat[i][k] == '#') {
                            mat[i][k] = '#';
                        } else {
                            break;
                        }
                    }
                }
                if (mat[i][j] == '<') {
                    for (int k = j - 1; k >= 0; k--) {
                        if (mat[i][k] == '.' || mat[i][k] == '#') {
                            mat[i][k] = '#';
                        } else {
                            break;
                        }
                    }
                }

                if (mat[i][j] == 'v') {
                    for (int k = i + 1; k < row; k++) {
                        if (mat[k][j] == '.' || mat[i][k] == '#') {
                            mat[k][j] = '#';
                        } else {
                            break;
                        }
                    }
                }
                if (mat[i][j] == '^') {
                    for (int k = i - 1; k >= 0; k--) {
                        if (mat[k][j] == '.' || mat[i][k] == '#') {
                            mat[k][j] = '#';
                        } else {
                            break;
                        }
                    }
                }


            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(mat[i][j]);
            }
            System.out.println();
        }
        return solveBFS(mat, new Node(ii, jj), row, col);

    }

    private static boolean solveBFS(char[][] mat, Node node, int row, int col) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        int[] dir = {1, 0, -1, 0, 1};
        while (queue.size() > 0) {
            Node nd = queue.poll();
            for (int k = 0; k < 4; k++) {
                int ii = nd.i + dir[k];
                int jj = nd.j + dir[k + 1];
                if (ii >= 0 && ii < row && jj >= 0 && jj < col && mat[ii][jj] == '.') {
                    if (ii == row - 1 && jj == col - 1) {
                        return true;
                    }
                    queue.add(new Node(ii, jj));
                    mat[ii][jj] = '*';
                }
            }
        }
        return false;
    }


    public static String reachTheEnd(List<String> grid, int maxTime) {

        int n = grid.size();
        int m = grid.get(0).length();
        int dp[][] = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                if (!(i == 0 || j == 0) && grid.get(i - 1).charAt(j - 1) == '.') {

                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]);

                    if (dp[i][j] != Integer.MAX_VALUE) {
                        dp[i][j]++;
                    }
                    if (i == 1 && j == 1) dp[i][j] = 0;
                }
            }
        }
        if (dp[n][m] != Integer.MAX_VALUE && dp[n][m] <= maxTime) {
            return "Yes";
        }
        return "No";
    }
}

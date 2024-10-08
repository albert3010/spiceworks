package leetcode_contest_2022.group1;

import java.util.*;

public class Contest_jan {
    static int[] pre;

    public static void main(String[] args) {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter wr = new PrintWriter(System.out);
        int T = 2;
        pre = preCompute();
        for (int t_i = 0; t_i < T; t_i++) {
//            String[] str = br.readLine().split(" ");
            int l = 31;
            int r = 32;

            long out_ = solve(l, r);
            System.out.println(out_);

        }

    }

    class Node {
        int i;
        int j;
        int health;

        public Node(int i, int j, int health) {
            this.i = i;
            this.j = j;
            this.health = health;
        }
    }

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> b.health - a.health);
        int m = grid.size();
        int n = grid.get(0).size();
        int dp[][] = new int[m][n];

        pq.add(new Node(0, 0, health));
        int dir[] = {-1, 0, 1, 0, -1};

        while (!pq.isEmpty()) {
            Node nd = pq.poll();
            if(nd.i ==m-1 && nd.j == n-1){
                if(nd.health>0) return true;
                return false;
            }
            for (int k = 0; k < 4; k++) {
                int x = nd.i + dir[k];
                int y = nd.j + dir[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && dp[x][y]< nd.health - grid.get(x).get(y)) {
                    dp[x][y] = nd.health - grid.get(x).get(y);
                    pq.add(new Node(x, y, dp[x][y]));
                }
            }
        }
        return false;
    }

    static long solve(int l, int r) {
        // Your code goes here
        return pre[r] - pre[l];

    }

    static int[] preCompute() {
        int t = 1000000;
        Map<Integer, Boolean> map = new HashMap<>();

        int[] pre = new int[t + 1];
        int count = 0;

        for (int i = 1; i <= t; i++) {
            Set<Integer> set = new HashSet<>();
            boolean f = dfs(i, map, set);
            if (f) count += i;
            pre[i] = count;
        }
        return pre;
    }

    static boolean dfs(int n, Map<Integer, Boolean> map, Set<Integer> set) {
        if (map.get(n) != null) {
            if (map.get(n))
                return true;
            return false;
        }
        if (set.contains(n)) {
            return false;
        }
        int sum = 0;
        int t = n;
        while (t > 0) {
            sum += (t % 10) * (t % 10);
            t /= 10;
        }
        set.add(n);
        if (sum == 1) {
            map.put(n, true);
            return true;
        } else {
            boolean f = dfs(sum, map, set);
            map.put(n, f);
            return f;
        }
    }

}

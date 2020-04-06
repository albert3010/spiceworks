package leetcode.contests.ContetAD;

import org.junit.Test;

import java.util.*;

public class Contests11 {

    class Node {
        int v;
        int w;
        int k;

        Node(int v, int w, int k) {
            this.v = v;
            this.w = w;
            this.k = k;
        }
    }

    int[][] grid;
    int n;
    int m;

    @Test
    public void ContestsSolution() {

    }

    public int shortestPath(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;
        this.grid = grid;
        this.n = n;
        this.m = m;
        int dp[][] = new int[n][m];
        dfs(0, 0, 0, k, dp);
        return 1;
    }

    public void dfs(int i, int j, int count, int k, int sp[][]) {
        if (i < 0 || j < 0 || i >= n || j >= m) return;

    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        List<Node>[] graph = new ArrayList[n];
        if (src == dst) return 0;
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] flight : flights) {
            graph[flight[0]].add(new Node(flight[1], flight[2], 0));
        }
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);

        queue.add(new Node(src, 0, 0));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.v == dst) {
                return node.w;
            }
            if (node.k != K+1) continue;
            for (Node nd : graph[node.v]) {
                    queue.add(new Node(nd.v, node.w + nd.w, node.k + 1));
            }
        }
        return -1;
    }

}

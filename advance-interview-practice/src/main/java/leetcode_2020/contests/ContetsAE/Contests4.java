package leetcode_2020.contests.ContetsAE;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Contests4 {
    @Test
    public void ContestsSolution() {

    }

    class Edge {
        int to;
        int interNodes;
        int maxReached;

        Edge(int to, int interNodes, int maxReached) {
            this.to = to;
            this.interNodes = interNodes;
            this.maxReached = maxReached;
        }

        public void setMaxReached(int maxReached) {
            this.maxReached = maxReached;
        }

        public int getMaxReached() {
            return this.maxReached;
        }
    }

    public int reachableNodes(int[][] edges, int M, int N) {
        List<Edge>[] graph = new ArrayList[N];
        int m = edges.length;

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        // Construct graph
        for (int i = 0; i < m; i++) {
            graph[edges[i][0]].add(new Edge(edges[i][1], edges[i][2], 0));
            graph[edges[i][1]].add(new Edge(edges[i][0], edges[i][2], 0));
        }
        int steps = 0;
        traverseGraphDFS(0, steps, graph, M);
return 1;
    }

    public void traverseGraphDFS(int node, int steps, List<Edge>[] graph, int M) {
        if (steps > M) return;
        List<Edge> edges = graph[node];
        int remainingSteps = M - steps;
        for (Edge eg : edges) {
            if (eg.interNodes != 0) {
                if (remainingSteps < eg.interNodes + 1) {
                    eg.setMaxReached(Math.max(eg.getMaxReached(), remainingSteps));
                } else {
                    traverseGraphDFS(eg.to, steps + 1, graph, M);
                }
            } else {

                traverseGraphDFS(eg.to, steps + 1, graph, M);
            }

        }

    }

}

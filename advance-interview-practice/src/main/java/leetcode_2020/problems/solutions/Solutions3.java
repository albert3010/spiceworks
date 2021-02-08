package leetcode_2020.problems.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solutions3 {
    class Point implements Comparable<Point> {
        int x, y, dis;

        Point(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }

        @Override
        public int compareTo(Point p) {
            return this.dis > p.dis ? 1 : -1;
        }
    }

    class Node {
        int x;
        char c;

        Node(int x, char c) {
            this.x = x;
            this.c = c;
        }
    }

    @Test
    public void Tests() {

        int gar[][] = {{1, 2}};
//   5
//  [[2,2],[0,1],[0,3],[0,0],[0,4],[2,1],[2,0],[1,4],[3,4]]
//[[1,3],[0,0],[0,3],[4,2],[1,0]]
//       ans [0,1,2,1,1]
        int red_edges[][] = {{2,2},{0,1},{0,3},{0,0},{0,4},{2,1},{2,0},{1,4},{3,4}};
        int blue_edges[][] = {{1,3},{0,0},{0,3},{4,2},{1,0}};

//        System.out.println(kClosest(null, 1));
//        System.out.println(gardenNoAdj(5, gar));
        System.out.println(shortestAlternatingPaths(5, red_edges, blue_edges));
    }

    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        List<Node>[] graph = new ArrayList[n];
        int ans[] = new int[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            ans[i] = -1;
        }
        int l = red_edges.length;
        for (int i = 0; i < l; i++) {
            graph[red_edges[i][0]].add(new Node(red_edges[i][1], 'R'));
        }

        l = blue_edges.length;
        for (int i = 0; i < l; i++) {
            graph[blue_edges[i][0]].add(new Node(blue_edges[i][1], 'B'));
        }
        int visited[] = new int[n];
        int maxlen = 0;
        ans[0] = 0;
        traverseGraphDFS(0, graph, visited, ans, 'R', maxlen, false);
        traverseGraphDFS(0, graph, visited, ans, 'B', maxlen, false);

        return ans;
}

    public void traverseGraphDFS(int node, List<Node>[] graph, int[] visited, int[] ans, char parentColor, int maxlen, boolean self) {
        if (!self && visited[node]==3) {
            return;
        }
        if(!self && parentColor=='B' && visited[node]==1){
            return;
        }
        if(!self && parentColor=='R' && visited[node]==2){
            return;
        }
        visited[node] = parentColor=='B'? visited[node]+1: visited[node]+2;

        List<Node> childs = graph[node];
        char color = 'B';

        if (parentColor == 'B') {
            color = 'R';
        }
        ans[node] = ans[node] > 0 ? Math.min(maxlen, ans[node]) : maxlen;
        for (Node child : childs) {
            if (child.c == color) {
                if ((child.x == node)) {
                    if (!self)
                        traverseGraphDFS(child.x, graph, visited, ans, parentColor, maxlen + 1, true);
                } else {
                    traverseGraphDFS(child.x, graph, visited, ans, color, maxlen + 1, false);
                }
            }
        }
    }

    public int[] gardenNoAdj(int N, int[][] paths) {

        List<Integer>[] graph = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        int n = paths.length;
        for (int i = 0; i < n; i++) {
            graph[paths[i][0]].add(paths[i][1]);
            graph[paths[i][1]].add(paths[i][0]);
        }
        for (int i = 1; i <= N; i++) {
            if (graph[i].size() == 0) {
                graph[i].add(i);
            }
        }
        int[] res = new int[N];
        for (int i = 1; i <= N; i++) {
            traverseGraph(graph, i, N, res);
        }
        return res;
    }

    public void traverseGraph(List<Integer>[] graph, int node, int N, int[] res) {

        if (graph[node].size() == 0 || res[node - 1] != 0) {
            return;
        }
        List<Integer> childs = graph[node];
        res[node - 1] = getValidColor(res, childs);
        for (Integer child : childs) {
            if (res[child - 1] == 0) {
                traverseGraph(graph, child, N, res);
            }
        }

    }

    public Integer getValidColor(int[] res, List<Integer> childs) {
        boolean[] colors = new boolean[5];

        for (Integer child : childs) {

            colors[res[child - 1]] = true;

        }
        for (int color = 1; color < 5; color++) {
            if (!colors[color]) {
                return color;
            }
        }
        return 1;
    }

    public int[][] kClosest(int[][] points, int K) {
        Queue<Point> pQueue = new PriorityQueue<>();
        int[][] res = new int[K][2];
        int n = points.length;

        for (int i = 0; i < n; i++) {
            int x = points[i][0];
            int y = points[i][1];
            pQueue.add(new Point(x, y, x * x + y * y));
        }

        while (K-- > 0) {
            Point p = pQueue.poll();
            res[K][0] = p.x;
            res[K][1] = p.y;
        }
        return res;
    }

//    int calculateDistance(int x, int y) {
//        return x * x + y + y;
//    }
}
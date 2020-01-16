package leetcode.contests.ContestAA;

import org.junit.Test;

import java.util.*;

public class Contests10 {
    int gv = 0;

    class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void increaseYByY(int y) {
            this.y += y;
        }

        public void increaseX() {
            this.x++;
        }
    }

    @Test
    public void ContestsSolution() {
        int mat[][] = {
            {0, 1, 0, 0, 0},
            {1, 1, 1, 1, 1},
            {0, 1, 1, 0, 1},
            {1, 0, 0, 1, 1},
            {0, 1, 0, 0, 0},
            {0, 1, 0, 1, 1},
            {0, 0, 0, 1, 0}
        };
        System.out.println(minTotalDistance(mat));
        System.out.println(minTotalDistanceA(mat));
//        System.out.println(gv);

    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 1) return 0;
        int buy[] = new int[n];
        int sell[] = new int[n];

        buy[0] = -prices[0];
        buy[1] = Math.max(-prices[0], -prices[1]);
        sell[0] = 0;
        sell[1] = Math.max(0, prices[1] - prices[0]);

        for (int i = 2; i < n; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }
        return sell[n - 1];
    }

    public int findCircleNum(int[][] M) {

        int n = M.length;
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (M[i][j] == 1) {
                    graph[i].add(j);
                }
            }
        }
        boolean v[] = new boolean[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (!v[i]) {
                ans++;
                traverseGrapgDFS(i, v, graph);
            }
        }
        return ans;

    }

    public void traverseGrapgDFS(int node, boolean v[], List<Integer>[] graph) {
        if (v[node]) return;
        v[node] = true;
        List<Integer> childs = graph[node];
        for (Integer a : childs) {
            traverseGrapgDFS(a, v, graph);
        }
    }

    public int minTotalDistanceA(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        ArrayList<Integer> cols = new ArrayList<Integer>();
        ArrayList<Integer> rows = new ArrayList<Integer>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    cols.add(j);
                    rows.add(i);
                }
            }
        }

        int sum = 0;

        for (Integer i : rows) {
            sum += Math.abs(i - rows.get(rows.size() / 2));
        }

        Collections.sort(cols);

        for (Integer i : cols) {
            sum += Math.abs(i - cols.get(cols.size() / 2));
        }

        return sum;
    }

    public int minTotalDistance(int[][] grid) {
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
        HashMap<Integer, Set<Integer>> mset = new HashMap<>();

        int n = grid.length;
        int m = grid[0].length;
        int a = Math.max(n, m);

        Queue<Node> queue = new LinkedList<>();
        int count = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    int key = i * a + j;
                    Set<Integer> set = mset.getOrDefault(key, new LinkedHashSet<>());
                    set.add(count);
                    mset.put(key, set);
                    // 1. count 2. dis
                    HashMap<Integer, Integer> mp = new HashMap<>();
                    mp.put(count, 0);
                    map.put(key, mp);
                    queue.offer(new Node(key, count++));
                    gv++;
                }
            }
        }
        while (!queue.isEmpty()) {
            Node newNode = queue.poll();
            addAllNiebhor(newNode, n, m, map, mset, queue);
        }
        int ans = Integer.MAX_VALUE;
        for (Integer key : map.keySet()) {
            HashMap<Integer, Integer> mp = map.get(key);
            if (mp.size() == count - 1) {
                int sum = 0;
                for (Integer k : mp.keySet()) {
                    sum += mp.get(k);
                }
                ans = Math.min(sum, ans);
            }
        }
        return ans;
    }

    public void addAllNiebhor(Node newNode, int n, int m, HashMap<Integer, HashMap<Integer, Integer>> map, HashMap<Integer, Set<Integer>> mset, Queue<Node> queue) {
        int b[] = {0, 1, 0, -1, 0};
        int a = Math.max(n, m);
        int i = newNode.x / a;
        int j = newNode.x % a;
        Integer qNumber = newNode.y;
        for (int k = 0; k < 4; k++) {
            if (isValidPath(i + b[k], j + b[k + 1], qNumber, n, m, mset)) {
                int key = (i + b[k]) * a + j + b[k + 1];
                Node node = new Node(key, qNumber);

                int disToAdd = getDistance(i, j, i + b[k], j + b[k + 1]);
                queue.offer(node);
                gv++;
                Set<Integer> set = mset.getOrDefault(node.x, new LinkedHashSet<>());
                set.add(qNumber);
                mset.put(node.x, set);
                HashMap<Integer, Integer> mp = new HashMap<>();
                HashMap<Integer, Integer> map1 = map.getOrDefault(node.x, mp);
                int dis = map1.getOrDefault(qNumber, map.get(newNode.x).get(qNumber));
                map1.put(qNumber, dis + disToAdd);
                map.put(key, map1);
            }
        }

    }

    public int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public boolean isValidPath(int i, int j, int qNumber, int n, int m, HashMap<Integer, Set<Integer>> mset) {
        int a = Math.max(n, m);
        if (i < 0 || i >= n || j < 0 || j >= m) {
            return false;
        }
        Set<Integer> node = mset.get(i * a + j);
        if (node != null && node.contains(qNumber)) {
            return false;
        }
        return true;
    }

    public int minTotalDistance2Pointer(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        List<Integer> rows = collectRow(grid);
        List<Integer> cols = collectCol(grid);
        return calDist(rows) + calDist(cols);
    }

    private List<Integer> collectRow(int[][] grid) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    res.add(i);
                }
            }
        }
        return res;
    }

    private List<Integer> collectCol(int[][] grid) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[j][i] == 1) {
                    res.add(i);
                }
            }
        }
        return res;
    }

    private int calDist(List<Integer> list) {
        int i = 0, j = list.size() - 1;
        int dist = 0;
        while (i < j) {
            dist += list.get(j) - list.get(i);
            i++;
            j--;
        }
        return dist;
    }

    public List<String> fizzBuzz(int n) {

        List<String> result = new LinkedList<>();

        for (int i = 1; i < n; i++) {
            if (i % 15 == 0) {
                result.add("FizzBuzz");
            }
            if (i % 5 == 0) {
                result.add("Buzz");
            }
            if (i % 3 == 0) {
                result.add("Fizz");
            }
            if (i % 3 != 0 && i % 5 != 0) {
                result.add(String.valueOf(i));
            }
        }
        return result;
    }
}

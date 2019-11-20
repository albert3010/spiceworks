package leetcode.problems;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;

public class Problems5 {

    @Test
    public void dietPlanPerformanceTest() {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            sc.nextInt();
            sc.nextInt();
            String s1 = sc.next();
            String s2 = sc.next();
            for (int j = 0; j < n; j++) {
                arr[j] = sc.nextInt();
            }
            System.out.println(trappingRainWater(n, arr));
        }

        List<Integer> pairs = Lists.newArrayList();

        String words[] = {"cat", "bt", "hat", "tree"};
        int a[] = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        String s = "aa";
//        System.out.println(trappingRainWater(12, a));
        System.out.println(longestPalindrome(s));

    }

    String longestPalindrome(String s) {

        int n = s.length();
        String result = s.substring(0, 1);
        int Max = 1;

        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            if (i != n - 1 && s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                result = s.substring(i, i+2);
            }
        }

        for (int gap = 3; gap <= n; gap++) {
            for (int start = 0; start <= n - gap; start++) {
                int end = start + gap - 1;

                if (s.charAt(start) == s.charAt(end) && dp[start + 1][end - 1]) {
                    dp[start][end] = true;
                    if (Max < end - start + 1) {
                        Max = end - start + 1;
                        result = s.substring(start, end + 1);
                    }
                }
            }
        }


        return result;
    }

    int trappingRainWater(int n, int[] a) {

        int[] rMax = new int[n];
        rMax[n - 1] = a[n - 1];

        for (int i = n - 2; i > 0; i--) {
            rMax[i] = Math.max(rMax[i + 1], a[i]);
        }
        int result = 0;
        int lMax = a[0];

        for (int i = 1; i < n - 1; i++) {
            lMax = Math.max(lMax, a[i]);
            result += Math.min(lMax, rMax[i]) - a[i];
        }
        return result;

    }


    // We record the timestamp that we visit each node. For each node, we check every neighbor except its parent and
    // return a smallest timestamp in all its neighbors.
    // If this timestamp is strictly less than the node's timestamp,
    // we know that this node is somehow in a cycle. Otherwise, this edge from the parent to this node is a critical connection
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        //
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (List<Integer> connection : connections) {
            graph[connection.get(0)].add(connection.get(1));
            graph[connection.get(1)].add(connection.get(0));
        }

        int timer = 0;
        List<List<Integer>> results = new ArrayList<>();
        boolean[] visited = new boolean[n];
        int[] timeStampAtThatNode = new int[n];
        criticalConnectionsUtil(graph, -1, 0, timer, visited, results, timeStampAtThatNode);
        return results;
    }

    public void criticalConnectionsUtil(List<Integer>[] graph, int parent, int node, int timer, boolean[] visited, List<List<Integer>> results, int[] timeStampAtThatNode) {
        visited[node] = true;
        timeStampAtThatNode[node] = timer++;
        int currentTimeStamp = timeStampAtThatNode[node];
        for (int oneNeighbour : graph[node]) {

            if (parent == oneNeighbour) continue;

            if (!visited[oneNeighbour]) {
                criticalConnectionsUtil(graph, node, oneNeighbour, timer, visited, results, timeStampAtThatNode);
            }
            timeStampAtThatNode[node] = Math.min(timeStampAtThatNode[oneNeighbour], timeStampAtThatNode[node]);

            if (currentTimeStamp < timeStampAtThatNode[oneNeighbour]) {
                results.add(Arrays.asList(node, oneNeighbour));
            }


        }


    }

}
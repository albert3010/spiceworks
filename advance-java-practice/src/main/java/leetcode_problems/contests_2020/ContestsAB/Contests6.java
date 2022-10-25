package leetcode_problems.contests_2020.ContestsAB;

import org.junit.Test;


import java.util.*;
import java.util.stream.Collectors;

public class Contests6 {
    class Node implements Comparable<Node> {
        int id;
        int r;

        Node(int id, int r) {
            this.id = id;
            this.r = r;
        }

        @Override
        public int compareTo(Node o) {
            if (o.r == this.r)
                return o.id - this.id;
            return o.r - this.r;
        }
    }

    @Test
    public void ContestsSolution() {
        int a[] = {40, 10, 20, 30};
//        System.out.println(arrayRankTransform(a));
//        System.out.println(breakPalindrome("abba"));
        int rest[][] = {{1, 4, 1, 40, 10}, {2, 8, 0, 50, 5}, {3, 8, 1, 30, 4}, {4, 10, 0, 10, 3}, {5, 1, 1, 15, 1}};

//        System.out.println(filterRestaurants(rest, 0, 50, 10));
        int ede[][] = {{0,1,2},{0,4,8},{1,2,3},{1,4,2},{2,3,1},{3,4,1}};
        System.out.println(findTheCity(5, ede, 2));
    }

    public int findTheCity1(int n, int[][] edges, int distanceThreshold) {
        List<Node>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        int m = edges.length;
        for (int i = 0; i < m; i++) {
            graph[edges[i][0]].add(new Node(edges[i][1], edges[i][2]));
            graph[edges[i][1]].add(new Node(edges[i][0], edges[i][2]));
        }
        boolean v[] = new boolean[n];
        int ans = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < n; i++) {
            Set<Integer> set = new LinkedHashSet<>();
            traverseGraph(i, i, 0, distanceThreshold, graph, v, set);
            v[i] = false;
            if (set.size() <= ans) {
                ans = set.size();
                res = i;
                set.clear();
            }
        }
        return res;
    }

    public void traverseGraph(int node, int p, int cd, int distanceThreshold, List<Node>[] graph, boolean v[], Set<Integer> set) {

        if (v[node]) return;
        v[node] = true;
        List<Node> adjNodes = graph[node];
        for (Node nd : adjNodes) {
            if (cd + nd.r <= distanceThreshold && nd.id != p) {
                set.add(nd.id);
                traverseGraph(nd.id, p, cd + nd.r, distanceThreshold, graph, v, set);
            }
        }
        if (node == p) return;
        v[node] = false;
    }

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        long[][] d = new long[n][n];
        for (long[] row : d)  Arrays.fill(row, (long)Integer.MAX_VALUE);
        for (int[] edge : edges) {
            int i = edge[0], j = edge[1], dis = edge[2];
            d[i][j] = dis;
            d[j][i] = dis;
        }
        floyd(d, n);
        int city = -1, count = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (d[i][j] <= distanceThreshold) {
                    sum++;
                }
            }
            if (sum <= count) {
                city = i;
                count = sum;
            }
        }
        return city;
    }
    public void floyd(long[][] d, int n)
    {
        for(int k = 0;k < n;k++)
            for(int i = 0;i < n;i++)
                for(int j = 0;j < n;j++)
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
    }
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        int n = restaurants.length;
        List<Node> rest = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (toBeFiltered(restaurants[i], veganFriendly, maxPrice, maxDistance)) {
                rest.add(new Node(restaurants[i][0], restaurants[i][1]));
            }
        }
        Collections.sort(rest);
        return rest.stream().map(node -> node.id).collect(Collectors.toList());
    }

    public boolean toBeFiltered(int r[], int veganFriendly, int maxPrice, int maxDistance) {
        if (veganFriendly == 1 && r[2] != veganFriendly) return false;
        if (r[3] > maxPrice) return false;
        if (r[4] > maxDistance) return false;
        return true;
    }

    public int maxValueAfterReverse(int[] nums) {
        int n = nums.length;
        int arr[] = new int[n + 2];
        for (int i = 0; i < n; i++) {

        }
        return 0;

    }

    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int minIndex = Math.min(i, j);
                int rank = (i - minIndex) * n + (j - minIndex);
                List arr = map.getOrDefault(rank, new ArrayList());
                arr.add(mat[i][j]);
                map.put(rank, arr);
            }
        }
        for (Integer key : map.keySet()) {
            Collections.sort(map.get(key));
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int minIndex = Math.min(i, j);
                int rank = (i - minIndex) * n + (j - minIndex);
                List<Integer> arr = map.get(rank);
                mat[i][j] = arr.get(minIndex);
            }
        }
        return mat;
    }

    public String breakPalindrome(String palindrome) {
        int n = palindrome.length();
        if (n == 1) return "";
        int mid = (n - 1) / 2;
        int indexToBeChanged = -1;

        for (int i = 0; i <= mid; i++) {
            if (palindrome.charAt(i) != 'a') {
                indexToBeChanged = i;
                break;
            }
        }
        StringBuilder s = new StringBuilder(palindrome);
        if (indexToBeChanged == -1) {
            s.setCharAt(n - 1, 'b');
        } else {
            s.setCharAt(indexToBeChanged, 'a');
        }
        return s.toString();
    }

    public int[] arrayRankTransform(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = arr.length;
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = arr[i];
        }
        Arrays.sort(a);
        int rank[] = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                rank[i] = 1;
            } else {
                if (a[i - 1] == a[i]) {
                    rank[i] = rank[i - 1];
                } else {
                    rank[i] = rank[i - 1] + 1;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            map.put(a[i], rank[i]);
        }
        for (int i = 0; i < n; i++) {
            arr[i] = map.get(arr[i]);
        }
        return arr;
    }
}

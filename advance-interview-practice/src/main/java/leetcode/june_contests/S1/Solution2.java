package leetcode.june_contests.S1;

import org.junit.Test;

import java.util.*;

public class Solution2 {

    @Test
    public void function() {
        int[][] A = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] B = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};
//        System.out.println(intervalIntersection(A, B));
//        3
        int[][] AA = {{1, 2}, {1, 3}, {2, 4}};
//        4
//                [[1,2],[1,3],[2,4]]
//        System.out.println(possibleBipartition(4, AA));
        int[][] prerequisites = {{1, 5}, {0,1}, {0,2}, {2,5}, {3,4}, {4,5}, {5,3}};
        System.out.println(canFinish(6, prerequisites));
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int n = prerequisites.length;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inCount = new HashMap<>();
        Set<Integer> set = new LinkedHashSet<>();
        Set<Integer> total = new LinkedHashSet<>();

        for (int[] prerequisite : prerequisites) {
            int a = prerequisite[0];
            int b = prerequisite[1];
            total.add(a);
            total.add(b);
            List<Integer> ls = graph.getOrDefault(a, new ArrayList<>());
            ls.add(b);
            graph.put(a, ls);
            int t = inCount.getOrDefault(b, 0);
            inCount.put(b, t + 1);
            if (t == 0) {
                set.remove(b);
            }
            if (inCount.get(a) == null) {
                set.add(a);
            }
        }
        int tt = total.size();
        int tx = 0;
        if (set.size() == 0 && tt > 0) return false;
        while (!set.isEmpty()) {
            Set<Integer> set1 = new LinkedHashSet<>();
            for (Integer v : set) {
                tx++;
                List<Integer> gr = graph.getOrDefault(v, new ArrayList<>());
                for (Integer i : gr) {
                    int inc = inCount.get(i);
                    inCount.put(i, inc - 1);
                    if (inc - 1 == 0) {
                        set1.add(i);
                    }
                }
            }
            set = set1;
        }
        return tx == tt;
    }

    public boolean possibleBipartition(int N, int[][] dislikes) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int n = dislikes.length;

        for (int i = 0; i < n; i++) {
            int a = dislikes[i][0];
            int b = dislikes[i][1];
            List<Integer> ls = map.getOrDefault(a, new ArrayList<>());
            ls.add(b);
            map.put(a, ls);
            ls = map.getOrDefault(b, new ArrayList<>());
            ls.add(a);
            map.put(b, ls);
        }
        int group = 1;
        int v[] = new int[N + 1];
        boolean flag = true;
        for (int i = 1; i <= N; i++) {
            if (v[i] == 0 && flag)
                flag = dfs(map, group, i, v);
        }
        return flag;
    }

    public boolean dfs(Map<Integer, List<Integer>> graph, int group, int node, int v[]) {
        if (graph.get(node) == null || v[node] == group) {
            return true;
        }
        if (v[node] != 0 && v[node] != group) return false;

        v[node] = group;
        List<Integer> ls = graph.get(node);
        group = group == 1 ? 2 : 1;
        boolean flag = true;
        for (Integer nd : ls) {
            if (flag)
                flag = dfs(graph, group, nd, v);
        }
        return flag;
    }

    public int[][] intervalIntersection(int[][] A, int[][] B) {

        int al = A.length;
        int bl = B.length;
        if (al == 0 || bl == 0) {
            int[][] t = new int[0][0];
            return t;
        }
        List<List<Integer>> ans = new ArrayList<>();
        int as = 0;
        int bs = 0;
        while (as < al && bs < bl) {
            if (A[as][1] < B[bs][1]) {
                if (A[as][1] >= B[bs][0]) {
                    ans.add(Arrays.asList(Math.max(B[bs][0], A[as][0]), A[as][1]));
                }
                as++;
            } else {
                if (B[bs][1] >= A[as][0]) {
                    ans.add(Arrays.asList(Math.max(B[bs][0], A[as][0]), B[bs][1]));
                }
                bs++;
            }
        }
        int[][] array = new int[ans.size()][2];

        int i = 0;
        for (List<Integer> nestedList : ans) {
            int kk = 0;
            array[i][kk] = nestedList.get(kk++);
            array[i++][kk] = nestedList.get(kk);
        }
        return array;
    }
}
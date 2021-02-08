package leetcode_2020.june_contests.S1;

import org.junit.Test;

import java.util.*;

public class Solution2 {

    @Test
    public void function() {
        int[][] A = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] B = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};
//        System.out.println(intervalIntersection(A, B));
//        3
//        int[][] AA = {{1, 2}, {1, 3}, {2, 4}};
        int[][] AA = {{0, 1}, {2, 1}, {3, 2}, {0, 4}, {5, 1}, {2, 6}, {5, 7}, {3, 8}, {8, 9}};
//        4
//                [[1,2],[1,3],[2,4]]
//        System.out.println(possibleBipartition(4, AA));
        int[][] prerequisites = {{1, 5}, {0, 1}, {0, 2}, {2, 5}, {3, 4}, {4, 5}, {5, 3}};
//        System.out.println(canFinish(6, prerequisites));
//        System.out.println(minReorder(10, AA));
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.left.left = new TreeNode(5);
        root.right.right = new TreeNode(9);

        widthOfBinaryTree(root);

    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        System.out.println();
        Deque<TreeNode> deque = new LinkedList<>();
        if (root == null) return 0;
        int ans = 0;
        deque.add(root);

        while (!deque.isEmpty()) {
            int l = deque.size();
            while (l > 0) {
                TreeNode nd = deque.poll();
                if (nd == null) {
                    deque.add(null);
                    deque.add(null);
                } else {
                    deque.add(nd.left);
                    deque.add(nd.right);
                }
                l--;
            }
            while (!deque.isEmpty() && (deque.getFirst()==null || deque.getLast()==null)) {
                if (deque.getFirst() == null) {
                    deque.pollFirst();
                }
                if (!deque.isEmpty() && deque.getLast() == null) {
                    deque.pollLast();
                }
            }
            ans = Math.max(ans, deque.size());
        }
        return ans;
    }

    public int minReorder(int n, int[][] connections) {
        int m = connections.length;
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, List<Integer>> mapx = new HashMap<>();
        boolean[][] aa = new boolean[n][n];
        for (int i = 0; i < m; i++) {
            int a = connections[i][0];
            int b = connections[i][1];
            aa[a][b] = true;

            List<Integer> la = mapx.getOrDefault(a, new ArrayList<>());
            la.add(b);
            mapx.put(a, la);
            List<Integer> lb = mapx.getOrDefault(b, new ArrayList<>());
            lb.add(a);
            mapx.put(b, lb);
        }
        boolean[] v = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> ll = mapx.get(0);
        int ans = 0;
        v[0] = true;
        for (Integer i : ll) {
            queue.add(i);
            v[i] = true;
            if (aa[0][i]) {
                ans++;
            }
        }

        while (!queue.isEmpty()) {
            Queue<Integer> queueT = new LinkedList<>();
            int l = queue.size();
            for (int i = 0; i < l; i++) {
                int val = queue.poll();
                List<Integer> ls = mapx.get(val);
                for (Integer j : ls) {
                    if (!v[j]) {
                        queueT.add(j);
                        v[j] = true;
                        if (aa[val][j]) {
                            ans++;
                        }
                    }
                }

            }
            queue = queueT;
        }
        return ans;
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

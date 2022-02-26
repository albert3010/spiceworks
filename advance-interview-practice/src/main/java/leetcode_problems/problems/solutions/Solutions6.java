package leetcode_problems.problems.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Solutions6 {
    @Test
    public void Tests() {
        String[] equations = new String[3];
        equations[0] = "a==b";
        equations[1] = "b!=c";
        equations[2] = "c==a";
//        System.out.println(equationsPossible(equations));
        int prerequisites[][] = {{1, 0}, {0, 1}};
//        System.out.println(findOrder(4, prerequisites));
        int edges[][] = {{0, 1}, {0, 2}, {1, 3}, {0, 4}, {1, 5}, {2, 6}, {1, 7}};
//        System.out.println(treeDiameter( edges));
        System.out.println(countPrimeSetBits(10, 15));

    }
//[[0,1],[0,2],[1,3],[0,4],[1,5],[2,6],[1,7]]

    public int countPrimeSetBits(int L, int R) {

        int k;
        int ans = 0;

        while (L <= R) {

//            int setbit = 0;
//            k = L;
//            while (k > 0) {
//                if (k % 2 == 1) {
//                    setbit++;
//                }
//                k = k >> 1;
//            }
            if (checkPrime(Integer.bitCount(L))) {
                ans++;
            }
            L++;
        }

        return ans;
    }

    boolean checkPrime(int n) {
        return n==2 || n==3 || n==5
            || n==7 || n==11 || n==13
            || n==17 || n==19;
    }

    public int treeDiameter(int[][] edges) {
        List<Integer>[] graph = new List[10000];
        for (int i = 0; i < 10000; i++) {
            graph[i] = new ArrayList<>();
        }
        int row = edges.length;

        for (int i = 0; i < row; i++) {
            graph[edges[i][0]].add(edges[i][1]);
        }
        int[] maxD = new int[1];

        int diameter = traveseGraphDiameter(graph, 0, maxD);
        return maxD[0] - 1;
    }

    int traveseGraphDiameter(List<Integer>[] graph, int node, int[] maxD) {

        List<Integer> childs = graph[node];

        if (childs.size() == 0) return 1;
        int right = 0;
        int left = 0;
        int max = 0;
        int z = childs.size();
        int[] a = new int[z];
        int i = 0;
        for (Integer child : childs) {
            int val = traveseGraphDiameter(graph, child, maxD);
            max = Math.max(val, max);
            a[i++] = val;
        }
        Arrays.sort(a);
        right = max;
        if (a.length >= 2) {
            left = a[z - 2];
        }

        int val = left + right + 1;
        if (maxD[0] < val) {
            maxD[0] = val;
        }
        return max + 1;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        List<Integer>[] graph = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        int row = prerequisites.length;

        for (int i = 0; i < row; i++) {
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        Stack<Integer> stack = new Stack<>();

        boolean[] recStack = new boolean[numCourses];
        boolean[] visited = new boolean[numCourses];
        boolean[] flag = new boolean[1];
        for (int node = 0; node < numCourses; node++) {
            if (!visited[node]) {
                traverseGraphTopological(graph, node, visited, stack, recStack, flag);
            }
        }
        int l = stack.size();
        int i = 0;
        int a[] = new int[l];
        if (flag[0]) {
            return new int[0];
        }
        while (l-- > 0) {
            a[i++] = stack.pop();
        }
        return a;
    }

    void traverseGraphTopological(List<Integer>[] graph, int node, boolean[] visited, Stack<Integer> stack, boolean[] recStack, boolean[] flag) {
        if (recStack[node]) {
            flag[0] = true;
        }
        if (visited[node] || flag[0]) return;
        List<Integer> childs = graph[node];
        visited[node] = true;
        recStack[node] = true;
        for (Integer child : childs) {
            traverseGraphTopological(graph, child, visited, stack, recStack, flag);
        }
        recStack[node] = false;
        stack.push(node);
    }

    public boolean equationsPossible(String[] equations) {

        List<Integer>[] graph = new List[26];
        for (int i = 0; i < 26; i++) {
            graph[i] = new ArrayList<>();
        }

        int l = equations.length;

        for (int i = 0; i < l; i++) {
            // make graph
            String s = equations[i];

            char var1 = s.charAt(0);
            char var2 = s.charAt(3);
            char eq = s.charAt(1);
            if (eq == '=') {
                graph[var1 - 'a'].add(var2 - 'a');
                graph[var2 - 'a'].add(var1 - 'a');
            }
        }
        boolean flag = true;

        for (int i = 0; i < l; i++) {
            // traverse graph
            String s = equations[i];

            char var1 = s.charAt(0);
            char var2 = s.charAt(3);
            char eq = s.charAt(1);
            boolean[] visited = new boolean[26];
            if (eq == '!') {
                flag &= traverseGraph(graph, var1 - 'a', var2 - 'a', visited);
            }
        }
        return flag;

    }

    boolean traverseGraph(List<Integer>[] graph, int var1, int var2, boolean[] visited) {

        if (var1 == var2) return false;
        if (visited[var1]) return true;

        List<Integer> childs = graph[var1];
        visited[var1] = true;
        boolean flag = true;
        for (Integer child : childs) {

            flag &= traverseGraph(graph, child, var2, visited);
        }
        return flag;

    }
}
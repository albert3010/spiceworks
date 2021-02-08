package leetcode_2020.june_contests.S1;

import org.junit.Test;

import java.util.*;

public class Solution3 {
    @Test
    public void function() {

    }

    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        int l = prerequisites.length;
        for (int i = 0; i < l; i++) {
            int a = prerequisites[i][0];
            int b = prerequisites[i][1];
            List<Integer> ls = graph.getOrDefault(a, new ArrayList<>());
            ls.add(b);
            graph.put(a, ls);
            set.add(a);
            set.add(b);
        }

        HashMap<Integer, Set<Integer>> mapSet = new HashMap<>();

        for (int i = 0; i < n; i++) {
            if (mapSet.get(i) == null && set.contains(i)) {
                travelGraph(i, mapSet, graph, set);
            }
        }
        List<Boolean> ans = new ArrayList<>();
        int m = queries.length;
        for (int i = 0; i < m; i++) {
            int a = queries[i][0];
            int b = queries[i][1];
            Set<Integer> seta = mapSet.get(a);
            if (seta.contains(b)) ans.add(true);
            else ans.add(false);
        }
        return ans;
    }

    Set<Integer> travelGraph(int node, HashMap<Integer, Set<Integer>> mapSet, HashMap<Integer,
            List<Integer>> graph, Set<Integer> set) {
        Set<Integer> setX = new HashSet<>();
        if (mapSet.get(node) != null) return mapSet.get(node);
        if (graph.get(node) == null) {
            if (set.contains(node)) {
                setX.add(node);
                mapSet.put(node, setX);
                return setX;
            } else {
                mapSet.put(node, setX);
                return setX;
            }
        }

        List<Integer> ls = graph.get(node);
        Set<Integer> ans = new HashSet<>();
        for (Integer nd : ls) {
            Set<Integer> set1 = travelGraph(nd, mapSet, graph, set);
            ans.addAll(set1);
        }
        ans.add(node);
        mapSet.put(node, ans);
        return ans;
    }
}



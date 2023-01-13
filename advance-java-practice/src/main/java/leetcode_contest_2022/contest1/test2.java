package leetcode_contest_2022.contest1;

import org.junit.Test;

import java.util.*;

public class test2 {


    @Test
    public void test() {
        int[][] a = {
                {4, 5, 8, 9, 4, 2, 4, 7, 2, 4},
                {7, 1, 9, 6, 6, 1, 4, 5, 7, 7},
                {7, 1, 5, 1, 1, 7, 10, 1, 3, 1},
                {7, 2, 2, 5, 2, 6, 6, 4, 7, 7},
                {1, 2, 3, 8, 4, 7, 6, 9, 6, 2},
                {5, 10, 3, 4, 7, 2, 7, 5, 3, 10}
        };


//        System.out.println(addRungs(arr, 2));
//        System.out.println(canBeTypedWords("hello world", "ad"));

//        System.out.println(droppedRequests(Arrays.asList(1,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,7,11,11,11,11)));
//        System.out.println(droppedRequests(Arrays.asList(7,7,7,7,11,11,11,11,20,20,21,25,60)));
//        System.out.println(getLucky("ab", 1));
//        int[][] edges = {{0, 1}, {1, 2}, {1, 3}, {3, 4}};
        int[][] edges = {{0, 1}};
//        int[] arr = {-2, 4, 2, -4, 6};
        int[] arr = {-7280,2350};
        System.out.println(mostProfitablePath(edges, 1, arr));
    }

    class Node {
        int nd;
        int amount;

        public Node(int nd, int amount) {
            this.amount = amount;
            this.nd = nd;
        }
    }

    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        int m = edges.length;

        Map<Integer, List<Integer>> graph = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        Set<Integer> opened = new HashSet<>();
        Map<Integer, Integer> parent = new HashMap<>();
        parent.put(0, -1);
        for (int i = 0; i < m; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            graph.putIfAbsent(a, new ArrayList<>());
            graph.putIfAbsent(b, new ArrayList<>());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        traverse2(graph, visited, 0, parent);
        visited.clear();
        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(0, amount[0]));
        int max = Integer.MIN_VALUE;
        TreeSet<Integer> set = new TreeSet<>();

        while (!queue.isEmpty()) {
            if (bob != -1) {
                opened.add(bob);
                bob = parent.get(bob);
            }
            int l = queue.size();
            while (l > 0) {
                Node node = queue.poll();
                visited.add(node.nd);
                int val1 = node.amount;

                boolean isLeaf = true;
                for (Integer nd : graph.getOrDefault(node.nd,new ArrayList<>())) {
                    if (!visited.contains(nd)) {
                        isLeaf = false;
                        int val = val1;
                        if (!opened.contains(nd)) {
                            if (bob == nd) {
                                val += amount[nd] / 2;
                            } else {
                                val += amount[nd];
                            }
                        }
                        queue.add(new Node(nd, val));
                    }
                }
                if (isLeaf) {
                    max = Math.max(max, node.amount);
                }
                l--;
            }

        }
        return max;
    }

    private void traverse2(Map<Integer, List<Integer>> graph, Set<Integer> visited,
                           int node, Map<Integer, Integer> parent) {

        if (visited.contains(node)) return;
        visited.add(node);
        for (Integer nd : graph.get(node)) {

            if(!visited.contains(nd)){
                parent.put(nd, node);
                traverse2(graph, visited, nd, parent);
            }

        }
    }

    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        int m = edges.length;

        Map<Integer, List<Integer>> graph = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int v : restricted) {
            set.add(v);
        }
        for (int i = 0; i < m; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            graph.putIfAbsent(a, new ArrayList<>());
            graph.putIfAbsent(b, new ArrayList<>());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        return traverse(graph, 0, set);
    }

    private int traverse(Map<Integer, List<Integer>> graph, int node, Set<Integer> set) {
        if (set.contains(node)) return 0;
        int count = 0;
        for (Integer nd : graph.get(node)) {
            set.add(nd);
            count += traverse(graph, nd, set);
        }
        return count + 1;

    }

    public int longestIdealString(String s, int k) {
        int n = s.length();
        int st = 0;
        TreeSet<Character> set = new TreeSet<>((a, b) -> {
            int l = a - 'a';
            int h = b - 'a';
            int abs = Math.abs(l - h);
            return k - abs;
        });
        int max = 1;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            Character ceil = set.ceiling(c);
            if (ceil != null) {
                set.remove(c);
            }
            set.add(c);
            max = Math.max(max, set.size());

        }
        return max;
    }

    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        int m = students.length;
        int n = students[0].length;
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            list.add(new ArrayList<>());
            for (int j = 0; j < m; j++) {
                int score = 0;
                for (int k = 0; k < n; k++) {
                    if (students[i][k] == mentors[j][k]) {
                        score++;
                    }
                }
                list.get(0).add(score);
            }
        }

        return 1;
    }

    public String maximumNumber(String num, int[] change) {
        int l = num.length();
        int start = -1;
        for (int i = 0; i < l; i++) {
            int t = num.charAt(i) - 48;
            int t1 = change[t];
            if (t1 > t) {
                start = i;
                break;
            }
        }
        if (start == -1) return num;
        StringBuilder ss = new StringBuilder();
        ss.append(num, 0, start);

        for (int i = start; i < l; i++) {
            int t = num.charAt(i) - 48;
            int t1 = change[t];
            if (t1 >= t) {
                ss.append(t1);
            } else {
                ss.append(num, i, num.length());
                break;
            }
        }
        return ss.toString();
    }

    public int getLucky(String s, int k) {
        int l = s.length();
        StringBuilder tmp = new StringBuilder();

        for (int i = 0; i < l; i++) {
            tmp.append(s.charAt(i) - 'a' + 1);
        }
        int ans = 0;
        while (k > 0) {
            int sum = 0;
            int x = tmp.length();
            for (int i = 0; i < x; i++) {
                int val = tmp.charAt(i) - 48;
                sum += val;
            }
            ans = sum;
            tmp = new StringBuilder(String.valueOf(sum));
            k--;
        }
        return ans;
    }

    public static int droppedRequests(List<Integer> requestTime) {
        Deque<Integer> window20 = new ArrayDeque<>();
        Deque<Integer> window60 = new ArrayDeque<>();
        HashMap<Integer, Integer> window1 = new HashMap<>();
        int dropped = 0;

        for (Integer reqTime : requestTime) {
            window1.put(reqTime, window1.getOrDefault(reqTime, 0) + 1);
            window20.addFirst(reqTime);
            window60.addFirst(reqTime);

            while (!window20.isEmpty()) {
                int last = window20.getLast();
                int first = window20.getFirst();
                int diff = first - last + 1;

                if (diff > 20) {
                    window20.pollLast();
                } else {
                    break;
                }
            }
            while (!window60.isEmpty()) {
                int last = window60.getLast();
                int first = window60.getFirst();
                int diff = first - last + 1;

                if (diff > 60) {
                    window60.pollLast();
                } else {
                    break;
                }
            }
            if (window1.get(reqTime) > 3 || window20.size() > 20 || window60.size() > 60) {
                dropped++;
                window20.pollFirst();
                window60.pollFirst();
            }
        }
        return dropped;
    }

    public int addRungs(int[] rungs, int dist) {
        int n = rungs.length;
        int currentPos = 0;
        int minRungs = 0;
        int maxReach = 0;
        for (int i = 0; i < n; ) {
            maxReach = currentPos + dist;

            if (rungs[i] <= maxReach) {
                currentPos = rungs[i++];
            } else {
                int diff = rungs[i] - currentPos;
                minRungs += (diff / dist);
                if (diff % dist == 0) {
                    minRungs--;
                }
                currentPos = rungs[i++];
            }

        }
        return minRungs;
    }

    public int canBeTypedWords(String text, String brokenLetters) {

        int bl = brokenLetters.length();
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < bl; i++) {
            set.add(brokenLetters.charAt(i));
        }
        String[] list = text.split(" ");
        int n = list.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int t = list[i].length();
            boolean f = true;
            for (int j = 0; j < t; j++) {
                if (set.contains(list[i].charAt(j))) {
                    f = false;
                    break;
                }
            }
            if (f) {
                count++;
            }
        }
        return count;
    }
}

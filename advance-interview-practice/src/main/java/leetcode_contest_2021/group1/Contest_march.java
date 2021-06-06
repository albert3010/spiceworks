package leetcode_contest_2021.group1;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Contest_march {

    @Test
    public void ContestsSolution() {
//        [5,6,4,3,1,2]
//[6,3,3,1,4,5,3,4,1,3,4]
        int[] a = {1, 1, 2, 2, 2};
        int[] b = {2, 2, 3, 3, 4};
        int[] c = {1, 2, 1, 3, 3};
//        System.out.println(minOperations(a, b));
//        System.out.println(checkPowersOfThree(12));
//        System.out.println(beautySum("aabcb"));
//        System.out.println(getPowerCouple2021(4, 5, a, b, c));
        int n = 7;
        int[][] edges = {{1, 3, 1}, {4, 1, 2}, {7, 3, 4}, {2, 5, 3}, {5, 6, 1}, {6, 7, 2}, {7, 5, 3}, {2, 6, 4}};
//        countRestrictedPaths(n, edges);
        int[][] classes = {{1, 2}, {3, 5}, {2, 2}};
//        System.out.println(maxAverageRatio(classes, 2));

    }

    public int maxValue(int n, int index, int maxSum) {
        int left = index;
        int right = n - left - 1;
        int ans = 1;
        if (maxSum == n) return ans;
        if (maxSum == 1 + n) return ans + 1;
        ans++;
        int min = Math.min(left, right);
        int max = Math.max(left, right);
        int t = min;
//        min ==0
        int aSum = (t * (2 * 3 + (t - 1) * 2)) / 2;
        int rem = maxSum - n;

        if (aSum <= rem) {
            ans = t;
            rem -= aSum;
//            binary
        } else {
//            binary
            int minl = 1;
            int maxl = t;
            while (minl < maxl) {

                int mid = (minl + maxl) / 2;

            }


        }


        return ans;
    }

    class NodeA {
        int price;
        int total;

        public NodeA(int price, int total) {
            this.price = price;
            this.total = total;
        }
    }

    public int getNumberOfBacklogOrders(int[][] orders) {
        int mod = 1000000000 + 7;
        int n = orders.length;
        PriorityQueue<NodeA> buyQueue = new PriorityQueue<>((a, b) -> b.price - a.price);
        PriorityQueue<NodeA> sellQueue = new PriorityQueue<>((a, b) -> a.price - b.price);
        for (int i = 0; i < n; i++) {
            if (orders[i][2] == 0) {
                buyQueue.add(new NodeA(orders[i][0], orders[i][1]));
            } else {
                sellQueue.add(new NodeA(orders[i][0], orders[i][1]));
            }
            while (!buyQueue.isEmpty() && !sellQueue.isEmpty()) {
                NodeA buy1 = buyQueue.peek();
                NodeA sell1 = sellQueue.peek();
                if (buy1.price >= sell1.price) {
                    NodeA buy = buyQueue.poll();
                    NodeA sell = sellQueue.poll();
                    int minEx = Math.min(buy.total, sell.total);
                    if (buy.total > minEx) {
                        buyQueue.add(new NodeA(buy.price, buy.total - minEx));
                    }
                    if (sell.total > minEx) {
                        buyQueue.add(new NodeA(sell.price, sell.total - minEx));
                    }
                } else {
                    break;
                }
            }
        }

        int ans = 0;
        while (!buyQueue.isEmpty()) {
            NodeA buy = buyQueue.poll();
            ans = (ans + buy.total) % mod;
        }
        while (!sellQueue.isEmpty()) {
            NodeA sell = sellQueue.poll();
            ans = (ans + sell.total) % mod;
        }
        return ans;
    }

    public int maxAscendingSum(int[] nums) {
        int n = nums.length;
        int ans = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                maxSum += nums[i];
            } else {
                maxSum = nums[i];
            }
            ans = Math.max(maxSum, ans);
        }
        return ans;
    }


    class NodeC implements Comparable<NodeC> {
        int pass;
        int total;

        public NodeC(int pass, int total) {
            this.pass = pass;
            this.total = total;
        }

        public int compareTo(NodeC p) {
            double avg1 = (p.pass) / (p.total + 0.0);
            double avg2 = (this.pass) / (this.total + 0.0);

            double avg3 = (p.pass + 1) / (p.total + 1.0);
            double avg4 = (this.pass + 1) / (this.total + 1.0);
            double diff1 = avg3 - avg1;
            double diff2 = avg4 - avg2;
            if (diff1 >= diff2) {
                return 1;
            }
            return -1;
        }
    }

    public double maxAverageRatio(int[][] classes, int extraStudents) {
        int n = classes.length;
        PriorityQueue<NodeC> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            priorityQueue.add(new NodeC(classes[i][0], classes[i][1]));
        }
        for (int i = 1; i <= extraStudents; i++) {
            NodeC node = priorityQueue.poll();
            int pass = node.pass + 1;
            int tot = node.total + 1;
            priorityQueue.add(new NodeC(pass, tot));
        }
        double totalAvg = 0.0;
        while (!priorityQueue.isEmpty()) {
            NodeC node = priorityQueue.poll();
            int pass = node.pass;
            int tot = node.total;
            totalAvg += pass / (tot + 0.0);
        }
        return totalAvg / n;
    }

    public int findCenter(int[][] edges) {
        int n = edges.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(edges[i][0], map.getOrDefault(edges[i][0], 0) + 1);
            map.put(edges[i][1], map.getOrDefault(edges[i][1], 0) + 1);
            if (map.get(edges[i][0]) > 1) {
                return edges[i][0];
            }
            if (map.get(edges[i][1]) > 1) {
                return edges[i][1];
            }
        }
        return 0;
    }

    public boolean areAlmostEqual(String s1, String s2) {
        int n = s1.length();
        StringBuilder ss = new StringBuilder(s1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char ci = ss.charAt(i);
                char cj = ss.charAt(j);
                ss.setCharAt(i, cj);
                ss.setCharAt(j, ci);
                if (ss.toString().equals(s2)) {
                    return true;
                } else {
                    ss.setCharAt(i, ci);
                    ss.setCharAt(j, cj);
                }
            }

        }
        return false;
    }

    class Node {
        int w;
        int node;

        public Node(int node, int w) {
            this.w = w;
            this.node = node;
        }
    }

    public int countRestrictedPaths(int n, int[][] edges) {
        int m = edges.length;
        List<Node>[] graph = new ArrayList[n + 1];
        int[] shortPath = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
            shortPath[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < m; i++) {
            graph[edges[i][0]].add(new Node(edges[i][1], edges[i][2]));
            graph[edges[i][1]].add(new Node(edges[i][0], edges[i][2]));
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        shortPath[n] = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            List<Node> adgNodes = graph[node];
            for (Node nd : adgNodes) {
                if (shortPath[node] + nd.w < shortPath[nd.node]) {
                    shortPath[nd.node] = shortPath[node] + nd.w;
                    queue.add(nd.node);
                }

            }
        }

        return 1;
    }


    public int minElements(int[] nums, int limit, int goal) {
        long sum = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        long req = Math.abs(goal - sum);
        long mod = req % limit;
        long count = req / limit;

        if (mod > 0) count++;
        return (int) count;

    }

    public boolean checkOnesSegment(String s) {
        int n = s.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 && s.charAt(i) == '1') {
                count++;
                continue;
            }
            if (i == 0 && s.charAt(i) == '0') continue;
            if (count > 0 && s.charAt(i) == '1' && s.charAt(i - 1) == '0') {
                return false;
            }
            if (s.charAt(i) == '1') {
                count++;
            }
        }
        return true;

    }

    int getPowerCouple2021(int friends_nodes, int friends_edges, int[] friends_from,
                           int[] friends_to, int[] friends_weight) {

        int n = friends_edges;

        HashMap<Integer, Set<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            Set<Integer> set = map.getOrDefault(friends_weight[i], new HashSet<>());
            set.add(friends_from[i]);
            set.add(friends_to[i]);
            map.put(friends_weight[i], set);
        }
        int maxAns = 0;
        int maxProd = 0;
        for (int i = 1; i <= friends_nodes; i++) {
            for (int j = i + 1; j <= friends_nodes; j++) {
                int count = 0;
                for (Integer key : map.keySet()) {
                    Set<Integer> set = map.get(key);
                    if (set.contains(i) && set.contains(j)) {
                        count++;
                    }
                }
                if (count > maxAns) {
                    maxAns = count;
                    maxProd = i * j;
                } else if (count == maxAns) {
                    if (i * j > maxProd) {
                        maxProd = i * j;
                    }
                }

            }

        }
        return maxProd;
    }

    public int beautySum(String s) {
        int n = s.length();
        int sum = 0;
        int[][] a = new int[n][26];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                a[i][s.charAt(j) - 'a']++;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int[] b = new int[26];
                for (int k = 0; k < 26; k++) {
                    if (i != 0) {
                        b[k] = a[j][k] - a[i - 1][k];
                    } else {
                        b[k] = a[j][k];
                    }
                }
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                for (int k = 0; k < 26; k++) {
                    if (b[k] != 0) {
                        min = Math.min(min, b[k]);
                        max = Math.max(max, b[k]);
                    }
                }
                sum += max - min;
            }

        }
        return sum;

    }

    public boolean checkPowersOfThree(int n) {

        int[] pow3 = new int[16];
        int val = 1;
        pow3[0] = 1;
        for (int i = 1; i <= 15; i++) {
            val = 3 * val;
            pow3[i] = val;
        }
        return possibleWays(pow3, 0, 0, n);
    }

    private boolean possibleWays(int[] pow3, int i, int sum, int target) {
        if (target == sum) {
            return true;
        }
        if (i == pow3.length) return false;
        return possibleWays(pow3, i + 1, sum + pow3[i], target)
                || possibleWays(pow3, i + 1, sum, target);
    }

    public int minOperations(int[] nums1, int[] nums2) {
        int sum1 = Arrays.stream(nums1).sum();
        int sum2 = Arrays.stream(nums2).sum();

        List<Integer> list1 = Arrays.stream(nums1).boxed().collect(Collectors.toList());
        List<Integer> list2 = IntStream.of(nums2).boxed().collect(Collectors.toList());

        int i = 0;
        int j = 0;
        if (sum1 == sum2) return 0;
        int ans = 0;

        if (sum1 > sum2) {
            list1 = Arrays.stream(nums2).boxed().collect(Collectors.toList());
            list2 = IntStream.of(nums1).boxed().collect(Collectors.toList());
        }
        int n = list1.size();
        int m = list2.size();

        if (list1.size() * 6 < list2.size()) return -1;
        int diff = Math.abs(sum2 - sum1);
        Collections.sort(list1);
        list2.sort(Collections.reverseOrder());
        while ((i < n || j < m) && diff > 0) {

            if (i < n && j < m) {
                int a = list1.get(i);
                int b = list2.get(j);
                int d1 = 6 - a;
                int d2 = b - 1;
                if (d1 >= d2) {
                    i++;
                    diff -= d1;
                } else {
                    j++;
                    diff -= d2;
                }
                ans++;
            } else if (i < n) {
                int a = list1.get(i);
                int d1 = 6 - a;
                i++;
                diff -= d1;
                ans++;
            } else {
                int b = list2.get(j);
                int d1 = b - 1;
                j++;
                diff -= d1;
                ans++;
            }
        }
        return ans;
    }

    public int nearestValidPoint(int x, int y, int[][] points) {
        int n = points.length;

        int min = -1;
        int index = -1;
        for (int i = 0; i < n; i++) {
            if (points[i][0] == x || points[i][1] == y) {
                int val = Math.abs(points[i][0] - x) + Math.abs(points[i][1] - y);
                if (index == -1) {
                    index = i;
                    min = val;
                } else if (val < min) {
                    index = i;
                    min = val;
                }
            }
        }
        return index;
    }
}

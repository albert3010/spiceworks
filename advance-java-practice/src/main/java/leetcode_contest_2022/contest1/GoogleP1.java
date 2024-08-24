package leetcode_contest_2022.contest1;

//import com.sun.tools.javac.util.Pair;
import leetcode_contest_2022.TreeNode;
import org.junit.Test;

import java.util.*;

public class GoogleP1 {

    @Test
    public void test() {
        int[] a = {2, 3, 1, 2};

        int[] nums1 = {30, 29, 19, 5};
        int[] nums2 = {25, 25, 25, 25, 25};
        maxDistance(nums1, nums2);
    }

    class Node {
        int x;
        int y;
        int dis;
        String key;

        public Node(int x, int y, int dis, String key) {
            this.x = x;
            this.y = y;
            this.dis = dis;
            this.key = key;
        }
    }

    public int minTotalDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        HashMap<String, Integer>[][] visited = new HashMap[m][n];
        int[][] dist = new int[m][n];
        PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> a.dis - b.dis);
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = new HashMap<>();
                if (grid[i][j] == 1) {
                    queue.add(new Node(i, j, 0, i + "#" + j));
                    visited[i][j].put(i + "#" + j, 0);
                    count++;
                }
            }
        }
        int minDis = 1;
        int[] dir = {1, 0, -1, 0, 1};
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            String key = node.key;

            for (int i = 0; i < 4; i++) {
                int xx = dir[i] + node.x;
                int yy = dir[i + 1] + node.y;

                if (xx >= 0 && xx < m && yy >= 0 && yy < n
                        && !visited[xx][yy].containsKey(key)) {
                    queue.add(new Node(xx, yy, node.dis + 1, key));
                    visited[xx][yy].put(key, node.dis + 1);
                    dist[xx][yy] += node.dis + 1;
                    if (visited[xx][yy].size() == count) {
                        return dist[xx][yy];
                    }
                }
            }
        }
        return minDis;
    }

    public int shortestDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        HashMap<String, Integer>[][] visited = new HashMap[m][n];
        Queue<Node> queue = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = new HashMap<>();
                if (grid[i][j] == 1) {
                    queue.add(new Node(i, j, 0, i + "#" + j));
                    visited[i][j].put(i + "#" + j, 0);
                    count++;
                }
            }
        }

        int minDis = -1;
        int[] dir = {1, 0, -1, 0, 1};
        while (!queue.isEmpty()) {
            int l = queue.size();

            while (l > 0) {
                Node node = queue.poll();
                String key = node.x + "#" + node.y;
                for (int i = 0; i < 4; i++) {
                    int xx = dir[i] + node.x;
                    int yy = dir[i + 1] + node.y;

                    if (xx >= 0 && xx < m && yy >= 0 && yy < n && grid[xx][yy] == 0
                            && !visited[xx][yy].containsKey(key)) {
                        queue.add(new Node(xx, yy, node.dis + 1, key));
                        visited[xx][yy].put(key, node.dis + 1);
                        int tmp = 0;
                        if (visited[xx][yy].size() == count) {
                            for (Integer val : visited[xx][yy].values()) {
                                tmp += val;
                            }
                            if (minDis == -1) {
                                minDis = tmp;
                            } else {
                                minDis = Math.min(minDis, tmp);
                            }
                        }
                    }
                }
                l--;
            }

        }

        return minDis;
    }

    public int[] pivotArray(int[] nums, int pivot) {
        int l = nums.length;
        int ans[] = new int[l];
        List<Integer> small = new ArrayList<>();
        List<Integer> large = new ArrayList<>();
        List<Integer> eq = new ArrayList<>();
        for (int i = 0; i < l; i++) {
            if (nums[i] < pivot) {
                small.add(nums[i]);
            } else if (nums[i] > pivot) {
                large.add(nums[i]);
            } else {
                eq.add(nums[i]);
            }
        }
        small.addAll(eq);
        small.addAll(large);

        return small.stream().mapToInt(i -> i).toArray();

    }

    public int minimumSum(int num) {

        int[] a = new int[4];
        int i = 0;
        while (num > 0) {
            a[i++] = num % 10;
            num /= 10;
        }
        Arrays.sort(a);
        int ans = makeNum(0, 0, a[0]) + makeNum(a[1], a[2], a[3]);

        ans = Math.min(ans, makeNum(0, 0, a[1]) + makeNum(a[0], a[2], a[3]));
        ans = Math.min(ans, makeNum(0, 0, a[2]) + makeNum(a[0], a[1], a[3]));
        ans = Math.min(ans, makeNum(0, 0, a[3]) + makeNum(a[0], a[1], a[2]));

        ans = Math.min(ans, makeNum(0, a[0], a[1]) + makeNum(0, a[2], a[3]));
        ans = Math.min(ans, makeNum(0, a[0], a[2]) + makeNum(0, a[1], a[3]));
        ans = Math.min(ans, makeNum(0, a[0], a[3]) + makeNum(0, a[1], a[2]));
        ans = Math.min(ans, makeNum(0, a[1], a[2]) + makeNum(0, a[0], a[3]));
        ans = Math.min(ans, makeNum(0, a[1], a[3]) + makeNum(0, a[0], a[2]));
        ans = Math.min(ans, makeNum(0, a[2], a[3]) + makeNum(0, a[0], a[1]));
        return ans;
    }

    int makeNum(int a, int b, int c) {
        return a * 100 + b * 10 + c;
    }


    public int maxDistance(int[] nums1, int[] nums2) {

        int l = nums1.length;
        int m = nums2.length;
        int[] cache = new int[m];
        int ans = 0;
        cache[m - 1] = m - 1;
        for (int i = m - 2; i >= 0; i--) {
            if (nums2[i] > nums2[cache[i + 1]]) {
                cache[i] = i;
            } else {
                cache[i] = cache[i + 1];
            }
        }
        for (int i = 0; i < l; i++) {
            if (i < m && nums1[i] <= nums2[cache[i]]) {
                int j = binarySearch(nums1[i], i, m - 1, cache, nums2);
                ans = Math.max(ans, j - i);
            }

        }
        StringBuilder sb = new StringBuilder("");

        return ans;
    }

    private int binarySearch(int val, int l, int h, int[] cache, int[] num2) {

        if (l == h) {
            return cache[l];
        }
        if (l + 1 == h) {
            if (val <= num2[cache[h]]) {
                return cache[h];
            }
            return cache[l];
        }
        int mid = (l + h) / 2;
        if (val <= num2[cache[mid]]) {
            return binarySearch(val, mid, h, cache, num2);
        }
        return binarySearch(val, l, mid, cache, num2);
    }


    public int maxHeight(int[][] cuboids) {
        int m = cuboids.length;

        int[][][] cubes = new int[m][6][3];

        return 1;
    }


    TreeNode commonNode = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode l = lowestCommonAncestor(root.right, p, q);
        TreeNode r = lowestCommonAncestor(root.left, p, q);

        if (l != null && r != null) return root;

        if (l != null) return l;
        return r;

    }


    public String getDirections(TreeNode root, int startValue, int destValue) {

        getCommonNode(root, startValue, destValue);

        int count = travelForStart(commonNode, startValue) - 1;
        StringBuilder des = new StringBuilder("");
        travelForDest(commonNode, destValue, "", des);
        StringBuilder ans = new StringBuilder("");

        for (int i = 0; i < count; i++) {
            ans.append("U");
        }

        StringBuilder ss = des.reverse();
        ans.append(ss);
        return ans.toString();
    }

    private int travelForStart(TreeNode node, int start) {

        if (node == null) return 0;
        if (node.val == start) return 1;
        int l = travelForStart(node.left, start);
        int r = travelForStart(node.right, start);
        if (l > 0 || r > 0) return l + r + 1;
        return 0;
    }

    private int travelForDest(TreeNode node, int destValue, String dr, StringBuilder dir) {
        if (node == null) return 0;
        if (node.val == destValue) return 1;
        int l = travelForDest(node.left, destValue, "L", dir);
        int r = travelForDest(node.right, destValue, "R", dir);

        if (l == 1) {
            dir.append("L");
        }
        if (r == 1) {
            dir.append("R");
        }
        return l + r;
    }

    private int getCommonNode(TreeNode root, int startValue, int destValue) {

        if (root == null) return 0;

        if (root.val == startValue) {
            commonNode = root;
            return 1;
        }
        if (root.val == destValue) {
            commonNode = root;
            return 2;
        }
        int l = getCommonNode(root.left, startValue, destValue);
        int r = getCommonNode(root.right, startValue, destValue);
        if (l + r == 3) {
            commonNode = root;
            return 0;
        }
        return l + r;
    }
}

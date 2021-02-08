package leetcode_2020.contests.ContestAA;

import org.junit.Test;

import java.util.*;

public class Contests2 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Node {
        int i;
        int j;
        int sum;

        Node(int i, int j, int sum) {
            this.i = i;
            this.j = j;
            this.sum = sum;
        }
    }

    @Test
    public void ContestsSolutionBW16() {

        isSolvable(null, null);
    }

    public int findNumbers(int[] nums) {
        int result = 0;
        int count = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int t = nums[i];
            count = 0;
            int k = 10;
            while (t > 0) {
                t = t / 10;
                count++;
            }
            if (count % 2 == 0) {
                result++;
            }
        }
        return result;
    }

    public void isSolvable(String[] words, String result) {
        char[] s = {'0', '1', '2', '3', '4'};
        System.out.println("sd");
        permitation(s, 0);
    }

    public void permitation(char[] num, int index) {

        if (index + 1 == num.length) {
            for (int i = 0; i < num.length; i++)
                System.out.print(num[i]);
            System.out.println();
        }
        for (int i = index; i < num.length; i++) {
            swap(num, i, index);
            permitation(num, index + 1);
            swap(num, i, index);
        }
    }

    public void swap(char[] num, int i, int j) {
        char t = num[j];
        num[j] = num[i];
        num[i] = t;
    }

    public boolean canReach(int[] arr, int start) {

        int n = arr.length;
        boolean v[] = new boolean[n];
        if (arr[start] == 0) return true;

        return dfsHelper(start, v, arr, n);

    }

    public boolean dfsHelper(int at, boolean[] v, int arr[], int n) {
        if (v[at] || at < 0 || at >= n) {
            return false;
        }
        if (arr[at] == 0)
            return true;
        v[at] = true;

        return dfsHelper(at - arr[at], v, arr, n) || dfsHelper(at + arr[at], v, arr, n);
    }

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {

        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();

        treeTraverse(root1, l1);
        treeTraverse(root2, l2);
        return mergeTwoSortedLists(l1, l2);
    }

    public List<Integer> mergeTwoSortedLists(List<Integer> l1, List<Integer> l2) {

        int n = l1.size();
        int m = l2.size();
        int a = 0, b = 0;
        List<Integer> result = new ArrayList<>();

        while (a < n && b < m) {
            if (l1.get(a) <= l2.get(b)) {
                result.add(l1.get(a));
                a++;
            } else {
                result.add(l2.get(b));
                b++;
            }
        }
        while (a < n) {
            result.add(l1.get(a));
            a++;
        }
        while (b < m) {
            result.add(l2.get(b));
            b++;
        }
        return result;

    }

    public void treeTraverse(TreeNode node, List<Integer> list) {
        if (node == null) return;

        treeTraverse(node.left, list);
        list.add(node.val);
        treeTraverse(node.right, list);
    }

    public int[] sumZero(int n) {

        int[] ans = new int[n];
        ans[0] = 0;
        if (n == 1) return ans;

        int k = n / 2;
        int t = n - 1;
        for (int i = 0; i <= k; i++) {
            ans[i] = i;
            ans[t--] = i * -1;
        }
        if (n % 2 == 1) {
            ans[k + 1] = 0;
        }
        return ans;
    }

    public int[] pathsWithMaxScore(List<String> board) {
        int mod = 1000000007;
        int n = board.size();
        Queue<Node> queue = new LinkedList<>();
        Queue<Node> rq = new LinkedList<>();
        int a[] = new int[1001];

        queue.add(new Node(n - 1, n - 1, 0));
        while (!queue.isEmpty()) {
            int l = queue.size();

            for (int i = 0; i < l; i++) {
                Node node = queue.poll();
                int x = node.i;
                int y = node.j;
                int sum = node.sum;
                checkValidAndAdd(x, y - 1, sum, n, board, queue, rq, a, mod);
                checkValidAndAdd(x - 1, y, sum, n, board, queue, rq, a, mod);
                checkValidAndAdd(x - 1, y - 1, sum, n, board, queue, rq, a, mod);
            }
        }
        n = rq.size();
        int max = 0;
        for (int i = 0; i < n; i++) {
            Node node = ((LinkedList<Node>) rq).get(i);
            max = Math.max(max, node.sum);
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            Node node = rq.poll();
            if (max == node.sum) {
                count++;
            }
        }

        int answ[] = new int[2];
        answ[0] = max;
        answ[1] = count;
        return answ;
    }

    public void checkValidAndAdd(int i, int j, int sum, int n, List<String> board, Queue<Node> queue, Queue<Node> rq, int[] a, int mod) {
        if (i < 0 || j < 0) {
            return;
        }
        String s = board.get(i);
        char c = s.charAt(j);
        if (c == 'X') {
            return;
        }
        int val = c - '0';
        if (c == 'E') {
            rq.add(new Node(i, j, sum));
            return;
        }
        if (a[i * 10 + j] != 0) {
            if (sum + val > a[i * 10 + j])
                queue.add(new Node(i, j, (sum + val) % mod));
        } else {
            queue.add(new Node(i, j, (sum + val) % mod));
        }
        a[i * 10 + j] = (sum + val) % mod;
    }

    public int findBestValue(int[] arr, int target) {
        Arrays.sort(arr);
        int l = 1;
        int n = arr.length;
        int r = arr[0];
        for (int i = 0; i < n; i++) {
            r = Math.max(arr[i], r);
        }
        int sum = 0;
        while (l < r) {
            int m = (l + r) / 2;

            sum = getSum(arr, m, n);

            if (sum >= target) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        int sum2 = getSum(arr, l - 1, n);
        if (Math.abs(sum2 - target) <= Math.abs(sum - target)) {
            return l - 1;
        }
        return l;
    }

    public int getSum(int[] arr, int val, int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i] <= val ? arr[i] : val;
        }
        return sum;
    }

    public int deepestLeavesSum(TreeNode root) {

        HashMap<Integer, Integer> map = new HashMap<>();
        traverse(root, map, 0);
        int max = 0;
        for (Integer n : map.keySet()) {
            max = Math.max(n, max);
        }
        return map.get(max);
    }

    public void traverse(TreeNode node, HashMap<Integer, Integer> map, int h) {

        if (node == null) return;
        if (node.left == null && node.right == null) {
            map.put(h, node.val + map.getOrDefault(h, 0));
        }
        traverse(node.left, map, h + 1);
        traverse(node.right, map, h + 1);
    }

    public int[] replaceElements(int[] arr) {
        int n = arr.length;
        int ans[] = new int[n];

        ans[n - 1] = -1;

        int max = arr[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            ans[i] = max;
            max = Math.max(arr[i], max);
        }
        return ans;
    }
}

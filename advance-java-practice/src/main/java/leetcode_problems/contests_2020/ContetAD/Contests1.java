package leetcode_problems.contests_2020.ContetAD;

import org.junit.Test;

import java.util.Deque;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.LinkedList;

public class Contests1 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Node {
        int count;
        int index;

        Node(int count, int index) {
            this.count = count;
            this.index = index;
        }
    }

    @Test
    public void ContestsSolution() {

        int mat[][] =
            {{1, 1, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 1}};
//        System.out.println(kWeakestRows(mat, 3));
        int arr[] = {3, 3, 3, 3, 5, 5, 5, 2, 2, 7};
//        minSetSize(arr);
        int a[] = {7, 1, 7, 1, 7, 1};
        System.out.println(maxJumps(a, 2));
    }
    // Java Program to find the maximum for
// each and every contiguous subarray of size k.
    // Driver program to test above functions
    public static void main(String[] args)
    {
        int arr[] = { 1,2,4,3,1,5};
        int k = 3;
        printMax(arr, arr.length, k);
    }
        // A Dequeue (Double ended queue) based method for printing maximum element of
        // all subarrays of size k
        static void printMax(int arr[], int n, int k)
        {
            // Create a Double Ended Queue, Qi that will store indexes of array elements
            // The queue will store indexes of useful elements in every window and it will
            // maintain decreasing order of values from front to rear in Qi, i.e.,
            // arr[Qi.front[]] to arr[Qi.rear()] are sorted in decreasing order
            Deque<Integer> Qi = new LinkedList<Integer>();

            /* Process first k (or first window) elements of array */
            int i;
            for (i = 0; i < k; ++i) {
                // For every element, the previous smaller elements are useless so
                // remove them from Qi
                while (!Qi.isEmpty() && arr[i] >= arr[Qi.peekLast()])
                    Qi.removeLast(); // Remove from rear

                // Add new element at rear of queue
                Qi.addLast(i);
            }

            // Process rest of the elements, i.e., from arr[k] to arr[n-1]
            for (; i < n; ++i) {
                // The element at the front of the queue is the largest element of
                // previous window, so print it
                System.out.print(arr[Qi.peek()] + " ");

                // Remove the elements which are out of this window
                while ((!Qi.isEmpty()) && Qi.peek() <= i - k)
                    Qi.removeFirst();

                // Remove all elements smaller than the currently
                // being added element (remove useless elements)
                while ((!Qi.isEmpty()) && arr[i] >= arr[Qi.peekLast()])
                    Qi.removeLast();

                // Add current element at the rear of Qi
                Qi.addLast(i);
            }

            // Print the maximum element of last window
            System.out.print(arr[Qi.peek()]);
        }



// This code is contributed by Sumit Ghosh


    public int maxProduct(TreeNode root) {
        TreeNode sum = new TreeNode(0);
        updateSumTree(root, sum);
        long ans[] = {0};

        maxProductHelper(root, ans, sum);
        return (int)ans[0]%1000000007;
    }

    public void maxProductHelper(TreeNode node, long[] ans, TreeNode sum) {

        if (node == null) return;
        if (node.left != null) {
            int val = node.left.val;
            int val2 = sum.val - val;
            ans[0] = Math.max(ans[0], val * val2);
        }
        if (node.right != null) {
            int val = node.right.val;
            int val2 = sum.val - val;
            ans[0] = Math.max(ans[0], val * val2);
        }
        maxProductHelper(node.left, ans, sum);
        maxProductHelper(node.right, ans, sum);
    }

    public int updateSumTree(TreeNode root, TreeNode sum) {
        if (root == null) return 0;
        sum.val += root.val;
        int l = updateSumTree(root.left, sum);
        int r = updateSumTree(root.right, sum);

        root.val = l + r + root.val;
        return root.val;
    }

    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int dp[] = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        int ans = Integer.MIN_VALUE;
        for (int k = 1; k <= n; k++) {
            for (int i = 0; i < n; i++) {
                int left = 0;
                int right = n - 1;
                if (i - d >= 0)
                    left = i - d;
                if (i + d < n)
                    right = i + d;
                boolean rflag = true;
                int Val = Integer.MIN_VALUE;
                for (int r = i + 1; r <= right; r++) {
                    if (arr[i] <= arr[r]) rflag = false;

                    if (rflag) {
                        Val = Math.max(dp[r] + 1, Val);
                    } else break;
                }
                dp[i] = Math.max(dp[i], Val);
                boolean lflag = true;
                Val = Integer.MIN_VALUE;
                for (int l = i - 1; l >= left; l--) {
                    if (arr[i] <= arr[l]) lflag = false;

                    if (lflag) {
                        Val = Math.max(dp[l] + 1, Val);
                    } else break;
                }
                dp[i] = Math.max(dp[i], Val);
                ans = Math.max(ans, dp[i]);
            }
        }
        return ans;
    }

    public int minSetSize(int[] arr) {
        int n = arr.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int val = map.getOrDefault(arr[i], 0);
            map.put(arr[i], val + 1);
        }
        // default in min heap
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((x, y) -> y - x);
        PriorityQueue<Integer> q = new PriorityQueue<>((x, y)-> x-y);
        for (Integer k : map.keySet()) {
            priorityQueue.add(map.get(k));
        }
        int mid = (n + 1) / 2;
        int ans = 0;
        while (!priorityQueue.isEmpty()) {
            int count = priorityQueue.poll();
            n = n - count;
            ans++;
            if (n <= mid) return ans;
        }

        return ans;
    }

    public int[] kWeakestRows(int[][] mat, int k) {
        int n = mat.length;
        int m = mat[0].length;

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>((n1, n2) -> n1.count == n2.count ? n2.index - n1.index : n2.count - n1.count);
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1) count++;
                else break;
            }
            priorityQueue.add(new Node(count, i));
            if (priorityQueue.size() > k) priorityQueue.poll();
        }
        int ans[] = new int[k];
        int i = k - 1;
        while (!priorityQueue.isEmpty()) {
            ans[i--] = priorityQueue.poll().index;
        }
        return ans;
    }
}

package leetcode.contests.ContetsAE;

import leetcode.contests.ContetAD.Contests10;
import org.junit.Test;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Contests7 {

    class Test1<T> {
        // An object of type T is declared
        T obj;

        Test1(T obj) {
            this.obj = obj;
        }  // constructor

        public T getObject() {
            return this.obj;
        }
    }


    @Test
    public void ContestsSolution() throws ClassNotFoundException, NoSuchMethodException {
//        int a[] = {7, 7, 7, 7, 7, 7};

        //[[1,3,11],[2,4,6]], k = 9
        int mat [][] = {{1,3,11}, {2,4,6}};
        System.out.println(kthSmallest(mat, 9));

    }
    class Node implements Comparable<Node> {
        int i;
        int j;
        int val;

        Node(int i, int j,  int val) {
            this.j = j;
            this.i = i;
            this.val = val;
        }

        @Override
        public int compareTo(Node p) {
            return this.val - p.val;
        }


    }

    public int kthSmallest(int[][] mat, int k) {
        int n = mat.length;
        int m = mat[0].length;
        PriorityQueue<Node> queue = new PriorityQueue<>();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += mat[i][0];
            queue.add(new Node(i, 0, mat[i][0]));
        }
        if (k == 1) return sum;
        int t =1;
        while (t<k) {
            Node node = queue.peek();
            if (node.j+1==m - 1) {
                queue.poll();
                sum = sum- node.val + mat[node.i][m-1];
                t++;
                queue.add(new Node(node.i, m-1, 5001));
            } else {
                if(node.val != 5001){
                    queue.poll();
                    sum = sum- node.val + mat[node.i][node.j+1];
                    t++;
                    queue.add(new Node(node.i, node.j+1, mat[node.i][node.j+1]));
                }
            }
            //[[1,3,11],[2,4,6]], k = 9
            if(t==k){
                return sum;
            }

        }
return 0;

    }


    public int maxDiff(int num) {

        int x = num;
        int l = 0;
        int p = 0;
        int val = -1;
        while (x > 0) {
            int t = x % 10;
            if (t != 9) {
                p = l;
                val = t;
            }
            x = x / 10;
        }
        x = num;
        int max = 0;
        int ten = 1;
        while (x > 0) {
            int t = x % 10;
            if (val != -1 && val == t) {
                t = 9;
            }
            max = max + t * ten;
            ten = ten * 10;
            x = x / 10;
        }
        x = num;
        int last = -1;
        int slast = -1;

        while (x > 0) {
            int t = x % 10;
            if (x > 9 && t != 0 && t != 1) {
                slast = t;
            }
            if (x <= 9 && x != 1) {
                last = t;
            }
            x = x / 10;
        }
        x = num;
        int min = 0;
        ten = 1;
        while (x > 0) {
            int t = x % 10;
            if (last > -1) {
                if (last == t) t = 1;
            } else if (slast != -1 && slast == t) {
                t = 0;
            }

            min = min + t * ten;
            ten = ten * 10;
            x = x / 10;
        }
        return max - min;

    }


    public int reArrangeRebBlueByK(int k, int n) {
        int dp[][] = new int[n + 1][2];
        if (k > n) {
            return (int) Math.pow(2, n);
        }
        dp[1][0] = 1;
        dp[1][1] = 1;
        for (int i = 2; i <= n; i++) {
            int val = dp[i - 1][0] + dp[i - 1][1];
            if (i < k) {
                dp[i][0] = val;
                dp[i][1] = val;
            } else {
                dp[i][0] = val - dp[i - k][1];
                dp[i][1] = val - dp[i - k][0];
            }
        }
        return dp[n][0] + dp[n][1];
    }

    public boolean checkIfCanBreak(String s1, String s2) {
        char[] a = s1.toCharArray();
        char[] b = s2.toCharArray();
        Arrays.sort(a);
        Arrays.sort(b);
        int n = a.length;
        boolean f1 = true;
        boolean f2 = true;
        for (int i = 0; i < n; i++) {
            if (a[i] < b[i]) {
                f1 = false;
            }
            if (a[i] > b[i]) {
                f2 = false;
            }
        }
        System.out.println();
        return f1 || f2;
    }

    public int longestSubarray(int[] nums, int limit) {
        // min by Default
        PriorityQueue<Integer> max = new PriorityQueue<>((x, y) -> y - x);
        PriorityQueue<Integer> min = new PriorityQueue<>();
        int k = nums.length;
        int s = 0;
        int e = 0;
        while (s != k) {

            if (Math.abs(max.peek() - min.peek()) <= limit) {
                max.add(nums[e]);
                min.add(nums[e]);
            } else {

            }
        }
        return 1;
    }


    class FirstUnique {
        Node tail = null;
        Node head = null;
        Map<Integer, Node> map = new HashMap<>();

        class Node {
            int val;
            Node back;
            Node forward;

            Node(int val) {
                this.val = val;
            }

            public void setValNeg() {
                this.val = -1;
            }
        }

        public FirstUnique(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                add(nums[i]);
            }
        }

        public int showFirstUnique() {
            if (tail == null) return -1;
            return tail.val;
        }

        public void add(int value) {
            Node a = map.get(value);
            if (a != null && a.val == -1) return;
            if (a == null && head != null) {
                Node t = new Node(value);
                head.forward = t;
                t.back = head;
                head = t;
                map.put(value, head);
                return;
            }
            if (head == null) {
                head = new Node(value);
                tail = head;
                map.put(value, head);
            } else {
                if (tail != null && tail.val == value || tail.val == head.val) {
                    if (tail.val == head.val) {
                        tail = null;
                        head = null;
                        return;
                    }
                    Node next = tail.forward;
                    tail = next;
                    if (tail != null) {
                        tail.back = null;
                    }

                    a.setValNeg();
                    map.put(value, a);
                } else if (head.val == value) {
                    Node back = head.back;
                    head = back;
                    if (head != null) {
                        head.forward = null;
                    }
                    a.setValNeg();
                    map.put(value, a);
                } else {
                    //mid
                    Node b1 = a.back;
                    Node h1 = a.forward;
                    b1.forward = h1;
                    h1.back = b1;
                    a.setValNeg();
                    map.put(value, a);
                }
            }

        }
    }


}

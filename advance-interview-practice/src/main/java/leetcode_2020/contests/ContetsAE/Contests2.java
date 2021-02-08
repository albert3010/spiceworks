package leetcode_2020.contests.ContetsAE;

import org.junit.Test;

import java.util.*;

public class Contests2 {
    class Node implements Comparable<Node> {
        int val;
        int count;

        Node(int val, int count) {
            this.val = val;
            this.count = count;
        }

        @Override
        public int compareTo(Node p) {
            return p.count - this.count;
        }
    }

    @Test
    public void ContestsSolution() {
            int[] a = {1,1,1,2,2,2};
        System.out.println(rearrangeBarcodes(a));
    }

    public int[] rearrangeBarcodes(int[] barcodes) {
        int n = barcodes.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            map.put(barcodes[i], map.getOrDefault(barcodes[i], 0)+1);
        }
        for (Integer node : map.keySet()) {
            queue.add(new Node(node, map.get(node)));
        }
        int counter = 0;
        while (!queue.isEmpty()) {
            Node v1 = queue.poll();
            Node v2 = new Node(0, 0);
            if (queue.size() > 0) {
                v2 = queue.poll();
            }
            if (v2.count == 0) {
                if(v1.count!=0)
                    barcodes[counter++] = v1.val;
            } else {
                barcodes[counter++] = v1.val;
                barcodes[counter++] = v2.val;
                queue.add(new Node(v1.val, v1.count-1));
                queue.add(new Node(v2.val, v2.count-1));
            }
        }
        return barcodes;
    }

    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;

        boolean v[][] = new boolean[m][n];
        Queue<Integer> queue = new LinkedList<>();
        int max = Math.max(m, n);
        int steps = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.add(i * max + j);
                }
                if (grid[i][j] != 1) {
                    v[i][j] = true;
                }
            }
        }
        int adj[] = {0, 1, 0, -1, 0};
        while (!queue.isEmpty()) {
            int l = queue.size();
            while (l > 0) {
                int x = queue.poll();
                int i = x / max;
                int j = x % max;
                for (int k = 0; k < 4; k++) {
                    int xx = i + adj[k];
                    int yy = j + adj[k + 1];
                    if (xx >= 0 && xx < m && yy >= 0 && yy < n && !v[xx][yy]) {
                        queue.add(xx * max + yy);
                        v[xx][yy] = true;
                    }
                }
                l--;
            }
            if (queue.size() > 0)
                steps++;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!v[i][j]) {
                    return -1;
                }
            }
        }
        return steps;
    }

    class MedianFinder {
        // min by default
        PriorityQueue<Integer> min, max;

        int count;

        public MedianFinder() {
            min = new PriorityQueue<>();
            max = new PriorityQueue<>((x, y) -> y - x);
            count = 0;
        }

        public void addNum(int num) {
            count++;
            if (count < 2) {
                max.add(num);
            } else if (count == 2) {
                min.add(max.poll());
            } else {
                if (max.peek() < num) {
                    min.add(num);
                    if (Math.abs(max.size() - min.size()) >= 2) {
                        max.add(min.poll());
                    }

                } else {
                    max.add(num);
                    if (Math.abs(max.size() - min.size()) >= 2) {
                        min.add(max.poll());
                    }
                }
            }

        }

        public double findMedian() {
            int l = max.size() + min.size();
            if (l > 0 && l % 2 == 0) {
                return max.peek() + min.peek() / 2.0;
            } else {
                if (max.size() > min.size()) {
                    return max.peek();
                } else {
                    return min.peek();
                }
            }

        }
    }

}

package leetcode_problems.contests_2020.ContestsAB;

import org.junit.Test;

import java.util.Arrays;

public class Contests4 {
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }

    @Test
    public void ContestsSolution() {

    }

    public int minTaps(int n, int[] ranges) {
        int[] intervals = new int[n + 1];
        Arrays.fill(intervals, -1);
        int l = ranges.length;
        for (int i = 0; i < l; i++) {
            int left = i - ranges[i];
            int right = i + ranges[i];
            if (left < 0) {
                left = 0;
            }
            intervals[left] = Math.max(intervals[left], right);
        }

        int maxRange = intervals[0];
        if (maxRange <= 0) return -1;
        int index = 0;
        int bound = maxRange;
        int ans = 1;
        while (index <= n && maxRange <= n) {
            if (maxRange >= n) return ans;

            if (index <= bound) {
                maxRange = Math.max(maxRange, intervals[index++]);
            } else {
                ans++;
                bound = maxRange;
            }
            if (maxRange > bound && maxRange >= n) return ans + 1;
            if (index > maxRange) {
                return -1;
            }
        }
        return ans;
    }

    public Node flatten(Node head) {
        if (head == null) return null;

        if (head.child != null) {
            Node child = flatten(head.child);
            head.child = null;

            Node t2 = head.next;
            Node t4 = getLastNode(child);

            head.next = child;
            child.prev = head;

            t4.next = t2;
            if (t2 != null) {
                t2.prev = t4;
            }
            flatten(t4);
        } else {
            flatten(head.next);
        }
        return head;
    }

    public Node getLastNode(Node node) {
        if (node == null || node.next == null) return node;
        return getLastNode(node.next);
    }

}

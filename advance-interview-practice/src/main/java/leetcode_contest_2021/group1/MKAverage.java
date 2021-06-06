package leetcode_contest_2021.group1;

import java.util.*;

public class MKAverage {
    Deque<Integer> queue = new ArrayDeque<>();
    int k;
    int m;

    public MKAverage(int m, int k) {
        this.k = k;
        this.m = m;
    }

    public void addElement(int num) {
        queue.add(num);
        if (queue.size() > m) {
            queue.removeFirst();
        }
    }

    public int calculateMKAverage() {
        if (queue.size() < m) {
            return -1;
        }
        return 1;
    }

}

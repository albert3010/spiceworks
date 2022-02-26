package leetcode_problems.contests_2020.ContetAD;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Contests9 {

    class Node {
        int lastIndex0;
        int val;
        List<Integer> prodList;

        public Node(int val, int lastIndex0) {
            this.lastIndex0 = lastIndex0;
            this.val = val;
            this.prodList = new ArrayList<>();
        }
    }

    class Point implements Comparable<Point> {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point p) {
            if (this.y > p.y) {
                // +ve means swapping less val
                return 1;
            }
            if (this.y == p.y) {
                return this.x - p.x;
            }
            // no action on current element
            return -1;
        }
    }

    @Test
    public void ContestsSolution() {
        int seats[] = {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0};
//        System.out.println(maxDistToClosest(seats));

//        int a[][] = {{1,2},{2,2},{1,1}, {1,2}};
//        int a[][] = {{1,2},{2,3},{3,4},{1,2}};
        int a[][] = {{1, 5}, {1, 5}, {1, 5}, {2, 3}, {2, 3}};
//        int a[][] = {{1,4},{4,4},{2,2},{3,4},{1,1}};
        System.out.println(maxEvents(a));
    }

    public int maxEvents(int[][] events) {
        int n = events.length;
        List<Point> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(new Point(events[i][0], events[i][1]));
        }
        Collections.sort(arr);
        int count = 0;
        boolean v[] = new boolean[100000];

        for (int i = 0; i < n; i++) {
            Point p = arr.get(i);
            for (int s = p.x; s < p.y; s++) {
                if(!v[s]){
                    v[s] = true;
                    count++;
                    break;
                }
            }

        }
        return count;
    }

    class ProductOfNumbers {
        List<Node> list;
        int lastIndexZero = -1;

        public ProductOfNumbers() {
            list = new ArrayList<>();
        }

        public void add(int num) {
            if (num == 0) {
                lastIndexZero = list.size();
            }
            list.add(new Node(num, lastIndexZero));
        }

        public int getProduct(int k) {
            int l = list.size();
            int lastZero = list.get(l - 1).lastIndex0;
            int index = l - k;
            if (index <= lastZero) {
                return 0;
            }
            int prod = 1;
            List<Integer> ls = list.get(l - 1).prodList;
            if (k > ls.size()) {
                return 0;
            }
            if (k <= ls.size()) {
                return ls.get(k - 1);
            }

            for (int i = l - 1; i >= 0; i--) {
                prod = prod * list.get(i).val;
                ls.add(prod);
                if (prod == 0) {
                    break;
                }
            }
            return ls.get(k - 1);
        }
    }

    public int countNegatives(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] < 0) {
                    ans += n - j;
                    break;
                }
            }
        }
        return ans;
    }

    public int maxDistToClosest(int[] seats) {
        int n = seats.length;
        int l = 0;
        int ans = 0;
        int fans = 0;
        int onefound = -1;
        for (int i = 0; i < n; i++) {
            if (seats[i] == 1 && onefound < 0) {
                onefound = i;
            }
            if (seats[i] == 0) {
                l++;
            } else {
                int mid = l / 2;
                int findex = i - l - 1;
                int x = i - mid - 1;
                int dis = x - findex;
                if (dis >= ans) {
                    ans = x - findex;
                    fans = x;
                }
                l = 0;
            }
        }

        if (onefound > 0) {
            if (onefound >= ans) {
                ans = 0;
                fans = 0;
            }
        }
        if (seats[n - 1] == 0) {
            if (l >= ans && l >= onefound) {
                ans = n - 1;
                fans = n - 1;
            }
        }
        return fans;
    }
}

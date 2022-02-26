package leetcode_problems.june_contests.S2;

import org.junit.Test;

import java.util.*;

public class testA1 {
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
    public void function() {

        int[][] a = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
//        System.out.println(reconstructQueue(a));
//        int[] aa = {3, 1, 1, 4, 1, 5, 1, 4};
        int[] aa = {3, 2, 2, 4, 3};
        System.out.println(minSumOfLengths(aa, 3));
    }

    class Node {
        int i;
        int j;

        Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public int minSumOfLengths(int[] arr, int target) {

        int l = arr.length;
        int s = 0;
        int e = 0;
        int sum = 0;
        List<Node> subPath = new ArrayList<>();

        int[] left = new int[l];
        int[] right = new int[l];
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < l; i++) {
            left[i] = Integer.MAX_VALUE;
            right[i] = Integer.MAX_VALUE;
        }

        while (s < l) {
            if(e>=l && sum<= target)  break;
            if (sum == target) {
                sum += arr[e];
            } else if (sum < target) {
                sum += arr[e];
                if (sum == target) {
                    subPath.add(new Node(s, e));
                    min = Math.min(e - s, min);
                    left[e] = min;
                }
                e++;
            } else {
                sum -= arr[s];
                s++;
                if (sum == target) {
                    subPath.add(new Node(s, e));
                    min = Math.min(e - s, min);
                    left[e] = min;
                    e++;
                }
                if (sum < target) e++;

            }
        }
        s = l - 1;
        e = l - 1;
        sum = 0;
        min = Integer.MAX_VALUE;
        while (e >= 0) {
            if (sum == target) {
                sum += arr[s];
            } else if (sum < target) {
                sum += arr[s];
                if (sum == target) {
                    subPath.add(new Node(s, e));
                    min = Math.min(e - s, min);
                    right[s] = min;
                }
                s--;
            } else if (sum > target) {
                sum -= arr[e];
                e--;
                if (sum == target) {
                    subPath.add(new Node(s, e));
                    min = Math.min(e - s, min);
                    left[s] = min;
                    s--;
                }
                if (sum < target) s--;

            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < l - 1; i++) {
            if (left[i] != Integer.MAX_VALUE && right[i] != Integer.MAX_VALUE) {
                ans = Math.min(left[i] + right[i + 1], ans);
            }
        }

        if (ans != Integer.MAX_VALUE) return ans;
        return -1;
    }

    public int[][] reconstructQueue(int[][] people) {
        System.out.println();
        Arrays.sort(people, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        List<int[]> res = new ArrayList<>();

        for (int[] cur : people) {
            res.add(cur[1], cur);
        }

        return res.toArray(new int[people.length][2]);
    }

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {

        int[][] dp = new int[m][target + 1];

        Set<Integer> set = new LinkedHashSet<>();
        for (int i = 0; i < m; i++) {

        }
        int color = -1;
        for (int i = 0; i < m; i++) {
            if (houses[i] != 0) {
                set.add(houses[i]);
                color = houses[i];
            }
            if (set.size() <= 1) {
                if (color > -1) {
                    dp[i][1] += cost[i][color - 1];
                } else {
                    dp[i][1] += cost[i][color - 1];
                }

            }

        }

        return 1;
    }


    class BrowserHistory {
        Stack<String> backward;
        Stack<String> forward;

        public BrowserHistory(String homepage) {
            backward = new Stack<>();
            forward = new Stack<>();
            backward.add(homepage);
        }

        public void visit(String url) {
            backward.add(url);
            forward.clear();
        }

        public String back(int steps) {

            while (steps > 0 && !backward.isEmpty() && backward.size() > 1) {
                String bk = backward.pop();
                forward.add(bk);
                steps--;
            }
            return backward.peek();
        }

        public String forward(int steps) {
            while (steps > 0 && !forward.isEmpty()) {
                String bk = forward.pop();
                backward.add(bk);
                steps--;
            }
            return backward.peek();
        }
    }

    public int[] getStrongest(int[] arr, int k) {

        int n = arr.length;

        List<Integer> ans = new ArrayList<>();
        Arrays.sort(arr);
        int median = arr[(n - 1) / 2];
        for (int i = 0; i < n; i++) {
            ans.add(arr[i]);
        }
        Collections.sort(ans, (a, b) -> getAbsVal(a, b, median));
        int[] an = new int[k];
        for (int i = 0; i < k; i++) {
            an[i] = ans.get(i);
        }
        return an;

    }

    int getAbsVal(int a, int b, int m) {
        int x = Math.abs(a - m);
        int y = Math.abs(b - m);
        if (y > x) return 1;
        if (x == y) return b - a;
        return -1;
    }
}


package leetcode.problems1;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

public class problems9 {
//        List<List<Integer>> books = Lists.newArrayList();
//        books.add(Lists.newArrayList(1,1));
//        books.add(Lists.newArrayList(2,3));
//        books.add(Lists.newArrayList(2,3));
//        books.add(Lists.newArrayList(1,1));
//        books.add(Lists.newArrayList(1,1));
//        books.add(Lists.newArrayList(1,1));
//        books.add(Lists.newArrayList(1,2));

//        int arr[][] = { {2,7,9},{3,6,1},{7,4,2} };

    private class node {

        node(int max, int sum) {
            this.max = max;
            this.sum = sum;
        }

        int max;
        int sum;
    }

    @Test
    public void numRescueBoatsTest() {
        int people[] = {1, 2};
        System.out.println(numRescueBoats(people, 3));
    }

    @Test
    public void eraseOverlapIntervalsTest() {

//
//        int intervals[][] = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
//        System.out.println(eraseOverlapIntervals(intervals));
        System.out.println(minDistance("horse", "ros"));
    }

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = i + j;
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                    } else {
                        dp[i][j] = Math.min(dp[i][j], Math.min(dp[i][j - 1], dp[i - 1][j]) + 1);
                    }
                }
            }
        }
        return dp[m][n];
    }

    @Test
    public void mctFromLeafValuesTest() {


        int arr[] = {15, 13, 5, 3, 15};
        System.out.println(mctFromLeafValuesDp(arr));
    }

    public int mctFromLeafValuesDp(int[] arr) {

        int l = arr.length;
        int[][][] dp = new int[l][l][2];

        for (int i = 0; i < l; i++) {
            dp[i][i][0] = arr[i];
            dp[i][i][1] = 0;
        }

        for (int g = 1; g < l; g++) {

            for (int i = 0; i < l - g; i++) {

                int s = i;
                int e = i + g;

                int minSum = Integer.MAX_VALUE;
                for (int k = s; k < e; k++) {
                    int val = dp[s][k][0] * dp[k + 1][e][0] + dp[s][k][1] + dp[k + 1][e][1];
                    if (val <= minSum) {
                        minSum = val;
                        dp[s][e][0] = Math.max(dp[s][k][0], dp[k + 1][e][0]);
                        dp[s][e][1] = val;
                    }
                }
            }
        }
        return dp[0][l - 1][1];
    }


    public int mctFromLeafValues(int[] arr) {

        List<node> list = new ArrayList();

        for (int i = 0; i < arr.length; i++) {
            list.add(new node(arr[i], arr[i]));
        }
        int totalSum = 0;

        while (list.size() > 1) {
            int minIndex = 0;
            int minSum = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < list.size() - 1; i++) {
                int sum = list.get(i).max * list.get(i + 1).max;
                if (sum <= minSum) {
                    minSum = sum;
                    minIndex = i;
                    max = Math.max(list.get(i).max, list.get(i + 1).max);
                }
            }
//            System.out.println(minSum);
            totalSum += minSum;
            list.remove(minIndex);
            list.remove(minIndex);
            list.add(minIndex, new node(max, minSum));
        }


        return totalSum;

    }


    public int eraseOverlapIntervals(int[][] intervals) {

        Arrays.sort(intervals, (int[] a, int[] b) -> {
            return a[0] - b[0];
        });

        Stack<Integer[]> stack = new Stack<Integer[]>();
        stack.push(new Integer[]{intervals[0][0], intervals[0][1]});
        int intervalSize = intervals.length;
        for (int i = 1; i < intervalSize; i++) {

            Integer[] top = stack.peek();
            if (top[1] <= intervals[i][0]) {
                stack.push(new Integer[]{intervals[i][0], intervals[i][1]});
                continue;
            }

            if (top[1] >= intervals[i][1]) {
                stack.pop();
                stack.push(new Integer[]{intervals[i][0], intervals[i][1]});

            }


        }
        return (intervalSize - stack.size());
    }

    public int numRescueBoats(int[] people, int limit) {

        int n = people.length;
        int l = 0;
        int r = n - 1;
        Arrays.sort(people);

        int count = 0;
        int m = 0;

        while (l <= r) {
            if (l == r) {
                count++;
                break;
            }
            m = limit - people[l];

            if (m < people[r]) {
                r--;

            } else {
                r--;
                l++;
            }
            count++;
        }
        return count;
    }

    @Test
    public void SkylineTest() {

//        [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
//        List<List<Integer>> buildings = new ArrayList<>();
        //[Li, Ri, Hi]
        int[] a1 = new int[]{2, 9, 10};
        int[] a2 = new int[]{3, 7, 15};
        int[] a3 = new int[]{5, 12, 12};
        int[] a4 = new int[]{15, 20, 10};
        int[] a5 = new int[]{19, 24, 8};
        int buildings[][] = {a1, a2, a3, a4, a5};

        List<List<Integer>> results = getSkyline(buildings);
        System.out.println(results);
    }

    public List<List<Integer>> getSkyline(int[][] b) {

        HashMap<Integer, List<Point>> leftMapList = new HashMap<>();

        HashMap<Integer, Point> leftMap = new HashMap<>();
        HashMap<Integer, Point> rightMap = new HashMap<>();

        TreeSet<Integer> xValues = new TreeSet<>();
        int l = b.length;

        for (int i = 0; i < l; i++) {
            // x values
            xValues.add(b[i][0]);
            xValues.add(b[i][1]);
            Point p = new Point(b[i][0], b[i][1], b[i][2]);
            // left
            if (Objects.isNull(leftMap.get(b[i][0]))) {

                ArrayList<Point> list = new ArrayList<>();
                list.add(p);

                leftMapList.put(b[i][0], list);
                leftMap.put(b[i][0], p);

            } else {
                leftMapList.get(b[i][0]).add(p);
                if (leftMap.get(b[i][0]).h < b[i][2]) {
                    leftMap.put(b[i][0], p);
                }
            }

            //right
            if (!Objects.isNull(rightMap.get(b[i][1]))) {
                if (rightMap.get(b[i][1]).h < b[i][2]) {
                    rightMap.put(b[i][1], p);
                }
            } else {
                rightMap.put(b[i][1], p);
            }

        }
        return getOutLine(xValues, leftMap, rightMap, leftMapList, b);

    }

    private List<List<Integer>> getOutLine(TreeSet<Integer> xValues,
                                           HashMap<Integer, Point> leftMap, HashMap<Integer, Point> rightMap,
                                           HashMap<Integer, List<Point>> leftMapList, int[][] b) {

        List<List<Integer>> results = new ArrayList<>();
        int lastH = 0;
        List<Point> scan = new ArrayList<>();
//        Queue<Point> pQueue = new PriorityQueue<>();

        for (Integer x : xValues) {

            Point left = leftMap.get(x);
            Point right = rightMap.get(x);

            int bMax = 0;
            for (Point p : scan) {
                if (p.a < x && x < p.b) {
                    bMax = Math.max(bMax, p.h);
                }
            }

            if (Objects.isNull(left) && bMax == 0) {
                results.add(Arrays.asList(x, 0));
                lastH = 0;
            } else {
                int lMax = 0;
                if (left != null) {
                    lMax = left.h;
                }

                int m = Math.max(lMax, bMax);

                if (lastH != m) {

                    if (lMax >= bMax) {
                        results.add(Arrays.asList(x, lMax));
                        lastH = lMax;
                    } else {
                        results.add(Arrays.asList(x, bMax));
                        lastH = bMax;
                    }
                }

            }
            List<Point> ls = leftMapList.get(x);
            if (ls != null) {
                scan.addAll(ls);
            }

        }

        return results;
    }


    class Point implements Comparable<Point> {
        int a;
        int b;
        int h;

        Point(int a, int b, int h) {
            this.a = a;
            this.b = b;
            this.h = h;
        }

        public int compareTo(Point b) {
            if (h == b.h)
                return 0;

            return h < b.h ? 1 : -1;
        }

    }


    @Test
    public void minimumPossibleHeightBooks() {
        int[] a1 = new int[]{1, 1};
        int[] a2 = new int[]{2, 3};
        int[] a3 = new int[]{2, 3};
        int[] a4 = new int[]{1, 1};
        int[] a5 = new int[]{1, 1};
        int[] a6 = new int[]{1, 1};
        int[] a7 = new int[]{1, 2};
        int books[][] = {a1, a2, a3, a4, a5, a6, a7};
        int shelf_width = 4;
        Queue<Point> pQueue = new PriorityQueue<>();

        pQueue.add(new Point(3, 4, 5));
        pQueue.add(new Point(3, 4, 1));
        pQueue.add(new Point(3, 4, 2));
        pQueue.add(new Point(3, 4, 2));
        System.out.println(pQueue.peek().h);

//        int h = minHeightShelvesNew(books, shelf_width);
//        System.out.println(h);

    }

    private int minHeightShelves(int[][] books, int shelf_width) {
        int l = books.length;
        int[][] dp = new int[l][l];
        for (int i = 0; i < l; i++) {
            dp[i][i] = books[i][1];
        }

        for (int g = 1; g < l; g++) {
            for (int i = 0; i < l - g; i++) {
                int j = i + g;

                updateDp(i, j, books, dp, shelf_width);
            }
        }
        return dp[0][l - 1];
    }

    private int minHeightShelvesNew(int[][] books, int shelf_width) {
        int l = books.length;
        int[] dp = new int[l + 1];
        dp[1] = books[0][1];

        for (int i = 2; i <= l; i++) {
            int w = 0;
            int maxH = 0;
            dp[i] = Integer.MAX_VALUE;
            for (int j = i - 1; j >= 0; j--) {

                w += books[j][0];
                if (w > shelf_width) {
                    break;
                }
                maxH = Math.max(maxH, books[j][1]);
                dp[i] = Math.min(dp[i], dp[j] + maxH);

            }

        }
        return dp[l];
    }


    private void updateDp(int i, int j, int[][] books, int[][] dp, int shelf_width) {
        int a[] = getIndexAndHeight(i, j, books, shelf_width);
        int k = a[0];
        int maxH = a[1];
        if (k < j) {
            dp[i][j] = maxH + dp[k + 1][j];
        } else {
            dp[i][j] = maxH;
        }

        a = getIndexAndHeight(j, i, books, shelf_width);
        k = a[0];
        maxH = a[1];
        if (i < k) {
            dp[i][j] = Math.min(dp[i][j], maxH + dp[i][k - 1]);
        } else {
            dp[i][j] = Math.min(dp[i][j], maxH);
        }
    }

    private int[] getIndexAndHeight(int i, int j, int[][] books, int shelf_width) {
        int[] a = new int[2];
        int thickness = 0;
        int maxH = 0;
        if (i <= j) {
            for (int k = i; k <= j; k++) {
                thickness += books[k][0];
                if (thickness <= shelf_width) {
                    a[0] = k;
                    a[1] = Math.max(a[1], books[k][1]);
                } else {
                    break;
                }
            }
        }
        if (i > j) {
            for (int k = i; k >= j; k--) {
                thickness += books[k][0];
                if (thickness <= shelf_width) {
                    a[0] = k;
                    a[1] = Math.max(a[1], books[k][1]);
                } else {
                    break;
                }
            }
        }
        return a;
    }


}

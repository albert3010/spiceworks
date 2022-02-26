package leetcode_problems.contests_2020.ContetAD;

import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Contests12 {


    @Test
    public void ContestsSolution() {
        int a[][] = {{0, 0}, {0, 0}, {2, 3}};

//        System.out.println(maxPoints(a));
        System.out.println(diffWaysToCompute("2*3-4*5"));
    }

    public List<Integer> diffWaysToCompute(String input) {

        int n = input.length();
        List<Integer> ans = new ArrayList<>();
        return waysHepler(input, 0, n - 1);
    }

    public List<Integer> waysHepler(String input, int s, int e) {
        boolean f = false;
        List<Integer> ans = new ArrayList<>();
        for (int i = s; i <= e; i++) {
            char c = input.charAt(i);

            if (c == '+' || c == '-' || c == '*') {
                f = true;
                List<Integer> l1 = waysHepler(input, s, i - 1);
                List<Integer> r1 = waysHepler(input, i + 1, e);
                ans.addAll(merge(l1, r1, c));
            }
        }
        if (!f) {
            Integer ss = Integer.parseInt(input.substring(s, e+1));
            ans.add(ss);
        }

        return ans;
    }

    public List<Integer> merge(List<Integer> l1, List<Integer> r1, char c) {
        List<Integer> ans = new ArrayList<>();
        for (Integer s : l1) {
            for (Integer r : r1) {
                int ss = s + r;
                if (c == '*') {
                    ss = s * r;
                }
                if (c == '-') {
                    ss = s - r;
                }
                ans.add(ss);
            }
        }
        return ans;
    }

    public int maxPoints(int[][] points) {
        int n = points.length;

        HashMap<Double, HashMap<Double, Integer>> map = new HashMap<>();
        HashMap<Integer, Integer> infi = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int x1 = points[i][0];
                int y1 = points[i][1];
                int x2 = points[j][0];
                int y2 = points[j][1];
                Double slope = getSlope(x1, y1, x2, y2);
                if (getSlope(x1, y1, x2, y2) == 0) {
                    if (x1 == x2 && y1 == y2) {
                        Double c = y1 + 0.0;
                        HashMap<Double, Integer> map1 = map.getOrDefault(slope, new HashMap<>());
                        int val = map1.getOrDefault(c, 0) + 1;
                        ans = Math.max(ans, val);
                        map1.put(c, val);
                        map.put(slope, map1);
//
//                        for (Double slop : map.keySet()) {
//                            HashMap<Double, Integer> map_ = map.get(slop);
//                            for (Double k : map_.keySet()) {
//                                int cc = map_.get(k);
//                                if (y1 == slop * x1 + k) {
//                                    map_.put(k, cc + 1);
//                                }
//                            }
//
//                        }
                    }
                    int val = infi.getOrDefault(x1, 0) + 1;
                    ans = Math.max(ans, val);
                    infi.put(x1, infi.getOrDefault(x1, 0) + 1);

                } else {
                    // y= m*x +c;
                    Double c = y1 - slope * x1;
                    HashMap<Double, Integer> map1 = map.getOrDefault(slope, new HashMap<>());
                    int val = map1.getOrDefault(c, 0) + 1;
                    ans = Math.max(ans, val);
                    map1.put(c, val);
                    map.put(slope, map1);
                }
            }

        }
        System.out.println(ans);
        int point = 0;
        for (int i = 1; i <= ans + 1; i++) {
            if (i * (i - 1) == ans * 2) {
                point = i;
                break;
            }
        }
        return point;
    }

    Double getSlope(int x1, int y1, int x2, int y2) {
        Double d1 = y2 - y1 - 0.0;
        Double d2 = x2 - x1 + 0.0;
        List<String> l = new ArrayList<>();
        if (d2 == 0) return 0.0;
        return d1 / d2;
    }

    public void generateWays(String input, String s, int i, int open, List<Integer> ways) {

        char c = input.charAt(i);
        if (c >= '0' && c <= '9') {

        }


    }

}

package leetcode_2020.problems;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;

public class Problems3 {

    @Test
    public void dietPlanPerformanceTest() {

        List<List<Integer>> pairs = Lists.newArrayList();
        pairs.add(Lists.newArrayList(0, 3));
        pairs.add(Lists.newArrayList(1, 2));
        pairs.add(Lists.newArrayList(0, 2));

//        int calories[] = {1, 2, 3, 4, 5};
        String words[] = {"cat", "bt", "hat", "tree"};

//        System.out.println(minKnightMoves(5, 5));
        System.out.println(smallestStringWithSwaps("dcab", pairs));

    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {

        HashMap<Integer, Integer> map = new HashMap<>();

        int len = pairs.size();

        for (List<Integer> pair : pairs) {
            int a = pair.get(0);
            int b = pair.get(1);
            map.put(a, b);
        }

        int sz = s.length();
        boolean[] visited = new boolean[sz];
        char[] st = new char[sz];

        int pt = 0;
        boolean flag = true;
        while (flag) {

            List<Integer> index = new LinkedList<>();
            List<Character> chart = new LinkedList<>();

            while (!Objects.isNull(map.get(pt)) && !visited[pt]) {
                index.add(pt);
                chart.add(s.charAt(pt));
                visited[pt] = true;
                pt = map.get(pt);
            }
            visited[pt] = true;
            index.add(pt);
            chart.add(s.charAt(pt));

            Collections.sort(index);
            Collections.sort(chart);

            int l = index.size();
            for (int i = 0; i < l; i++) {
                st[index.get(i)] = chart.get(i);
            }
            int i = 0;
            for (; i < sz; i++) {
                if (!visited[i]) {
                    pt = i;

                }
            }
            if (i == sz) {
                flag = false;
            }
        }
        return st.toString();
    }

    class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int minKnightMoves(int x, int y) {

        if (x == 0 && y == 0) return 0;

        x = x + 300;
        y = y + 300;

        int nx = 300;
        int ny = 300;

        boolean[][] visited = new boolean[601][601];

        Queue<Point> queue = new LinkedList<Point>();

        queue.offer(new Point(nx, ny));
        visited[nx][ny] = true;

        int[] xx = {1, 2, 2, 1, -1, -2, -2, -1};
        int[] yy = {2, 1, -1, -2, 2, 1, -1, -2};

        int steps = 0;

        while (!queue.isEmpty()) {

            int len = queue.size();

            while (len > 0) {

                Point point = queue.poll();
                int cx = point.x;
                int cy = point.y;

                for (int i = 0; i < 8; i++) {

                    int newx = cx + xx[i];
                    int newy = cy + yy[i];

                    if (!visited[newx][newy]) {
                        queue.offer(new Point(newx, newy));
                        visited[newx][newy] = true;
                    }
                }
                len--;
            }
            steps++;
            if (visited[x][y] == true) {
                break;
            }
        }
        return steps;
    }
}
package leetcode.contests;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Contests164 {


    @Test
    public void ContestsSolution() {

        String[] products = {"bags", "baggage", "banner", "box", "cloths"};

//        synonyms.add(Lists.newArrayList("happy", "joy"));
//        System.out.println(countServers(2));
        System.out.println(suggestedProducts(products, "bags"));


    }

    public int minTimeToVisitAllPoints(int[][] points) {
        int time = 0;
        int n = points.length;

        for (int i = 1; i < n; i++) {

            int cur[] = points[i];
            int prv[] = points[i - 1];

            time += Math.max(Math.abs(cur[0] - prv[0]), Math.abs(cur[1] - prv[1]));
        }
        return time;

    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {

        List<List<String>> ans = new ArrayList<>();

        int n = searchWord.length();
        int m = products.length;
        for (int i = 1; i <= n; i++) {
            String search = searchWord.substring(0, i);

            List<String> pr = new ArrayList<>();

            for (int j = 0; j < m; j++) {

                if (products[j].length() >= i && search.equals(products[j].substring(0, i))) {
                    pr.add(products[j]);
                }
            }
            Collections.sort(pr);
            int k = pr.size();
            List<String> pt = new ArrayList<>();
            for (int t = 0; t < 3 && t < k; t++) {
                pt.add(pr.get(t));
            }
            ans.add(pt);

        }

        return ans;
    }


    public int countServers(int[][] grid) {


        int m = grid.length;
        int n = grid[0].length;

        int row[] = new int[m + 1];
        int col[] = new int[n + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                row[i] += grid[i][j];
                col[j] += grid[i][j];

            }
        }
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {

                    if (row[i] > 1 || col[j] > 1) {
                        count++;
                    }
                }

            }
        }
        return count;
    }


}

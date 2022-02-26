package leetcode_problems.contests_2020.ContestsAC;

import org.junit.Test;

import java.util.*;

public class Contests164 {


    @Test
    public void ContestsSolution() {

        String[] products = {"bags", "baggage", "banner", "box", "cloths"};

//        synonyms.add(Lists.newArrayList("happy", "joy"));
//        System.out.println(countServers(2));
        int a[][] = {{0, 0, 1, 1, 0, 1, 0, 0, 1, 0}, {1, 1, 0, 1, 1, 0, 1, 1, 1, 0}, {1, 0, 1, 1, 1, 0, 0, 1, 1, 0}, {0, 1, 1, 0, 0, 0, 0, 1, 0, 1}, {0, 0, 0, 0, 0, 0, 1, 1, 1, 0}, {0, 1, 0, 1, 0, 1, 0, 1, 1, 1}, {1, 0, 1, 0, 1, 1, 0, 0, 0, 1}, {1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 1, 0, 1, 0, 1}, {1, 1, 1, 0, 1, 1, 0, 1, 1, 0}};
//        System.out.println(closedIsland(a));
//        System.out.println(suggestedProducts(products, "bags"));
        int[] nums = {3, 6, 5, 1, 8};
        System.out.println(maxSumDivThree(nums));


    }

    public static int longestVowelSubsequence(String s) {

        int n = s.length();
        HashMap<Character, Character> prvmap = new HashMap<>();
        prvmap.put('a', null);
        prvmap.put('e', 'a');
        prvmap.put('i', 'e');
        prvmap.put('o', 'i');
        prvmap.put('u', 'o');

        HashMap<Character, Integer> map = new HashMap<>();
        map.put('a', 0);
        map.put('e', 0);
        map.put('i', 0);
        map.put('o', 0);
        map.put('u', 0);


        for (int i = 0; i < n; i++) {

            if(s.charAt(i)=='a'){
                map.put('a', map.get('a')+1);
            }else{
                char x=s.charAt(i);
                if(map.get(prvmap.get(x))>0){
                    map.put(x,Math.max(map.get(x),map.get(prvmap.get(x)))+1);
                }
            }


        }

       return map.get('u');
    }

    public static int kDifference(List<Integer> arr, int k) {

        int count = 0;
        int n = arr.size();

        HashSet<Integer> map = new HashSet<>();

        for (int i = 0; i < n; i++) {

            int j = arr.get(i);
            map.add(j);
        }
        for (int i = 0; i < n; i++) {

            int j = arr.get(i);
            if (map.contains(j + k)) {
                count++;
            }
            if (map.contains(j - k)) {
                count++;
            }
            map.remove(j);
        }
        return count;
    }

    public int maxSumDivThree(int[] nums) {
        int n = nums.length;
        int count = 0;
        int count2 = 0;
        int oneMin = 0;
        int oneNextMin = 0;
        int twoMin = 0;
        int twoNextMin = 0;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (nums[i] % 3 == 1) {

                if (count == 0) {
                    oneMin = nums[i];
                    count++;
                } else if (count == 1) {
                    oneNextMin = Math.max(oneMin, nums[i]);
                    oneMin = Math.min(oneMin, nums[i]);
                    count++;
                } else if (count == 2) {
                    if (oneNextMin > nums[i]) {
                        if (oneMin >= nums[i]) {
                            oneNextMin = oneMin;
                            oneMin = nums[i];
                        } else {
                            oneNextMin = nums[i];
                        }
                    }
                }
            }

            if (nums[i] % 3 == 2) {
                if (count2 == 0) {
                    twoMin = nums[i];
                    count2++;
                } else if (count2 == 1) {
                    twoNextMin = Math.max(twoMin, nums[i]);
                    twoMin = Math.min(twoMin, nums[i]);
                    count2++;
                } else if (count2 == 2) {
                    if (twoNextMin > nums[i]) {
                        if (twoMin >= nums[i]) {
                            twoNextMin = twoMin;
                            twoMin = nums[i];
                        } else {
                            twoNextMin = nums[i];
                        }
                    }
                }
            }

        }
        int max = 0;
        if (sum % 3 == 0) {
            return sum;
        }
        if (sum % 3 == 1) {
            if (twoNextMin != 0) {
                max = sum - twoMin - twoNextMin;
            }
            return Math.max(sum - oneMin, max);
        }

        if (sum % 3 == 2) {

            if (oneNextMin != 0) {
                max = sum - oneMin - oneNextMin;
            }
            max = Math.max(max, sum - twoMin);

        }
        return max;
    }

    public int closedIsland(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        boolean[][] v = new boolean[m][n];

        int islands = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (v[i][j] == false && grid[i][j] == 0 && dfs(grid, v, i, j, m, n)) {
                    islands++;
                }
            }
        }
        return islands;
    }

    boolean dfs(int[][] grid, boolean[][] v, int i, int j, int m, int n) {

        if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
            if (grid[i][j] == 0) {
                v[i][j] = true;
                return false;
            }
        }
        if (i < 0 || i >= m || j < 0 || j >= n) return false;

        if (grid[i][j] == 1 || v[i][j]) return true;
        v[i][j] = true;

        int a[] = {0, 1, 0, -1, 0};
        boolean flag = true;

        for (int k = 0; k < 4; k++) {
            flag = dfs(grid, v, i + a[k], j + a[k + 1], m, n) && flag;
        }
        return flag;
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

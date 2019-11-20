package leetcode.problems1;


import org.junit.Test;

import java.util.*;

public class Distance {

    @Test
    public void maxDistanceProblem1162() {
        int grid[][] = {{1,0,7}, {2,0,6}, {3,4,5},{0,3,0}, {9,0,20}};
        int arr[]={1,5,7,8,5,3,4,2,1};
//        System.out.println(maxDistance(grid));
//        System.out.println(numPrimeArrangements(100));
        //System.out.println(getMaximumGold(grid));
        System.out.println(Longest_Arithmetic_Subsequence(arr,-2));
    }

    public int getMaximumGold(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int maxAns = 0;
        boolean visited[][] = new boolean[m][n];

        for (int r = 0; r < m; r++)
            for (int c = 0; c < n; c++) {
                if (grid[r][c] != 0) {

                    maxAns = Math.max(dfs(grid, r, c, visited), maxAns);

                }
            }
        return maxAns;
    }

    public int dfs(int[][] grid, int r, int c, boolean visited[][]) {
        int m = grid.length;
        int n = grid[0].length;
        if (r < 0 || r >= m || c < 0 || c >=n || grid[r][c] == 0 || visited[r][c] == true) {
            return 0;
        }

        visited[r][c] = true;

        int a = dfs(grid, r + 1, c, visited);
        int b = dfs(grid, r - 1, c, visited);
        int x = dfs(grid, r, c + 1, visited);
        int y = dfs(grid, r, c - 1, visited);

        visited[r][c] = false;

        int mx = Math.max(Math.max(x, y), Math.max(a, b));

        return grid[r][c] + mx;
    }


    private int maxDistance(int[][] grid) {
//        dfs
        Queue<int[]> queue = new LinkedList<>();
        int n = grid.length;
        int dis = 0;

        for (int r = 0; r < n; r++)
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1) {
                    queue.offer(new int[]{r, c, dis});
                }
            }
        if (queue.size() == n * n) return -1;
        int[] cur, dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};
        int nx, ny;
        while (!queue.isEmpty()) {

            int range = queue.size();

            for (int i = 0; i < range; i++) {
                cur = queue.poll();

                for (int t = 0; t < 4; t++) {
                    nx = cur[0] + dx[t];
                    ny = cur[1] + dy[t];
                    dis = cur[2];

                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] == 0) {
                        grid[nx][ny] = dis + 1;
                        queue.offer(new int[]{nx, ny, dis + 1});
                    }
                }
            }
        }
        return dis;

    }

    public int minCostToMoveChips(int[] chips) {
        int maj_index = 0, count = 1;
        int size = chips.length;
        for (int i = 1; i < size; i++) {
            if (chips[maj_index] == chips[i])
                count++;
            else
                count--;
            if (count == 0) {
                maj_index = i;
                count = 1;
            }
        }
        int val = chips[maj_index];
        int result = 0;
        for (int i = 0; i < size; i++) {

            if (chips[i] != val) {

                int diff = chips[i] - val;
                if (diff % 2 != 0) {
                    result++;
                }


            }

        }
        return result;

    }

    public int numPrimeArrangements(int n) {

        boolean p[] = new boolean[101];


        for (int i = 2; i < 101; i++) {
            if (!p[i]) {
                for (int j = i + i; j < 101; j = j + i) {
                    p[j] = true;
                }
            }
        }
        p[2] = false;
        int pcount = 0, rcount = 0;
        for (int i = 2; i <= n; i++) {
            if (!p[i]) {
                pcount++;
            }
        }
        rcount = n - pcount;
        long result = (factorial(pcount) % 1000000007) * (factorial(rcount) % 1000000007);

        return (int) (result % 1000000007);
    }

    long factorial(int n) {
        long rs = 1;
        for (int i = 1; i <= n; i++) {
            rs = (rs * i) % 1000000007;
        }
        return rs % (1000000007);
    }

    public int Longest_Arithmetic_Subsequence(int[] arr, int diff) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int l = arr.length;
        int ans = 0;
        for (int i = 0; i < l; i++) {

            int a = map.getOrDefault(arr[i]-diff, 0);

            map.put(arr[i], a + 1);

            ans = Math.max(a + 1, ans);
        }
        return ans;
    }
    //longest AP with Difference diff
    public int Longest_Arithmetic_Subsequence1(int[] arr,int diff){

        HashMap<Integer,Integer> hashMap=new HashMap<Integer, Integer>();
        HashMap<Integer,Integer>hashMap1=new HashMap<Integer, Integer>();
        for(int i=0;i<arr.length;i++){
            if(!hashMap.containsKey(arr[i]))
                    hashMap.put(arr[i],i);
            if(diff==0){
                if(hashMap1.containsKey(arr[i])){
                    int value=hashMap1.get(arr[i]);
                    hashMap1.put(arr[i],value+1);
                }
               else
                   hashMap1.put(arr[i],0);
            }
        }

        int count=0;
        int maxlen=0;
        int index=0;
        for(int i=0;i<arr.length;i++){

            int key=arr[i];
            count=0;
            index=i;
            if(diff!=0){
            while(hashMap.containsKey(key)&&hashMap.get(key)>=index) {
                count++;
                index = hashMap.get(key);
                key += diff;
                maxlen = Math.max(maxlen, count);
            }
            }
            else {
                    while(hashMap1.containsKey(key)&&hashMap1.get(key)>=index) {
                        count=hashMap1.get(key);
                        index = hashMap1.get(key);
                        key += diff;
                        maxlen = Math.max(maxlen, count);

                }
        }
        }
        return  maxlen;
    }
    public int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int a: arr) {
            if(map.containsKey(a)) {
                map.put(a + difference, map.get(a) + 1);
            } else {
                map.put(a + difference, 1);
            }
        }
        int max = 1; // because the minimum size subarray is 1.
        for(int v: map.values()) {
            max = Math.max(max, v);
        }
        return max;
    }
    private int maxDistanceOld(int[][] grid) {
        int n = grid.length;
        boolean row[] = new boolean[n];
        boolean col[] = new boolean[n];
        Queue<Integer> rowQueue = new PriorityQueue<>();
        Queue<Integer> colQueue = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    row[i] = col[j] = true;
                }
            }

            if (row[i]) {
                rowQueue.add(i);
            }
        }

        for (int j = 0; j < n; j++) {
            if (col[j]) {
                colQueue.add(j);
            }
        }

        int closestRowBefore = -1;
        int closestColBefore = -1;
        int closestRowAfter = -1;
        int closestColAfter = -1;
        int result = -1;
        if (rowQueue.size() == 0 || colQueue.size() == 0) {
            return -1;
        }

        for (int i = 0; i < n; i++) {
            if (row[i]) {
                closestRowBefore = i;
            }
            closestColBefore = -1;
            for (int j = 0; j < n; j++) {
                if (col[j]) {
                    closestColBefore = j;
                }
                if (grid[i][j] == 1) {
                    continue;
                }
                if (rowQueue.size() > 0) {
                    closestRowAfter = rowQueue.poll();
                }
                if (colQueue.size() > 0) {
                    closestColAfter = colQueue.poll();
                }

                int x1val = Math.abs(i - closestRowBefore);
                int y1val = Math.abs(j - closestColBefore);
                int cloValB = x1val + y1val;

                int x2val = Math.abs(closestRowAfter - i);
                int y2val = Math.abs(closestColAfter - j);
                int cloValA = x2val + y2val;
                result = Math.max(result, Math.max(cloValA, cloValB));


            }
        }


        return result;
    }
}
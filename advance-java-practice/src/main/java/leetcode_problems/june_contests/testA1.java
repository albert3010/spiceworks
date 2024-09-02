package leetcode_problems.june_contests;

import practice_lld.top25.lld.stock.entity.User;

import java.util.*;

public class testA1 {
    static Map<String, Integer> memo = new HashMap<>();
    static Set<Integer> visited = new HashSet<>();
//[[1,5,20,18],[19,6,17,3],[12,10,6,3],[1,20,12,15]]
    public static void main(String[] args) {
        List<List<Integer>> grid = new ArrayList<>();
        grid.add(List.of(1,5,20,18));
        grid.add(List.of(19,6,17,3));
        grid.add(List.of(12,10,6,3));
        grid.add(List.of(1,20,12,15));
        System.out.println("ans "+maxScore(grid));
        User user = new User(1, "", "");
        System.out.println(user.userId());

    }

    static public int maxScore(List<List<Integer>> grid) {

        return dfsHelper(0, grid, 0);

    }
    static int dfsHelper(int i, List<List<Integer>> grid, int sum){
        int n = grid.size();
        if(sum>=65){
            System.out.println(i+"..." + sum);
        }
        if(i==n) return sum;
        String key = i+"," + sum;
        if(memo.get(key) != null) return memo.get(key);

        int max = sum;

        for(int v : grid.get(i)){
            if(!visited.contains(v)){
                visited.add(v);
                max = Math.max( max, dfsHelper(i+1, grid, sum+v));
                visited.remove(v);
            }
        }
        memo.put(key, max);
        return max;
    }
}


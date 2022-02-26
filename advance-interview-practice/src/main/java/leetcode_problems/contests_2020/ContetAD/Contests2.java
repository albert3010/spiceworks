package leetcode_problems.contests_2020.ContetAD;

import leetcode_contest_2022.TreeNode;
import org.junit.Test;

import java.util.*;

public class Contests2 {

    @Test
    public void ContestsSolution() {
        int a[] = {1, 2, 3, 1, 1};
//        System.out.println(minDifficulty(a, 6));
//        System.out.println(shipWithinDays(a, 4));
    }

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> ans = new ArrayList<>();
        HashSet<Integer> map = new HashSet<>();

        for (int i = 0; i < to_delete.length; i++) {
            map.add(to_delete[i]);
        }
        if (root==null) return ans;
        if(delNodesHelper(root, ans, map)!=null){
            ans.add(root);
        }
        return ans;
    }

    private TreeNode delNodesHelper(TreeNode root, List<TreeNode> ans, HashSet<Integer> map) {

        if (root == null) return root;

        TreeNode l = delNodesHelper(root.left, ans, map);
        TreeNode r = delNodesHelper(root.right, ans, map);

        if (map.contains(root.val)) {
            if (l != null)
                ans.add(l);
            if (r != null)
                ans.add(r);
            return null;
        } else {
            root.left = l;
            root.right = r;
            return root;
        }
    }

    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        int l = recipes.length;
        Set<String> suppliesSet = new HashSet<>();
        HashMap<String, List<String>> recipesMap = new HashMap<>();

        for (int i = 0; i < supplies.length; i++) {
            suppliesSet.add(supplies[i]);
        }
        for (int i = 0; i < l; i++) {
            recipesMap.put(recipes[i], ingredients.get(i));
        }

        HashMap<String, Boolean> visited = new HashMap<>();

        HashMap<String, Boolean> cyclicMap = new HashMap<>();
        List<String> ans = new ArrayList<>();

        for (int i = 0; i < l; i++) {
            if (dfsHelper(recipes[i], recipesMap, suppliesSet, visited, cyclicMap)) {
                ans.add(recipes[i]);
            }
        }
        return ans;

    }

    private boolean dfsHelper(String recipe, HashMap<String, List<String>> recipesMap, Set<String> suppliesSet,
                              HashMap<String, Boolean> visited, HashMap<String, Boolean> cyclicMap) {
        if (suppliesSet.contains(recipe)) {
            return true;
        }
        if (cyclicMap.containsKey(recipe)) {
            return false;
        }
        if (visited.containsKey(recipe)) {
            return true;
        }
        visited.put(recipe, true);
        cyclicMap.put(recipe, true);
        boolean flag = true;
        if (recipesMap.get(recipe) == null) {
            flag = false;
        } else {
            for (String ing : recipesMap.get(recipe)) {
                flag = flag && dfsHelper(ing, recipesMap, suppliesSet, visited, cyclicMap);
            }
        }

        cyclicMap.remove(recipe);
        return flag;
    }

    public boolean find132pattern(int[] nums) {
        int l = nums.length;
        if (l < 3) return false;

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        int[] mins = new int[l];
        mins[0] = nums[0];
        for (int i = 1; i < l; i++) {
            mins[i] = Math.min(mins[i - 1], nums[i]);
        }
        queue.add(nums[l - 1]);

        for (int i = l - 2; i > 0; i--) {
            while (!queue.isEmpty() && queue.peek() <= mins[i - 1]) {
                queue.poll();
            }
            if (!queue.isEmpty() && nums[i] > queue.peek()) {
                return true;
            }
            queue.add(nums[i]);
        }

        return false;
    }


    public int shipWithinDays(int[] weights, int D) {
        int n = weights.length;
        int maxWeight = 500 * 50000;
        int minWeight = 1;

        int ansMinW = maxWeight;

        while (minWeight < maxWeight) {
            int midW = (minWeight + maxWeight) / 2;
            int daysRemaining = getDaysRemaining(weights, midW, D);

            if (daysRemaining >= 0) {
                ansMinW = Math.min(ansMinW, midW);
            }
            if (minWeight + 1 == maxWeight) {
                daysRemaining = getDaysRemaining(weights, maxWeight, D);
                if (daysRemaining >= 0) {
                    ansMinW = Math.min(ansMinW, maxWeight);
                }
            }
            if (daysRemaining >= 0) {
                maxWeight = midW - 1;
            } else {
                minWeight = midW + 1;
            }
        }
        return ansMinW;
    }

    public int getDaysRemaining(int[] weights, int midW, int D) {
        int n = weights.length;
        int days = 0;
        int wd = weights[0];
        if (weights[0] > midW) return -1;
        for (int i = 1; i < n; i++) {
            if (weights[i] > midW) return -1;
            wd += weights[i];
            if (wd > midW) {
                days++;
                wd = weights[i];
            }
        }
        if (wd <= midW) {
            days++;
        }
        return D - days;
    }

    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        int dp[][] = new int[n][d + 1];
        dp[0][1] = jobDifficulty[0];

        for (int day = 1; day <= d; day++) {
            for (int i = 0; i < n; i++) {
                if (day == 1 && i == 0) continue;
                dp[i][day] = Integer.MAX_VALUE;
                if (i + 1 < day) {
                    continue;
                }
                if (day == 1) {
                    dp[i][day] = Math.max(dp[i - 1][day], jobDifficulty[i]);
                }
                int max = 0;
                for (int k = i; k >= day - 1; k--) {
                    max = Math.max(max, jobDifficulty[k]);
                    dp[i][day] = Math.min(dp[i][day], dp[k - 1][day - 1] + max);
                }
            }
        }
        if (dp[n - 1][d] == Integer.MAX_VALUE) return -1;
        return dp[n - 1][d];
    }

}

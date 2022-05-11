package leetcode_contest_2022.home_contest_1;

import org.junit.Test;

import java.util.*;

public class HomeContest6 {

    @Test
    public void test() {
//        int aa[] = {1,2,3,4,2,3,1,-4,2};
//        System.out.println(medianSlidingWindow(aa, 4));
        int [][] grid = {{1,1,0,0,1},{1,1,0,1,1},{1,1,0,0,1},{1,0,1,1,0},{0,0,0,0,0}};
        Solution sl = new Solution();
        sl.largestIsland(grid);
    }
    class Node {
        int i;
        int val;
        Node(int i, int val){
            this.i=i;
            this.val=val;
        }

    }
    int lambda(Node a, Node b){
        if(a.val==b.val){
            return a.i - b.i;
        }
        return a.val - b.val;
    }
    public double[] medianSlidingWindow(int[] nums, int k) {
        List<Node> list = new ArrayList<>();
        int j=0;
        int l = nums.length;
        TreeSet<Node> maxSet = new TreeSet<>( (a, b) -> lambda(b, a));
        TreeSet<Node> minSet = new TreeSet<>(this::lambda);
        for(int val: nums){
            list.add(new Node(j++, val));
            if(maxSet.contains(list.get(j-1))){
                maxSet.add(list.get(j-1));
            }else {
                maxSet.add(list.get(j-1));
            }
        }

        double [] ans = new double [l-k+1];
        int left = k/2;
        int right = k-left;

        for(int i=0;i<k;i++){
            maxSet.add(list.get(i));
            minSet.add(list.get(i));
            if(maxSet.size()>left){
                maxSet.pollFirst();
            }
            if(minSet.size()>right){
                minSet.pollFirst();
            }
        }
        int t=0;
        for(int i=k;i<=l;i++){
            ans[t]= getMedian(maxSet, minSet);
            if(i==l) break;
            updateSets(maxSet, minSet, t, i, list);
            t++;
        }
        return ans;
    }
    double getMedian(TreeSet<Node> maxSet, TreeSet<Node> minSet){

        if(maxSet.size()>minSet.size()){
            return maxSet.first().val+0.0;
        }
        if(maxSet.size()<minSet.size()){
            return minSet.first().val+0.0;
        }
        return (maxSet.first().val+minSet.first().val)/2.0;
    }

    void updateSets(TreeSet<Node> maxSet, TreeSet<Node> minSet, int rm, int add, List<Node> list){
        maxSet.remove(list.get(rm));
        minSet.remove(list.get(rm));

        maxSet.add(list.get(add));

        Node nd = maxSet.pollFirst();
        minSet.add(nd);

        if(maxSet.size()>minSet.size()){
            minSet.add(maxSet.pollFirst());
        }else if(maxSet.size()<minSet.size()){
            maxSet.add(minSet.pollFirst());
        }
    }

    public int[] findOriginalArray(int[] changed) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>();
        int l = changed.length;
        for (int i = 0; i < l; i++) {
            int val = changed[i];
            queue.add(val);
            int v = map.getOrDefault(val, 0);
            map.put(changed[i], v + 1);
        }
        int[] ans = new int[0];
        if (l % 2 != 0) return ans;

        int ansW[] = new int[l / 2];
        int k = 0;
        while (!queue.isEmpty()) {
            int val = queue.poll();
            int dv = val * 2;
            if (map.get(val) > 0) {
                if (map.get(dv) != null && map.get(dv) > 0) {
                    ansW[k++] = val;
                    int x = map.get(dv);
                    map.put(dv, x - 1);
                    int y = map.get(val);
                    map.put(val, y - 1);
                } else {
                    return ans;
                }
            }
        }
        return ansW;
    }

    public int countKDifference(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    ans++;
                }
            }
        }
        return ans;
    }
}

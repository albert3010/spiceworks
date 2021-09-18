package leetcode_contest_2021.home_contest_1;

import java.util.Arrays;
import java.util.Scanner;

public class HomeContest3 {

    public static void main(String[] args) {
        int[] w = {28,8,49,85,37,90,20,8};
//        int[] r = {50, 100, 50, 50, 50};
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int[] water = new int[n];
//        int[] rate = new int[n];
//        for (int i = 0; i < n; i++) {
//            water[i] = scanner.nextInt();
//        }
//        for (int i = 0; i < n; i++) {
//            rate[i] = scanner.nextInt();
//        }
//        int[] ans = solve(water, rate);
//        [28,8,49,85,37,90,20,8]

//        print ans
        System.out.println(countQuadruplets(w));

    }

    public static int countQuadruplets(int[] nums) {
//        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                            if (nums[i] + nums[j] + nums[k] == nums[l]) {
                                ans++;
                        }
                    }
                }
            }

        }
        return ans;
    }

    static class Node implements Comparable<Node> {
        double finishTime;
        int index;

        public Node(double finishTime, int index) {
            this.finishTime = finishTime;
            this.index = index;
        }

        @Override
        public int compareTo(Node o) {
            if (o.finishTime == this.finishTime)
                return this.index - o.index;
            return o.finishTime - this.finishTime > 0 ? -1 : 1;
        }
    }

    public static int[] solve(int[] water, int[] leakRate) {
        int n = water.length;
        Node[] finishTime = new Node[n];

        for (int i = 0; i < n; i++) {
            finishTime[i] = new Node(water[i] / (leakRate[i] + 0.0), i);
        }
        Arrays.sort(finishTime);
        int i = 0;
        for (; i < n; i++) {
            if (finishTime[0] != finishTime[i]) {
                break;
            }
        }
        int ans[] = new int[i];
        for (int j = 0; j < i; j++) {
            ans[i] = finishTime[j].index;
        }
        return ans;
    }

}

package leetcode_problems.problems.Solutions1;

public class Solution2 {


    public static void main(String[] args) {
        int[] arv = {900, 940, 950, 1100, 1500, 1800};
        int[] dep = {910, 1200, 1120, 1130, 1900, 2000};

        System.out.println(solve(arv, dep));

    }

    static int solve(int[] arv, int[] dep) {
        int[] space = new int[2402];

        int l = arv.length;
        int max = 0;
        for (int i = 0; i < l; i++) {
            if (arv[i] == dep[i]) continue;

            space[arv[i]]++;
            if (dep[i] + 1 <=2400) {
                space[dep[i] + 1]--;
            }
        }
        int counter = 0;
        for (int i = 0; i <= 2400; i++) {
            counter += space[i];
            max = Math.max(counter, max);
        }
        return max;
    }

}

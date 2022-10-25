package leetcode_problems.june_contests.S1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Solution4 {
    @Test
    public void function() {
        List<Integer> energy = new ArrayList<>();
        energy.add(2);
        energy.add(1);
        energy.add(1);
        List<Integer> coins = new ArrayList<>();
        coins.add(11);
        coins.add(5);
        coins.add(7);


        System.out.println(getRich(0, energy, coins));
    }

    public static int getRich(long initialEnergy, List<Integer> energy, List<Integer> coins) {
        // Write your code here
        int max = 0;
        int[] maxG = new int[1];
        int house = 0;
        int l = energy.size();
        int[][] dpx = new int[2002][l];
        dfsHelper(initialEnergy, house, energy, coins, max, maxG, dpx);
        return maxG[0];
    }

    void JavaTest() {
        //        IntStream.range(0, 10).flatMap()
//        Stream<Color> stream = IntStream.range(1, 5).mapToObj(i -> getColor(i));
        IntStream.range(1, 5).anyMatch(i -> i % 2 == 0);
        IntStream.range(1, 5).allMatch(i -> i % 2 == 0);
        IntStream.range(1, 5).mapToObj(i -> i * i);
        IntStream.iterate(0, i -> i + 2).limit(3);
        IntStream.range(1, 5)
                .filter(i -> i % 2 == 0)
                .allMatch(i -> i % 2 == 0);
        // > true
        IntStream.range(1, 5).max().getAsInt();
        List<Integer> ans = new ArrayList<>();
        IntStream.range(1, 5).reduce(1, (x, y) -> x * y);
        // > 24
        int[] intArray = IntStream.of(1, 2, 3, 4, 5).toArray();
        int[] a = new int[10];
        int[] intArray2 = IntStream.range(1, 10).map(ans::get).toArray();
    }

    public static void dfsHelper(long initialEnergy, int house, List<Integer> energy, List<Integer> coins, int max, int[] maxG, int[][] dpx) {
        maxG[0] = Math.max(maxG[0], max);


        if (house == energy.size() || initialEnergy < 0) return;
        int e = 2001;
        if (initialEnergy <= 2000) {
            e = (int) initialEnergy;
        } else {
            for (int i = house; i < energy.size(); i++) {
                maxG[0] = Math.max(maxG[0], max + coins.get(house));
            }
            return;
        }
        if (dpx[e][house] > 0) return;
        dpx[e][house] = maxG[0];
        dfsHelper(initialEnergy + energy.get(house) - 1, house + 1, energy, coins, max, maxG, dpx);
        dfsHelper(initialEnergy - 1, house + 1, energy, coins, max + coins.get(house), maxG, dpx);

    }

    public static List<String> threePalindromicSubstrings(String word) {
        // Write your code here
        int l = word.length();
        boolean[][] dp = computeAllPalindromic(word);
        List<String> ans = new ArrayList<>();

        for (int i = 0; i < l; i++) {
            for (int j = i + 1; j < l - 1; j++) {
                if (dp[0][i] && dp[i + 1][j] && dp[j + 1][l - 1]) {
                    ans.add(word.substring(0, i + 1));
                    ans.add(word.substring(i + 1, j + 1));
                    ans.add(word.substring(j + 1, l));
                }
            }

        }
        if (ans.size() == 0) {
            ans.add("Impossible");
            return ans;
        }
        return ans;

    }

    public static boolean[][] computeAllPalindromic(String word) {
        int l = word.length();
        boolean[][] dp = new boolean[l][l];

        for (int i = 0; i < l; i++) {
            dp[i][i] = true;
            if (i + 1 < l && word.charAt(i) == word.charAt(i + 1)) {
                dp[i][i + 1] = true;
            }
        }
        for (int gap = 2; gap < l; gap++) {
            for (int i = 0; i < l - gap; i++) {
                int j = gap + i;
                if (word.charAt(i) == word.charAt(j) && dp[i + 1][j - 1])
                    dp[i][j] = true;
            }
        }
        return dp;
    }
}

//        19999
//3
//12
//2
//2
//3
//5
//5
//5
//        ans 15
//        1
//5
//1
//5
//3
//3
//1
//5
//3
//23
//9
//2
//2
//        ans 32

// 0
//3
//2
//1
//1
//3
//11
//5
//7
// ans 12

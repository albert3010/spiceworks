package leetcode.contests.ContetsAE;

import com.google.common.base.Strings;
import org.junit.Test;

import java.util.*;

public class Contests9 {
    static int dp1[];

    //8531703
    @Test
    public void ContestsSolution() {
        updatePrimes();
        System.out.println(primeDigitSums(6652));
    }

    public static Set<Integer> getPrimes() {
        //        2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43
        return new HashSet<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43));

    }

    public static List<List<Integer>> get3DigitPrimes(Set<Integer> primes) {
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k <= 9; k++) {
                    int sum = i + j + k;
                    if (primes.contains(sum)) {
                        lists.add(Arrays.asList(i, j, k));
                    }
                }
            }
        }
        return lists;
    }

    public static List<List<Integer>> updateTo4DigitPrimes(List<List<Integer>> list, Set<Integer> primes) {
        List<List<Integer>> lists = new ArrayList<>();
        list.forEach(e -> {
            int sum = e.stream().mapToInt(i -> i).sum();
            for (int i = 0; i <= 9; i++) {
                int v = e.get(0);
                if (primes.contains(sum + i) && primes.contains(sum + i - v)) {
                    List<Integer> ls = new ArrayList<>();
                    e.forEach(k -> ls.add(k));
                    ls.add(i);
                    lists.add(ls);
                }
            }
        });
        return lists;
    }

    public static List<List<Integer>> updateTo5DigitPrimes(List<List<Integer>> list, Set<Integer> primes) {
        List<List<Integer>> lists = new ArrayList<>();
        list.stream().forEach(e -> {
            int sum = e.stream().mapToInt(i -> i).sum();
            for (int i = 0; i <= 9; i++) {
                int v = e.get(0);
                int v1 = e.get(1);

                if (primes.contains(sum + i) && primes.contains(sum + i - v) && primes.contains(sum + i - v - v1)) {
                    List<Integer> ls = new ArrayList<>();
                    e.forEach(k -> ls.add(k));
                    ls.add(i);
                    lists.add(ls);
                }
            }
        });
        return lists;
    }

    public static String getString(List<Integer> ls) {
        StringBuilder s = new StringBuilder();
        ls.forEach(i -> {
            s.append(i.toString());
        });
        return s.toString();
    }

    public static List<List<Integer>> get5DigitPrimesHash(List<List<Integer>> list) {
        List<List<Integer>> lists = new ArrayList<>();
        list.forEach(e -> {
            String key = getString(e);
            String sr = "1" + key.substring(1);
            String sl = "1" + key.substring(0, 4);

            lists.add(Arrays.asList(Integer.parseInt(sr), Integer.parseInt(sl)));
        });
        return lists;
    }

    public static List<List<Integer>> removeZero(List<List<Integer>> list) {
        List<List<Integer>> lists = new ArrayList<>();
        list.forEach(e -> {
            if (e.get(0) != 0) {
                lists.add(e);
            }
        });
        return lists;
    }

    public static void updatePrimeHash(List<List<Integer>> list, int[] primeHash, HashMap<Integer, Integer> valToIndex) {
        int n = list.size();
        for (int i = 0; i < n; i++) {
            List<Integer> e = list.get(i);
            String s = "1" + getString(e).substring(1);
            primeHash[valToIndex.get(Integer.parseInt(s))]++;
        }
    }

    public static HashMap<Integer, Integer> updatePrimeHash4(List<List<Integer>> list) {
        int n = list.size();
        int ct = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            List<Integer> e = list.get(i);
            String s = "1" + getString(e);
            set.add(Integer.parseInt(s));
        }
        HashMap<Integer, Integer> pmapVtoI = new HashMap<>();
        for (Integer a : set) {
            pmapVtoI.put(a, ct);
            ct++;
        }
        return pmapVtoI;
    }

    static void updatePrimes() {
        int n = 200001;
        int mod = 1000000007;
        int dp[] = new int[n];
        int[] primeHash = new int[416];
        Set<Integer> primes = getPrimes();
        List<List<Integer>> lists3 = get3DigitPrimes(primes);
        List<List<Integer>> lists4 = updateTo4DigitPrimes(lists3, primes);
        List<List<Integer>> lists5 = updateTo5DigitPrimes(lists4, primes);
        List<List<Integer>> primeSet = get5DigitPrimesHash(lists5);
        HashMap<Integer, Integer> valToIndex = updatePrimeHash4(lists4);
        lists5 = removeZero(lists5);
        dp[1] = 9;
        dp[2] = 90;
        dp[3] = removeZero(lists3).size();
        dp[4] = removeZero(lists4).size();
        dp[5] = lists5.size();
        updatePrimeHash(lists5, primeHash, valToIndex);
        int lk = primeSet.size();
        for (int i = 6; i < n; i++) {
            int[] primeHash2 = new int[416];
            long sum = 0;
            for (int k = 0; k < lk; k++) {
                // a b c d e

                Integer sr = primeSet.get(k).get(0);
                Integer sl = primeSet.get(k).get(1);
                int cnt = primeHash[valToIndex.get(sl)];
                sum = (sum + cnt) % mod;
                primeHash2[valToIndex.get(sr)] = (primeHash2[valToIndex.get(sr)] + cnt) % mod;
            }
            primeHash = primeHash2;
            dp[i] = (int) (sum % mod);
        }
        dp1 = dp;
    }

    static int primeDigitSums(int n) {
        return dp1[n];
    }
}

package MovieBooking;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Test {

    public static void main(String[] args) {
        System.out.println("test 1");
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
//        System.out.println(isItPossible("aa", "ab"));
        int[] piles = {312884420};
        System.out.println(minEatingSpeed(piles, 312884419));
    }

    public static int minEatingSpeed(int[] piles, int h) {

        int n = piles.length;
        int max = Arrays.stream(piles).max().getAsInt();
        int low = 1;
        int high = max;
        int ans = max;
        while (low <= high) {
            int k = low + (high - low) / 2;
            if (canEat(piles, k, h)) {
                ans = Math.min(k, ans);
                high = k - 1;
            } else {
                low = k + 1;
            }
        }
        return ans;
    }

    private static boolean canEat(int[] piles, int k, int h) {
        int n = piles.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        Arrays.stream(piles).forEach(e -> pq.add(e));

        while (!pq.isEmpty() && h > 0) {
            Integer val = pq.poll();
            if (k < val) {
                int t = val / k;
                int rem = val % k;
                if (rem > 0) pq.add(rem);
                h = h - t;
            } else {
                h--;
            }
        }
        if (pq.isEmpty()) return true;
        return false;

    }

    public static boolean isItPossible(String word1, String word2) {

        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        for (char c : word1.toCharArray()) {
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }
        for (char c : word2.toCharArray()) {
            map2.put(c, map2.getOrDefault(c, 0) + 1);
        }
        int s1 = map1.size();
        int s2 = map2.size();
        // if(s1==s2) return true;

        for (Character c1 : map1.keySet()) {
            for (Character c2 : map2.keySet()) {
                int count1 = s1;

                if (c1.equals(c2)) {
                    if (s1 == s2) return true;
                }
                if (map1.get(c1) == 1) {
                    count1--;
                }
                if (map1.get(c2) == null) {
                    count1++;
                }

                int count2 = s2;
                if (map2.get(c2) == 1) {
                    count2--;
                }
                if (map2.get(c1) == null) {
                    count2++;
                }
                if (count1 == count2) return true;
            }

        }
        return false;
    }

}

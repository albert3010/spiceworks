package leetcode_contest_2021.contest1;

import org.junit.Test;

import java.util.*;

public class test2 {


    @Test
    public void test() {
        int[][] a = {
                {4, 5, 8, 9, 4, 2, 4, 7, 2, 4},
                {7, 1, 9, 6, 6, 1, 4, 5, 7, 7},
                {7, 1, 5, 1, 1, 7, 10, 1, 3, 1},
                {7, 2, 2, 5, 2, 6, 6, 4, 7, 7},
                {1, 2, 3, 8, 4, 7, 6, 9, 6, 2},
                {5, 10, 3, 4, 7, 2, 7, 5, 3, 10}
        };
        int[] arr = {3, 7, 15};

//        System.out.println(addRungs(arr, 2));
//        System.out.println(canBeTypedWords("hello world", "ad"));

//        System.out.println(droppedRequests(Arrays.asList(1,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,7,11,11,11,11)));
//        System.out.println(droppedRequests(Arrays.asList(7,7,7,7,11,11,11,11,20,20,21,25,60)));
        System.out.println(getLucky("ab", 1));
    }

    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        int m = students.length;
        int n = students[0].length;
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            list.add(new ArrayList<>());
            for (int j = 0; j < m; j++) {
                int score = 0;
                for (int k = 0; k < n; k++) {
                    if (students[i][k] == mentors[j][k]) {
                        score++;
                    }
                }
                list.get(0).add(score);
            }
        }

return 1;
    }

    public String maximumNumber(String num, int[] change) {
        int l = num.length();
        int start = -1;
        for (int i = 0; i < l; i++) {
            int t = num.charAt(i) - 48;
            int t1 = change[t];
            if (t1 > t) {
                start = i;
                break;
            }
        }
        if (start == -1) return num;
        StringBuilder ss = new StringBuilder();
        ss.append(num, 0, start);

        for (int i = start; i < l; i++) {
            int t = num.charAt(i) - 48;
            int t1 = change[t];
            if (t1 >= t) {
                ss.append(t1);
            } else {
                ss.append(num, i, num.length());
                break;
            }
        }
        return ss.toString();
    }

    public int getLucky(String s, int k) {
        int l = s.length();
        StringBuilder tmp = new StringBuilder();

        for (int i = 0; i < l; i++) {
            tmp.append(s.charAt(i) - 'a' + 1);
        }
        int ans = 0;
        while (k > 0) {
            int sum = 0;
            int x = tmp.length();
            for (int i = 0; i < x; i++) {
                int val = tmp.charAt(i) - 48;
                sum += val;
            }
            ans = sum;
            tmp = new StringBuilder(String.valueOf(sum));
            k--;
        }
        return ans;
    }

    public static int droppedRequests(List<Integer> requestTime) {
        Deque<Integer> window20 = new ArrayDeque<>();
        Deque<Integer> window60 = new ArrayDeque<>();
        HashMap<Integer, Integer> window1 = new HashMap<>();
        int dropped = 0;

        for (Integer reqTime : requestTime) {
            window1.put(reqTime, window1.getOrDefault(reqTime, 0) + 1);
            window20.addFirst(reqTime);
            window60.addFirst(reqTime);

            while (!window20.isEmpty()) {
                int last = window20.getLast();
                int first = window20.getFirst();
                int diff = first - last + 1;

                if (diff > 20) {
                    window20.pollLast();
                } else {
                    break;
                }
            }
            while (!window60.isEmpty()) {
                int last = window60.getLast();
                int first = window60.getFirst();
                int diff = first - last + 1;

                if (diff > 60) {
                    window60.pollLast();
                } else {
                    break;
                }
            }
            if (window1.get(reqTime) > 3 || window20.size() > 20 || window60.size() > 60) {
                dropped++;
                window20.pollFirst();
                window60.pollFirst();
            }
        }
        return dropped;
    }

    public int addRungs(int[] rungs, int dist) {
        int n = rungs.length;
        int currentPos = 0;
        int minRungs = 0;
        int maxReach = 0;
        for (int i = 0; i < n; ) {
            maxReach = currentPos + dist;

            if (rungs[i] <= maxReach) {
                currentPos = rungs[i++];
            } else {
                int diff = rungs[i] - currentPos;
                minRungs += (diff / dist);
                if (diff % dist == 0) {
                    minRungs--;
                }
                currentPos = rungs[i++];
            }

        }
        return minRungs;
    }

    public int canBeTypedWords(String text, String brokenLetters) {

        int bl = brokenLetters.length();
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < bl; i++) {
            set.add(brokenLetters.charAt(i));
        }
        String[] list = text.split(" ");
        int n = list.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int t = list[i].length();
            boolean f = true;
            for (int j = 0; j < t; j++) {
                if (set.contains(list[i].charAt(j))) {
                    f = false;
                    break;
                }
            }
            if (f) {
                count++;
            }
        }
        return count;
    }
}

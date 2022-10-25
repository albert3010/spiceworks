package leetcode_problems.problems.solutions;


import org.junit.Test;

import java.util.*;

public class Solutions4 {

    @Test
    public void Tests() {
        System.out.println(longestDecomposition("abcxyztxyzabc"));

    }

    public List<List<Integer>> findSolution(int z) {
        int start = 1;
        List<List<Integer>> ans = new ArrayList<>();

        for (int n = start; n < 100; n++) {

            int x = n / 10;
            int y = n % 10;

            if (x != 0 && y != 0 && x + y == z) {
                ans.add(Arrays.asList(x, y));
            }
        }
        return ans;
    }

    public int longestDecomposition(String text) {
        Deque<Character> left = new LinkedList<>();
        Deque<Character> right = new LinkedList<>();
        int i = 0;
        int j = text.length() - 1;
        int maxCount = 0;
        while (i < j) {
            char c = text.charAt(i);
            char ch = text.charAt(j);
            left.addLast(c);
            right.addFirst(ch);

            if (checkEqual(left, right)) {
                maxCount += 2;
                left.clear();
                right.clear();
            }
            i++;
            j--;
        }
        if (!left.isEmpty() || i == j) {
            maxCount++;
        }
        return maxCount;

    }

    boolean checkEqual(Deque<Character> left, Deque<Character> right) {
        boolean flag = true;
        int n = left.size();
        while (n-- > 0) {
            char lc = left.poll();
            char rc = right.poll();
            if (lc != rc) {
                flag = false;
            }
            left.add(lc);
            right.add(rc);
        }
        return flag;

    }
}
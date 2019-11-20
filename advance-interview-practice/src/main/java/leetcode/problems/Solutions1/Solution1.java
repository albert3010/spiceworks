package leetcode.problems.Solutions1;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;

public class Solution1 {


    @Test
    public void Tests() {
//        upper = 2, lower = 1, colsum = [1,1,1]
        int[] a = {2, 1, 2, 0, 1, 0, 1, 2, 0, 1};
//        System.out.println(reconstructMatrix(5, 5, a));
//        System.out.println(canReach(1,4,5,9));
//        System.out.println(compareStrings("one", "two", "three"));
//        System.out.println(compressWord("abbcccb", 1));
//        System.out.println(remove_k_characters("abbcccb", 7, 3));


        List<List<String>> regions = Lists.newArrayList();
        regions.add(Lists.newArrayList("Earth","North America","South America"));
        regions.add(Lists.newArrayList("North America","United States","Canada"));
        regions.add(Lists.newArrayList("United States","New York","Boston"));
        regions.add(Lists.newArrayList("Canada","Ontario","Quebec"));
        regions.add(Lists.newArrayList("South America","Brazil"));

        System.out.println(findSmallestRegion(regions, "Quebec", "New York"));
    }

    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {

        HashMap<String, String> revMap = new HashMap<>();
        int n = regions.size();

        for (int i = 0; i < n; i++) {
            List<String> rg = regions.get(i);

            int t = rg.size();
            for (int j = 1; j < t; j++) {
                revMap.put(rg.get(j), rg.get(0));
            }
        }

        List<String> r1 = new ArrayList<>();
        List<String> r2 = new ArrayList<>();
        String node = region1;

        while (node != null) {
            r1.add(node);
            node = revMap.get(node);
        }

        node = region2;

        while (node != null) {
            r2.add(node);
            node = revMap.get(node);
        }

        int x1 = r1.size() - 1;
        int x2 = r2.size() - 1;
        int ans = 0;
        while (x1 >= 0 && x2 >= 0) {

            if (r1.get(x1).equals(r2.get(x2))) {
                ans = x1;
            }
            x1--;
            x2--;
        }
        return r1.get(ans);
    }


    static class Node {
        char c;
        int f;

        Node(char c, int f) {
            this.c = c;
            this.f = f;
        }
    }

    public static String compressWord(String word, int K) {
        Stack<Node> st = new Stack<>();

        int n = word.length();
        int i = 0;
        if (n <= 1) {
            return word;
        }

        while (i < n) {

            char cr = word.charAt(i);

            if (!st.isEmpty() && st.peek().f == K) {
                char cc = st.peek().c;
                while (!st.isEmpty() && cc == st.peek().c) {
                    st.pop();
                }
            }
            if (!st.isEmpty() && st.peek().c == cr) {
                st.push(new Node(cr, st.peek().f + 1));
            } else {
                st.push(new Node(cr, 1));
            }
            i++;
        }
        if (!st.isEmpty() && st.peek().f == K) {
            char cc = st.peek().c;
            while (!st.isEmpty() && cc == st.peek().c) {
                st.pop();
            }
        }

        StringBuilder res = new StringBuilder();
        StringBuilder ans = new StringBuilder();
        while (!st.isEmpty()) {
            res.append(st.pop().c);
        }
        int l = res.length();
        for (int j = l - 1; j >= 0; j--) {
            ans.append(res.charAt(j));
        }
        return ans.toString();
    }

    static String remove_k_characters(String st1, int n, int k) {

        // Stack to store the character
        Stack<Node> st = new Stack<Node>();

        int i = 0;
        for (i = 0; i < n; i++) {


            char x = st1.charAt(i);

            if (st.size() > 0 && st.peek().f == k) {

                char curr = st.peek().c;

                while (st.size() > 0 && st.peek().c == curr)
                    st.pop();
            }

            if (st.size() > 0 && st.peek().c == x) {

                Node new_top = new Node(x, st.peek().f + 1);
                st.push(new_top);
            } else {
                Node new_top = new Node(x, 1);
                st.push(new_top);
            }
        }
        if (st.size() > 0 && st.peek().f == k) {
            char curr = st.peek().c;

            while (st.size() > 0 && st.peek().c == curr)
                st.pop();
        }
        String stack_string = "";
        while (st.size() > 0)
            stack_string += st.pop().c;

        String reduced_string = "";

        for (i = stack_string.length() - 1; i >= 0; i--)
            reduced_string += stack_string.charAt(i);

        return reduced_string;
    }

    public static String compareStrings(String firstString, String secondString, String thirdString) {
        // Write your code here
        String result = "";
        List<String> strs = new ArrayList<>();
        strs.add(firstString);
        strs.add(secondString);
        strs.add(thirdString);

        Collections.sort(strs);
        for (String s : strs) {
            result += s;
        }
        System.out.println(result);
        return "";
    }

    boolean canReach(int x1, int y1, int x2, int y2) {

        if (x2 < x1 || y2 < y1) {
            return false;
        }
        if (x1 == x2 && y1 == y2) {
            return true;
        }

        boolean[][] a = new boolean[2001][2001];

        return helper(x1, y1, x2, y2, a);

    }

    boolean helper(int x1, int y1, int x2, int y2, boolean[][] a) {

        if (x1 > x2 || y1 > y2) return false;
        if (a[x2][y2]) return true;
        if (a[x1][y1]) {
            return a[x2][y2];
        }

        a[x1][y1] = true;

        return helper(x1 + y1, y1, x2, y2, a)
            || helper(x1, x1 + y1, x2, y2, a);
    }

}

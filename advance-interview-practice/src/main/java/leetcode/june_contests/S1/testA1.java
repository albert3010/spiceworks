package leetcode.june_contests.S1;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

public class testA1 {
    @Test
    public void function(){
//        S.next(100) is called and returns 1,
        StockSpanner stockSpanner = new StockSpanner();
//        System.out.println(stockSpanner.next(100));
//        System.out.println(stockSpanner.next(80));
//        System.out.println(stockSpanner.next(60));
//        System.out.println(stockSpanner.next(70));
//        System.out.println(stockSpanner.next(60));
//        System.out.println(stockSpanner.next(75));
//        System.out.println(stockSpanner.next(85));
        System.out.println(maxVowels("aeiou", 2));
    }
    public int maxVowels(String s, int k) {
        Set<Character> set = new HashSet<>();
        HashMap<Character, Integer> count = new HashMap<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');

        int l = s.length();
        int ans =0;
        for(int i=1; i<=l ;i++){
            char c= s.charAt(i-1);
            if(set.contains(c)){
                count.put(c, count.getOrDefault(c, 0)+1);
            }

            if(i>=k){
                int max =0;
                for(Character cc: set){
                    max += count.getOrDefault(cc, 0);
                }
                ans = Math.max(max, ans);
                char ct = s.charAt(i-k);
                if(set.contains(ct)){
                    int t =count.getOrDefault(ct, 0);
                    if(t>0){
                        count.put(ct, t-1);
                    }
                }
            }


        }
        return ans;

    }
}


class StockSpanner {
    class Node {
        int val;
        int index;

        Node(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }

    Stack<Node> stack;
    int day;

    public StockSpanner() {
        stack = new Stack<>();
        day = 0;
    }

    public int next(int price) {



        int count = 0;
        day++;
        if (stack.isEmpty()) {
            stack.add(new Node(price, day));
            count = 1;
        } else {

            if (stack.peek().val <= price) {
                int x1 = stack.peek().index;
                count += day - x1;
                int p1 = x1;
                while (!stack.isEmpty() && stack.peek().val <= price) {
                    stack.pop();
                    int p2 = stack.isEmpty() ? 0 : stack.peek().index;
                    count += p1 - p2;
                    p1 = p2;
                }

                stack.add(new Node(price, day));
            } else {
                stack.add(new Node(price, day));
                count = 1;
            }

        }
        return count;
    }
}

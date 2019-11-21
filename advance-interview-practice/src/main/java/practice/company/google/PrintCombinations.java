package practice.company.google;

import org.junit.Test;

import java.util.Stack;

/**
 * Created by oyadav on 12/13/16.
 */
//# take an array and print non over lapping in order pairs. example:
//
//
//        # [1,2,3,4] => input
//
//        # output below is in order combination
//
//        # (1234)
//        # (1)(234)
//        # (1)(23)(4)
//        # (1)(2)(34)
//        # (12)(34)
//        # (12)(3)(4)
//        # (123)(4)
//        # (1)(2)(3)(4)
public class PrintCombinations {

    @Test
    public void printAllCombinations() {


        String given = "1234";

        Stack<Character> stack = new Stack<>();

        printAllCombinationsUtil(given, 0, stack);

    }

    private void printAllCombinationsUtil(String s, int index, Stack<Character> stack) {

        if (s.length() == index) {
            for (int i = 0; i < stack.size(); i++) {
                System.out.print(stack.get(i));

            }
            System.out.println();
        }
        stack.push('(');
        for (int i = index; i < s.length(); i++) {
            int t = index;
            int r = t;

            while (t <= i)
                stack.push(s.charAt(t++));
            stack.push(')');
            printAllCombinationsUtil(s, i + 1, stack);
            stack.pop();
            while (r <= i) {
                stack.pop();
                r++;
            }
        }
        stack.pop();

    }
}

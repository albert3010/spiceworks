package leetcode.contests.ContestAA;

import com.google.common.base.Strings;
import org.junit.Test;

import java.util.*;

public class Contests1 {


    @Test
    public void ContestsSolution() {
        System.out.println(removeInvalidParentheses("()())()"));
    }

    public int findSpecialInteger(int[] arr) {
        int n = arr.length;

        int preVal = arr[0];
        int preFq = 1;
        int currVal = arr[0];
        int currFq = 1;

        for (int i = 1; i < n; i++) {
            if (arr[i] != arr[i - 1]) {
                currVal = arr[i];
                currFq = 1;
            }
            if (arr[i] == arr[i - 1]) {
                currFq++;
            }
            if (currFq > preFq) {
                preFq = currFq;
                preVal = currVal;
            }
        }
        return preVal;
    }


    public List<String> removeInvalidParentheses(String s) {
        Set<String> results = new HashSet<>();
        backtrackingHelper(s, 0, "", 0, 0, results);
        List<String> aList = new ArrayList<>();
        aList.addAll(results);
        return aList;
    }

    public void backtrackingHelper(String s, int index, String ans, int leftCount, int rightCount, Set<String> results) {
        if (leftCount < rightCount) {
            return;
        }
        if (index == s.length()) {
            if (leftCount == rightCount) {
                if (results.size() == 0) {
                    results.add(ans);
                } else {
                    int l = 0;
                    for (String ss : results) {
                        l = ss.length();
                        break;
                    }
                    if (ans.length() > l) {
                        results.clear();
                    }
                    if (ans.length() >= l) {
                        results.add(ans);
                    }
                }
            }
            return;
        }

        char c = s.charAt(index);
        int l = 0, r = 0;
        if (c == '(') {
            l = 1;
        } else if (c == ')') {
            r = 1;
        }
        if (l == 1 || r == 1) {
            backtrackingHelper(s, index + 1, ans, leftCount, rightCount, results);
        }
        backtrackingHelper(s, index + 1, ans + c, leftCount + l, rightCount + r, results);
    }

}

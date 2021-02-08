package leetcode_2020.problems;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;

public class Problems4 {

    @Test
    public void dietPlanPerformanceTest() {

        List<Integer> pairs = Lists.newArrayList();

        String words[] = {"cat", "bt", "hat", "tree"};
        int a[] = {8, 2, 3, 6, 1, 25, 3};
        String s = "abcde";

//        System.out.println(nextGreater(a));
        System.out.println(reverseStr("abcdefg", 2));

    }

    public String reverseStr(String s, int k) {

        char[] a = s.toCharArray();

        int start = 0;

        int l = s.length();
        int end = 0;

        while (start <l-1) {

            int st = start;
            int mid = start + k - 1;
                end = start + 2 * k - 1;

            if (mid >= l) {
                mid = l - 1;
            }
            while (st < mid) {
                char c = a[mid];
                a[mid--] = a[st];
                a[st++] = c;
            }
            start = end+1;
        }
        String r = "";
        int i=0;
        while (i<l){
            r+=a[i++];
        }
        return r;
    }

    public String reverseStr1(String s, int k) {


        int start = 0;

        int l = s.length();
        int end = 0;
        String result = "";
        while (start < l) {

            end = start + 2 * k - 1;

            if (start < l) {
                result = reverse(s, start, k, result);
                start = end + 1;
            }

        }
        return result;

    }

    public String reverse(String s, int start, int k, String result) {

        int l = s.length();
        int end = start + k - 1;
        String sx = "";
        int endx = (start + 2 * k) < l ? (start + 2 * k) : l;
        if (end >= l) {
            end = l - 1;
        } else {
            sx = s.substring(end, endx);
        }

        while (start <= end) {
            result += s.charAt(end--);
        }
        return result + sx;
    }

    public Stack<Integer> nextGreater(int a[]) {
        Stack<Integer> s = new Stack<>();
        Stack<Integer> result = new Stack<>();
        int l = a.length;
        for (int i = l - 1; i >= 0; i--) {

            if (i == l - 1) {
                s.push(a[i]);
                result.push(-1);
            } else {

                if (a[i] < s.peek()) {
                    result.push(s.peek());
                    s.push(a[i]);
                } else {

                    while (!s.empty() && s.peek() <= a[i]) {
                        s.pop();
                    }
                    if (s.empty()) {
                        result.push(-1);
                    } else {
                        result.push(s.peek());
                    }
                    s.push(a[i]);

                }
            }
        }

        return result;
    }

    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {

        int l1 = arr1.length;
        int l2 = arr2.length;
        int l3 = arr3.length;

        List<Integer> results = new ArrayList<>();

        int i = 0, j = 0, k = 0;

        while (i < l1 && j < l2 && k < l3) {

            if (arr1[i] == arr2[j] && arr2[j] == arr3[k]) {
                results.add(arr1[i]);
            }
            // Move index
            int minVal = Math.min(Math.min(arr1[i], arr1[j]), arr1[k]);

            if (minVal == arr1[i]) {
                i++;
            }
            if (minVal == arr1[j]) {
                j++;
            }
            if (minVal == arr1[k]) {
                k++;
            }


        }
        return results;

    }
}
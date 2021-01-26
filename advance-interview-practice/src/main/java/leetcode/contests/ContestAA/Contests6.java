package leetcode.contests.ContestAA;

import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Contests6 {

    @Test
    public void ContestsSolution() {
//        System.out.println(distributeCandies(36, 4));
        System.out.println(flipLights(4, 2));
    }

    public int flipLights(int n, int m) {

        char a[] = new char[n];
        int ans = 0;
        StringBuilder s = new StringBuilder("");
        for (int i = 0; i < n; i++) {
            a[i] = '1';
            s.append('1');
        }
        int k = 0;
        Set<String> set = new LinkedHashSet<>();
        Queue<StringBuilder> queue = new LinkedList();

        queue.offer(s);
        set.add(s.toString());

        while ( k < m) {
            int l = queue.size();
            set.clear();
            ans=0;
            while (--l >= 0) {
                StringBuilder t = ((LinkedList<StringBuilder>) queue).poll();
                StringBuilder newState = flipLight(t, '0');
                if (!set.contains(newState.toString())) {
                    set.add(newState.toString());
                    queue.add(newState);
                    ans++;
                }
                newState = flipLight(t, '1');
                if (!set.contains(newState.toString())) {
                    set.add(newState.toString());
                    queue.add(newState);
                    ans++;
                }
                newState = flipLight(t, '2');
                if (!set.contains(newState.toString())) {
                    set.add(newState.toString());
                    queue.add(newState);
                    ans++;
                }
                newState = flipLight(t, '3');
                if (!set.contains(newState.toString())) {
                    set.add(newState.toString());
                    queue.add(newState);
                    ans++;
                }
            }
            k++;
        }
        return queue.size();
    }

    public StringBuilder flipLight(StringBuilder s, char type) {
        int n = s.length();
        StringBuilder r = new StringBuilder(s);
        if (type == '0') {
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '1') {
                    r.setCharAt(i, '0');
                } else {
                    r.setCharAt(i, '1');
                }
            }
        }
        if (type == '1') {
            for (int i = 1; i <=n; i += 2) {
                if (s.charAt(i-1) == '1') {
                    r.setCharAt(i-1, '0');
                } else {
                    r.setCharAt(i-1, '1');
                }
            }
        }
        if (type == '2') {
            for (int i = 2; i <=n; i += 2) {
                if (s.charAt(i-1) == '1') {
                    r.setCharAt(i-1, '0');
                } else {
                    r.setCharAt(i-1, '1');
                }
            }
        }
        if (type == '3') {
            for (int i = 0; 3 * i + 1 <=n; i++) {
                if (s.charAt(3 * i) == '1') {
                    r.setCharAt(3 * i , '0');
                } else {
                    r.setCharAt(3 * i, '1');
                }
            }
        }
        return r;
    }

    public int[] distributeCandies(int candies, int num_people) {
        int sum = 0;
        int sr = ((num_people + 1) * num_people) / 2;
        int lastRowSum = 0;
        int t = 0;
        // use binary search to find how many times we have to repeat
        while (sum < candies) {
            lastRowSum = sum;
            sum += num_people * num_people * t + sr;
            t++;
        }
        int rem = 0;
        int st = 0;
        if (sum > candies) {
            t--;
            rem = candies - lastRowSum;
            st = num_people * t + 1; // st is 1st person of last repeat
        }
        int ans[] = new int[num_people];
        int x = t - 1;
        if (x < 0) x = 0;

        for (int i = 0; i < num_people; i++) {
            ans[i] = ((i + 1) * t) + (num_people * x * (x + 1)) / 2;
            if (sum > candies) {
                if (rem <= st) {
                    ans[i] += rem;
                    rem = 0;
                } else {
                    ans[i] += st;
                    rem = rem - st;
                    st++;
                }
            }
        }

        return ans;
    }

}

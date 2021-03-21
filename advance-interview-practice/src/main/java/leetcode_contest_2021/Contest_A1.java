package leetcode_contest_2021;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Contest_A1 {

    @Test
    public void ContestsSolution() {
//        System.out.println(strWithout3a3b(7, 10));
//        System.out.println(solution(5, 5, 10));
        int [] arr = {3,4,9,5};
        System.out.println(solve(2,arr));
    }


    public int secondHighest(String s) {
        int n = s.length();
        Set<Integer> set = new HashSet<>();
        for (int i=0; i< n ;i++){
            if (s.charAt(i)>= '0' && s.charAt(i)<= '9'){
                set.add(Integer.parseInt(s.charAt(i)+""));
            }
        }
        List<Integer> aList = new ArrayList<>();
        aList.addAll(set);
        Collections.sort(aList, (a, b)-> b-a);
        if (aList.size()<=1) return -1;
        return aList.get(1);
    }

    static long solve(int N, int[] Arr) {
        List<Integer> list = Arrays.stream(Arr).boxed().collect(Collectors.toList());
        List<Integer> ans = new ArrayList<>();

        while (list.size()>0){
            getMaxGCD(list, ans);
        }
        int answ =0;
        for (int i = N; i >=1; i--) {
            answ+=ans.get(N-i)*i;
        }
        return answ;
    }

    static List<Integer> getMaxGCD(List<Integer> list, List<Integer> ans) {
        int n = list.size();
        int maxgcd = -1;
        int ii = -1;
        int jj = -1;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int gcd = gcd(list.get(i), list.get(j));
                if (gcd > maxgcd) {
                    ii = i;
                    jj = j;
                    maxgcd = gcd;
                }
            }
        }
        list.remove(ii);
        ans.add(maxgcd);
        if (jj < ii) {
            list.remove(jj);
        } else list.remove(jj - 1);
        return list;
    }

    static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }


    class Node {
        int count;
        char c;

        public Node(int count, char c) {
            this.count = count;
            this.c = c;
        }

        public void dec() {
            this.count--;
        }
    }

    public String solution(int A, int B, int C) {
        PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> b.count - a.count);
        if (A > 0)
            queue.add(new Node(A, 'a'));
        if (B > 0)
            queue.add(new Node(B, 'b'));
        if (C > 0)
            queue.add(new Node(C, 'c'));

        StringBuilder ans = new StringBuilder();

        while (!queue.isEmpty()) {

            Node max1 = queue.poll();

            ans.append(max1.c);
            max1.dec();
            if (queue.size() > 0) {
                Node max2 = queue.poll();
                if (max1.count > max2.count) {
                    ans.append(max1.c);
                    max1.dec();
                    ans.append(max2.c);
                    max2.dec();
                }
                if (max2.count > 0) {
                    queue.add(max2);
                }
            } else {
                if (max1.count > 0) {
                    ans.append(max1.c);
                    max1.dec();
                    return ans.toString();
                }
            }
            if (max1.count > 0) {
                queue.add(max1);
            }
        }
        return ans.toString();
    }

    public String solution2(int A, int B, int C) {
        PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> b.count - a.count);
        if (A > 0)
            queue.add(new Node(A, 'a'));
        if (B > 0)
            queue.add(new Node(B, 'b'));
        if (C > 0)
            queue.add(new Node(C, 'c'));

        StringBuilder ans = new StringBuilder();

        while (queue.size() > 1) {

            Node max1 = queue.poll();

            ans.append(max1.c);
            max1.dec();
            Node max2 = queue.poll();
            if (max1.count > max2.count) {
                ans.append(max1.c);
                max1.dec();
                ans.append(max2.c);
                max2.dec();
            }
            if (max2.count > 0) {
                queue.add(max2);
            }
            if (max1.count > 0) {
                queue.add(max1);
            }
        }

        return ans.toString();
    }

}

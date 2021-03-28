package leetcode_contest_2021;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Contest_A1 {

    @Test
    public void ContestsSolution() {
//        System.out.println(strWithout3a3b(7, 10));
//        System.out.println(solution(5, 5, 10));
        int[] arr = {3, 4, 9, 5};
//        System.out.println(solve(2, arr));
//        "a123bc34d8ef34"
//"leet1234code234"
//"a1b01c001"
//"167278959591294"
//"03598b00005750011523523129774573439111590559325"
//        System.out.println(numDifferentIntegers("0000a000caa01"));
        List<List<String>> list = new ArrayList<>();
        list.add(Arrays.asList("name", "bob"));
        list.add(Arrays.asList("age", "two"));
        System.out.println(evaluate("(name)is(age)yearsold", list));

    }

    public String evaluate(String s, List<List<String>> knowledge) {
        HashMap<String, String> map = new HashMap<>();
        for (List<String> list : knowledge) {
            map.put(list.get(0), list.get(1));
        }
        int n = s.length();
        StringBuilder ans = new StringBuilder();
        int open = -1;
        int close = -1;
        for (int i = 0; i <= n; i++) {
            if (i == n) {
                ans.append(s.substring(close + 1, i));
            } else if (s.charAt(i) == '(') {
                open = i;
                if (close == -1) {
                    ans.append(s.substring(0, i));
                } else {
                    ans.append(s.substring(close + 1, i));
                }
                int w = 0;
            } else if (s.charAt(i) == ')') {

                close = i;
                String key = s.substring(open + 1, i);
                String st = map.get(key);
                if (st != null) {
                    ans.append(st);
                } else {
                    ans.append("?");
                }
                int w = 0;
                open = -1;
            }

        }
        return ans.toString();
    }

    public int reinitializePermutation(int n) {
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        int count = 0;
        while (true) {
            int tmp[] = new int[n];

            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    tmp[i] = arr[i / 2];
                } else {
                    tmp[i] = arr[(n / 2) + (i - 1) / 2];
                }
            }
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (tmp[j] != j) {
                    flag = false;
                }
            }
            count++;
            if (flag) {
                return count;
            }
            arr = tmp;
        }
    }

    public int numDifferentIntegers(String word) {
        int n = word.length();
        int prev = -1;
        Set<String> set = new HashSet<>();
        for (int i = 0; i <= n; i++) {
            if (i < n && word.charAt(i) >= '0' && word.charAt(i) <= '9') {
                if (prev == -1 && word.charAt(i) != '0') {
                    prev = i;
                }
            } else {
                if (prev != -1) {
                    set.add(word.substring(prev, i));
                    prev = -1;
                } else {
                    if (i > 0 && word.charAt(i - 1) == '0') {
                        set.add("0");
                    }
                }
            }
        }
        return set.size();
    }


    public int secondHighest(String s) {
        int n = s.length();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                set.add(Integer.parseInt(s.charAt(i) + ""));
            }
        }
        List<Integer> aList = new ArrayList<>();
        aList.addAll(set);
        Collections.sort(aList, (a, b) -> b - a);
        if (aList.size() <= 1) return -1;
        return aList.get(1);
    }

    static long solve(int N, int[] Arr) {
        List<Integer> list = Arrays.stream(Arr).boxed().collect(Collectors.toList());
        List<Integer> ans = new ArrayList<>();

        while (list.size() > 0) {
            getMaxGCD(list, ans);
        }
        int answ = 0;
        for (int i = N; i >= 1; i--) {
            answ += ans.get(N - i) * i;
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

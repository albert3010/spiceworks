package leetcode_contest_2021.group1;

import leetcode_2020.contests.ContetAD.Contests6;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Contest_A1 {

    @Test
    public void ContestsSolution() {
//        [107,103,102]

        int[][] grid = {
                {20, 17, 9, 13, 5, 2, 9, 1, 5},
                {14, 9, 9, 9, 16, 18, 3, 4, 12},
                {18, 15, 10, 20, 19, 20, 15, 12, 11},
                {19, 16, 19, 18, 8, 13, 15, 14, 11},
                {4, 19, 5, 2, 19, 17, 7, 2, 2}
        };

//        System.out.println(getBiggestThree(grid));
        int[] a = {7, 9, 10, 8, 6, 4, 1, 5, 2, 3};
        System.out.println(minFlips("01001001101"));
//        System.out.println(reductionOperations(a));
//        System.out.println(isSumEqual("h", "fhjfdghj", "fhjfdgig"));
    }

    public int minFlips(String s) {
        String ss = s + s;
        int t = s.length();
        int l = ss.length();
        StringBuilder zo = new StringBuilder();
        StringBuilder oz = new StringBuilder();

        for (int i = 0; i < l; i++) {
            if (i%2==0){
                zo.append('0');
                oz.append('1');
            }else {
                zo.append('1');
                oz.append('0');
            }

        }
        int countDP[][] = new int[l][2];
        if (s.charAt(0) != zo.charAt(0)) {
            countDP[0][0] = 1;
        }
        if (s.charAt(0) != oz.charAt(0)) {
            countDP[0][1] = 1;
        }

        for (int i = 1; i < l; i++) {
            countDP[i][0] += countDP[i - 1][0];
            if (ss.charAt(i) != zo.charAt(i)) {
                countDP[i][0] += +1;
            }
            countDP[i][1] += countDP[i - 1][1];
            if (ss.charAt(i) != oz.charAt(i)) {
                countDP[i][1] += +1;
            }
        }
        int ans =Math.max(countDP[t-1][0], countDP[t-1][1]);
        for (int i = t; i < l; i++) {
            int val = countDP[i][0] -countDP[i-t][0];
            int val1 = countDP[i][1] -countDP[i-t][1];
            ans = Math.min(ans, val);
            ans = Math.min(ans, val1);
        }
        return ans;
    }

    class NodeAA {
        int num;
        int count;

        public NodeAA(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }

    public int reductionOperations(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        List<NodeAA> list = new ArrayList<>();
        map.forEach((k, v) -> list.add(new NodeAA(k, v)));
        list.sort((a, b) -> b.num - a.num);
        int l = list.size();
        int ans = 0;
        int lastCt = 0;
        for (int i = 0; i < l - 1; i++) {

            ans = ans + lastCt + list.get(i).count;
            lastCt += list.get(i).count;
        }
        return ans;
    }

    public boolean findRotation(int[][] mat, int[][] target) {
        int r = mat.length;
        int c = mat[0].length;
        int count = 0;
        while (count < 5) {
            mat = rotate90Clockwise(mat);
            boolean f = checkEquel(mat, target);
            if (f) return true;
            count++;
        }
        return false;
    }

    private boolean checkEquel(int[][] mat, int[][] target) {
        int r = mat.length;
        int c = mat[0].length;

        int rt = target.length;
        int ct = target[0].length;

        if (r == rt && c == ct) {
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (mat[i][j] != target[i][j]) {
                        return false;
                    }
                }
            }
        } else return false;
        return true;
    }

    int[][] rotate90Clockwise(int mat[][]) {
        int r = mat.length;
        int c = mat[0].length;
        int[][] output = new int[c][r];
        // Traverse each cycle
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                output[j][r - 1 - i] = mat[i][j];
        return output;
    }


    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        int l = firstWord.length();
        long a = 0;
        long b = 0;
        long c = 0;
        for (int i = 0; i < l; i++) {
            int t = firstWord.charAt(i) - 'a';
            a = a * 10 + t;
        }
        for (int i = 0; i < secondWord.length(); i++) {
            int t = secondWord.charAt(i) - 'a';
            b = b * 10 + t;
        }


        for (int i = 0; i < targetWord.length(); i++) {
            int t = targetWord.charAt(i) - 'a';
            c = c * 10 + t;

        }
        if (a + b == c) return true;
        return false;

    }

    public int[] getBiggestThree(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        Set<Integer> sums = new HashSet();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                int x1 = i;
                int y1 = j;
                int range = 1;
                sums.add(grid[x1][y1]);
                while (true) {

                    int x2 = x1 + range;
                    int y2 = y1 - range;

                    int x3 = x1 + range;
                    int y3 = y1 + range;

                    int x4 = x1 + 2 * range;
                    int y4 = y1;

                    boolean v1 = isValid(x2, y2, row, col);
                    boolean v2 = isValid(x3, y3, row, col);
                    boolean v3 = isValid(x4, y4, row, col);

                    if (v1 && v2 && v3) {
                        int sum = getSum(x1, y1, x2, y2, x3, y3, x4, y4, grid);
                        sums.add(sum);
                    } else {
                        break;
                    }
                    if (range == 3) {
                        System.out.println("got 3");
                    }
                    range++;
                }


            }
        }
        List<Integer> arr = new ArrayList();
        for (Integer v : sums) {
            arr.add(v);
        }
        Collections.sort(arr);
        int l = arr.size();
        List<Integer> ans = new ArrayList<>();
        int count = 0;
        for (int i = l - 1; i >= 0; i--) {
            ans.add(arr.get(i));
            count++;
            if (count == 3) break;
        }
        int[] array = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            array[i] = ans.get(i);
        }
        return array;
    }


    int getSum(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int grid[][]) {
        int sum = 0;
        int xy2 = grid[x2][y2];
        int xy3 = grid[x3][y3];
        int xx2 = x2;
        int yy2 = y2;
        int xx3 = x3;
        int yy3 = y3;
        while (true) {

            sum += grid[x2][y2];
            x2--;
            y2++;
            if (x2 == x1 && y2 == y1) break;
        }
        while (true) {

            sum += grid[x3][y3];
            x3--;
            y3--;
            if (x3 == x1 && y3 == y1) break;
        }

        while (true) {

            sum += grid[xx2][yy2];
            xx2++;
            yy2++;
            if (xx2 == x4 && yy2 == y4) break;
        }
        while (true) {

            sum += grid[xx3][yy3];
            xx3++;
            yy3--;
            if (xx3 == x4 && yy3 == y4) break;
        }
        sum += grid[x1][y1];
        sum += grid[x4][y4];
        sum -= xy2;
        sum -= xy3;
        return sum;
    }


    boolean isValid(int x, int y, int row, int col) {
        if (x < row && x >= 0 && y < col && y >= 0) {
            return true;
        }
        return false;
    }


    public int minPairSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int j = n - 1;
        int i = 0;
        int ans = 0;
        while (i < j) {
            ans = Math.max(ans, nums[i++] + nums[j--]);
        }
        return ans;
    }

    public int countGoodSubstrings(String s) {
        int l = s.length();
        int count = 0;
        for (int i = 0; i <= l - 3; i++) {
            char a = s.charAt(i);
            char b = s.charAt(i + 1);
            char c = s.charAt(i + 2);
            if (a != b && a != c && b != c) {
                count++;
            }
        }
        return count;
    }


    class NodeB implements Comparable<NodeB> {
        int enqTime;
        int processTime;
        int index;

        public NodeB(int enqTime, int processTime, int index) {
            this.enqTime = enqTime;
            this.processTime = processTime;
            this.index = index;
        }

        public int compareTo(NodeB p) {
            if (this.processTime > p.processTime) {
                // +ve means swapping less val
                return 1;
            }
            if (this.processTime == p.processTime) {
                return this.enqTime - p.enqTime;
            }
            // no action on current element
            return -1;
        }

    }

    public int[] getOrder(int[][] tasks) {
        int l = tasks.length;
        List<NodeB> list = new ArrayList<>();
        PriorityQueue<NodeB> priorityQueue = new PriorityQueue<>();

        for (int i = 0; i < l; i++) {
            list.add(new NodeB(tasks[i][0], tasks[i][1], i));
        }
        Collections.sort(list, (a, b) -> a.enqTime - b.enqTime);
        for (NodeB b : list) {
            System.out.println("ss " + b.index);
        }
        int[] ans = new int[l];
        int currIndex = -1;
        int startTime = list.get(0).enqTime;
        priorityQueue.add(list.get(0));
        for (int i = 1; i < l; i++) {
            if (list.get(i).enqTime == startTime) {
                priorityQueue.add(list.get(i));
                currIndex = i;
            } else {
                break;
            }
        }
        int k = 0;
        while (!priorityQueue.isEmpty()) {
            NodeB nodeB = priorityQueue.poll();
            int farIndex = nodeB.processTime + currIndex;
            for (int i = currIndex + 1; i < l && list.get(i).enqTime <= farIndex; i++) {
                priorityQueue.add(list.get(i));
                currIndex = i;
            }
            System.out.println("q size : " + priorityQueue.size());
            System.out.println(k);
            ans[k++] = nodeB.index;
        }

        return ans;
    }

    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int n = costs.length;
        if (coins < costs[0]) return 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (coins > 0 && coins - costs[i] >= 0) {
                coins -= costs[i];
                ans++;
            } else {
                break;
            }
        }
        return ans;
    }

    public boolean checkIfPangram(String sentence) {
        int l = sentence.length();
        boolean check[] = new boolean[26];
        for (int i = 0; i < l; i++) {
            check[sentence.charAt(i) - 'a'] = true;
        }
        for (int i = 0; i < 26; i++) {
            if (!check[i]) return false;
        }
        return true;
    }

    public static int countGroups(List<String> related) {
        List<Set<Integer>> groups = new ArrayList<>();

        int n = related.size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    boolean found = false;
                    if (related.get(i).charAt(j) == '1') {
                        for (Set<Integer> set : groups) {
                            if (set.contains(i) || set.contains(j)) {
                                found = true;
                                set.add(i);
                                set.add(j);
                                break;
                            }
                        }
                    } else {
                        for (Set<Integer> set : groups) {
                            if (set.contains(i)) {
                                found = true;
                                break;
                            }
                        }
                    }
                    if (!found) {
                        Set<Integer> set = new HashSet<>();
                        set.add(i);
                        groups.add(set);
                    }
                }
            }
        }
        return groups.size();
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

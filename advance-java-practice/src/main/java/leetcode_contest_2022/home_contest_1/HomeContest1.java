package leetcode_contest_2022.home_contest_1;

import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

public class HomeContest1 {
    int maxScore = 0;

    class Node {
        Node prev;
        Node next;
        int val;
    }

    Map<Integer, Node> map = new HashMap<>();

    @Test
    public void test() {
//        System.out.println(minSwaps("]]][[["));
//            [5,1,5,5,1,3,4,5,1,4]
//        a:  [1,1,2,3,2,3,4,5,3,5]
        int aa[] = {1, 5, 6};
//        my: [1,1,2,3,2,3,4,5,3,4]
//        System.out.println(test(Arrays.asList(7, 5, 9), Arrays.asList(13, 1, 4), 3));
//        System.out.println(solution(aa, 4, 3));
        String[] ax = {"ab", "babc", "bca"};
//        System.out.println(maxOccurrences("ababcbabc", ax));
//        performWarehouseQueries(null);
        System.out.println(findTotalPower(Arrays.asList(2, 1, 3)));
    }

    int solve(int[][] arr) {
        int n = arr[0].length;

        int dp[][][] = new int[2][n + 1][2];

        for (int i = 1; i <= n; i++) {
            dp[0][i][0] = Math.max(
                    Math.max(dp[0][i - 1][0], dp[0][i - 1][1]),
                    Math.max(dp[1][i - 1][0], dp[1][i - 1][1])
            );
            dp[0][i][1] = Math.max(dp[0][i - 1][0], dp[1][i - 1][0]);

            dp[1][i][0] = Math.max(
                    Math.max(dp[0][i - 1][0], dp[0][i - 1][1]),
                    Math.max(dp[1][i - 1][0], dp[1][i - 1][1])
            );
            dp[1][i][1] = Math.max(dp[0][i - 1][0], dp[1][i - 1][0]);
        }
        return Math.max(
                Math.max(dp[0][n][0], dp[0][n][1]),
                Math.max(dp[1][n][0], dp[1][n][1])
        );
    }

    int solve2(int[][] arr) {
        int n = arr[0].length;
        int arr2[] = new int[n];
        int exclude = 0;
        int include = 0;
        for (int i = 0; i < n; i++) {
            arr2[i] = Math.max(arr[i][0], arr[i][1]);
            int ex = Math.max(exclude, include);
            include = exclude + arr2[i];
            exclude = ex;
        }
        return Math.max(exclude, include);
    }

    public static int findTotalPower(List<Integer> power) {
        int l = power.size();
        int[] preSum = new int[l];
        int k = 0;
        int mod = 1000000000 + 7;
        for (Integer val : power) {
            if (k == 0) {
                preSum[k] = val;
            } else {
                preSum[k] = (preSum[k - 1] + val) % mod;
            }
            k++;
        }
        int totalSum = 0;
        for (int i = 0; i < l; i++) {
            int min = power.get(i);
            for (int j = i; j < l; j++) {
                min = Math.min(min, power.get(j));
                int sum = preSum[j];
                if (i - 1 >= 0) {
                    sum = preSum[j] - preSum[i - 1];
                }
                totalSum = (totalSum + (min * sum) % mod) % mod;
            }
        }
        return totalSum;
    }


    public static List<List<String>> performWarehouseQueries(List<List<String>> query) {

        Deque<String> deque = new LinkedList<>();
        deque.addLast("1");
        deque.addLast("2");
        deque.addLast("3");
        System.out.println(deque.removeFirst());

        List<List<String>> ans = new ArrayList<>();

        for (List<String> q : query) {
            if (q.get(0).equals("SHIP")) {
                List<String> pk = new ArrayList<>();
                if (deque.size() >= 3) {
                    pk.add(deque.removeFirst());
                    pk.add(deque.removeFirst());
                    pk.add(deque.removeFirst());
                } else {
                    pk.add("N/A");
                }
                ans.add(pk);
            } else {
                deque.addLast(q.get(1));
            }
        }
        return ans;
    }


    public void interchange(int x, int y) {
        Node value1 = map.get(x);
        Node value2 = map.get(y);
        if (value1.next == null || value1.next.val == value2.val) return;

        Node change = value1.next;
        remove(change);
        add(value2, change);

    }

    void remove(Node change) {
        Node prv1 = change.prev;
        Node nxt1 = change.next;
        prv1.next = nxt1;
        if (nxt1 != null) {
            nxt1.prev = prv1;
        }
    }

    void add(Node st, Node add) {
        Node nxt1 = st.next;
        st.next = add;
        add.prev = st;
        add.next = nxt1;

        if (nxt1 != null) {
            nxt1.prev = add;
        }
    }

    int bestSquares(int[][] m, int k) {
        int r = m.length;
        int c = m[0].length;
        return 0;

    }

    int[] maxOccurrences(String sequence, String[] words) {

        int n = words.length;
        int[] ans = new int[n];
        StringBuilder ss = new StringBuilder("");
        int j = 0;
        for (int i = 0; i < n; i++) {
            int k = 0;
            while (ss.length() <= sequence.length()) {
                ss.append(words[i]);
                if (sequence.contains(ss.toString())) {
                    k++;
                } else {
                    break;
                }
            }
            ss = new StringBuilder("");
            ans[j++] = k;
        }
        return ans;
    }

    public int[] solution(int[] A, int F, int M) {
        int n = A.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += A[i];
        }
        int sumVal = M * (F + n);
        int remSum = sumVal - sum;

        int min = F;
        int max = F * 6;
        if (remSum < min || remSum > max) {
            int ans[] = new int[1];
            return ans;
        }
        int res[] = new int[F];
        for (int i = 0; i < F; i++) {
            res[i] = 1;
        }
        remSum = remSum - F;
        int i = 0;
        while (remSum > 0) {
            res[i++] += 5;
            remSum -= 5;
        }
        return res;
    }

    Integer aggregate(Stream<Integer> products) {

        final Integer[] total = {0};
        Stream<Integer> simpleSoldProducts = products.map(product -> {
            total[0] += product;
            return total[0];
        });
        return 1;
    }

    public int[] rearrangeArray(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        for (int i = 0; i < n; i += 2) {
            if (i < n - 1) {
                int tmp = nums[i];
                nums[i] = nums[i + 1];
                nums[i + 1] = tmp;
            }
        }
        return nums;
    }

    public int numOfStrings(String[] patterns, String word) {
        int l = patterns.length;
        int count = 0;
        for (int i = 0; i < l; i++) {
            if (word.contains(patterns[i])) {
                count++;
            }
        }
        return count;
    }

    public int test(List<Integer> a, List<Integer> b, int d) {
        int min = b.get(0);
        int max = b.get(0);
        int n = b.size();
        for (int i = 0; i < n; i++) {
            min = Math.min(min, b.get(i));
            max = Math.max(max, b.get(i));
        }
        int ans = 0;
        for (Integer val : a) {
            if (val + d < max) {
                ans++;
            }
        }
        return ans;
    }

    private void binaryS(int val, List<Integer> a) {

        int l = a.size();
        int i = 0;
        int j = l - 1;
        while (i < j) {
            int mid = (i + j) / 2;
        }

    }

    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int n = obstacles.length;
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek() > obstacles[i]) {
                stack.pop();
            }
            stack.add(obstacles[i]);
            ans[i] = stack.size();
        }
        return ans;
    }

    public int minSwaps(String s) {
        int l = s.length();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < l; i++) {
            if (stack.isEmpty()) {
                stack.add(s.charAt(i));
            } else {
                if (s.charAt(i) == ']' && stack.peek() == '[') {
                    stack.pop();
                } else {
                    stack.add(s.charAt(i));
                }
            }
        }
        int t = stack.size() / 2;
        if (t % 2 == 0) {
            return t / 2;
        }
        return (t / 2) + 1;
    }

    public int minStoneSum(int[] piles, int k) {
        int n = piles.length;
        PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> x - y);
        for (int i = 0; i < n; i++) {
            queue.add(piles[i]);
        }
        while (k > 0) {
            int t = queue.poll();
            int x = t - (t / 2);
            queue.add(x);
            if (t == x) break;
            k--;
        }
        int sum = 0;
        while (!queue.isEmpty()) {
            sum += queue.poll();
        }
        return sum;
    }

    public boolean isPrefixString(String s, String[] words) {
        int n = words.length;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            sb.append(words[i]);
            if (sb.toString().equals(s)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        int r = board.length;
        int c = board[0].length;
        int inside = 0;
        int outside = 0;
        if (color == 'B') {
            char in = 'W';
            char out = 'B';
            inside = 0;
            outside = 1;
            for (int i = cMove + 1; i < c; i++) {
                if (board[rMove][i] == in) {
                    if (outside > 1) {
                        break;
                    }
                    inside++;
                }
                if (board[rMove][i] == out) {
                    if (outside > 1) {
                        break;
                    }
                    if (inside > 0) {
                        outside++;
                    } else {
                        break;
                    }
                }
                if (board[rMove][i] == '.') {
                    if (inside > 0 && outside == 2) {
                        return true;
                    } else {
                        break;
                    }
                }
                if (outside == 2 && inside > 0) {
                    return true;
                }
            }
            inside = 0;
            outside = 1;
            for (int i = cMove - 1; i >= 0; i--) {
                if (board[rMove][i] == in) {
                    if (outside > 1) {
                        break;
                    }
                    inside++;
                }
                if (board[rMove][i] == out) {
                    if (outside > 1) {
                        break;
                    }
                    if (inside > 0) {
                        outside++;
                    } else {
                        break;
                    }
                }
                if (board[rMove][i] == '.') {
                    if (inside > 0 && outside == 2) {
                        return true;
                    } else {
                        break;
                    }
                }
                if (outside == 2 && inside > 0) {
                    return true;
                }
            }
            inside = 0;
            outside = 1;
            for (int i = rMove + 1; i < r; i++) {
                if (board[i][cMove] == in) {
                    if (outside > 1) {
                        break;
                    }
                    inside++;
                }
                if (board[i][cMove] == out) {
                    if (outside > 1) {
                        break;
                    }
                    if (inside > 0) {
                        outside++;
                    } else {
                        break;
                    }
                }
                if (board[i][cMove] == '.') {
                    if (inside > 0 && outside == 2) {
                        return true;
                    } else {
                        break;
                    }
                }
                if (outside == 2 && inside > 0) {
                    return true;
                }
            }
            inside = 0;
            outside = 1;
            for (int i = rMove - 1; i >= 0; i--) {
                if (board[i][cMove] == in) {
                    if (outside > 1) {
                        break;
                    }
                    inside++;
                }
                if (board[i][cMove] == out) {
                    if (outside > 1) {
                        break;
                    }
                    if (inside > 0) {
                        outside++;
                    } else {
                        break;
                    }
                }
                if (board[i][cMove] == '.') {
                    if (inside > 0 && outside == 2) {
                        return true;
                    } else {
                        break;
                    }
                }
                if (outside == 2 && inside > 0) {
                    return true;
                }
            }
            inside = 0;
            outside = 1;
            for (int i = rMove + 1, j = cMove + 1; i < r && j < c; i++, j++) {
                if (board[i][j] == in) {
                    if (outside > 1) {
                        break;
                    }
                    inside++;
                }
                if (board[i][j] == out) {
                    if (outside > 1) {
                        break;
                    }
                    if (inside > 0) {
                        outside++;
                    } else {
                        break;
                    }
                }
                if (board[i][j] == '.') {
                    if (inside > 0 && outside == 2) {
                        return true;
                    } else {
                        break;
                    }
                }
                if (outside == 2 && inside > 0) {
                    return true;
                }
            }
            inside = 0;
            outside = 1;
            for (int i = rMove - 1, j = cMove - 1; i >= 0 && j >= 0; i--, j--) {
                if (board[i][j] == in) {
                    if (outside > 1) {
                        break;
                    }
                    inside++;
                }
                if (board[i][j] == out) {
                    if (outside > 1) {
                        break;
                    }
                    if (inside > 0) {
                        outside++;
                    } else {
                        break;
                    }
                }
                if (board[i][j] == '.') {
                    if (inside > 0 && outside == 2) {
                        return true;
                    } else {
                        break;
                    }
                }
                if (outside == 2 && inside > 0) {
                    return true;
                }
            }
            inside = 0;
            outside = 1;
            for (int i = rMove + 1, j = cMove - 1; i < r && j >= 0; i++, j--) {
                if (board[i][j] == in) {
                    if (outside > 1) {
                        break;
                    }
                    inside++;
                }
                if (board[i][j] == out) {
                    if (outside > 1) {
                        break;
                    }
                    if (inside > 0) {
                        outside++;
                    } else {
                        break;
                    }
                }
                if (board[i][j] == '.') {
                    if (inside > 0 && outside == 2) {
                        return true;
                    } else {
                        break;
                    }
                }
                if (outside == 2 && inside > 0) {
                    return true;
                }
            }
            inside = 0;
            outside = 1;
            for (int i = rMove - 1, j = cMove + 1; i >= 0 && j < c; i--, j++) {
                if (board[i][j] == in) {
                    if (outside > 1) {
                        break;
                    }
                    inside++;
                }
                if (board[i][j] == out) {
                    if (outside > 1) {
                        break;
                    }
                    if (inside > 0) {
                        outside++;
                    } else {
                        break;
                    }
                }
                if (board[i][j] == '.') {
                    if (inside > 0 && outside == 2) {
                        return true;
                    } else {
                        break;
                    }
                }
                if (outside == 2 && inside > 0) {
                    return true;
                }
            }

        } else {
            char in = 'B';
            char out = 'W';
            inside = 0;
            outside = 1;
            for (int i = cMove + 1; i < c; i++) {
                if (board[rMove][i] == in) {
                    if (outside > 1) {
                        break;
                    }
                    inside++;
                }
                if (board[rMove][i] == out) {
                    if (outside > 1) {
                        break;
                    }
                    if (inside > 0) {
                        outside++;
                    } else {
                        break;
                    }
                }
                if (board[rMove][i] == '.') {
                    if (inside > 0 && outside == 2) {
                        return true;
                    } else {
                        break;
                    }
                }
                if (outside == 2 && inside > 0) {
                    return true;
                }
            }
            inside = 0;
            outside = 1;
            for (int i = cMove - 1; i >= 0; i--) {
                if (board[rMove][i] == in) {
                    if (outside > 1) {
                        break;
                    }
                    inside++;
                }
                if (board[rMove][i] == out) {
                    if (outside > 1) {
                        break;
                    }
                    if (inside > 0) {
                        outside++;
                    } else {
                        break;
                    }
                }
                if (board[rMove][i] == '.') {
                    if (inside > 0 && outside == 2) {
                        return true;
                    } else {
                        break;
                    }
                }
                if (outside == 2 && inside > 0) {
                    return true;
                }
            }
            inside = 0;
            outside = 1;
            for (int i = rMove + 1; i < r; i++) {
                if (board[i][cMove] == in) {
                    if (outside > 1) {
                        break;
                    }
                    inside++;
                }
                if (board[i][cMove] == out) {
                    if (outside > 1) {
                        break;
                    }
                    if (inside > 0) {
                        outside++;
                    } else {
                        break;
                    }
                }
                if (board[i][cMove] == '.') {
                    if (inside > 0 && outside == 2) {
                        return true;
                    } else {
                        break;
                    }
                }
                if (outside == 2 && inside > 0) {
                    return true;
                }
            }
            inside = 0;
            outside = 1;
            for (int i = rMove - 1; i >= 0; i--) {
                if (board[i][cMove] == in) {
                    if (outside > 1) {
                        break;
                    }
                    inside++;
                }
                if (board[i][cMove] == out) {
                    if (outside > 1) {
                        break;
                    }
                    if (inside > 0) {
                        outside++;
                    } else {
                        break;
                    }
                }
                if (board[i][cMove] == '.') {
                    if (inside > 0 && outside == 2) {
                        return true;
                    } else {
                        break;
                    }
                }
                if (outside == 2 && inside > 0) {
                    return true;
                }
            }
            inside = 0;
            outside = 1;
            for (int i = rMove + 1, j = cMove + 1; i < r && j < c; i++, j++) {
                if (board[i][j] == in) {
                    if (outside > 1) {
                        break;
                    }
                    inside++;
                }
                if (board[i][j] == out) {
                    if (outside > 1) {
                        break;
                    }
                    if (inside > 0) {
                        outside++;
                    } else {
                        break;
                    }
                }
                if (board[i][j] == '.') {
                    if (inside > 0 && outside == 2) {
                        return true;
                    } else {
                        break;
                    }
                }
                if (outside == 2 && inside > 0) {
                    return true;
                }
            }
            inside = 0;
            outside = 1;
            for (int i = rMove - 1, j = cMove - 1; i >= 0 && j >= 0; i--, j--) {
                if (board[i][j] == in) {
                    if (outside > 1) {
                        break;
                    }
                    inside++;
                }
                if (board[i][j] == out) {
                    if (outside > 1) {
                        break;
                    }
                    if (inside > 0) {
                        outside++;
                    } else {
                        break;
                    }
                }
                if (board[i][j] == '.') {
                    if (inside > 0 && outside == 2) {
                        return true;
                    } else {
                        break;
                    }
                }
                if (outside == 2 && inside > 0) {
                    return true;
                }
            }
            inside = 0;
            outside = 1;
            for (int i = rMove + 1, j = cMove - 1; i < r && j >= 0; i++, j--) {
                if (board[i][j] == in) {
                    if (outside > 1) {
                        break;
                    }
                    inside++;
                }
                if (board[i][j] == out) {
                    if (outside > 1) {
                        break;
                    }
                    if (inside > 0) {
                        outside++;
                    } else {
                        break;
                    }
                }
                if (board[i][j] == '.') {
                    if (inside > 0 && outside == 2) {
                        return true;
                    } else {
                        break;
                    }
                }
                if (outside == 2 && inside > 0) {
                    return true;
                }
            }
            inside = 0;
            outside = 1;
            for (int i = rMove - 1, j = cMove + 1; i >= 0 && j < c; i--, j++) {
                if (board[i][j] == in) {
                    if (outside > 1) {
                        break;
                    }
                    inside++;
                }
                if (board[i][j] == out) {
                    if (outside > 1) {
                        break;
                    }
                    if (inside > 0) {
                        outside++;
                    } else {
                        break;
                    }
                }
                if (board[i][j] == '.') {
                    if (inside > 0 && outside == 2) {
                        return true;
                    } else {
                        break;
                    }
                }
                if (outside == 2 && inside > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean solveA(char board, char in, char out, int outside, int inside) {
        if (board == in) {
            if (outside > 1) {
                return false;
            }
            inside++;
        }
        if (board == out) {
            if (outside > 1) {
                return false;
            }
            if (inside > 0) {
                outside++;
            } else {
                return false;
            }
        }
        if (board == '.') {
            if (inside > 0 && outside == 2) {
                return true;
            } else {
                return false;
            }
        }
        if (outside == 2 && inside > 0) {
            return true;
        }
        return false;
    }

    public String makeFancyString(String s) {
        int n = s.length();
        StringBuilder ans = new StringBuilder("");
        int count = 1;
        ans.append(s.charAt(0));
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ans.charAt(ans.length() - 1)) {
                if (count < 2) {
                    ans.append(s.charAt(i));
                    count++;
                }
            } else {
                count = 1;
                ans.append(s.charAt(i));
            }
        }
        return ans.toString();
    }

    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        // 43
        int n = students.length;
        boolean[] taken = new boolean[n];
        int score = 0;
        backtracking(0, students, mentors, taken, score, n);
        return maxScore;
    }

    private void backtracking(int s, int[][] students, int[][] mentors, boolean[] taken, int score, int n) {
        if (s == n) {
            maxScore = Math.max(maxScore, score);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!taken[i]) {
                int sc = getScore(students[s], mentors[i]);
                taken[i] = true;
                backtracking(0, students, mentors, taken, score + sc, n);
                taken[i] = false;
            }
        }
    }

    int getScore(int[] st, int[] mt) {
        int score = 0;
        for (int i = 0; i < 3; i++) {
            if (st[i] == mt[i]) {
                score++;
            }
        }
        return score;
    }
}

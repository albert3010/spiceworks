//package leetcode.contests.ContestsAC;
//
//import com.google.common.collect.Lists;
//import org.junit.MovieBooking.Test;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.TreeSet;
//
//public class ContestB13 {
//
//    class GNode {
//        int v;
//        int w;
//
//        GNode(int v, int w) {
//            this.v = v;
//            this.w = w;
//        }
//    }
//
//    @MovieBooking.Test
//    public void ContestsSolution() {
//
//        List<List<String>> synonyms = Lists.newArrayList();
//        synonyms.add(Lists.newArrayList("happy", "joy"));
//        synonyms.add(Lists.newArrayList("sad", "sorrow"));
//        synonyms.add(Lists.newArrayList("joy", "cheerful"));
////[[1,2,1],[2,3,7],[1,3,4],[2,1,2]]
////        3
////        2
//        System.out.println(generateSentences(synonyms, "I am happy today but was sad yesterday"));
////        System.out.println(numberOfWays(2));
//        int[][] times = {{1, 2, 1}, {2, 3, 7}, {1, 3, 4}, {2, 1, 2}};
////        int [][] times = {{2,1,1}};
////        System.out.println(networkDelayTime(times, 3, 2));
//        int A[] = {65,44,5,11};
////        System.out.println(numSquarefulPerms(A));
//    }
//
//
//    public int numSquarefulPerms(int[] A) {
//        return permitationUtil(A, 0);
//    }
//
//    public int permitationUtil(int[] A, int index) {
//
//        HashSet<Integer> visited = new HashSet<>();
//        int n = A.length;
//        if(index == n) return 1;
//        int res = 0;
//        for (int i = index; i < n; i++) {
//            if (!visited.contains(A[i])) {
//                if (index == 0 || isSqure(A[i] + A[index - 1])) {
//                    visited.add(A[i]);
//                    swap(A, index, i);
//                    res += permitationUtil(A, index + 1);
//                    swap(A, index, i);
//                }
//            }
//        }
//        return res;
//    }
//
//    void swap(int A[], int i, int j) {
//        int t = A[i];
//        A[i] = A[j];
//        A[j] = t;
//    }
//
//    public boolean isSqure(int x) {
//        int sqrt = (int)Math.sqrt(x);
//        return sqrt * sqrt == x;
//    }
//
//    public int networkDelayTime(int[][] times, int N, int K) {
//
//        List<GNode>[] graph = new ArrayList[N + 1];
//        int dp[] = new int[N + 1];
//        int n = times.length;
//        if (n == 0) return 0;
//        for (int i = 1; i <= N; i++) {
//            graph[i] = new ArrayList<>();
//            dp[i] = Integer.MAX_VALUE;
//        }
//        for (int i = 0; i < n; i++) {
//            graph[times[i][0]].add(new GNode(times[i][1], times[i][2]));
//        }
//        dp[K] = 0;
//
//        calculateMinTimeToReach(graph, dp, N);
//
//        int ans = Integer.MIN_VALUE;
//        for (int i = 1; i <= N; i++) {
//            if (dp[i] == Integer.MAX_VALUE) {
//                return -1;
//            }
//            ans = Math.max(ans, dp[i]);
//        }
//        return ans;
//    }
//
//    public void calculateMinTimeToReach(List<GNode>[] graph, int[] dp, int N) {
//        int n = N;
//        boolean flag = false;
//        while (flag) {
//            for (int node = 1; node <= N; node++) {
//                if (dp[node] != Integer.MAX_VALUE) {
//                    flag = true;
//                    List<GNode> adj = graph[node];
//                    for (GNode gNode : adj) {
//                        dp[gNode.v] = Math.min(dp[gNode.v], dp[node] + gNode.w);
//                    }
//                }
//            }
//            n--;
//        }
//    }
//
//    boolean isBSTHelper(Node node, Integer min, Integer max) {
//
//        if (node == null) {
//            return true;
//        }
//        if (node.data < min || node.data > max) {
//            return false;
//        }
//
//        boolean leftSubTreeCheck = isBSTHelper(node.left, min, node.data);
//        boolean rightSubTreeCheck = isBSTHelper(node.right, node.data, max);
//
//        return leftSubTreeCheck && rightSubTreeCheck;
//    }
//
//    int isBST(Node root) {
//        boolean b = isBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
//
//        if (b == true) {
//            return 1;
//        }
//        return 0;
//    }
//
//    public int numberOfWays(int num_people) {
//
//        Integer mod = 1000000007;
//
//        long dp[] = new long[num_people + 1];
//
//        if (num_people <= 4) {
//            return num_people / 2;
//        }
//        dp[0] = 0;
//        dp[2] = 1;
//        dp[4] = 2;
//
//        for (int k = 6; k <= num_people; k += 2) {
//            int n = k - 2;
//            for (int i = 2, j = n - 2; i <= j; i += 2, j -= 2) {
//                if (i != j) {
//                    dp[k] += (dp[i] * dp[j] * 2) % mod;
//                } else {
//                    dp[k] += (dp[i] * dp[j]) % mod;
//                }
//            }
//            dp[k] = (dp[k] + dp[n] * 2) % mod;
//        }
//        return (int) dp[num_people] % mod;
//    }
//
//    public List<String> generateSentences(List<List<String>> synonyms, String text) {
//
//        List<TreeSet<String>> hset = new ArrayList<>();
//
//        int n = synonyms.size();
//        String[] texts = text.split(" ");
//
//        for (int i = 0; i < n; i++) {
//
//            int k = hset.size();
//            boolean flag = false;
//            List<String> curSyn = synonyms.get(i);
//            for (int j = k - 1; j >= 0; j--) {
//
//                TreeSet<String> set = hset.get(j);
//
//                if (set.contains(curSyn.get(0)) || set.contains(curSyn.get(1))) {
//                    flag = true;
//                    set.add(curSyn.get(0));
//                    set.add(curSyn.get(1));
//                }
//            }
//            if (!flag) {
//                TreeSet<String> set = new TreeSet<>();
//                set.add(curSyn.get(0));
//                set.add(curSyn.get(1));
//                hset.add(set);
//            }
//        }
//
//        List<String> ans = new ArrayList<>();
//
//        generateSentencesHelper(texts, 0, texts.length, hset, "", ans);
//
//        return ans;
//    }
//
//    public void generateSentencesHelper(String[] texts, int pos, int l, List<TreeSet<String>> hset, String s, List<String> ans) {
//
//        if (pos == l) {
//            ans.add(s);
//            return;
//        }
//        String cur = texts[pos];
//        int k = hset.size();
//        boolean flag = true;
//        String sp = "";
//        if (pos != 0) {
//            sp = " ";
//        }
//        for (int i = 0; i < k; i++) {
//            if (hset.get(i).contains(cur)) {
//                flag = false;
//                for (String st : hset.get(i)) {
//                    generateSentencesHelper(texts, pos + 1, l, hset, s + sp + st, ans);
//                }
//            }
//        }
//        if (flag) {
//            generateSentencesHelper(texts, pos + 1, l, hset, s + sp + texts[pos], ans);
//        }
//    }
//
//}

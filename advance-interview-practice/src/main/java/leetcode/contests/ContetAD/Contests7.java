package leetcode.contests.ContetAD;

import com.google.gson.Gson;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.IntStream;

public class Contests7 {

    public static void main(String[] args) {
//        removeKdigits("", 2);
    }

    @Test
    public void ContestsSolution() throws Exception {
//        System.out.println(removeKdigits("102300453523523452300", 7));
//        String[] ans = getMovieTitles("spiderman");
//        int a[] = {4, 3, 2, 5, 6, 7, 2, 5, 5};
        int a[] = {1,1,1,1,1,1,1,3,2};
//        System.out.println(maxSubarraySumCircular(a));
//        System.out.println(maxPower("cc"));
//        System.out.println(simplifiedFractions(1));
//        //[1,1,1,1,1,1,1,1,1]
////5000
        System.out.println(largestNumber(a, 10));
    }

    public String largestNumber(int[] cost, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, String> cMap = new HashMap<>();

        for (int i = 0; i < 9; i++) {
            map.put(i + 1, cost[i]);
            String s = Integer.toString(i + 1);
            cMap.put(cost[i], s);
        }
        HashMap<String, Integer> dp[] = new HashMap[target + 1];
        for (int t = 1; t <= target; t++) {
            dp[t] = new HashMap<>();
            for (int i = 1; i <=9; i++) {
                dp[t].put(Integer.toString(i), 0);
            }

        }
        for (int t = 1; t <= target; t++) {
            for (int i = 0; i < 9; i++) {
                if (t == 1) {
                    if (cMap.get(t) != null && checkAllZero(dp[t])) {
                        dp[t] = getMax(dp[t], dp[t], cMap.get(t));
                    }
                } else {
                    int newCost = t - cost[i];
                    if (cMap.get(t) != null && checkAllZero(dp[t])) {
                        dp[t] = getMax(dp[t], dp[t], cMap.get(t));
                    }
                    if (newCost > 0 && !checkAllZero(dp[newCost])) {
                        dp[t] = getMax(dp[t], dp[newCost], cMap.get(cost[i]));
                    }
                }
            }
        }
        return getResult(dp[target]);
    }

    String getResult(HashMap<String, Integer> map) {
        StringBuilder ans = new StringBuilder();
        for (int i = 9; i >= 1; i--) {
            int t = map.get(Integer.toString(i));
            while (t-- > 0) {
                ans.append(i);
            }
        }
        if(ans.equals(""))return "0";
        return ans.toString();
    }

    boolean checkAllZero(HashMap<String, Integer> map) {
        for (int i = 9; i >= 1; i--) {
            if (map.get(Integer.toString(i)) != 0) {
                return false;
            }
        }
        return true;
    }

    public HashMap<String, Integer> getMax(HashMap<String, Integer> mapNew, HashMap<String, Integer> mapOld, String s) {
        HashMap<String , Integer> mapCopy = new HashMap<>(mapOld);
        mapCopy.put(s, mapCopy.get(s) + 1);
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 9; i >= 1; i--) {
            sum1 += mapNew.get(Integer.toString(i));
            sum2 += mapCopy.get(Integer.toString(i));
        }
        if (sum1 > sum2) return mapNew;
        if (sum1 < sum2) return mapCopy;
        for (int i = 9; i >= 1; i--) {
            String sn =Integer.toString(i);
            if (mapNew.get(sn) > mapCopy.get(sn)) {
                return mapNew;
            }
            if (mapNew.get(sn) < mapCopy.get(sn)) {
                return mapCopy;
            }
        }
        return mapNew;
    }

    String sortString(String str) {
        int MAX_CHAR = 10;
        int charCount[] = new int[MAX_CHAR];

        int l = str.length();
        char[] cc = new char[l];
        int k = 0;
        for (int i = 0; i < l; i++) {

            charCount[str.charAt(i) - '0']++;
        }

        for (int i = MAX_CHAR - 1; i >= 0; i--) {
            for (int j = 0; j < charCount[i]; j++) {
                cc[k++] = (char) ('0' + i);
            }
        }
        return String.valueOf(cc);
    }

    public List<String> simplifiedFractions(int n) {
        HashSet<String> set = new HashSet<>();
        for (int d = 2; d <= n; d++) {
            for (int i = 1; i < d; i++) {
                int gcd = gcd(i, d);
                int nm = i / gcd;
                int dm = d / gcd;
                set.add(nm + "/" + dm);
            }
        }
        return new ArrayList<>(set);
    }

    int gcd(int a, int b) {
        // Everything divides 0
        if (a == 0)
            return b;
        if (b == 0)
            return a;

        // base case
        if (a == b)
            return a;

        // a is greater
        if (a > b)
            return gcd(a - b, b);
        return gcd(a, b - a);
    }

    public int maxPower(String s) {
        int n = s.length();
        char prv = s.charAt(0);
        int ans = 1;
        int tmp = 1;
        for (int i = 1; i < n; i++) {
            char cc = s.charAt(i);
            if (cc == prv) {
                tmp++;
                ans = Math.max(ans, tmp);
            } else {
                tmp = 1;
                prv = cc;
            }
        }
        return ans;

    }

    public int maxSubarraySumCircular(int[] A) {
        int m = A.length;
        int allSum = IntStream.range(0, m).map(i -> A[i]).sum();
        int imax = kadane(A, 1);
        int ngmax = kadane(A, -1);
        return imax <= 0 ? imax : Math.max(allSum + ngmax, imax);
    }

    int kadane(int A[], int flag) {
        int m = A.length;
        int max = 0;
        int sum = 0;
        long ngmin = IntStream.range(0, m).filter(i -> flag * A[i] < 0).count();
        int ngmax = IntStream.range(0, m).map(i -> flag * A[i]).max().getAsInt();
        for (int i = 0; i < m; i++) {
            sum += A[i] * flag;
            if (sum >= max) {
                max = sum;
            }
            if (sum < 0) {
                sum = 0;
            }
        }
        return ngmin == m ? ngmax : Math.max(ngmax, max);
    }

    class MovieL {
        String Title;
        Integer Year;
        String imdbID;
    }

    class Movie {
        String page;
        Integer per_page;
        Integer total;
        String total_pages;
        List<MovieL> data;
    }

    static String[] getMovieTitles(String substr) throws Exception {
        String[] a = new String[0];
        String url = "https://jsonmock.hackerrank.com/api/movies/search/?Title=" + substr;
        Movie movie = getHTML(url);
        int page = Integer.parseInt(movie.total_pages);
        if (page == 0) {
            return a;
        }
        List<String> movieTiles = new ArrayList<>();
        int t = movie.data.size();
        List<MovieL> movieLS = movie.data;

        for (int i = 0; i < t; i++) {
            movieTiles.add(movieLS.get(i).Title);
        }
        for (int p = 2; p <= page; p++) {
            url += "&page=" + p;
            Movie movie1 = getHTML(url);
            t = movie1.data.size();
            movieLS = movie1.data;
            for (int i = 0; i < t; i++) {
                movieTiles.add(movieLS.get(i).Title);
            }
        }
        Collections.sort(movieTiles);

        int x = movieTiles.size();
        String[] ans = new String[x];
        for (int i = 0; i < x; i++) {
            ans[i] = movieTiles.get(i);
        }
        return ans;
    }

    public static Movie getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        String jsonString = result.toString();

        Gson gson = new Gson();
        Movie movie = gson.fromJson(jsonString, Movie.class);
        return movie;
    }


    //    public int widthOfBinaryTree(TreeNode root) {
//        return 0;
//        Queue<Pair> queue= new LinkedList<>();
//        if(root==null) return 0;
//        int ans =0;
//        queue.add(new Pair(root, 0));
//        while(!queue.isEmpty()){
//
//            int l = queue.size();
//            Pair peek = queue.peek();
//            Pair last;
//            while(l>0){
//                Pair nd = queue.poll();
//                if(l==1){
//                    last =nd;
//                }
//                if(nd.left!=null){
//                    queue.add(nd.left, nd.level-1);
//                }
//                if(nd.right!=null){
//                    queue.add(nd.right, nd.level+1);
//                }
//                l--;
//            }
//            ans = Math.max(ans, 0)
//
//        }
//    }
    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();

        int n = num.length();
        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            while (!stack.isEmpty() && stack.peek() > c && k > 0) {
                stack.pop();
                k--;
            }
            if (c == '0') {
                if (!stack.isEmpty())
                    stack.push(c);
            } else {
                stack.push(c);
            }
        }

        int t = stack.size();
        StringBuilder s = new StringBuilder();
        while (!stack.isEmpty()) {
            s.append(stack.pop());
        }
        String v = s.reverse().toString();
        if (v.equals("")) return "0";
        return v;
    }

}

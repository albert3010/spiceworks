package test;

import java.util.*;

public class Test {

    public static void main(String[] args) {
//        int[] arr = {5, 4, 3, 2, 1};
        int[] arr = {2, 4, 1, 6, 12};
//        System.out.println(solve(arr));
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
//        List<Integer> ans = solve1(list, 1);
//        System.out.println(ans);
//        for (Integer v :ans){
//            System.out.print(v+" ");
//        }
        test();

    }

    static class Doc {
        int id;
        String doc;

        public Doc(int id, String doc) {
            this.id = id;
            this.doc = doc;
        }
    }

    public int minimizeSum(int[] nums) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        if (n == 3) return 0;
        list.add(nums[0]);
        int min = nums[n - 1] - nums[0];
        int ans =0;
        for (int i = 1; i < n; i++) {
            int t = nums[i]-nums[i-1];
            if(t<=min){
                ans =Math.min(nums[i]-nums[i-1], ans);
                ans =Math.min(nums[i-1]-nums[0], ans);
                ans =Math.min(nums[n-1]-nums[i], ans);
                min = t;
            }
        }
        return  ans;
    }

    public int minMaxDifference(int num) {

        List<Integer> digits = new ArrayList<>();

        while (num > 0) {
            digits.add(num % 10);
            num = num / 10;
        }
        Collections.reverse(digits);
        int rep = 9;
        int min = 0;
        for (Integer d : digits) {
            if (d != 9) {
                rep = d;
                break;
            }
        }
        List<Integer> maxN = new ArrayList<>();
        List<Integer> minnN = new ArrayList<>();
        for (Integer d : digits) {
            if (d == rep) {
                maxN.add(9);
            } else {
                maxN.add(d);
            }
        }
        int minDd = digits.get(0);
        for (Integer d : digits) {
            if (d == minDd) {
                minnN.add(0);
            } else {
                minnN.add(d);
            }
        }

        Collections.reverse(minnN);
        Collections.reverse(maxN);
        int count = 0;
        int minN = 0;
        int max = 0;
        int tt = 1;
        for (int d : maxN) {
            minN += tt * d;
            tt = tt * 10;
            count++;
        }
        tt = 1;
        for (int d : minnN) {
            max += tt * d;
            tt = tt * 10;
        }
        return max - minN;

    }

    static void test() {

        Doc doc1 = new Doc(1, "good one this good");
        Doc doc2 = new Doc(2, "good one this");
        Doc doc3 = new Doc(3, "bad this");


        List<Doc> docs = new ArrayList<>();
        docs.add(doc1);
        docs.add(doc2);
        docs.add(doc3);

        Map<Integer, String[]> docWords = new HashMap<>();
        Map<Integer, Map<String, List<Integer>>> docMap = new HashMap<>();
        Map<String, Set<Integer>> docIds = new HashMap<>();

        for (Doc doc : docs) {
            String[] words = doc.doc.split(" ");
            docWords.put(doc.id, words);

            int pos = 0;
            for (String w : words) {

                docMap.putIfAbsent(doc.id, new HashMap<>());
                docMap.get(doc.id).putIfAbsent(w, new ArrayList<>());
                docMap.get(doc.id).get(w).add(pos);

                docIds.putIfAbsent(w, new HashSet<>());
                docIds.get(w).add(doc.id);

                pos++;
            }
        }
        String search = "good one";
        String[] words = search.split(" ");
        for (String word : words) {
            Set<Integer> docIds_ = docIds.get(word);
            List<Integer> ans = new ArrayList<>();

            for (Integer docId : docIds_) {
                Map<String, List<Integer>> docPos = docMap.get(docId);
            }
        }

    }

    static List<Integer> solve1(List<Integer> arr, int k) {
        int n = arr.size();
        List<Integer> reverse = new ArrayList<>();
        int i = 0;
        if (k == 0) return arr;
        while (i < n) {
            int end = Math.min(i + k - 1, n - 1);
            if (end - i + 1 == k) {
                for (int j = end; j >= i; j--) {
                    reverse.add(arr.get(j));
                }
            } else {
                for (int j = i; j <= end; j++) {
                    reverse.add(arr.get(j));
                }
            }
            if (end == n - 1) break;
            i = Math.min(i + k, n - 1);

        }
        return reverse;
    }

    private static int solve(int arr[]) {
        int n = arr.length;
        Stack<Integer> st = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            st.push(arr[i]);
            while (st.size() > 1) {
                int t1 = st.pop();
                int t2 = st.pop();

                if (t1 > t2) {
                    st.push(t1 + t2);
                } else {
                    st.push(t2);
                    st.push(t1);
                    break;
                }
            }
        }
        return st.size();
    }
}

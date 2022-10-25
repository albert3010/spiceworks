package leetcode_problems.june_contests.S1;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution6 {

//    ["onepiece","onepiece(1)","onepiece(2)","onepiece(3)","onepiece"]

    @Test
    public void function1() {
        String sorted =
                Stream.of("edcba".split(""))
                        .sorted()
                        .collect(Collectors.joining());

        String[] names = {"onepiece", "onepiece(1)", "onepiecex", "onepiecex(2)", "onepiecex", "onepiece(2)", "onepiece", "onepiece(3)", "onepiece"};
//        System.out.println(getFolderNames(names));
        int[] a = {1,1,0,1,1,0,1,1};
        System.out.println(prisonAfterNDays(a, 2));
    }

    public int xorOperation(int n, int start) {

        int ans = start;

        for (int i = 1; i < n; i++) {
            ans = ans ^ (start + 2 * i);
        }
        return ans;
    }

    public String[] getFolderNames(String[] names) {
        int min=0;
//        Arrays.stream(names).map(e-> 2-e).max().isPresent();
        HashMap<String, Set<Integer>> numMap = new HashMap<>();
        HashMap<String, Integer> counter = new HashMap<>();
        Set<String> setx = new HashSet<>();
        int n = names.length;
        String[] ans = new String[n];
        Arrays.stream(names).forEach(e -> {
            String sm = e;
            if (e.contains("(")) {
                if (setx.contains(e)) {
                    setx.add(e);
                    Set<Integer> set = numMap.getOrDefault(e, new HashSet<>());
                    set.add(0);
                    numMap.put(sm, set);
                }
                int i = e.lastIndexOf("(");
                String ss = e.substring(i + 1, e.length() - 1);
                int nm = Integer.parseInt(ss);
                sm = e.substring(0, i);
                Set<Integer> set = numMap.getOrDefault(sm, new HashSet<>());
                set.add(nm);
                numMap.put(sm, set);
            } else {
                Set<Integer> set = numMap.getOrDefault(e, new HashSet<>());
                set.add(0);
                numMap.put(sm, set);
            }
            counter.put(sm, 0);
        });

        for (int i = 0; i < n; i++) {
            String ss = names[i];
            if (ss.contains("(")) {
                ans[i] = ss;
            } else {
                int cc = counter.get(ss);
                Set<Integer> set = numMap.get(ss);
                if (cc == 0) {
                    ans[i] = ss;
                } else {
                    while (set.contains(cc)) {
                        cc++;
                    }
                    String sk = Integer.toString(cc);
                    ans[i] = ss + "(" + sk + ")";
                }
                cc++;
                counter.put(ss, cc);
            }
        }
        return ans;
    }

    public int[] prisonAfterNDays(int[] cells, int N) {
        HashMap<String, Integer> map = new HashMap<>();
        HashMap<Integer, List<Integer>> post = new HashMap<>();
        int counter = 0;
        while (N > 0) {


            cells = next(cells);
            if(map.get(getHash(cells))!=null) break;
            counter++;
            N--;
            map.put(getHash(cells), counter);
            List<Integer> t = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                t.add(cells[i]);
            }
            post.put(counter, t);

        }
        if (N == 0) return cells;
        int l = counter - map.get(getHash(cells)) + 1;

        int po = map.get(getHash(cells)) + (N % l) - 1;
        int[] a = new int[8];
        List<Integer> ans = post.get(po);
        for (int i = 0; i < 8; i++) {
            a[i] = ans.get(i);
        }
        return a;
    }

    int[] next(int[] cells) {
        int[] newCells = new int[8];
        for (int i = 0; i < 8; i++) {
            if (i == 0 || i == 7) {
                newCells[i] = 0;
            } else {
                newCells[i] = 0;
                if (cells[i - 1] == 1 && cells[i + 1] == 1) {
                    newCells[i] = 1;
                }
                if (cells[i - 1] == 0 && cells[i + 1] == 0) {
                    newCells[i] = 1;
                }
            }
        }
        return newCells;
    }

    String getHash(int[] cells) {
        return Arrays.toString(cells);
    }

}




package leetcode.june_contests.S1;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Solution6 {

//    ["onepiece","onepiece(1)","onepiece(2)","onepiece(3)","onepiece"]

    @Test
    public void function() {
        String[] names = {"onepiece", "onepiece(1)", "onepiecex","onepiecex(2)","onepiecex","onepiece(2)", "onepiece", "onepiece(3)", "onepiece"};
        System.out.println(getFolderNames(names));
    }

    public int xorOperation(int n, int start) {

        int ans = start;

        for (int i = 1; i < n; i++) {
            ans = ans ^ (start + 2 * i);
        }
        return ans;
    }

    public String[] getFolderNames(String[] names) {
        HashMap<String, Set<Integer>> numMap = new HashMap<>();
        HashMap<String, Integer> counter = new HashMap<>();
        Set<String> setx = new HashSet<>();
        int n = names.length;
        String[] ans = new String[n];
        Arrays.stream(names).forEach(e -> {
            String sm = e;
            if (e.contains("(")) {
                if(setx.contains(e)){
                    setx.add(e);
                    Set<Integer> set = numMap.getOrDefault(e, new HashSet<>());
                    set.add(0);
                    numMap.put(sm, set);
                }
                int i = e.lastIndexOf("(");
                String ss = e.substring(i+1, e.length() - 1);
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

}


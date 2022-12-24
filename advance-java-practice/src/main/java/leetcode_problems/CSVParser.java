package leetcode_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CSVParser {

    // "a,"b,",c,",d",l"
    List<String> getParsed(String s) {
        Stack<Character> stack = new Stack();
        List<String> ans = new ArrayList<>();
        int n = s.length();
        boolean open = false;
        int startIndex = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == ',') {
                if (!open) {
                    ans.add(s.substring(startIndex, i));
                    startIndex = i + 1;
                }
            }
            if (s.charAt(i) == '\"') {
                open = !open;
            }
        }
        if (startIndex < n) {
            ans.add(s.substring(startIndex, n));
        }
        return ans;

    }

    public static void main(String[] args) {
        CSVParser csvParser = new CSVParser();
//        System.out.println(csvParser.getParsed("a,\"b,\",c,\",d\",l"));
        List<String> ans = new ArrayList<>();

        System.out.println(csvParser.getParsed("\"b,t\",cdd,ffs,\"sd,d\"").size());
        System.out.println(csvParser.getParsed("\"b,t\",cdd,ffs,\"sd,d\""));
    }
}

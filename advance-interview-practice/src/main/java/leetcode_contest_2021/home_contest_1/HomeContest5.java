package leetcode_contest_2021.home_contest_1;

import org.junit.Test;

public class HomeContest5 {

    @Test
    public void test() {
//        System.out.println(solution(-100));
        solution("20hgf100dfdbvs12@sd8dff60");
    }

    public void solution(String s) {
        int l = s.length();
        String tmp = "";
        String rem = "";
        int sum = 0;
        for (int i = 0; i < l; i++) {

            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                tmp += s.charAt(i);
            } else {
                if (tmp != "") {
                    sum += Integer.parseInt(tmp);
                }
                tmp = "";
                rem += s.charAt(i);
            }
        }
        if (tmp != "") {
            sum += Integer.parseInt(tmp);
        }
        System.out.println(rem);
        System.out.println(sum);
    }

    public int solution(int N) {
        String ans = "";
        if (N < 0) {
            N = -N;
            boolean added = false;
            String n = String.valueOf(N);
            int l = n.length();
            for (int i = 0; i < l; i++) {
                char c = n.charAt(i);
                if (c <= '5') {
                    ans += c;
                } else {
                    ans += '5';
                    added = true;
                    ans = ans + n.substring(i);
                    break;
                }
            }
            if (!added) {
                ans += '5';
            }
            ans = "-" + ans;
        } else {
            String n = String.valueOf(N);
            boolean added = false;
            int l = n.length();
            for (int i = 0; i < l; i++) {
                char c = n.charAt(i);
                if (c <= '5') {
                    ans += '5';
                    ans = ans + n.substring(i);
                    added = true;
                    break;
                } else {
                    ans += c;
                }
            }
            if (!added) {
                ans += '5';
            }
        }
        return Integer.parseInt(ans);
    }


}

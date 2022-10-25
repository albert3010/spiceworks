package leetcode_problems.june_contests.S3;

import org.junit.Test;

public class testA1 {
    @Test
    public void test() {
        String[] grid = {"@..aA", "..B#.", "....b"};
        Solution solution = new Solution();
        System.out.println(solution.shortestPathAllKeys(grid));
    }

    public String addBinary(String a, String b) {
        int n = a.length()-1;
        System.out.println();
        int m = b.length()-1;
        int index = Math.max(n, m)+1;
        int carry = 0;
        StringBuilder ans = new StringBuilder(index+1);

        while (n >= 0 || m >= 0 || carry>0) {
            int c = n < 0 ? 0 : Integer.parseInt(a.substring(n, n + 1));
            int d = m < 0 ? 0 : Integer.parseInt(b.substring(m, m + 1));

            int sum = c + d + carry;
            n--;
            m--;
            if (sum >=2) {
                if(sum==3) ans.append('1');else ans.append('0');
                carry = 1;
            } else {
                char cc = Integer.toString(sum).charAt(0);
                ans.append(cc);
                carry = 0;
            }
            index--;

        }
        return ans.reverse().toString();
    }
}


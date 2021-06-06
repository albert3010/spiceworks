package leetcode_contest_2021.group1;

import org.junit.Test;

public class Contest_april {

    @Test
    public void ContestsSolution() {
        char mat[][] = {{'#', '.', '*', '#', '.', '*'},
                {'#', '#', '#', '.', '*', '#'},
                {'#', '.', '*', '.', '#', '*'}};
        char ans[][] = rotateTheBox(mat);
        int row = ans.length;
        int col = ans[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int minSwaps(String s) {
        int l = s.length();
        int zero = 0;
        int one = 0;
        int minSwap = -1;
        for (int i = 0; i < l; i++) {
            if (i % 2 == 0 && s.charAt(i) != '0') {
                one++;
            }
            if (i % 2 != 0 && s.charAt(i) != '1') {
                zero++;
            }
        }
        if (one == zero) {
            minSwap = one;
        }
        zero = 0;
        one = 0;
        for (int i = 0; i < l; i++) {
            if (i % 2 == 0 && s.charAt(i) != '1') {
                one++;
            }
            if (i % 2 != 0 && s.charAt(i) != '0') {
                zero++;
            }
        }
        if (one == zero) {
            if (minSwap == -1) {
                minSwap = one;
            } else {
                minSwap = Math.min(minSwap, one);
            }

        }
        return minSwap;
    }

    public int subsetXORSum(int[] nums) {
        int l = nums.length;
        int ans[] = new int[1];
        int xor = nums[0];
        dfs(nums, 0, l, ans, 0);

        return ans[0];
    }

    private void dfs(int[] nums, int i, int l, int ans[], int xor) {
        if (i == l) return;
        int xor1 = xor ^ nums[i + 1];
        ans[0] += xor1;
        dfs(nums, i + 1, l, ans, xor1);
        dfs(nums, i + 1, l, ans, xor);
    }

    public char[][] rotateTheBox(char[][] box) {
        int row = box.length;
        int col = box[0].length;
        char mat[][] = new char[col][row];
        char ans[][] = new char[col][row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mat[j][row - i - 1] = box[i][j];
                if (mat[j][row - i - 1] == '*') {
                    ans[j][row - i - 1] = mat[j][row - i - 1];
                }
            }
        }
        int row_ = col;
        int col_ = row;


        for (int j = 0; j < col_; j++) {
            int i = 0;
            while (i < row_) {

                int count = 0;
                int st = i;
                while (i < row_ && mat[i][j] != '*') {
                    if (mat[i][j] == '#') {
                        count++;
                    }
                    i++;
                }
                int ll = i - st - count;
                int y = 0;
                for (int k = st; k < i; k++) {
                    if (ll > 0 && y < ll) {
                        ans[k][j] = '.';
                        y++;
                    } else {
                        ans[k][j] = '#';
                    }
                }
                i++;
            }
        }
        return ans;
    }

    public int[] memLeak(int memory1, int memory2) {
        int time = 1;
        int ans[] = new int[3];
        while (true) {
            if (memory1 < memory2) {
                if (memory2 >= time) {
                    memory2 -= time;
                } else {
                    break;
                }
            } else {
                if (memory1 >= time) {
                    memory1 -= time;
                } else {
                    break;
                }
            }
            time++;
        }
        ans[0] = time;
        ans[1] = memory1;
        ans[0] = memory2;
        return ans;
    }


    public String sortSentence(String s) {

        String[] ss = s.split(" ");
        int l = ss.length;
        String a[] = new String[10];
        for (int i = 0; i < l; i++) {
            int m = ss[i].length();
            int n = Integer.parseInt(ss[i].charAt(m - 1) + "");
            a[n] = ss[i].substring(m);
        }
        String ans = "";
        for (int i = 0; i < 10; i++) {
            if (a[i] != null) {
                ans += a[i];
            }
        }
        return ans;
    }

    public int maxDistance(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int i = 0;
        int j = 0;
        int ans = 0;

        while (true) {
            if (nums1[i] <= nums2[j]) {
                if (i < j) {
                    ans = Math.max(j - i, ans);
                    System.out.println(i + "__" + j + "__" + ans);
                }
                j++;
            }
            if (nums1[i] > nums2[j]) {
                i++;
            }
            if (i == n || j == m) {
                break;
            }
        }
        return ans;
    }


    public int maximumPopulation(int[][] logs) {
        int n = logs.length;
        int space[] = new int[2055];

        for (int i = 0; i < n; i++) {
            space[logs[i][0]] += 1;
            space[logs[i][1]] -= -1;
        }
        int ans = logs[0][1];
        int max = 0;
        int count = 0;
        for (int i = 1950; i <= 2050; i++) {
            count += space[i];
            if (count > max) {
                max = count;
                System.out.println(max);
                ans = i;
            }
        }
        return ans;
    }


}

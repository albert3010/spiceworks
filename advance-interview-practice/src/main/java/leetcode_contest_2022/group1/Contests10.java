package leetcode_contest_2022.group1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Contests10 {

    @Test
    public void test() {
        int[][] trip = {{2, 5, 3}, {1, 8, 4}, {1, 7, 5}, {1, 7, 4}};
        int[] target = {2, 7, 5};
//        System.out.println(largestMagicSquare(grid));
//        System.out.println(mergeTriplets(trip, target));


        System.out.println(numberOfRounds("23:59", "23:58"));
    }

    public int numberOfRounds(String startTime, String finishTime) {

        List<String> times = new ArrayList<>();
        int s = 0;
        String[] quarter = {"00", "15", "30", "45"};
        for (int i = 0; i < 24; i++) {
            String zero = "";
            if (i < 10) {
                zero = "0";
            }
            String start = zero + s + ":";
            for (int j = 0; j < 4; j++) {
                times.add(start + quarter[j]);
            }
            s++;
        }
        List<String> times2 = new ArrayList<>();
        times2.addAll(times);
        times2.addAll(times);
        int n = times2.size();
        int st = 0;
        int end = times.size() - 1;
        if (startTime.compareTo(finishTime) < 0) {
            int in = 0;
            for (String ss : times2) {

                if (ss.compareTo(startTime) < 0) {
                    st = in + 1;
                }
                if (ss.compareTo(startTime) == 0) {
                    st = in;
                }
                if (finishTime.compareTo(ss) == 0) {
                    end = in;
                }
                if (finishTime.compareTo(ss) < 0) {
                    end = in - 1;
                    break;
                }

                in++;
                if (in == n / 2) break;
            }
        } else {
            int m = times2.size();
            st =96;
            for (int i = m / 2 - 1; i >= 0; i--) {
                if (startTime.compareTo(times2.get(i)) < 0) {
                    st = i;
                }
                if (times2.get(i).compareTo(startTime) == 0) {
                    st = i;
                }
            }
            end = 192;
            for (int i = m / 2; i < m; i++) {
                if (finishTime.compareTo(times2.get(i)) == 0) {
                    end = i;
                }
                if (finishTime.compareTo(times2.get(i)) < 0) {
                    end = i - 1;
                    break;
                }

            }
        }
        return end - st;
    }

    public String largestOddNumber(String num) {
        int l = num.length();
        int index = -1;
        StringBuilder ans = new StringBuilder("");
        int max = 0;
        for (int i = 0; i < l; i++) {
            if (num.charAt(i) == '1' || num.charAt(i) == '3' || num.charAt(i) == '5' || num.charAt(i) == '7' || num.charAt(i) == '9') {
                index = i;
            }
        }


        return num.substring(0, index);
    }

    class NodeT implements Comparable<NodeT> {
        int a;
        int b;
        int c;

        public NodeT(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(NodeT o) {
            if (this.a == o.a) {
                if (this.b == o.b) {
                    return this.c - o.c;
                } else {
                    return this.b - o.b;
                }
            }
            return this.a - o.a;
        }
    }

    public boolean mergeTriplets(int[][] triplets, int[] target) {
        List<NodeT> list = new ArrayList<>();
        int n = triplets.length;
        for (int i = 0; i < n; i++) {
            if (!(triplets[i][0] > target[0] || triplets[i][1] > target[1] || triplets[i][2] > target[2])) {
                list.add(new NodeT(triplets[i][0], triplets[i][1], triplets[i][2]));
            }
        }
        Collections.sort(list);
        return true;
    }

    public boolean makeEqual(String[] words) {
        int[] count = new int[26];
        int n = words.length;

        for (int i = 0; i < n; i++) {
            int t = words[i].length();
            for (int j = 0; j < t; j++) {
                count[words[i].charAt(j) - 'a']++;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (count[i] % n != 0) {
                return false;
            }
        }
        return true;
    }

    public int largestMagicSquare(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[][] rowSum = new int[row + 1][col + 1];
        int[][] colSum = new int[row + 1][col + 1];

        for (int i = 0; i < row; i++) {
            int sum = 0;
            for (int j = 0; j < col; j++) {
                sum += grid[i][j];
                rowSum[i + 1][j + 1] = sum;
            }
        }
        for (int i = 0; i < col; i++) {
            int sum = 0;
            for (int j = 0; j < row; j++) {
                sum += grid[j][i];
                colSum[j + 1][i + 1] = sum;
            }
        }
        int maxSize = 1;
        for (int r1 = 0; r1 < row; r1++) {
            for (int r2 = r1 + 1; r2 < row; r2++) {
                for (int col1 = 0; col1 < col; col1++) {
                    for (int col2 = col1 + 1; col2 < col; col2++) {
                        boolean f = check(r1, r2, col1, col2, rowSum, colSum, grid, row, col);
                        if (f) {
                            maxSize = Math.max(maxSize, r2 - r1 + 1);
                        }
                    }
                }
            }

        }
        return maxSize;
    }

    private boolean check(int r1, int r2, int col1, int col2, int[][] rowSum, int[][] colSum, int[][] grid, int row, int col) {
        if (r2 - r1 != col2 - col1) return false;
        System.out.println();
        int sum = rowSum[r1 + 1][col2 + 1] - rowSum[r1 + 1][col1 + 1 - 1];
        for (int i = r1; i <= r2; i++) {
            int tmp = rowSum[i + 1][col2 + 1] - rowSum[i + 1][col1 + 1 - 1];
            if (tmp != sum) return false;
        }
        for (int i = col1; i <= col2; i++) {
            int tmp = colSum[r2 + 1][i + 1] - colSum[r1 + 1 - 1][i + 1];
            if (tmp != sum) return false;
        }
        int s1 = 0;
        for (int i = r1, j = col1; i <= r2; i++, j++) {
            s1 += grid[i][j];
        }
        if (s1 != sum) return false;
        s1 = 0;
        for (int i = r2, j = col1; i >= r1; i--, j++) {
            s1 += grid[i][j];
        }
        if (s1 != sum) return false;
        return true;
    }


    public int chalkReplacer(int[] chalk, int k) {
        int n = chalk.length;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += chalk[i];
        }
        long rem = k % sum;
        if (rem == 0) return 0;
        int s1 = 0;
        for (int i = 0; i < n; i++) {
            if (chalk[i] > rem) {
                return i;
            } else {
                rem -= chalk[i];
            }
        }
        return n - 1;
    }

    public boolean isCovered(int[][] ranges, int left, int right) {
        int n = ranges.length;

        for (int i = left; i <= right; i++) {
            boolean f = false;
            for (int j = 0; j < n; j++) {
                if (i >= ranges[j][0] && i <= ranges[j][1]) {
                    f = true;
                    break;
                }
            }
            if (!f) return false;
        }
        return true;
    }

}

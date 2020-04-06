package leetcode.contests.ContetsAE;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Contests1 {

    @Test


    public void ContestsSolution() {


//        System.out.println(canConstruct("qlkzenwmmnpkopu", 15));
//        System.out.println(numSteps("1101"));
//        System.out.println(longestDiverseString(0, 8, 11));
        System.out.println(checkOverlap(1415, 807,-784,-733,623,-533, 1005));
//        System.out.println(checkOverlap1(1415, 807,-784,-733,623,-533, 1005));
//        1415
//        807
//                -784
//                -733
//        623
//                -533
//        1005

    }

    public String longestDiverseString(int a, int b, int c) {
        int arr[] = {a, b, c};
        StringBuilder ans = new StringBuilder();
        int last = -1;
        int lastmax = -1;
        while (arr[0] + arr[1] + arr[2] > 0) {
            int index = getMaxIndex(arr, last);
            if (index == -1) break;

            if ((arr[index] >= 2 && last == -1) || (arr[index] >= 2 && (last != -1 && arr[last] <= arr[index]))) {
                char cc = (char) ('a' + index);
                ans.append(cc);
                ans.append(cc);
                arr[index] -= 2;
            } else {
                char cc = (char) ('a' + index);
                ans.append(cc);
                arr[index]--;
            }
            last = index;


        }
        return ans.toString();
    }

    public int getMaxIndex(int arr[], int ex) {
        if (ex == -1) {
            int v = Math.max(arr[0], Math.max(arr[1], arr[2]));
            if (v == arr[0]) return 0;
            if (v == arr[1]) return 1;
            if (v == arr[2]) return 2;
        }
        if (ex == 0) {
            if (Math.max(arr[1], arr[2]) == 0) return -1;

            if (arr[1] > arr[2]) {
                return 1;
            }
            return 2;
        }
        if (ex == 1) {
            if (Math.max(arr[0], arr[2]) == 0) return -1;
            if (arr[0] > arr[2]) {
                return 0;
            }
            return 2;
        }
        if (Math.max(arr[0], arr[1]) == 0) return -1;

        if (arr[0] > arr[1]) {
            return 0;
        }
        return 1;
    }

    public int numSteps(String s) {
        s = "0" + s;
        int steps = 0;
        StringBuilder str = new StringBuilder();
        str.append(s);
        while (true) {
            int n = str.length();
            steps++;
            if (str.charAt(n - 1) == '0') {
                str.deleteCharAt(n - 1);
            } else {
                for (int i = n - 1; i >= 0; i--) {
                    if (str.charAt(i) == '1') {
                        str.setCharAt(i, '0');
                    } else {
                        str.setCharAt(i, '1');
                        break;
                    }
                }

            }

            s = str.toString();
            if (s.equals("01") || s.equals("1")) {
                return steps;
            }
        }
    }

    public List<Integer> minSubsequence(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        int n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            sum += nums[i];
        }
        int s2 = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            s2 += nums[i];
            int s1 = sum - s2;
            if (s2 > s1) {
                for (int j = n - 1; j >= i; j--) {
                    ans.add(nums[j]);
                }
                break;
            }
        }
        return ans;
    }

    public boolean checkOverlap(int radius, int x_center, int y_center, int x1, int y1, int x2, int y2) {
        int xmin = Math.min(x1, x2);
        int xmax = Math.max(x1, x2);

        int ymin = Math.min(y1, y2);
        int ymax = Math.max(y1, y2);

        int xcleft = x_center - radius;
        int xcright = x_center + radius;

        int ycdown = y_center - radius;
        int yctop = y_center + radius;

        if ((xmin <= xcright && xmax >= xcleft) && y_center <= ymax && y_center >= ymin)
            return true;
        if ((ymin <= yctop && ymax >= ycdown) && x_center <= xmax && x_center >= xmin)
            return true;
        int a[] = {x1, x2};
        int b[] = {y1, y2};
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                int dd = (x_center - a[i]) * (x_center - a[i]) + (y_center - b[j]) * (y_center - b[j]);
                if ( dd <= radius * radius) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean canConstruct(String s, int k) {

        int a[] = new int[26];
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int x = s.charAt(i) - 'a';
            a[x]++;
        }
        int twoPairs = 0;
        int uniqe = 0;
        for (int i = 0; i < 26; i++) {
            int t = a[i];
            twoPairs += t / 2;
            uniqe += t % 2;
        }
        if (uniqe > k) return false;
        if (twoPairs == k) return true;

        if (twoPairs + uniqe == k) return true;
        if (twoPairs * 2 + uniqe >= k) return true;

        return false;
    }

    public int countLargestGroup(int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = -1;
        for (int i = 1; i <= n; i++) {
            int s = getSumOfDigit(i);
            int x = map.getOrDefault(s, 0) + 1;
            map.put(s, x);
            max = Math.max(x, max);
        }
        int count = 0;
        for (Integer i : map.keySet()) {
            if (map.get(i) == max) {
                count++;
            }
        }
        return count;
    }

    int getSumOfDigit(int n) {
        int s = 0;

        while (n > 0) {
            s += n % 10;
            n = n / 10;
        }
        return s;
    }


}

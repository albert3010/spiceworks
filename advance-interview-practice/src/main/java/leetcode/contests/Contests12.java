package leetcode.contests;

import org.junit.Test;

import java.util.Scanner;

public class Contests12 {


    @Test
    public void ContestsSolutionGFG() {
//        Scanner sc = new Scanner(System.in);
//        int t = sc.nextInt();
//        for (int i = 0; i < t; i++) {
//            int n = sc.nextInt();
//            int k = sc.nextInt();
//
////            int[] arr = new int[n];
////            for (int j = 0; j < n; j++) {
////                arr[j] = sc.nextInt();
////            }
//
//            System.out.println(distributeCandy(n, k));
//        }
        System.out.println(distributeCandy(5, 1000));
    }

    public int distributeCandy(int n, int k) {

        int sum = (n * (n + 1)) / 2;

        int rem = k % sum;
        int give = 0;
        for (int i = 1; i <= rem; i++) {
            if (rem >= i) {
                rem = rem - i;
            }

        }
        return rem;

    }


}

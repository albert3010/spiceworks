package algorithms.solutions;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by OMPRAKASH on 8/29/2016.
 */
public class SumBitDifference {

    public static void main(String[] args) {

        Integer n, val;
        Scanner reader = new Scanner(System.in);
        //size of list
        n = reader.nextInt();
        ArrayList<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            val = reader.nextInt();
            list.add(val);
        }

        int ans = SumBitDifferenceHelper(list);
        System.out.println("Sum of bit differences is " + ans);

        // Time complexity is O(k*n) where k is constant here k=32;
    }

    private static int SumBitDifferenceHelper(ArrayList<Integer> list) {
        // base case
        int result = 0;
        Integer n = list.size();
        // considering 32 bit integer
        for (int i = 0; i < 32; i++) {
            // count total ones at every bit of number
            int oneCount = 0, zeroCount;
            for (int j = 0; j < n; j++) {
                int val = list.get(j);
                //checking if ith bit is one or not
                if ((val & (1 << i)) != 0)
                    oneCount++;
            }
            zeroCount = n - oneCount;
            // multiplying oneCount, countZero and 2. The reason for multiplying 2 is (a,b) and (b,a) are different.
            result += (oneCount * zeroCount * 2);
        }
        return result;
    }
}
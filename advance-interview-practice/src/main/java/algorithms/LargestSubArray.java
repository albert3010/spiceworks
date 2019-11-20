package algorithms;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by OMPRAKASH on 8/29/2016.
 */
public class LargestSubArray {

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

        int ans = getLongestLength(list);
        System.out.println("Length of the longest contiguous subarray is "+ans);
        //Time complexity is O(n^2)
    }

    private static int getLongestLength(ArrayList<Integer> list) {

        int size = list.size();
        if (size == 0) return 0;
        // As if list is not null
        int maxLength = 1;
        int min, max;
        for (int i = 0; i < size - 1; i++) {
            // base case for min and max
            min = list.get(i);
            max = min;
            for (int j = i + 1; j < size; j++) {
                // get min and max till jth index
                min = Math.min(min, list.get(j));
                max = Math.max(max, list.get(j));

                // if difference of min and max are equal to difference of array index
                // than it is continues from i to j( since values are distinct )
                if (j - i == (max - min))
                    maxLength = Math.max(maxLength, max - min + 1);
            }
        }
        return maxLength;
    }
}

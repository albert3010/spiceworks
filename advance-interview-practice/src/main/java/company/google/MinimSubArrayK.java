package company.google;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by oyadav on 12/5/16.
 */
// Find the minimum of every sub-array of size k in an array of size n.

public class MinimSubArrayK {


    public static void main(String[] args) {

        int[] ar = new int[]{9, 2, 3, 4, 1, 5, 6, 3};
        int k=3;
        minSlideArray(ar,k);

    }

    private static void minSlideArray(int [] a, int k){

        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < k; i++){

            while (!deque.isEmpty() && a[i]<= a[deque.getLast()])
            {
                deque.pollLast();
            }
            deque.add(i);
        }
        System.out.println(a[deque.getFirst()]+" - ");
        int n = a.length;
        for (int i = k; i < n; i++) {


            while (!deque.isEmpty() && deque.getFirst() <= i - k) {
                deque.pollFirst();
            }

            while (!deque.isEmpty() && a[i] <= a[deque.getLast()])
            {
                deque.pollLast();
            }
            deque.add(i);
            System.out.println(a[deque.getFirst()]+" - ");


        }
    }

}


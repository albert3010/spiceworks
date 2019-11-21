package algorithms.solutions;

import java.util.*;

/**
 * Created by OMPRAKASH on 9/17/2016.
 */
public class SwiggyTest {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);

        int n = reader.nextInt();
        int val;
        int max=1;
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            val = reader.nextInt();
            list.add(val);
        }

        derivableNumberHelper(list, n);
}
     private static void derivableNumberHelper(ArrayList<Integer> a, int n) {

        List<Boolean> dp = Arrays.asList(new Boolean[100000]);
        List<Boolean> b = Arrays.asList(new Boolean[100000]);

         int max = Collections.max(a);

        Collections.fill(dp, false);
        Collections.fill(b, false);
        Collections.sort(a);

        int val;

        for (int i = 0; i < n; i++) {

            val = a.get(i);

            for (int k = 1; k*val <=max; k++) {

                if (dp.get(k) || b.get(k)) {
                    dp.set(k * val, true);
                }
            }

            b.set(val, true);
        }

        // time complexity O(k*n) where k is max of all numbers
        for (int k = 1; k <= max; k++) {

            if (!dp.get(k) && b.get(k)) {
                System.out.println(k);
            }
        }

    }
}

package algorithms.solutions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by OMPRAKASH on 9/8/2016.
 */
public class testclass {

    public static int  solve(int a[], int b[], int n){

        if(n==0) {
            return 0;
        }
        int relativeDiff = 0;
        for (int pos = 0; pos < n; pos++)
        {
            if (a[pos] != b[pos])
            {
                int pos2 = pos ;
                while (a[pos] != b[pos2])
                {
                    pos2++;
                }
                while (pos2 != pos)
                {
                    int t = b[pos2-1];
                    b[pos2-1] = b[pos2];
                    b[pos2] = t;

                    pos2--;
                    relativeDiff++;
                }

            }
        }
        return relativeDiff;
}

    public static void main(String args[] ) throws Exception {

         // Read input from stdin and provide input before running

        Scanner reader = new Scanner(System.in);  // Reading from System.in

        int N = reader.nextInt(); // Scans the

        int a[] = new int[N];
        int b[] = new int[N];

        for (int i = 0; i < N; i++) {
                a[i] = reader.nextInt();
        }

        for (int i = 0; i < N; i++) {
            b[i] = reader.nextInt();
        }

        int ans = solve(a,b,N);

        System.out.println(ans);
    }


}

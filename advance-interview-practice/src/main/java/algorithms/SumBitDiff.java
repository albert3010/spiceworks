package algorithms;


import java.util.Scanner;

/**
 * Created by OMPRAKASH on 8/26/2016.
 */
public class SumBitDiff {

    public static void main(String[] args) {

        int t,n;
        Scanner reader = new Scanner(System.in);

        t = reader.nextInt();

        while(t-->0){

          n= reader.nextInt();
          int a[] = new int[n];

          for(int i=0 ;i<n ;i++)
          {
              a[i] = reader.nextInt();
          }
            int ans= helperUtil(a, n);
            System.out.println(ans);
        }

    }
    private static int  helperUtil(int a[], int n){

        int ans=0;
        for(int i=0;i<n;i++)
          for(int j=0 ; j<n ; j++){

            int zor = a[i]^a[j];

            int count=0;

           while(zor>0){
             zor = zor&(zor-1);
               count++;
           }
         ans+=count;

          }
return ans;

    }

}

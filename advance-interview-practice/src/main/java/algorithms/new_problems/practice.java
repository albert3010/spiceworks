package algorithms.new_problems;

import org.junit.Test;

import java.util.*;

import static com.google.common.primitives.Ints.asList;

/**
 * Created by OMPRAKASH on 8/24/2016.
 */
public class practice {

    public static void main(String[] args) {
        int c[]={6,8,1};
        int x=25;
        int n=3;
         int ans = solve(c, n, x);
           System.out.println(ans);
    }

   public static int solve(int c[], int n, int x) {

        int dp[] = new int[x + 1];
        dp[0] = 0;

       int ans[] = new int[x + 1];

        for (int w = 1; w <= x; w++) {
            dp[w] = 999999;
            for (int i = 0; i < n; i++) {
                int xx=c[i];
                if (w >= c[i]) {

                    dp[w] = Math.min(dp[w] , dp[w - c[i]] + 1);

                    if (dp[w] >= (dp[w - c[i]]+1)) {
                        ans[w] = c[i];
                    }
                }
            }
        }
       System.out.println("--------  ");
        for(int w=x; w>0 ;){

            System.out.println(ans[w]+" ");
            w=w-ans[w];

            if(w<=0) break;
        }



        return dp[x];
    }
   @Test
   void heapBuildTest(){

      ArrayList<Integer> a= new ArrayList<>(Arrays.asList(1,5,3,4));
       int n= a.size();
        sortArray(a, n);
   }
  private void sortArray(ArrayList<Integer> a, int n){

      Map<Integer, Integer> map =new  HashMap<>();
      map.get(1);
      map.put(1,1);
     // map.entrySet()
      for(int i=n/2 ; i>=0; i--){
          buildHeap(a,i, n);
      }
   }
   private void buildHeap(ArrayList<Integer> a, int i, int n){

       int left = 2*i+1;
       int right = 2*i+2;

       int largest=i;

       if(left<n && a.get(left)>a.get(largest)){
           largest = left;
       }
       if(right<n && a.get(right)>a.get(largest)){
           largest = right;
       }

       if(largest!=i){
           Collections.swap(a,i,largest);
           buildHeap(a, largest, n);
       }
 }

}

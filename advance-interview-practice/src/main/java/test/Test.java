package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Test {

    public static void main(String[] args) {
//        int[] arr = {5, 4, 3, 2, 1};
        int[] arr = {2, 4, 1, 6, 12};
//        System.out.println(solve(arr));
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        List<Integer> ans = solve1(list, 1);
        System.out.println(ans);
//        for (Integer v :ans){
//            System.out.print(v+" ");
//        }
    }
    static List<Integer> solve1(List<Integer> arr, int k){
        int n = arr.size();
        List<Integer> reverse = new ArrayList<>();
        int i =0;
        if(k==0) return  arr;
       while (i< n){
           int end = Math.min(i+k-1, n-1);
           if(end-i+1==k){
               for (int j = end; j >=i; j--) {
                   reverse.add(arr.get(j));
               }
           }else{
               for (int j = i; j <=end; j++) {
                   reverse.add(arr.get(j));
               }
           }
           if(end==n-1) break;
           i = Math.min(i+k, n-1);

       }
       return reverse;
    }

    private static int solve(int arr[]) {
        int n = arr.length; 
        Stack<Integer> st = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            st.push(arr[i]);
            while (st.size() > 1) {
                int t1 = st.pop();
                int t2 = st.pop();

                if (t1 > t2) {
                    st.push(t1 + t2);
                }else {
                    st.push(t2);
                    st.push(t1);
                    break;
                }
            }
        }
        return st.size();
    }
}

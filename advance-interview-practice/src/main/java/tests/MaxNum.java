package tests;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.zip.Inflater;

/**
 * Created by OMPRAKASH on 9/24/2016.
 */
public class MaxNum {

    public static void main(String[] args) {

        ArrayList<String> lis = new ArrayList<>();

//        lis.add("98");
//        lis.add("78");
//        lis.add("79");
//        lis.add("9");
//
//        helperUtil(lis);
//
//        for(String s : lis){
//            System.out.println(s);
//        }
//
//        int a[] = {1, 2,3};
//
//        helper(a);

        }

    private static void helper(int a[]){

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int n= a.length;

        for(int i=0;i<n;i++){
            pq.add(a[i]);
        }
        int ans =0;
        while (pq.size()>=2){

            int x= pq.poll();
            int y= pq.poll();
            ans +=(x+y);
            pq.add(x+y);
        }

        if(pq.size()==1){
            ans+=pq.poll();
        }
        return;
    }
 private static void helperUtil(ArrayList<String> lis){

     Collections.sort(lis, new MyComparator() );


 }


}

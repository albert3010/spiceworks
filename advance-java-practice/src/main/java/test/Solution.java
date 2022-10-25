package test;

import leetcode_contest_2022.home_contest_1.HomeContest2;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

class Solution {
       public class ListNode {
       int val;
       ListNode next;
       ListNode() {}
       ListNode(int val) { this.val = val; }
       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }
    List<Integer> list = new ArrayList<>();
    int k =0;
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int [][] mat = new int [m][n];
        for(int i=0; i<m ;i++){
            for(int j=0; j< n ;j++){
                mat[i][j] = -1;
            }
        }
        while(head!=null){
            list.add(head.val);
            head = head.next;
        }
        int k =0;
        int r1 =0;
        int r2 =m-1;
        int c1 =0;
        int c2 = n-1;
        while(list.size() >k){
            updateMetrics(c1, c2, r1, false, mat);

            System.out.println("tet. "+list.get(k));
            r1++;
            updateMetrics(r1, r2, c2, true, mat);

            c2--;
            updateMetrics(c1, c2, r2, false, mat);

            r2--;
            updateMetrics(r1, r2, c1, true, mat);

            c1++;
        }
        return mat;

    }

    void updateMetrics(int s, int e, int g, boolean isRow, int [][] mat ){
        for(;s<=e; s++){
            if(list.size() >k){
                int val = list.get(k);
                if(isRow){
                    mat[s][g] = val;
                } else{
                    mat[g][s] = val;
                }
                // System.out.println("acv "+head.val);
                k++;
            }
        }
    }



}

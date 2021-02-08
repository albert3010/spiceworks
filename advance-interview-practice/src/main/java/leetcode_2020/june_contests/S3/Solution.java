package leetcode_2020.june_contests.S3;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Solution {
    int k;
    class Node {
        int i;
        int j;
        boolean [] keys ;
        int steps;
        Node(int i, int j, int steps){
            this.i =i;
            this.j =j;
            this.steps = steps;
            this.keys = new boolean[k];
        }
        Node(int i, int j, boolean [] keys, int steps){
            this.i =i;
            this.j =j;
            this.keys = keys.clone();
            this.steps = steps;
        }
        public void addKey(char c){
            keys[c-'a']= true;
        }
        boolean allKeysCollected(){
            for(int i=0;i<k;i++){
                if(!keys[i]){
                    return false;
                }
            }
            return true;
        }
        public boolean canUnlock(char c){
            return keys[c-'A'];
        }
        public String toString(){
            String s ="";
            for(int i=0;i<k;i++){
                if(keys[i]){
                    s+=i;
                }
            }
            return s;
        }
    }
        public int shortestPathAllKeys(String[] grid) {
        int m = grid.length;
        int n = grid[0].length();
        k = getTotalKeys(grid);
        Node nd_ = getStartNode(grid, '@');
        Queue<Node> queue = new LinkedList<>();
        queue.add(nd_);

        Set<String> visited = new LinkedHashSet<>();

        String ss = "#"+ nd_.i+ "#"+ nd_.j + "#" + nd_.toString();
        visited.add(ss);
        int [] dir = {1,0,-1, 0, 1 };

        while(queue.size()>0){
            Node nd = queue.poll();
            System.out.println(nd.steps + "_ "+ nd.i+ "_" + nd.j + "_" + nd.toString()) ;
            if(nd.allKeysCollected()){
                return nd.steps;
            }
            for(int k =0;k< 4; k++) {
                int ii = nd.i+dir[k];
                int jj = nd.j+dir[k+1];
                Node nn = checkIfValidPath(ii, jj, m, n, grid, visited, nd);

                if(nn!=null){
                    queue.add(nn);
                }
            }

        }
        return 0;
    }
    Node checkIfValidPath(int ii, int jj, int m, int n, String [] grid, Set<String> visited, Node nd){

        if(ii<0 || jj<0 || ii>=m || jj>=n || grid[ii].charAt(jj)=='#' || grid[ii].charAt(jj)=='@') return null;

        char gc = grid[ii].charAt(jj);

        if(gc >='a' && gc <= 'f'){
            Node nn = new Node(ii, jj, nd.keys, nd.steps+1);
            nn.addKey(gc);
            String ss = "#"+ ii+ "#"+ jj + "#" + nn.toString();
            if(!visited.contains(ss)){
                visited.add(ss);
                return nn;
            } else{
                return null;
            }
        }
        if(gc=='.') {
            String ss = "#"+ ii+ "#"+ jj + "#" + nd.toString();
            if(!visited.contains(ss)){
                visited.add(ss);
                return new Node(ii, jj, nd.keys, nd.steps+1);
            } else{
                return null;
            }
        }
        if(gc >='A' && gc <='F'){
            if(nd.canUnlock(gc)){
                String ss = "#"+ ii+ "#"+ jj + "#" + nd.toString();
                if(!visited.contains(ss)){
                    visited.add(ss);
                    return new Node(ii, jj, nd.keys, nd.steps+1);
                } else{
                    return null;
                }
            }
        }
        return null;
    }

    Node getStartNode(String[] grid, char c){
        int m = grid.length;
        for(int i=0; i<m ;i++){
            if(grid[i].indexOf(c)!=-1){
                return new Node(i, grid[i].indexOf(c), 0);
            }
        }
        return null;
    }
    int getTotalKeys(String[] grid){
        int t=0;
        int m = grid.length;
        int n = grid[0].length();
        for(int i=0; i<m ;i++){
            for(int j=0; j<n ;j++){
                if(grid[i].charAt(j)>= 'a' && grid[i].charAt(j) <= 'f'){
                    t++;
                }
            }
        }
        return t;
    }
}

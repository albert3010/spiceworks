package leetcode_contest_2022.home_contest_1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    class UF{
        Map<String, String> parent = new HashMap<>();
        Map<String, Integer> size = new HashMap<>();

        UF(int[][] grid){
            int m = grid.length;
            int n = grid[0].length;
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    if(grid[i][j]==1){
                        String key =i+"#"+j;
                        parent.put(key, key);
                        size.put(key,1);
                    }
                }
            }
        }
        void merge(String key1, String key2){
            String p1 = getParent(key1);
            String p2 = getParent(key2);
            if(p1.equals(p2)) return;
            parent.put(p1, p2);
            size.put(p2, size.get(p1)+size.get(p2));
        }

        String getParent(String key){
            if(key.equals(parent.get(key))) return key;
            String k = getParent(parent.get(key));
            parent.put(key, k);
            return k;
        }
        int getSize(String key){
            return size.getOrDefault(getParent(key),0);
        }
    }
    public int largestIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int []dir = {1,0,-1,0,1};
        UF uf = new UF(grid);

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]==0) continue;
                for(int k=0; k<4; k++){
                    int ii = i+dir[k];
                    int jj = j+dir[k+1];
                    if(ii>=0 && ii<m && jj>=0 && jj<n && grid[ii][jj]==1){
                        if(j>2){
                            int kk=0;
                        }
                        uf.merge(getKey(i,j), getKey(ii,jj));
                    }
                }
            }
        }

        int ans =0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]==0){
                    Set<String> set = new HashSet<>();
                    for(int k=0; k<4; k++){
                        int ii = i+dir[k];
                        int jj = j+dir[k+1];
                        if(ii>=0 && ii<m && jj>=0 && jj<n && grid[ii][jj]==1){
                            set.add(uf.getParent(getKey(ii,jj)));
                        }
                    }
                    int area =1;
                    for(String key : set){
                        area+=uf.getSize(key);
                    }
                    ans = Math.max(area, ans);
                }else{
                    ans = Math.max(uf.getSize(getKey(i,j)), ans);
                }
            }
        }
        return ans;
    }
    String getKey(int i, int j){
        return i+"#"+j;
    }
}
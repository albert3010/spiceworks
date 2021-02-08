package leetcode_2020.problems1;

import java.util.*;

public class AlternateColorPath {

    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {

        Map<Integer, List<Integer>> redPath = new HashMap();
        Map<Integer,List<Integer>> bluePath = new HashMap();

        for(int i=0;i<red_edges.length;i++){
            if(!redPath.containsKey(red_edges[i][0])){
                redPath.put(red_edges[i][0],new ArrayList<Integer>());
            }
            redPath.get(red_edges[i][0]).add(red_edges[i][1]);
        }

        for(int i=0;i<blue_edges.length;i++){
            if(!bluePath.containsKey(blue_edges[i][0])){
                bluePath.put(blue_edges[i][0],new ArrayList<Integer>());
            }
            bluePath.get(blue_edges[i][0]).add(blue_edges[i][1]);
        }
        int[] minArray= new int[n];
        Arrays.fill(minArray,Integer.MAX_VALUE);
        minArray[0]=0;

        boolean[] redVisited=new boolean[n];
        boolean[] blueVisited=new boolean[n];
        Queue<Integer> queue = new LinkedList();
        queue.add(0);
        bfs(redPath,bluePath,minArray,redVisited,blueVisited,1,queue,0);
        Arrays.fill(redVisited,false);
        Arrays.fill(blueVisited,false);
        queue.add(0);
        bfs(redPath,bluePath,minArray,redVisited,blueVisited,-1,queue,0);

        return Arrays.stream(minArray).map(i ->{
            if(i==Integer.MAX_VALUE){
                return -1;
            }
            return i;
        }).toArray();

    }

    public  void bfs(Map<Integer, List<Integer>> redPath, Map<Integer,List<Integer>> bluePath,int[] minArray,boolean[] redVisited,boolean[] blueVisited,int color,Queue<Integer> queue,int depth){

        int queueSize=queue.size();
        for(int i=0;i<queueSize;i++){
            int node = queue.poll();
            minArray[node]=Math.min(depth,minArray[node]);
            if(color==1){
                blueVisited[node]=true;
                if(redPath.containsKey(node))
                    for(int k : redPath.get(node)){
                        if(!redVisited[k])
                            queue.add(k);
                    }
            }else{
                redVisited[node]=true;
                if(bluePath.containsKey(node))
                    for(int k : bluePath.get(node)){
                        if(!blueVisited[k])
                            queue.add(k);
                    }
            }

        }
        if(queue.size()>0)
            bfs(redPath,bluePath,minArray,redVisited,blueVisited,-1*color,queue,depth+1);

    }
}

package leetcode_contest_2021;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {
    class Node{

        String str;
        int value;
        Node(String str,int value){
            this.str=str;
            this.value=value;
        }
    }
    PriorityQueue<Node> pq;
    HashMap<String,Node> map=new HashMap<>();

    public void assign(String inputStr,int value){
        if(!map.containsKey(inputStr)){
            Node node=new Node(inputStr,value);
            map.put(inputStr,node);
            pq.add(node);
        }else{
            pq.remove(map.get(inputStr));
            Node node=new Node(inputStr,value);
            map.put(inputStr,node);
            pq.add(node);
        }

    }

    public void delete(String inputStr){
        pq.remove(map.get(inputStr));
        map.remove(inputStr);
    }
    public void getScore(String inputStr){
        if(!map.containsKey(inputStr)){
            System.out.println(0);
        }else{
            System.out.println(map.get(inputStr).value);
        }

    }
    public void maxScore(){
        System.out.println(pq.peek().str+" "+pq.peek().value);
    }
    public void perforQueries(){

        pq=new PriorityQueue<>((Node a,Node b)->{
            if(a.value==b.value){
                return a.str.compareTo(b.str);
            }
            return b.value-a.value;
        });
        Scanner scan=new Scanner(System.in);
        int N= scan.nextInt();

        String[] allQuery=new String[N];

        for(int i=0;i<allQuery.length;i++){
            allQuery[i]=scan.nextLine();
            String[] query=allQuery[i].split("\\s+");

            if(query[0].equals("A")){
                assign(query[1],Integer.parseInt(query[2]));
            }else if(query[0].equals("D")){
                delete(query[1]);
            }else if(query[0].equals("M")){
                maxScore();
            }else if(query[0].equals("G")){
                getScore(query[1]);
            }
        }
    }
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */

        Solution solution=new Solution();
        solution.perforQueries();
    }
}
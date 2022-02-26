package leetcode_problems.contests_2020.ContetAD;

import org.junit.Test;

import java.util.HashMap;

public class Contests14 {

    class Node {
        String a;
        int b;

        Node(String a, int b) {
            this.a = a;
            this.b = b;
        }
    }
    class NodeX implements Comparable<NodeX> {
        int a, b;

        NodeX(int x, int y) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(NodeX p) {
            return this.a - p.b;
        }
    }

    class Point {
        int a;
        int b;

        Point(int a, int b) {
            this.a = a;
            this.b = b;
        }

        void addTime(int t) {
            this.a += t;
        }

        void addCount() {
            this.b++;
        }
    }

    @Test
    public void ContestsSolution() {
        UndergroundSystem undergroundSystem = new UndergroundSystem();
        undergroundSystem.checkIn(45, "Leyton", 3);
        undergroundSystem.checkIn(32, "Paradise", 8);
        undergroundSystem.checkIn(27, "Leyton", 10);
        undergroundSystem.checkOut(45, "Waterloo", 15);
        undergroundSystem.checkOut(27, "Waterloo", 20);
        undergroundSystem.checkOut(32, "Cambridge", 22);
        System.out.println(undergroundSystem.getAverageTime("Paradise", "Cambridge"));
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));
        undergroundSystem.checkIn(10, "Leyton", 24);
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));          // return 11.0
        undergroundSystem.checkOut(10, "Waterloo", 38);
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));

    }

    public int numTeams(int[] rating) {

        int n = rating.length;
        int ans = 0;

        for(int i=1; i<n-1; i++){
            int a =0;
            int b=0;
            int c =0;
            int d=0;
            for(int j=i+1; j<n; j++){

                if((rating[j]<rating[i])){
                    a++;
                }
                if((rating[j]>rating[i])){
                    b++;
                }

            }
            for(int j=0; j<i; j++){
                if((rating[j]<rating[i])){
                    c++;
                }
                if((rating[j]>rating[i])){
                    d++;
                }


            }
            ans+=a*d+b*c;
        }
        return ans;

    }

    class UndergroundSystem {
        HashMap<Integer, Node> map;
        HashMap<String, HashMap<String, Point>> sysMap;

        public UndergroundSystem() {
            this.map = new HashMap<>();
            this.sysMap = new HashMap<>();
        }

        public void checkIn(int id, String stationName, int t) {
            map.put(id, new Node(stationName, t));
        }

        public void checkOut(int id, String stationName, int t) {
            Node nd = map.get(id);
            int time = t - nd.b;

            HashMap<String, Point> map_ = sysMap.getOrDefault(stationName, new HashMap<>());
            Point p = map_.getOrDefault(nd.a, new Point(0, 0));
            p.addCount();
            p.addTime(time);
            map_.put(nd.a, p);
            sysMap.put(stationName, map_);
        }

        public double getAverageTime(String startStation, String endStation) {
            Point p = sysMap.get(endStation).get(startStation);
            return (p.a + 0.0) / p.b;
        }
    }


}

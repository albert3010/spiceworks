package leetcode_contest_2022.contest1;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class StockPrice {

    Map<Integer, Integer> map;
    PriorityQueue<Pair<Integer, Integer>> min = new PriorityQueue<>((x, y) -> x.fst - y.fst);
    PriorityQueue<Pair<Integer, Integer>> max = new PriorityQueue<>((x, y) -> y.fst - x.fst);
    int latestTime = 0;

    public StockPrice() {
        map = new HashMap<>();
    }

    public void update(int timestamp, int price) {
        latestTime = Math.max(timestamp, latestTime);
        map.put(timestamp, price);
        min.add(new Pair<>(timestamp, price));
        max.add(new Pair<>(timestamp, price));
    }

    public int current() {
        return map.getOrDefault(latestTime, 0);
    }

    public int maximum() {
        while (!map.get(max.peek().fst).equals(max.peek().snd)) {
            System.out.println(max.poll());
        }
        return max.peek().fst;
    }

    public int minimum() {
        while (!map.get(min.peek().fst).equals(min.peek().snd)) {
            min.poll();
        }
        return min.peek().fst;
    }
    public class Pair<A, B> {

        public final A fst;
        public final B snd;

        public Pair(A fst, B snd) {
            this.fst = fst;
            this.snd = snd;
        }
    }
}

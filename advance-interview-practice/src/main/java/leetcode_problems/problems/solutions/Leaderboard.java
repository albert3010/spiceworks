package leetcode_problems.problems.solutions;

import java.util.*;

public class Leaderboard {
    HashMap<Integer, Integer> leaderboard;

    public Leaderboard() {
        this.leaderboard = new HashMap<>();
    }

    public void addScore(int playerId, int score) {
        if (Objects.isNull(leaderboard.get(playerId))) {
            leaderboard.put(playerId, score);
        } else {
            leaderboard.put(playerId, leaderboard.get(playerId) + score);
        }
    }

    public int top(int K) {
        Queue<Integer> pQueue = new PriorityQueue<>((x,y) -> y-x);

        for (Integer key : leaderboard.keySet()) {
            pQueue.offer(leaderboard.get(key));
        }
        int sum = 0;
        while (!pQueue.isEmpty() && K--> 0) {
            sum += pQueue.poll();
        }
        return sum;
    }

    public void reset(int playerId) {
        leaderboard.remove(playerId);

    }
}

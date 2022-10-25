package practice.Hackethon;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

@AllArgsConstructor
public class HackathonApp {


    public List<String> findWinner(List<Vote> votes) {

        HashMap<String, Integer> scoreMap = new HashMap<>();

        for (Vote vote : votes) {
            for (TeamRating teamRating : vote.getTeamRatings()) {
                String name = teamRating.getTeamName();
                scoreMap.put(name,
                        scoreMap.getOrDefault(name, 0) + teamRating.getRatingPoints().getPoint());
            }
        }
        PriorityQueue<TeamScore> scores = new PriorityQueue<>((t1, t2) -> t2.getScore() - t1.getScore());

        for (String teamName : scoreMap.keySet()) {
            scores.add(new TeamScore(teamName, scoreMap.get(teamName)));
        }
        List<String> topList = new ArrayList<>();

        while (!scores.isEmpty()) {
            topList.add(scores.poll().getTeamName());
        }
        return topList;
    }

}

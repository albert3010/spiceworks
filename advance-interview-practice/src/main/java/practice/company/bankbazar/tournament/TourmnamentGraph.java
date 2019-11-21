package practice.company.bankbazar.tournament;

import java.util.*;

/**
 * Created by OMPRAKASH on 9/30/2016.
 */
public class TourmnamentGraph {

    private static HashMap<Team, Set<Team>> tournamentGraph = new HashMap<>();

    public static HashMap<Team, Set<Team>> getTournamentGraph(List<TeamMatch> teamMatches) {

        teamMatches.forEach(e -> {
            Team win = e.getWinTeam();
            Team lost = e.getLostTeam();

            if (tournamentGraph.get(win) != null) {
                tournamentGraph.get(win).add(lost);

            } else {
                Set<Team> t = new HashSet<>();
                t.add(lost);
                tournamentGraph.put(win, t);
            }
        });
        return tournamentGraph;
    }
}
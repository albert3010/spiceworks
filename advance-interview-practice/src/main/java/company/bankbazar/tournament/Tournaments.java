package company.bankbazar.tournament;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Created by OMPRAKASH on 9/30/2016.
 */

public class Tournaments {

    List<TeamMatch> teamMatches;

    public Tournaments(List<TeamMatch> teamMatches) {
        this.teamMatches = teamMatches;
    }

    // win team will be always first in least
    public Stack<Team> getArrangedTeam(){

        HashMap<Team, Set<Team>> tournamentGraph = TourmnamentGraph.getTournamentGraph(teamMatches);

        //dfs
        HashMap<Team, Boolean> visitedTeams = new HashMap<>();

        Stack<Team> teamStack = new Stack<>();

        Set<Team> teams = tournamentGraph.keySet();

        for(Team team : teams){
            if(visitedTeams.get(team)==null){

                topologicalSort(team, tournamentGraph, visitedTeams, teamStack);
            }
        }
        return teamStack;
    }
    private void topologicalSort(Team team, HashMap<Team, Set<Team>> tournamentGraph, HashMap<Team, Boolean> visitedTeams, Stack<Team> teamStack ){

        visitedTeams.put(team, true);

        Set<Team> list = tournamentGraph.get(team);
        if(list!=null)
        for (Team team1 : list){
            if(visitedTeams.get(team1)==null)
            topologicalSort(team1, tournamentGraph, visitedTeams, teamStack);
        }

        teamStack.push(team);
    }

}

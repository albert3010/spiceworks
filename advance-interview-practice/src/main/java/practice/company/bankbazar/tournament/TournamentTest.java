package practice.company.bankbazar.tournament;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by OMPRAKASH on 9/30/2016.
 */
public class TournamentTest {

    @Test
    public void getArrangedTeams(){

        Team t1 = new Team(1,"t1");
        Team t2 = new Team(2,"t2");
        Team t3 = new Team(3,"t3");
        Team t4 = new Team(4,"t4");
        Team t5 = new Team(4,"t5");
        Team t6 = new Team(4,"t6");
        Team t7 = new Team(4,"t7");

        TeamMatch tm1 = new TeamMatch(t1,t3);
        TeamMatch tm2 = new TeamMatch(t1,t2);
        TeamMatch tm3 = new TeamMatch(t2,t4);
        TeamMatch tm4 = new TeamMatch(t2,t5);
        TeamMatch tm5 = new TeamMatch(t3,t5);
        TeamMatch tm6 = new TeamMatch(t3,t6);
        TeamMatch tm7 = new TeamMatch(t5,t7);
        TeamMatch tm8 = new TeamMatch(t6,t5);
        TeamMatch tm9 = new TeamMatch(t6,t7);

        List<TeamMatch> teamMatches = new ArrayList<>();

        teamMatches.add(tm1);
        teamMatches.add(tm2);
        teamMatches.add(tm3);
        teamMatches.add(tm4);
        teamMatches.add(tm5);
        teamMatches.add(tm6);
        teamMatches.add(tm7);
        teamMatches.add(tm8);
        teamMatches.add(tm9);

        Tournaments tournaments = new Tournaments(teamMatches);

       Stack<Team> teamStack = tournaments.getArrangedTeam();

        StringBuilder result = new StringBuilder();
        System.out.println(teamStack.size());
        while(!teamStack.isEmpty()){

            Team t = teamStack.pop();
            result.append(t.getName()+"  ");

        }
        System.out.println(result);
    }

}

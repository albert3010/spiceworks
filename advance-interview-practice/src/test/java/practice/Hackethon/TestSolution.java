package practice.Hackethon;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestSolution {

    @Test
    public void testTopNames(){
        List<Vote> votes = new ArrayList<>();
        TeamRating teamRating1 = new TeamRating("team1", RatingPoints.THIRD);
        TeamRating teamRating2 = new TeamRating("team2", RatingPoints.SECOND);
        TeamRating teamRating3 = new TeamRating("team3", RatingPoints.FIRST);

        List<TeamRating> teamRatingList1 = Arrays.asList(teamRating1,teamRating2, teamRating3);
        votes.add(new Vote(teamRatingList1));

        TeamRating teamRating4 = new TeamRating("team1", RatingPoints.THIRD);
        TeamRating teamRating5 = new TeamRating("team2", RatingPoints.SECOND);
        TeamRating teamRating6 = new TeamRating("team3", RatingPoints.FIRST);

        List<TeamRating> teamRatingList2 = Arrays.asList(teamRating4,teamRating5, teamRating6);
        votes.add(new Vote(teamRatingList2));

        HackathonApp  hackathonApp = new HackathonApp();

        List<String > names = hackathonApp.findWinner(votes);

        names.forEach(name -> System.out.println(name));
        // 6, 4 , 2
        Assert.assertEquals("team3", names.get(0));
        Assert.assertEquals("team2", names.get(1));
        Assert.assertEquals("team1", names.get(2));

    }

}

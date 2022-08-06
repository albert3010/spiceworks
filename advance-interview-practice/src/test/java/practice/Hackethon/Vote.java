package practice.Hackethon;

import lombok.Getter;

import java.util.List;

@Getter
public class Vote {

    private List<TeamRating> teamRatings;

    public Vote(List<TeamRating> teamRatings) {
        this.teamRatings = teamRatings;
    }
    public void addTeamRating(TeamRating teamRating){
        teamRatings.add(teamRating);
    }
}

package practice.Hackethon;

import lombok.Getter;

@Getter
public class TeamRating {
    private String teamName;
    private RatingPoints ratingPoints;

    public TeamRating(String teamName, RatingPoints ratingPoints) {
        this.teamName = teamName;
        this.ratingPoints = ratingPoints;
    }
}

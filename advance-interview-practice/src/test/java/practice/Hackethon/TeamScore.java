package practice.Hackethon;

import lombok.Getter;

@Getter
public class TeamScore {
    private String teamName;
    private Integer score;

    public TeamScore(String teamName, Integer score) {
        this.teamName = teamName;
        this.score = score;
    }

}

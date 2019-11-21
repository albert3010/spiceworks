package practice.company.bankbazar.tournament;

/**
 * Created by OMPRAKASH on 9/30/2016.
 */

public class Team {

    private Integer teamId;
    private String name;

    public Team(Integer teamId, String name) {
        this.teamId = teamId;
        this.name = name;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public String getName() {
        return name;
    }
}


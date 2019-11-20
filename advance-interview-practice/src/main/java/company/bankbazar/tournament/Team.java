package company.bankbazar.tournament;

import lombok.AllArgsConstructor;
import lombok.Getter;

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


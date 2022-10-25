package practice.company.bankbazar.tournament;

/**
 * Created by OMPRAKASH on 9/30/2016.
 */

public class TeamMatch {

    private Team winTeam;
    private Team LostTeam;

    public TeamMatch(Team winTeam, Team lostTeam) {
        this.winTeam = winTeam;
        LostTeam = lostTeam;
    }

    public Team getWinTeam() {
        return winTeam;
    }

    public Team getLostTeam() {
        return LostTeam;
    }
}

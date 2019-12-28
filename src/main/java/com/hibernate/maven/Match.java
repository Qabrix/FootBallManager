package com.hibernate.maven;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    @Column(name = "id_match")
    private int matchId;

    @Column(name = "id_team_1")
    private int teamOneId;

    @Column(name = "id_team_2")
    private int teamTwoId;

    @Column(name = "goals_team_1")
    private int goalsTeamOne;

    @Column(name = "goals_team_2")
    private int goalsTeamTwo;

    @Column(name = "match_date")
    private Date createdDate;

    @Column(name = "id_host")
    private int hostId;

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getTeamOneId() {
        return teamOneId;
    }

    public void setTeamOneId(int teamOneId) {
        this.teamOneId = teamOneId;
    }

    public int getTeamTwoId() {
        return teamTwoId;
    }

    public void setTeamTwoId(int teamTwoId) {
        this.teamTwoId = teamTwoId;
    }

    public int getGoalsTeamOne() {
        return goalsTeamOne;
    }

    public void setGoalsTeamOne(int goalsTeamOne) {
        this.goalsTeamOne = goalsTeamOne;
    }

    public int getGoalsTeamTwo() {
        return goalsTeamTwo;
    }

    public void setGoalsTeamTwo(int goalsTeamTwo) {
        this.goalsTeamTwo = goalsTeamTwo;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }
}



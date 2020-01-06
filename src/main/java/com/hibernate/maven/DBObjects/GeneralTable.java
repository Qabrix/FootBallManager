package com.hibernate.maven.DBObjects;

import org.hibernate.annotations.NamedQuery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "general_table")

@NamedQuery(name="get_all_general_results", query="from GeneralTable")

public class GeneralTable {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "points")
    private int points;

    @Column(name = "goals_for")
    private int goalsFor;

    @Column(name = "goals_against")
    private int goalsAgainst;

    @Column(name = "matches_played")
    private int matchesPlayed;

    public int getId() {
        return id;
    }
    public int getPoints() {
        return points;
    }
    public int getGoalsFor() {
        return goalsFor;
    }
    public int getGoalsAgainst() {
        return goalsAgainst;
    }
    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }
}



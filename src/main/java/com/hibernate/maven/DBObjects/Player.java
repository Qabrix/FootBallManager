package com.hibernate.maven.DBObjects;

import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "players")

@NamedNativeQuery(name="get_players", query="Select id, name, surname from players join squads on players.id = squads.player_id Where squads.team_id = :selectedTeam",
        resultClass = Player.class)

public class Player {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
}

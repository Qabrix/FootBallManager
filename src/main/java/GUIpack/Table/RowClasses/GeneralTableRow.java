package GUIpack.Table.RowClasses;


public class GeneralTableRow {
    private String team;
    private Integer points;
    private Integer goalsFor;
    private Integer goalsAgainst;
    private Integer matchesPlayed;

    public GeneralTableRow(String team, Integer points, Integer goalsFor, Integer goalsAgainst, Integer matchesPlayed) {
        this.team = team;
        this.points = points;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.matchesPlayed = matchesPlayed;
    }

    public String getTeam() { return team; }
    public Integer getPoints() { return points; }
    public Integer getGoalsFor() { return goalsFor; }
    public Integer getGoalsAgainst() { return goalsAgainst; }
    public Integer getMatchesPlayed() { return matchesPlayed; }
}

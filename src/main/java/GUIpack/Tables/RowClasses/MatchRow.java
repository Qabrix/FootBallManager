package GUIpack.Tables.RowClasses;


public class MatchRow {
    private Integer id;
    private String teamOne;
    private String teamTwo;
    private Integer goalsTeamOne;
    private Integer goalsTeamTwo;
    private String matchDate;
    private String host;

    public MatchRow(Integer id, String teamOne, String teamTwo, Integer goalsTeamOne, Integer goalsTeamTwo, String matchDate, String host) {
        this.id = id;
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.goalsTeamOne = goalsTeamOne;
        this.goalsTeamTwo = goalsTeamTwo;
        this.matchDate = matchDate;
        this.host = host;
    }

    public boolean teamOneHasWon(){ return goalsTeamOne > goalsTeamTwo; }
    public boolean teamTwoHasWon(){ return goalsTeamOne < goalsTeamTwo; }
    public boolean wasDraw(){ return goalsTeamOne.equals(goalsTeamTwo); }
    public Integer getId() {return id;}
    public String getTeamOne() { return teamOne; }
    public String getTeamTwo() { return teamTwo; }
    public Integer getGoalsTeamOne() { return goalsTeamOne; }
    public Integer getGoalsTeamTwo() { return goalsTeamTwo; }
    public String getMatchDate() { return matchDate; }
    public String getHost() { return host; }


}

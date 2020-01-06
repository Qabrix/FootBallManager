package GUIpack;
import GUIpack.Tables.Models.TeamTableModel;
import GUIpack.Tables.RowClasses.GeneralTableRow;
import GUIpack.Tables.CellRenderers.MatchCellRenderer;
import GUIpack.Tables.Models.GeneralTableModel;
import GUIpack.Tables.Models.MatchTableModel;
import GUIpack.Tables.RowClasses.MatchRow;
import GUIpack.Tables.RowClasses.TeamRow;
import com.hibernate.maven.DBObjects.GeneralTable;
import com.hibernate.maven.DBObjects.Match;
import com.hibernate.maven.DBObjects.Team;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static com.hibernate.maven.AppMain.hibSessionManager;

public abstract class GUI extends JFrame {
    private static final int DEFALUT_WIDTH = 1200,
                DEFALUT_HEIGHT = 900;

    protected static ArrayList<Match> matchList;
    protected static ArrayList<GeneralTable> generalTableList;
    protected static Map<Integer,String> teamsNameMap;

    private static GeneralTableModel generalTableModel;
    private static MatchTableModel matchModel;
    private static TeamTableModel teamModel;
    protected static JTable matchTable, generalTable, teamTable;;

    public GUI(){
        initializeGUI();
    }
    private void initializeGUI(){
        preSetContent();
        showMatches();
        showGeneralTable();
        showTeamTable();
    }
    private void preSetContent(){
        setSize(new Dimension(DEFALUT_WIDTH, DEFALUT_HEIGHT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setResizable(false);
    }
    protected void showGUI(){
        setVisible(true);
    }
    protected void setFields() {
        add(new JScrollPane(matchTable));
        add(new JScrollPane(generalTable));
        addTeams();
        addSortingMatchButton();
        addShowSquadButton();
        addSortingPointsButton();
        addRefreshButton();
    };
    //matches:
    private static void showMatches(){
        prepareMatchTable();
        matchList = new ArrayList<>();
        getMatches();
    }
    private static void prepareMatchTable(){
        matchModel = new MatchTableModel();
        matchTable = new JTable(matchModel);
        matchTable.setDefaultRenderer(String.class, new MatchCellRenderer());

        teamsNameMap = new HashMap<>();

        hibSessionManager.openSession();
        reloadTeams();

        hibSessionManager.getSession().close();
    }
    private static void getMatches() {
        hibSessionManager.openSession();
        List matches = hibSessionManager.getSession().getNamedQuery("get_all_matches").list();
        for(Object match : matches){
            Match curMatch = (Match) match;
            matchList.add(curMatch);
        }
        reloadMatches();
        hibSessionManager.getSession().close();
    }
    public static void reloadMatches(){
        resetMatchesModel();
        for(Match curMatch : matchList){
            Integer id = curMatch.getMatchId();
            String teamOne = teamsNameMap.get(curMatch.getTeamOneId());
            String teamTwo = teamsNameMap.get(curMatch.getTeamTwoId());
            Integer goalsTeamOne = curMatch.getGoalsTeamOne();
            Integer goalsTeamTwo = curMatch.getGoalsTeamTwo();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateFormat.format(curMatch.getMatchDate());
            String host =  teamsNameMap.get(curMatch.getHostId());
            matchModel.addMatch(new MatchRow(id, teamOne, teamTwo, goalsTeamOne, goalsTeamTwo, date, host));
        }
    }
    //general
    private static void prepareGeneralTable(){
        generalTableModel = new GeneralTableModel();
        generalTable = new JTable(generalTableModel);
    }

    private static void showGeneralTable(){
        prepareGeneralTable();
        generalTableList = new ArrayList<>();
        getGeneralTable();
    }

    private static void getGeneralTable() {
        hibSessionManager.openSession();
        List generalTables = hibSessionManager.getSession().getNamedQuery("get_all_general_results").list();
        for(Object gt : generalTables){
            GeneralTable curGeneralTable = (GeneralTable) gt;
            generalTableList.add(curGeneralTable);
        }
        reloadGeneralTable();
        hibSessionManager.getSession().close();
    }
    public static void reloadGeneralTable(){
        resetGeneralTableModel();
        for(GeneralTable curGeneralTable : generalTableList) {
            String team = teamsNameMap.get(curGeneralTable.getId());
            Integer points = curGeneralTable.getPoints();
            Integer goalsFor = curGeneralTable.getGoalsFor();
            Integer goalsAgainst = curGeneralTable.getGoalsAgainst();
            Integer matchesPlayed = curGeneralTable.getMatchesPlayed();
            generalTableModel.addGeneralRow(new GeneralTableRow(team, points, goalsFor, goalsAgainst, matchesPlayed));
        }
    }
    //teams
    private static void prepareTeamTable(){
        teamModel = new TeamTableModel();
        teamTable = new JTable(teamModel);
        teamTable.setSize(new Dimension(100,100));
    }
    private static void showTeamTable(){
        prepareTeamTable();
        for(Map.Entry<Integer, String> team: teamsNameMap.entrySet()){
            teamModel.addTeam(new TeamRow(team.getKey(), team.getValue()));
        }
    }
    protected void addTeams(){
        JScrollPane teamPane = new JScrollPane(teamTable);
        teamPane.setPreferredSize(new Dimension(300,300));
        add(teamPane);
    }
    //components
    protected void addShowSquadButton(){
        add(new ShowSquadButton());
    }
    protected void addSortingMatchButton(){
        add(new SortingMatchButton(matchList));
    }
    protected void addSortingPointsButton(){
        add(new SortingPointsButton(generalTableList));
    }
    protected void addRefreshButton(){
        add(new RefreshButton());
    }
    //resets
    private static void resetGeneralTableModel(){
        generalTableModel = new GeneralTableModel();
        generalTable.setModel(generalTableModel);
    }
    private static void resetMatchesModel(){
        matchModel = new MatchTableModel();
        matchTable.setModel(matchModel);
    }
    private static void resetTeamModel(){
        teamModel = new TeamTableModel();
        teamTable.setModel(teamModel);
    }
    //refreshing data
    public static void refreshData(){
        matchList.clear();
        generalTableList.clear();
        getMatches();
        getGeneralTable();
    }
    private static void reloadTeams(){
        List teams = hibSessionManager.getSession().getNamedQuery("get_all_teams").list();
        for(Object team : teams){
            Team curTeam = (Team) team;
            teamsNameMap.put(curTeam.getId(), curTeam.getName());
        }
    }
}

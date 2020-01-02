package GUIpack;
import GUIpack.Tables.RowClasses.GeneralTableRow;
import GUIpack.Tables.CellRenderers.MatchCellRenderer;
import GUIpack.Tables.Models.GeneralTableModel;
import GUIpack.Tables.Models.MatchTableModel;
import GUIpack.Tables.RowClasses.MatchRow;
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
    protected ArrayList<Match> matchList;
    protected ArrayList<GeneralTable> generalTableList;
    protected Map<Integer,String> teamsNameMap;

    private GeneralTableModel generalTableModel;
    private MatchTableModel matchModel;
    protected JTable matchTable, generalTable;

    public GUI(){
        initializeGUI();
    }
    private void initializeGUI(){
        preSetContent();
        showMatches();
        showGeneralTable();
    }
    private void preSetContent(){
        setSize(new Dimension(1000,800));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setResizable(false);
    }
    protected void showGUI(){
        setVisible(true);
    }
    protected abstract void setFields();
    //matches:
    private void showMatches(){
        prepareMatchTable();
        getMatches();
    }
    private void prepareMatchTable(){
        matchModel = new MatchTableModel();
        matchTable = new JTable(matchModel);
        matchTable.setDefaultRenderer(String.class, new MatchCellRenderer());

        teamsNameMap = new HashMap<>();

        hibSessionManager.openSession();
        List teams = hibSessionManager.getSession().getNamedQuery("get_all_teams").list();
        for(Object team : teams){
            Team curTeam = (Team) team;
            teamsNameMap.put(curTeam.getId(), curTeam.getName());
        }

        hibSessionManager.getSession().close();
    }
    private void getMatches() {
        matchList = new ArrayList<>();
        hibSessionManager.openSession();
        List matches = hibSessionManager.getSession().getNamedQuery("get_all_matches").list();
        for(Object match : matches){
            Match curMatch = (Match) match;
            matchList.add(curMatch);
            String teamOne = teamsNameMap.get(curMatch.getTeamOneId());
            String teamTwo = teamsNameMap.get(curMatch.getTeamTwoId());
            Integer goalsTeamOne = curMatch.getGoalsTeamOne();
            Integer goalsTeamTwo = curMatch.getGoalsTeamTwo();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            String date = dateFormat.format(curMatch.getMatchDate());
            String host =  teamsNameMap.get(curMatch.getHostId());
            matchModel.addMatch(new MatchRow(teamOne, teamTwo, goalsTeamOne, goalsTeamTwo, date, host));
        }
        hibSessionManager.getSession().close();
    }
    //general
    private void prepareGeneralTable(){
        generalTableModel = new GeneralTableModel();
        generalTable = new JTable(generalTableModel);
    }

    private void showGeneralTable(){
        prepareGeneralTable();
        getGeneralTable();
    }

    private void getGeneralTable() {
        generalTableList = new ArrayList<>();
        hibSessionManager.openSession();
        List generalTables = hibSessionManager.getSession().getNamedQuery("get_all_general_results").list();
        for(Object gt : generalTables){
            GeneralTable curGeneralTable = (GeneralTable) gt;
            generalTableList.add(curGeneralTable);
            String team =  teamsNameMap.get(curGeneralTable.getId());
            Integer points = curGeneralTable.getPoints();
            Integer goalsFor = curGeneralTable.getGoalsFor();
            Integer goalsAgainst = curGeneralTable.getGoalsAgainst();
            Integer matchesPlayed = curGeneralTable.getMatchesPlayed();

            generalTableModel.addGeneralRow(new GeneralTableRow(team, points, goalsFor, goalsAgainst, matchesPlayed));
        }
        hibSessionManager.getSession().close();
    }
}

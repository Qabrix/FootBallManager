package GUIpack;
import com.hibernate.maven.DBObjects.GeneralTable;
import com.hibernate.maven.DBObjects.Match;
import org.hibernate.query.Query;
import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import static com.hibernate.maven.AppMain.hibSessionManager;

public abstract class GUI extends JFrame {
    protected ArrayList<Match> matchList;
    protected ArrayList<GeneralTable> generalTableList;
    protected CustomTable generalTable;
    protected CustomTable matchesTable;
    public GUI(){
        initializeGUI();
    }
    private void initializeGUI(){
        presetContent();
        showMatches();
        showGeneralTable();
    }
    private void presetContent(){
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
        String []header = {"Team 1", "Team 2", "Goals Team 1", "Goals Team 2", "Date", "Host"};
        String [][]rec = new String[0][0];
        matchesTable = new CustomTable(header, rec);
    }
    private void getMatches() {
        matchList = new ArrayList<>();
        hibSessionManager.openSession();
        Query query = hibSessionManager.getSession().getNamedQuery("get_all_matches");
        Iterator it = query.iterate();
        while (it.hasNext()) {
            Match curMatch = (Match) it.next();
            matchList.add(curMatch);
            String teamOneId = Integer.toString(curMatch.getTeamOneId());
            String teamTwoId = Integer.toString(curMatch.getTeamTwoId());
            String goalsTeamOne = Integer.toString(curMatch.getGoalsTeamOne());
            String goalsTeamTwo = Integer.toString(curMatch.getGoalsTeamTwo());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            String date = dateFormat.format(curMatch.getMatchDate());
            String host = Integer.toString(curMatch.getHostId());
            matchesTable.getModel().addRow(new String[]{teamOneId, teamTwoId, goalsTeamOne, goalsTeamTwo, date, host});
        }
        hibSessionManager.getSession().close();
    }
    //general
    private void showGeneralTable(){
        prepareGeneralTable();
        getGeneralTable();
    }
    private void prepareGeneralTable(){
        String []header = {"Team", "Points", "Goals for", "Goals against", "Matches played"};
        String [][]rec = new String[0][0];
        generalTable = new CustomTable(header, rec);
    }
    private void getGeneralTable() {
        generalTableList = new ArrayList<>();
        hibSessionManager.openSession();
        Query query = hibSessionManager.getSession().getNamedQuery("get_all_results");
        Iterator it = query.iterate();
        while (it.hasNext()) {
            GeneralTable curGeneralTable = (GeneralTable)it.next();
            generalTableList.add(curGeneralTable);
            String teamId = Integer.toString(curGeneralTable.getId());
            String points = Integer.toString(curGeneralTable.getPoints());
            String goalsFor = Integer.toString(curGeneralTable.getGoalsFor());
            String goalsAgainst = Integer.toString(curGeneralTable.getGoalsAgainst());
            String matchesPlayed = Integer.toString(curGeneralTable.getMatchesPlayed());

            generalTable.getModel().addRow(new String[]{teamId, points, goalsFor, goalsAgainst, matchesPlayed});
        }
        hibSessionManager.getSession().close();
    }
}

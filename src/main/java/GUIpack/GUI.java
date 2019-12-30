package GUIpack;
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
    protected CustomTable generalTable;
    protected CustomTable matchesTable;
    public GUI(){
        initializeGUI();
    }
    private void initializeGUI(){
        presetContent();
        showMatches();
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
        Query query = hibSessionManager.getSession().createQuery("from Match");
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
    }
}

package GUIpack;

import com.hibernate.maven.DBObjects.GeneralTable;
import com.hibernate.maven.DBObjects.Match;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

public class ExportButton extends JButton implements ActionListener {
    private Exporter exporter;
    public ExportButton(){
        setText("Export");
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            exporter = new Exporter();
            writeMatches();
            writeTeams();
            writeGeneralTable();
            exporter.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //matches
    private void writeMatches() throws IOException {
        for(Match match : GUI.matchList){
            exporter.getWriter().write(matchToString(match));
        }
        exporter.getWriter().write("\n");
    }
    private String matchToString(Match match){
        StringBuilder str = new StringBuilder();
        str.append(match.getMatchId()).append(";");
        str.append(match.getTeamOneId()).append(";");
        str.append(match.getTeamTwoId()).append(";");
        str.append(match.getGoalsTeamOne()).append(";");
        str.append(match.getGoalsTeamTwo()).append(";");
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String strDate = dateFormat.format(match.getMatchDate());
        str.append(strDate).append(";");
        str.append(match.getHostId()).append("\n");
        return str.toString();
    }
    //teams
    private void writeTeams() throws IOException {
        for(Map.Entry<Integer, String> team: GUI.teamsNameMap.entrySet()){
            exporter.getWriter().write(team.getKey() +";");
            exporter.getWriter().write(team.getValue()+"\n");
        }
        exporter.getWriter().write("\n");
    }
    //general
    private void writeGeneralTable() throws IOException {
        for(GeneralTable gt : GUI.generalTableList){
            exporter.getWriter().write(generalTableToString(gt));
        }
        exporter.getWriter().write("\n");
    }
    private String generalTableToString(GeneralTable gt){
        StringBuilder str = new StringBuilder();
        str.append(gt.getId()).append(";");
        str.append(gt.getPoints()).append(";");
        str.append(gt.getGoalsFor()).append(";");
        str.append(gt.getGoalsAgainst()).append(";");
        str.append(gt.getMatchesPlayed()).append("\n");
        return str.toString();
    }
}

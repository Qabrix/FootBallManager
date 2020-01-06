package GUIpack;

import com.hibernate.maven.DBObjects.GeneralTable;
import com.hibernate.maven.DBObjects.Match;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.hibernate.maven.AppMain.hibSessionManager;

public class ImportButton extends JButton implements ActionListener {
    Boolean finishedImporting;
    public ImportButton(){
        setText("Import");
        addActionListener(this);
        finishedImporting = true;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(finishedImporting)
            readMatches();
    }
    private void readMatches(){
        finishedImporting = false;
        String csvFile = "db.csv";
        BufferedReader br = null;
        String line = "";
        ArrayList<String> teamData = new ArrayList<>();
        ArrayList<String> data=new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(csvFile));
            //czytanie meczy
            while ((line = br.readLine()) != null) {
                if(line.equals(""))
                    break;
                data.add(line);
            }
            //czytanie general table
            while ((line = br.readLine()) != null) {
                if(line.equals(""))
                    break;
                teamData.add(line);
            }
            //usuwanie meczy
            for(int j=GUI.matchList.size()-1; j>=0; j--){
                DeleteMatchButton.deleteMatch(GUI.matchList.get(j));
            }
            //usuwanie general table
            hibSessionManager.openSession();
            hibSessionManager.getSession().beginTransaction();
            for(int k=GUI.generalTableList.size()-1; k>=0; k--){
                hibSessionManager.getSession().delete(GUI.generalTableList.get(k));
            }
            hibSessionManager.getSession().getTransaction().commit();
            hibSessionManager.getSession().close();
            //dodawanie general table
            hibSessionManager.openSession();
            hibSessionManager.getSession().beginTransaction();
            for(String dataString : teamData){
                String[] gtRow = dataString.split(";");
                GeneralTable gt = new GeneralTable();
                gt.setId(Integer.parseInt(gtRow[0]));
                gt.setPoints(Integer.parseInt(gtRow[1]));
                gt.setGoalsFor(Integer.parseInt(gtRow[2]));
                gt.setGoalsAgainst(Integer.parseInt(gtRow[3]));
                gt.setMatchesPlayed(Integer.parseInt(gtRow[4]));
                hibSessionManager.getSession().save(gt);
            }
            hibSessionManager.getSession().getTransaction().commit();
            hibSessionManager.getSession().close();
            //dodawanie meczy
            for(String dataString : data){
                String[] matchRow = splitMatchString(dataString);
                Match match = new Match();
                match.setMatchId(Integer.parseInt(matchRow[0]));
                match.setTeamOneId(Integer.parseInt(matchRow[1]));
                match.setTeamTwoId(Integer.parseInt(matchRow[2]));
                match.setGoalsTeamOne(Integer.parseInt(matchRow[3]));
                match.setGoalsTeamTwo(Integer.parseInt(matchRow[4]));
                DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                try{
                    Date date = dateFormat.parse(matchRow[5]);
                    match.setMatchDate(date);
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
                match.setHostId(Integer.parseInt(matchRow[6]));
                AddMatchButton.addMatchToDB(match);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                GUI.refreshData();
                finishedImporting = true;
            }
        }
    }
    private String[] splitMatchString(String str){
        return str.split(";");
    }
}

package GUIpack;

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

public class ImportButton extends JButton implements ActionListener {
    public ImportButton(){
        setText("Import");
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        readMatches();
    }
    private void readMatches(){
        String csvFile = "db.csv";
        BufferedReader br = null;
        String line = "";
        ArrayList<String> data=new ArrayList<>();

        try {

            br = new BufferedReader(new FileReader(csvFile));

            while ((line = br.readLine()) != null) {
                if(line.equals(""))
                    break;
                data.add(line);
            }

            int i=0;
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
                for(int j=GUI.matchList.size()-1; j>=0; j--){
                    DeleteMatchButton.deleteMatch(GUI.matchList.get(j));
                }
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
            }
        }
    }
    private String[] splitMatchString(String str){
        return str.split(";");
    }
}

package GUIpack;

import com.hibernate.maven.DBObjects.Match;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import static com.hibernate.maven.AppMain.hibSessionManager;

public class AddMatchButton extends JButton implements ActionListener {
    private JTextField idTeam1TF, idTeam2TF, goalsTeam1TF, goalsTeam2TF, matchDateTF, idHostTF;
    private Object[] data;
    public AddMatchButton(){
        setText("Add Match");
        addActionListener(this);
    }

    private void setInputData(){
        idTeam1TF = new JTextField();
        idTeam2TF = new JTextField();
        goalsTeam1TF = new JTextField();
        goalsTeam2TF = new JTextField();
        matchDateTF = new JTextField();
        idHostTF = new JTextField();
        data = new Object[]{
                "Team 1 id:", idTeam1TF,
                "Team 2 id:", idTeam2TF,
                "Team 1 goals:", goalsTeam1TF,
                "Team 2 goals:", goalsTeam2TF,
                "Match date:", matchDateTF,
                "Host id:", idHostTF
        };
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        setInputData();
        int option = JOptionPane.showConfirmDialog(getParent(), data, "Add new match", JOptionPane.OK_CANCEL_OPTION);
        if(option==-JOptionPane.CLOSED_OPTION || option==JOptionPane.CANCEL_OPTION)
            return;
        else if(option==JOptionPane.OK_OPTION){
            Match match = new Match();
            match.setMatchId(getMatchId());
            match.setTeamOneId(getInt(idTeam1TF));
            match.setTeamTwoId(getInt(idTeam2TF));
            match.setGoalsTeamOne(getInt(goalsTeam1TF));
            match.setGoalsTeamTwo(getInt(goalsTeam2TF));
            match.setMatchDate(getDate(matchDateTF));
            match.setHostId(getInt(idHostTF));
            addMatchToDB(match);
            GUI.refreshData();
        }
    }
    private int getInt(JTextField tf){
        String temp = tf.getText();
        return Integer.parseInt(temp);
    }
    private Date getDate(JTextField tf){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try{
            date = dateFormat.parse(matchDateTF.getText());
        }
        catch (ParseException ex){
            System.out.println(ex);
        }
        return date;
    }
    private int getMatchId(){
        GUI.refreshData();
        int max = 1;
        for(Match match : GUI.matchList){
            if(match.getMatchId()>max)
                max=match.getMatchId();
        }
        return max+1;
    }
    public static void addMatchToDB(Match match){
        hibSessionManager.openSession();
        hibSessionManager.getSession().beginTransaction();
        try {
            hibSessionManager.getSession().save(match);
            hibSessionManager.getSession().getTransaction().commit();
        }
        catch(Exception ex){
            hibSessionManager.getSession().getTransaction().rollback();
        }
        finally {
            hibSessionManager.getSession().close();
        }
    }
}

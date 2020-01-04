package GUIpack;

import com.hibernate.maven.DBObjects.Player;
import com.hibernate.maven.DBObjects.Team;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static com.hibernate.maven.AppMain.hibSessionManager;

public class ShowSquadButton extends JButton implements ActionListener {
    public ShowSquadButton(){
        setText("Show Squad");
        addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        int selected = GUI.teamList.getSelectedIndex();
        if(selected==-1)
            JOptionPane.showMessageDialog(getParent(),  "You need to choose team first!");
        else{
            hibSessionManager.openSession();
            ///sklady w joptionpane
                List teams = hibSessionManager.getSession().getNamedQuery("get_all_teams").list();
                Team selectedTeam = (Team)teams.get(selected);
                String message = "Team: " + selectedTeam.getName();

            List players = hibSessionManager.getSession().getNamedQuery("get_players").setParameter("selectedTeam",selected+1).list();
            for(Object player : players){
                Player p = (Player) player;
                message += "\n" + p.getName() + " " + p.getSurname();
            }


            JOptionPane.showMessageDialog(getParent(), message);
                //dopisac zawodnikow
            ///
            hibSessionManager.getSession().close();
        }
    }
}

package GUIpack.CEO;

import GUIpack.GUI;
import com.hibernate.maven.DBObjects.Match;
import com.hibernate.maven.DBObjects.Player;
import com.hibernate.maven.DBObjects.Team;
import org.hibernate.query.Query;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import static com.hibernate.maven.AppMain.hibSessionManager;


public class AddSquadPanel extends JPanel{
    private static JButton addPlayerButton;

    private static List playerAL;
    private static List squadAL;
    private static java.util.List<Player> playerList;
    private static java.util.List<Team> teamList;

    AddSquadPanel(int panelWidth, int panelHeight){
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));

        initializeFields();
        getPlayers();
        getSquads();
        createGui();
    }

    private void initializeFields() {
        playerList = new ArrayList<>();
        teamList = new ArrayList<>();
        playerAL = new List();
        squadAL = new List();
        addPlayerButton = new JButton("Add to squad");
        addPlayerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addSquadToDB();
            }
        });
    }

    private void getPlayers() {
        hibSessionManager.openSession();
        java.util.List players = hibSessionManager.getSession().getNamedQuery("get_all_players").list();
        for(Object player : players){
            Player curPlayer = (Player) player;
            playerList.add(curPlayer);
            playerAL.add("id: " + curPlayer.getId() + " " + curPlayer.getName() + " " + curPlayer.getSurname());
        }
        hibSessionManager.getSession().close();
    }
    
    private void getSquads() {
        hibSessionManager.openSession();
        java.util.List teams = hibSessionManager.getSession().getNamedQuery("get_all_teams").list();
        for(Object team : teams){
            Team curTeam = (Team) team;
            teamList.add(curTeam);
            squadAL.add("id: " + curTeam.getId() + " name: " + curTeam.getName());
        }
        hibSessionManager.getSession().close();
    }

    private static void addSquadToDB(){
        Integer selectedPlayer = playerList.get(playerAL.getSelectedIndex()).getId();
        Integer selectedTeam = teamList.get(squadAL.getSelectedIndex()).getId();
        String myQuery = String.format("Insert into squads (player_id,team_id) values(%d,%d)",selectedPlayer,selectedTeam);
        if(selectedPlayer.equals(-1)){ JOptionPane.showMessageDialog(null, "Select Player!"); }
        else if(selectedTeam.equals(-1)){ JOptionPane.showMessageDialog(null, "Select Team!"); }
        else{
            try{
                hibSessionManager.openSession();
                hibSessionManager.getSession().beginTransaction();

                Query query = hibSessionManager.getSession().createNativeQuery(myQuery);
                query.executeUpdate();

                hibSessionManager.getSession().getTransaction().commit();
            }catch (Exception e){
                e.printStackTrace();
                hibSessionManager.getSession().getTransaction().rollback();
            }finally {
                hibSessionManager.getSession().close();
            }
        }
    }

    private void createGui() {
        this.setLayout(new GridBagLayout());
        final GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        Font deflautLabelFont = new Font("Sherif", Font.BOLD, 18);

        JLabel label = new JLabel("Adding player to squad");
        label.setFont(new Font("Sherif", Font.BOLD, 24));
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(20,10,0,0);
        c.gridwidth = 4;
        c.weightx = 0.5;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 0;
        this.add(label, c);

        label = new JLabel("Players list");
        label.setFont(deflautLabelFont);
        c.weightx = 0.5;
        c.insets = new Insets(20,20,0,0);
        c.weighty = 0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        this.add(label, c);

        label = new JLabel("Squads list:");
        label.setFont(deflautLabelFont);
        c.insets = new Insets(20,10,10,0);
        c.gridx = 1;
        c.gridy = 1;
        this.add(label, c);

        c.insets = new Insets(0,30,0,10);
        c.gridwidth = 1;
        c.gridheight = 2;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 2;
        this.add(playerAL, c);

        c.insets = new Insets(0,10,0,30);
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 2;
        this.add(squadAL, c);

        c.anchor = GridBagConstraints.PAGE_END;
        c.weighty = 0.9;
        c.insets = new Insets(50,10,20,30);
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 4;
        c.ipady = 20;
        this.add(addPlayerButton, c);

    }
}

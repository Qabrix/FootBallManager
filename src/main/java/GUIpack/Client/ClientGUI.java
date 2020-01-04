package GUIpack.Client;
import GUIpack.GUI;

import javax.swing.*;


public class ClientGUI extends GUI{
    public ClientGUI(){
        setFields();
        showGUI();
    }
    @Override
    protected void setFields(){
        add(new JScrollPane(matchTable));
        add(new JScrollPane(generalTable));
        addSortingMatchButton();
        addTeamList();
        addShowSquadButton();
        addSortingPointsButton();
        addRefreshButton();
    }
}

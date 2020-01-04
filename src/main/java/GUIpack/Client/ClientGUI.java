package GUIpack.Client;
import GUIpack.GUI;

import javax.swing.*;
import java.awt.*;


public class ClientGUI extends GUI{
    public ClientGUI(){
        setFields();
        showGUI();
    }
    @Override
    protected void setFields(){
        add(new JScrollPane(matchTable));
        add(new JScrollPane(generalTable));
        addTeams();
        addSortingMatchButton();
        addShowSquadButton();
        addSortingPointsButton();
        addRefreshButton();
    }
}

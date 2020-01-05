package GUIpack.CEO;
import GUIpack.AddMatchButton;
import GUIpack.DeleteMatchButton;
import GUIpack.GUI;

import javax.swing.*;

public class CeoGUI extends GUI{

    public CeoGUI(){
        setFields();
        showGUI();
    }

    @Override
    protected void setFields() {
        add(new JScrollPane(matchTable));
        add(new JScrollPane(generalTable));
        addTeams();
        addSortingMatchButton();
        addShowSquadButton();
        addSortingPointsButton();
        addRefreshButton();
        add(new AddMatchButton());
        add(new DeleteMatchButton(matchTable));
    }
}

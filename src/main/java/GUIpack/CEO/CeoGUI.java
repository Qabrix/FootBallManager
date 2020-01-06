package GUIpack.CEO;
import GUIpack.AddMatchButton;
import GUIpack.DeleteMatchButton;
import GUIpack.GUI;

import javax.swing.*;

public class CeoGUI extends GUI{

    public CeoGUI(){
        setFields();
        showGUI();
        addFields();
    }

    protected void addFields() {
        add(new AddMatchButton());
        add(new DeleteMatchButton(matchTable));
        add(new AddPlayerButton());
        add(new AddPlayerToSquadButton());
    }
}

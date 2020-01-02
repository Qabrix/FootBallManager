package GUIpack.Client;
import GUIpack.GUI;

import javax.swing.*;


public class ClientGUI extends GUI{
    public ClientGUI(){
        setFields();
        addFields();
        showGUI();
    }
    @Override
    protected void setFields(){

    }
    private void addFields(){
        add(new JScrollPane(matchTable));
        add(new JScrollPane(generalTable));
    }

}

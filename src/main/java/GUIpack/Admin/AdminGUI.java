package GUIpack.Admin;

import GUIpack.GUI;

import javax.swing.*;

public class AdminGUI extends GUI{
    public AdminGUI(){
        setFields();
        addFields();
        showGUI();
    }
    @Override
    protected void setFields(){

    }
    private void addFields(){
        add(new JScrollPane(matchesTable.getTable()));
        add(new JScrollPane(generalTable.getTable()));
    }

}
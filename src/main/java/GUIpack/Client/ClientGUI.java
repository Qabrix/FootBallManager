package GUIpack.Client;
import GUIpack.GUI;
import GUIpack.CustomTable;
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
        add(new JScrollPane(matchesTable.getTable()));
    }

}

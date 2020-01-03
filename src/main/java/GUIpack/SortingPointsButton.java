package GUIpack;

import com.hibernate.maven.DBObjects.GeneralTable;
import com.hibernate.maven.DBObjects.Match;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;

public class SortingPointsButton extends JButton implements ActionListener {
    private ArrayList<GeneralTable> generalList;
    private boolean direction;
    public SortingPointsButton(ArrayList<GeneralTable> general){
        setText("Sort by points");
        generalList = general;
        direction=true;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(direction)
            generalList.sort(Comparator.comparing(GeneralTable::getPoints));
        else
            generalList.sort(Comparator.comparing(GeneralTable::getPoints).reversed());
        direction=!direction;
        GUI.reloadGeneralTable();
    }
}

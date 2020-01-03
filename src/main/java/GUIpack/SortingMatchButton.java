package GUIpack;

import com.hibernate.maven.DBObjects.Match;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;

public class SortingMatchButton extends JButton implements ActionListener {
    private ArrayList<Match> matchList;
    private boolean direction;
    public SortingMatchButton(ArrayList<Match> matches){
        setText("Sort by date");
        matchList = matches;
        direction=true;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(direction)
            matchList.sort(Comparator.comparing(Match::getMatchDate));
        else
            matchList.sort(Comparator.comparing(Match::getMatchDate).reversed());
        direction=!direction;
        GUI.reloadMatches();
    }
}

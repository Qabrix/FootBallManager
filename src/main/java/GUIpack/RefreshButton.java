package GUIpack;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefreshButton extends JButton implements ActionListener {

    RefreshButton(){
        setText("Refresh");
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        GUI.refreshData();
    }
}

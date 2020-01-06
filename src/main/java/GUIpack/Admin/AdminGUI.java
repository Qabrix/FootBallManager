package GUIpack.Admin;
import GUIpack.CEO.CeoGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminGUI extends CeoGUI {
    private AdminOptionsFrame adminOptionsFrame;
    public AdminGUI(){
        addMoreFields();
        showGUI();
    }

    private void addMoreFields() {
        adminOptionsFrame = new AdminOptionsFrame(400,600,"Options");

        JButton adminOptions = new JButton("Open Admin Panel");
        adminOptions.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                adminOptionsFrame.setVisible(true);
            }
        });
        add(adminOptions);
    }
}

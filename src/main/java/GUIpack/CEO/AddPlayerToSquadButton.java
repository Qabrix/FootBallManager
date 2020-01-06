package GUIpack.CEO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddPlayerToSquadButton extends JButton implements ActionListener {
    private static AddSquadFrame addSquadFrame;
    AddPlayerToSquadButton(){
        setText("Add Player To Squad");
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        addSquadFrame = new AddSquadFrame(840, 300, "Add Player To Squad");
        addSquadFrame.setVisible(true);
    }
}
